package api;
import java.util.ArrayList;

/**
 * Base class for a Comment
 */

public class Comment {
    protected static int nextID=0;
    private static int char_limit=250;
    private int likes;
    private String text,user,id;
    private ArrayList<Comment> replied_comments;

    /**
     *
     * @param Text the text of the comment
     * @param user the name of the creator
     */

    public Comment(String Text,String user){
        likes=0;
        replied_comments=new ArrayList<>();
        Text=Check_char_count(Text);
        this.text = Text;
        this.user=user;
        this.id=user+"#"+nextID;
        nextID++;
    }


    public void AddLike(){
        likes++;
    }

    public void RemoveLike(User user){      //Supposed a class User exists
        likes--;
    }


    /**
     *
     * @param Text the text of the replied comment
     */
    public void Reply(String Text,String uid){
        Text=Check_char_count(Text);
        Comment comment=new Comment(Text,uid);
        replied_comments.add(comment);
    }

    /**
     *
     * @param Text the new text of the edited comment
     */

    public void Edit(String Text){
        Text=Check_char_count(Text);
        this.text=Text;
    }

    /**
     *
     * @return return all the likes of a comment
     */
    public int getLikes(){
        return likes;
    }

    /**
     *
     * @return returns all the replies of a comment
     */

    public int getReplies(){
        return replied_comments.size();
    }

    public ArrayList<Comment> getReplied_comments(){
        return replied_comments;
    }

    /**
     *
     * @return returns the text of a comment
     */
    public String getText() {
        return text;
    }

    /**
     *
     * @param   Text the text of a comment
     * @return  the text, if text<=charLimit else returns the text but only with charLimit characters
     */
    public String Check_char_count(String Text){
        if (Text.length()>char_limit){
            return Text.substring(0,char_limit-1);
        }
        return Text;
    }

    /**
     * @return the creator of the comment
     */
    public String getUser() {
        return user;
    }

    /**
     * @return The unique ID of the Comment
     */
    public String getId() {
        return id;
    }
}
