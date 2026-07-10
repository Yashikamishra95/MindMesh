public class Request {

    private User sender;
    private User receiver;
    private String skill;
    private String status;
    private boolean completed;

    public Request(User sender, User receiver, String skill) {

        this.sender = sender;
        this.receiver = receiver;
        this.skill = skill;
        this.status = "Pending";
        this.completed = false;

    }

    public User getSender() {
        return sender;
    }

    public User getReceiver() {
        return receiver;
    }

    public String getSkill() {
        return skill;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    public boolean isCompleted() {
    return completed;
}
public void setCompleted(boolean completed) {
    this.completed = completed;
}

}