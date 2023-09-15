import java.util.ArrayList;

public class Comment {
    private static int char_limit=250;
    private int likes;
    private String text;
    private ArrayList<Comment> replied_comments;

    public Comment(String TEXT){
        likes=0;
        replied_comments=new ArrayList<>();
        TEXT=Check_char_count(TEXT);
        this.text = TEXT;
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

    public void Reply(String TEXT){
        TEXT=Check_char_count(TEXT);
        Comment comment=new Comment(TEXT);
        replied_comments.add(comment);
    }

    public void Edit(String TEXT){
        TEXT=Check_char_count(TEXT);
        this.text=TEXT;
    }

    public int getLikes(){
        return likes;
    }

    public int getReplies(){
        return replied_comments.size();
    }

    public String getText() {
        return text;
    }
    public String Check_char_count(String TEXT){
        if (TEXT.length()>char_limit){
            return TEXT.substring(0,char_limit-1);
        }
        return TEXT;
    }




}


