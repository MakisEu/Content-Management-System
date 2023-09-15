package api;

public class Post extends Content{
    public Post(String Title, String text) {
        super(Title, new BodyPost(text), 500);
    }

    public String getText() {
        return ((BodyPost)super.getBody()).getText();
    }

    public void setText(String text) {
        ((BodyPost)super.getBody()).setText(text);
    }
}
