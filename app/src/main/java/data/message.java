package data;


public class message {
    private String body ,sender  ;

    public message(String body, String sender) {
        this.body = body;
        this.sender=sender;
    }

    public String getBody() {
        return body;
    }

    public String getSender() {
        return sender;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }
}
