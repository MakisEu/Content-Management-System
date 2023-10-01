package api;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;



/**
 * The class for a normal user
 */
public class User {
    String userID;
    boolean isAdmin=false;
    ControlSystem system;
    Connection con;
    Helper h;

    /**
     * Base constructor of User
     * @param id the unique ID/username of the user
     * @param system the control system
     */
    public User(String id,ControlSystem system){
        con=system.connection;
        h=new Helper(con);
        userID=id;
        this.system=system;
    }

    /**
     * Creates and adds a content for the user
     * @param type      the type of the content
     * @param Title     the title of the content
     * @param body      the body of the content
     * @param extras    the extra fields of the content
     * @return          Control string tha describes what happened
     */
    public String AddContent(String type, String Title,Body body,HashMap<String,String> extras){
        Content b=null;
        try {
            Statement st=con.createStatement();
            ResultSet rs=st.executeQuery("SELECT * FROM content WHERE title= \'"+h.Encode(Title)+"\' AND owner= \'"+userID+"\';");
            if (rs.next()){
                st.close();
                return "You already have content with the same title!";


            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        switch (type){
            case ("Post"):{
                //Create Post
                BodyPost bp=((BodyPost)body);
                if (bp.getText().length()<Post.charLimitPost) {
                    Post p = new Post(h.Encode(Title), h.Encode(bp.getText()), userID);
                    b=p;
                }
                else {
                    return ("Max Limit of the Post's Text Exceeded by"+String.valueOf(bp.getText().length()-Post.charLimitPost)+"Characters.");
                }
                break;
            }
            case ("Article"):{
                //Create Article
                BodyArticle ba=((BodyArticle) body);
                String author=null;
                if (extras!=null && extras.get("Author")!=null){
                    author=h.Encode(extras.get("Author"));
                }
                if (ba.getText().length()<Article.charLimitPost) {
                    Article p = new Article(author,h.Encode(Title), h.Encode(ba.getText()), userID);
                    b=p;
                }
                else {
                    return ("Max Limit of the Post's Text Exceeded by"+String.valueOf(ba.getText().length()-Post.charLimitPost)+"Characters.");
                }
                break;


            }
        }

        /*
        if (system.content.get(userID)!=null) {
            for (String key : system.content.get(userID).keySet()) {
                if (system.content.get(userID).get(key) != null) {
                    if (system.content.get(userID).get(key).getTitle().equals(Title)) {
                        return "You already have content with the same title!";
                    }
                }
            }
        }
        switch (type){
            case ("Post"):{
                //Create Post
                BodyPost bp=((BodyPost)body);
                if (bp.getText().length()<Post.charLimitPost) {
                    Post p = new Post(Title, bp.getText(), userID);
                    b=p;
                }
                else {
                    return ("Max Limit of the Post's Text Exceeded by"+String.valueOf(bp.getText().length()-Post.charLimitPost)+"Characters.");
                }
                break;
            }
            case ("Article"):{
                //Create Article
                BodyArticle ba=((BodyArticle) body);
                String author=null;
                if (extras!=null && extras.get("Author")!=null){
                    author=extras.get("Author");
                }
                if (ba.getText().length()<Article.charLimitPost) {
                    Article p = new Article(author,Title, ba.getText(), userID);
                    b=p;
                }
                else {
                    return ("Max Limit of the Post's Text Exceeded by"+String.valueOf(ba.getText().length()-Post.charLimitPost)+"Characters.");
                }
                break;


            }
        }*/
        if (!(b==null)){
            if (system.AddContent(b,type)){
                return "Added successfully.";
            }
            return "This content already exists.";
        }
        else{
            return "Incorrect type. Addition failed.";
        }
    }

    /**
     * Adds a comment to a content
     * @param text      The text of the comment
     * @param contentID The content ID of the content it will be added to
     * @return          The control string of ControlSystem.AddComment() or that the operation was completed successfully
     */
    public String AddComment(String text,String contentID){
        Comment comment=new Comment(h.Encode(text),userID);
        String s=system.AddComment(comment,contentID);
        if (s.contains("#")){
            return "Added successfully.";
        }
        else{
            return s;
        }
    }

    /**
     * Edits a comment of the user
     * @param commentID The id of the comment that wll be edited
     * @param text      The text that will replace the original
     * @return          Control string of what happened
     */
    public String EditComment(String commentID,String text) {
        Comment comment=getComment(commentID);
        if (comment==null){
            return "This comment does not exist";
        }
        if (comment.getUser().equals(userID) || isAdmin) {
            try{
                Statement st=con.createStatement();
                String s="UPDATE comment SET text = \'"+h.Encode(text)+"\' WHERE unique_id=\'"+commentID+"\';";
                st.execute(s);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            return ("Comment has been edited.");
        }
        return ("You do not have sufficient access right to edit this comment.");
    }

    /**
     * Deletes a comment of the user
     * @param commentID the id of the comment that will be deleted
     * @param contentID the id of the content the comment belongs to
     * @return          Control string of what happened
     */
    public String DeleteComment(String commentID,String contentID){
        Comment comment=getComment(commentID);
        if (comment==null){
            return "This comment does not exist";
        }
        if (comment.getUser().equals(userID) || isAdmin) {
            system.DeleteComment(commentID);

            return ("Comment has been deleted.");
        }
        return ("You do not have sufficient access right to delete this comment.");
    }

    /**
     * Deletes a content of the user
     * @param contentID the id of the content that will be deleted
     * @return          Control string of what happened
     */
    public String DeleteContent(String contentID){
        Content content=getContent(contentID);
        if (content!=null && content.getUser().equals(userID) || isAdmin) {
            system.DeleteContent(content);
            return ("Content has been deleted.");
        }
        return ("You do not have sufficient access right to delete this content or it doesn't exist.");
    }

    /**
     * Edit's a content of the user
     * @param contentId The id of the content that will be edited
     * @param body      The new body of the content.   If it is null, it will not get changed
     * @param extras    The new extra fields of the content. If an extra field is not in extras, it will not be changed
     * @return          Control string of what happened
     */
    public String EditContent(String contentId,Body body,HashMap<String,String> extras){
        Content content=getContent(contentId);
        if (content.getUser().equals(userID)||isAdmin){
            String type= content.getID().split("#")[0];
            if (extras.isEmpty()){return ("Content has been edited.");}
            switch (type){
                case ("Post"):{
                    if (body!=null) {
                        try{
                            Statement st= con.createStatement();
                            st.execute("UPDATE post SET body=\'"+h.Encode(((BodyPost)body).getText())+"\' WHERE content_id=\'"+contentId+"\';");
                        } catch (SQLException e) {
                            throw new RuntimeException(e);
                        }
                    }

                    return ("Content has been edited.");
                }
                case ("Article"):{
                    String auth=((Article)content).getAuthor();
                    for (String s: extras.keySet()) {
                        if (s.equals("Author")){
                           auth=h.Encode(extras.get("Author"));
                        }
                    }
                    if (body!=null) {
                        try{
                            Statement st= con.createStatement();
                            st.execute("UPDATE article SET body=\'"+h.Encode(((BodyArticle)body).getText())+"\', author=\'"+auth+"\'  WHERE content_id=\'"+contentId+"\';");
                        } catch (SQLException e) {
                            throw new RuntimeException(e);
                        }
                    }
                    return ("Content has been edited.");
                }
            }
        }
        return ("You do not have sufficient access right to edit this content.");
    }

    /**
     * Likes a comment
     * @param contentId The id of the content that will be liked/disliked
     */
    public boolean LikeContent(String contentId,boolean isLiked){
        Content content=getContent(contentId);
        if (content==null){
            return false;
        }
        try {
            Statement st= con.createStatement();
            ResultSet rs= st.executeQuery("SELECT liked FROM liked_content WHERE user_id=\'"+userID+"\' AND content_id = \'"+contentId+"\'; ");
            if (rs.next()){
                int liked=rs.getInt(1);
                if ((liked==1 && isLiked) || (liked==-1 && !isLiked)){
                    return false;
                } else {
                    int l=-1;
                    if (isLiked && liked == -1) {
                        l=1;
                    }
                    st.execute("UPDATE content SET votes=" + (content.votes +l) + " WHERE content_id=\'" + contentId + "\';");
                    st.execute("DELETE FROM liked_content WHERE user_id=\'" + userID + "\' AND content_id=\'" + contentId + "\' ;");
                    return true;
                }
            }
            else{
                int liked=-1;
                if (isLiked){
                    liked=1;
                }
                st.execute("INSERT INTO liked_content (user_id,content_id,liked) VALUES (\'"+userID+"\',\'"+contentId+"\',\'"+liked+"\'); ");
                st.execute("UPDATE content SET votes="+(content.votes+liked)+" WHERE content_id=\'"+contentId+"\';");
                return true;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    /**
     * returns a comment
     * @param commentID the id of the comment that will be returned
     * @return          the comment
     */
    public Comment getComment(String commentID){
        return h.getComment(commentID);
    }

    /**
     * Returns a content
     * @param contentID The id of the content that will be returned
     * @return          The content
     */
    public Content getContent(String contentID){
        return h.getContent(contentID);
    }
}
