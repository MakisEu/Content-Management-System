package api;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * The Main Control Unit class for the CMS
 */
public class ControlSystem {
    protected static HashMap<String, ArrayList<Comment>>comments;
    protected static HashMap<String, HashMap<String,Integer>>liked; //TODO: Create a list n User that contains all (un)liked objects
    protected static ArrayList<Content> content;
    public ControlSystem(){
        comments=new HashMap<>();
        liked=new HashMap<>();
        content=new ArrayList<>();
    }
    public boolean AddContent(Content content){
        if (this.content.contains(content)){
            return false;
        }
        this.content.add(content);
        return true;
    }
    public boolean AddComment(Comment comment,String contentID){
        if (this.comments.get(contentID).equals(null)){
            this.comments.put(contentID,new ArrayList<>());
        }
        if (this.comments.get(contentID).contains(comment)){
            return false;
        }
        this.comments.get(contentID).add(comment);
        return true;
    }
    public boolean updateLiked(String user,Content content,int update){
        if (update==0){
            return false;
        }
        if (this.liked.get(content.getID()).equals(null)){
            return false;
        }
        if (this.liked.get(content.getID()).get(user).equals(null) && update!=0){
            this.liked.get(content.getID()).put(user,update);
            return true;
        }
        int current=this.liked.get(content.getID()).get(user);
        if ((current==-1 && update==1) || (current==1 && update==-1)){
            this.liked.get(content.getID()).remove(user);
        }
        return true;
    }
}
