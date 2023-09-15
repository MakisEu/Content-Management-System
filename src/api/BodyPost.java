package api;

public class BodyPost extends Body{
    String text;
    public BodyPost(){
        text=null;
    }
    public BodyPost(String text){
        this.text=text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }
}
