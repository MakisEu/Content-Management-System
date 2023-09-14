import java.util.ArrayList;

public class Comment {
   // private static int char_limit=200;
    // I thought it's better to check if the text characters are <=char_limit ,before we pass it as a parameter ,so we won't have to check it inside the constructor.
    private int likes;
    private String text;
    private ArrayList<Comment> replied_comments;

    public Comment(String TEXT){
        likes=0;
        this.text = TEXT;
        replied_comments=new ArrayList<>();
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
        Comment comment=new Comment(TEXT);
        replied_comments.add(comment);
    }



}


