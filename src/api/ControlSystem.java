package api;

import java.io.*;
import java.sql.*;
import java.util.HashMap;
import java.util.Vector;

/**
 * The Main Control Unit class for the CMS
 */
public class ControlSystem {
    protected static HashMap<String,Boolean> banned;
    //username, (password,type)
    Connection connection;


    /**
     * Base constructor of ControlSystem
     */
    public ControlSystem(){
        try {
            String dbURL1 = "jdbc:postgresql:CMS?user=makis";
            connection = DriverManager.getConnection(dbURL1);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Adds a content into the Control System
     * @param content The content that will be added
     * @return If the operation happened
     */
    public boolean AddContent(Content content,String type){
        String uid= content.getUser();
        try {
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM contentdb WHERE content_id = \'"+content.getID()+"\' AND content_owner = \'"+uid+"\';");
            if (rs.next()){
                st.close();
                return false;
            }
            else{
                boolean executed=st.execute("INSERT INTO contentdb (content_id,content_owner,type)\n" +
                        "VALUES (\'"+content.getID()+"\', \'"+content.getUser()+"\', \'"+type+"\');");
                executed=st.execute("INSERT INTO content (title,owner,charlimit,votes,content_id)\n"+
                        "VALUES(\'"+content.getTitle()+"\', \'"+content.getUser()+"\', \'"+ content.getCharLimit() +"\', \'"+content.getVotes()+"\', \'"+content.getID()+"\')");
                if (type.toLowerCase().equals("post")){
                    executed=st.execute("INSERT INTO post (body, content_id) VALUES (\'"+((BodyPost)content.getBody()).getText()+"\',\'"+content.getID()+"\');");
                } else if (type.toLowerCase().equals("article")) {
                    executed=st.execute("INSERT INTO article (body, content_id,author) VALUES (\'"+((BodyPost)content.getBody()).getText()+"\',\'"+content.getID()+"\', \'"+((Article)content).getAuthor()+"\');");
                }
                st.close();
                return true;

            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        /*if (this.content.get(uid)==null){
            this.content.put(uid,new HashMap<>());
        }
        if (!(this.content.get(uid).get(content.getID())==null)){
            return false;
        }
        this.content.get(uid).put(content.getID(),content);
        comments.put(content.getID(),new HashMap<>());
        //liked.put(content.getID(),new HashMap<>());
        return true;*/
    }

    /**
     * Adds a comment for a content
     * @param comment The comment that will be added
     * @param contentID The content id of the content it wll be added to
     * @return Control string to inform about what happened
     */
    public String AddComment(Comment comment,String contentID) {
        //AddCommentContent
        try {
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM contentdb WHERE content_id=\'"+contentID+"\'");
            if (!rs.next()){
                st.close();
                return "This content does not exist";
            }

            else {
                rs=st.executeQuery("SELECT * FROM comment WHERE unique_id = \'" + comment.getId() + "\';");
                if (rs.next()) {
                    st.close();
                    return "This comment already exist.";
                }
                else {
                    st.execute("INSERT INTO comment (text,owner,unique_id,charlimit,votes,parent_content,parent_comment) VALUES (\'" + comment.getText() + "\',\'" + comment.getUser() + "\',\'" + comment.getId() + "\',\'" + Comment.char_limit + "\',\'" + comment.getLikes() + "\',\'" + contentID + "\',NULL)");
                    st.close();
                    return comment.getId();
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    /**
     * Deletes a comment from a content
     * @param commentID the id of the comment that will be deleted
     * @return if the deletion was successful
     */
    public boolean DeleteComment(String commentID){

        try {
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM comment WHERE unique_id=\'" + commentID + "\';");
            if (rs.next()){
                Boolean b=st.execute("DELETE FROM comment WHERE unique_id=\'"+commentID+"\';");
                return true;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return false;
    }

    /**
     * Deletes a content
     * @param content the content that will be deleted
     */
    public void DeleteContent(Content content){
        try {
            Statement st= connection.createStatement();
            new Helper(connection).getContent(content.getID());
            Boolean rs=st.execute("DELETE FROM contentdb WHERE content_owner = \'"+content.getUser()+"\' AND content_id = \'"+content.getID()+"\';");
            st.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void Clear(){
        try {
            Statement st= connection.createStatement();
            st.execute("TRUNCATE users CASCADE;");
            st.close();
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}
