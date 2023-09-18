package api;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * The Main Control Unit class for the CMS
 */
public class ControlSystem {
    //String=ContentID
    protected static HashMap<String, ArrayList<Comment>>comments;
    //First String=ContentID, Second String=UserId
    protected static HashMap<String, HashMap<String,Integer>>liked;
    //First String=UserId, Second String=Content.Title
    protected static HashMap<String,HashMap<String,Content>> content;
    public ControlSystem(){
        comments=new HashMap<>();
        liked=new HashMap<>();
        content=new HashMap<>();
    }
    public boolean AddContent(Content content,String uid){
        if ((!this.content.get(uid).equals(null)) || (!this.content.get(uid).get(content.getTitle()).equals(null)) ){
            return false;
        }
        this.content.get(uid).put(content.getTitle(),content);
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
    public boolean DeleteComment(Comment comment){
        for (ArrayList<Comment> c: comments.values()) {
            if (c.remove(comment)){
                return true;
            }
        }
        return false;
    }
    public void DeleteContent(Content content){
        String ContentID= content.getID();
        comments.remove(ContentID);
        liked.remove(ContentID);//TODO: Remove from liked(User) as well
        this.content.remove(content.getUser()).remove(ContentID);
    }
}
