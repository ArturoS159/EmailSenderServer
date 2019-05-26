package rest;

import files.User;
import files.FileDo;
import files.UserUniqueSend;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;


@RestController
public class Controller {

    private static final Logger LOGGER = LoggerFactory.getLogger(Controller.class);

    private static <T> Predicate<T> distinctByKey(Function<? super T, ?> keyExtractor) {
        Set<Object> seen = ConcurrentHashMap.newKeySet();
        return t -> seen.add(keyExtractor.apply(t));
    }

    @ResponseBody
    @CrossOrigin
    @RequestMapping(value = "/email/contacts", method = RequestMethod.GET ,produces ="application/json")
    public ResponseEntity<List<UserUniqueSend>> sendContacts(){
        List<User> usersList = new ArrayList<>();
        List<UserUniqueSend> userListFiltered = new ArrayList<>();

        try{
            FileDo.loadUsersFromFile(usersList);
            usersList = usersList.stream()
                    .filter(distinctByKey(p -> p.getRecipient()))
                    .collect(Collectors.toList());

            for(User obj : usersList){
                userListFiltered.add(new UserUniqueSend(obj.getRecipient()));
            }

            if(userListFiltered.isEmpty()) throw new Exception();
        }catch(Exception err){
            err.printStackTrace();
            return new ResponseEntity(userListFiltered, HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity(userListFiltered, HttpStatus.OK);
    }

    @CrossOrigin
    @RequestMapping(value = "/email/send", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<?> addUser(@RequestBody User user) {
        //LOGGER.info("{} {} {}", user.getRecipient(), user.getTitle(), user.getContent());  //Show request from client

        try{
            FileDo.addUserToFile(user);
        }catch(Exception err){
            err.printStackTrace();
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity(HttpStatus.OK);
    }

}
