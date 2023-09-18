package api;
import java.util.ArrayList;

/**
 * Base class for a Comment
 */

public class Comment {
    private static int char_limit=250;
    private int likes;
    private String text;
    private ArrayList<Comment> replied_comments;

    /**
     *
     * @param Text the text of the comment
     */

    public Comment(String Text){
        likes=0;
        replied_comments=new ArrayList<>();
        Text=Check_char_count(Text);
        this.text = Text;
    }

    /*

    public void AddLike(){
        if (user.likedThisComment(this)==False){   //Supposed there is a method checking if a user has liked a specific comment
            likes++;
           }
        else{
            System.out.println("You already liked!");
        }
    }

    public void RemoveLike(User user){      //Supposed a class User exists
        if (user.likedThisComment(this)==True){   //Supposed there is a method checking if a user has liked a specific comment
            likes--;
        }
        else{
            System.out.println("You can not unlike!");
        }
    }

    */

    /**
     *
     * @param Text the text of the replied comment
     */
    public void Reply(String Text){
        Text=Check_char_count(Text);
        Comment comment=new Comment(Text);
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




}


