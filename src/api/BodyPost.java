package api;

/**
 * Sub-Class of Body that contains the body of a post
 */
public class BodyPost extends Body{
    String text;
    public BodyPost(){
        text=null;
    }

    /**
     * @param text The text of the Post's body
     */
    public BodyPost(String text){
        this.text=text;
    }

    /**
     * @param text The new text of the Post's body
     * Changes the text of the post
     */
    public void setText(String text) {
        this.text = text;
    }

    /**
     * @return The text of the post
     */
    public String getText() {
        return text;
    }
}
