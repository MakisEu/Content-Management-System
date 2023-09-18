package api;

/**
 * Class for a normal post
 */
public class Post extends Content{
    public static int charLimitPost =500;
    /**
     * @param Title The title of the post
     * @param text  The text of the post
     * @param user  The user that crated this post
     */
    public Post(String Title, String text,String user) {
        this(Title, text,user, charLimitPost);
    }
    public Post(String Title, String text,String user,int limit) {
        super(Title, new BodyPost(text),user, limit);
        this.ID=this.ID.replace("object","Post");
    }
    /**
     * @return The text of the post
     */
    public String getText() {
        return ((BodyPost)super.getBody()).getText();
    }
    /**
     * @param text The new text of the post
     * Changes the text of the post
     */
    public void setText(String text) {
        ((BodyPost)super.getBody()).setText(text);
    }
}
