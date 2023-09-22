package api;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * The Main Control Unit class for the CMS
 */
public class ControlSystem {
    //String=ContentID, commentID
    protected static HashMap<String, HashMap<String,ArrayList<Comment>>> comments;
    //First String=ContentID, Second String=UserId
    protected static HashMap<String, HashMap<String,Integer>>liked;
    //First String=UserId, Second String=ContentId
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
        if (!(this.content.get(uid).get(content.getID())==null)){
            return false;
        }
        this.content.get(uid).put(content.getID(),content);
        comments.put(content.getID(),new HashMap<>());
        liked.put(content.getID(),new HashMap<>());
        return true;
    }
    public String AddComment(Comment comment,String contentID){
        if (content.get(contentID.split("#")[1]) == null){
            return "this content does not exist anymore.";
        }
        if (this.comments.get(contentID)==null){
            this.comments.put(contentID,new HashMap<>());
            this.comments.get(contentID).put(comment.getUser(),new ArrayList<>());
        }
        else if (this.comments.get(contentID).get(comment.getUser())==null){
            this.comments.get(contentID).put(comment.getUser(),new ArrayList<>());

        }
        if (this.comments.get(contentID).get(comment.getUser()).contains(comment)){
            return "This comment already exist.";
        }
        this.comments.get(contentID).get(comment.getUser()).add(comment);
        return comment.getId();
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
    public boolean DeleteComment(String commentID,String contentID){

        if (comments.get(contentID)!=null) {
            ArrayList<Comment> c = comments.get(contentID).get(commentID.split("#")[0]);
            if (c != null) {
                for (Comment comment : c) {
                    if (comment.getId() == commentID) {
                        c.remove(comment);
                        return true;
                    }
                }
            }
        }
        return false;
    }
    public void DeleteContent(Content content){
        String ContentID= content.getID();
        comments.remove(ContentID);
        liked.remove(ContentID);//TODO: Remove from liked(User) as well
        this.content.get(content.getUser()).remove(content.getID());
    }
}
