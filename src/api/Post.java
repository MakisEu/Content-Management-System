package api;

public class Post extends Content{
    public Post(String Title, String text,String user) {
        super(Title, new BodyPost(text),user, 500);
        this.ID=this.ID.replace("object","Post");
    }

    public String getText() {
        return ((BodyPost)super.getBody()).getText();
    }

    public void setText(String text) {
        ((BodyPost)super.getBody()).setText(text);
    }
}
