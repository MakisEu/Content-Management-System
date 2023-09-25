package api;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Vector;

/**
 * The Main Control Unit class for the CMS
 */
public class ControlSystem {
    //String=ContentID, comment.user()
    protected static HashMap<String, HashMap<String,ArrayList<Comment>>> comments;
    //First String=ContentID, Second String=UserId
    //protected static HashMap<String, HashMap<String,Integer>>liked;
    //First String=UserId, Second String=ContentId
    protected static HashMap<String,HashMap<String,Content>> content;
    protected static HashMap<String,Boolean> banned;
    //username, (password,type)
    private static HashMap<String, Vector<String>> all_users;


    /**
     * Base constructor of ControlSystem
     */
    public ControlSystem(){
        comments=new HashMap<>();
        //liked=new HashMap<>();
        content=new HashMap<>();
        banned=new HashMap<>();
        all_users=new HashMap<>();

    }

    public HashMap<String,Boolean> getBanned(){
        return banned;
    }

    /**
     * Adds a content into the Control System
     * @param content The content that will be added
     * @return If the operation happened
     */
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
        //liked.put(content.getID(),new HashMap<>());
        return true;
    }

    /**
     * Adds a comment for a content
     * @param comment The comment that will be added
     * @param contentID The content id of the content it wll be added to
     * @return Control string to inform about what happened
     */
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

    /**
     * Deletes a comment from a content
     * @param commentID the id of the comment that will be deleted
     * @param contentID the id of the content that has the comment
     * @return if the deletion was successful
     */
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

    /**
     * Deletes a content
     * @param content the content that will be deleted
     */
    public void DeleteContent(Content content){
        String ContentID= content.getID();
        comments.remove(ContentID);
        //liked.remove(ContentID);//
        this.content.get(content.getUser()).remove(content.getID());
    }

    public HashMap<String,Vector<String>> getAll_users(){
        return all_users;
    }
}
