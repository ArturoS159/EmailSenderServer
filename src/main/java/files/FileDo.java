package files;


import java.io.*;
import java.util.List;

public class FileDo {
    private static String FILENAME = "email_backup.csv";

    public static void addUserToFile(User user) {
        File file = null;
        BufferedWriter bw = null;
        file = new File(FILENAME);
        try {
            SendEmail.send(user.getRecipient(),user.getTitle(),user.getContent());
            bw = new BufferedWriter(new FileWriter(file, true));
            bw.write(getId() + ";" + user.getRecipient() + ";" + user.getTitle() + ";" + user.getContent()+";");
            bw.newLine();
            bw.flush();
        } catch (IOException err) {
            err.printStackTrace();
        } finally {
            try {
                if (bw != null) {
                    bw.close();
                }
            } catch (Exception err) {
                err.printStackTrace();
            }
        }
    }

    private static int getId(){
        FileReader fr = null;
        BufferedReader br = null;
        String idd="";
        try{
            br = new BufferedReader(new FileReader(FILENAME));
            String Line;
            while ((Line = br.readLine()) != null) {
                idd = Line.split(";")[0];
            }
        }catch(IOException err){
            err.printStackTrace();
        }finally {
            try{
                if(br != null){br.close();}
            }catch(IOException err){
                err.printStackTrace();
            }
        }
        if(idd.equals("ID")) return 1;
        return Integer.parseInt(idd)+1;
    }

    public static void loadUsersFromFile(List userlist) {
        BufferedReader br = null;
        try {
            String line ="";
            int tmp=0;
            br = new BufferedReader(new FileReader("email_backup.csv"));
            while ((line = br.readLine()) != null) {
                String[] lineSplit = line.split(";");
                 if(tmp>0){
                     userlist.add(new User(Integer.parseInt(lineSplit[0]),lineSplit[1],lineSplit[2],lineSplit[3]));
                 }

                tmp++;
            }
        }catch(IOException err){
            err.printStackTrace();
        }finally {
            try{
                if(br != null){br.close();}
            }catch(IOException err){
                err.printStackTrace();
            }
        }

    }
}
