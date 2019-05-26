package files;

public class User {
    private int id;
    private String recipient;
    private String title;
    private String content;
    public User(){}

    public User(int id, String recipient, String title, String content) {
        this.recipient = recipient;
        this.title = title;
        this.content = content;
    }

    public int getId() {
        return id;
    }

    public String getRecipient() {
        return recipient;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public void setId(int id) { this.id = id; }

    public void setRecipient(String recipient) {
        this.recipient = recipient;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", recipient='" + recipient + '\'' +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                '}';
    }
}
