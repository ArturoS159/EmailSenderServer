package files;

public class UserUniqueSend {
    private String recipient;

    public UserUniqueSend(String recipient) {
        this.recipient = recipient;
    }

    public UserUniqueSend() {
    }

    public String getRecipient() {
        return recipient;
    }

    public void setRecipient(String recipient) {
        this.recipient = recipient;
    }
}
