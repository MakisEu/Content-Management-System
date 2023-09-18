package api;

import java.util.ArrayList;

public class User {
    String userID;
    ArrayList<String> liked;
    ControlSystem system;
    public User(String id,ControlSystem system){
        userID=id;
        this.system=system;
        liked=new ArrayList<>();
    }
    public String AddContent(String type, String Title,Body body){
        Content b=null;
        switch (type){
            case ("Post"):{
                //Create Post
                BodyPost bp=((BodyPost)body);
                if (bp.getText().length()<Post.charLimitPost) {
                    Post p = new Post(Title, bp.getText(), userID);
                    b=p;
                }
                else {
                    return ("Max Limit of the Post's Text Exceeded by"+String.valueOf(bp.getText().length()-Post.charLimitPost)+"Characters");
                }
            }
            case ("Article"):{
                //Create Article
                //TODO
            }
        }
        if (!b.equals(null)){
            if (system.AddContent(b,userID)){
                return "Added successfully";
            }
            return "You already have content with the same title";
        }
        else{
            return "Incorrect type. Addition failed.";
        }
    }
    public void AddComment(String text){
        Comment comment=new Comment(text,userID);

    }



}
