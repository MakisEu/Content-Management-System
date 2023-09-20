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
    protected static HashMap<String,Boolean> banned;

    public ControlSystem(){
        comments=new HashMap<>();
        liked=new HashMap<>();
        content=new HashMap<>();
        banned=new HashMap<>();
    }

    public HashMap<String,Boolean> getBanned(){
        return banned;
    }
    public boolean AddContent(Content content){
        String uid= content.getUser();
        if (this.content.get(uid)==null){
            this.content.put(uid,new HashMap<>());
        }
        if (!(this.content.get(uid).get(content.getTitle())==null)){
            return false;
        }
        this.content.get(uid).put(content.getTitle(),content);
        comments.put(content.getID(),new ArrayList<>());
        liked.put(content.getID(),new HashMap<>());
        return true;
    }
    public boolean AddComment(Comment comment,String contentID){
        if (this.comments.get(contentID)==null){
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
        if (this.liked.get(content.getID())==null){
            return false;
        }
        if (this.liked.get(content.getID()).get(user)==null && update!=0){
            this.liked.get(content.getID()).put(user,update);
            return true;
        }
        int current=this.liked.get(content.getID()).get(user);
        if ((current==-1 && update==1) || (current==1 && update==-1)){
            this.liked.get(content.getID()).remove(user);
        }
        return true;
    }
    public boolean DeleteComment(Comment comment,String contentID){
        ArrayList<Comment> c=comments.get(contentID);
        if (c!=null){
            return c.remove(comment);
        }
        return false;
    }
    public void DeleteContent(Content content){
        String ContentID= content.getID();
        comments.remove(ContentID);
        liked.remove(ContentID);//TODO: Remove from liked(User) as well
        this.content.get(content.getUser()).remove(content.getTitle());
    }
}
