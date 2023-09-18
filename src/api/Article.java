package api;

import api.Comment;

import java.util.ArrayList;

/**
 * Class for an article
 */
public class Article extends Post{
    private static int char_limit=1000;
    private String author;
    private ArrayList<Comment> replied_comments;

    /**
     *
     * @param Author    The author of the article
     * @param Title     The title of the article
     * @param Text      The text of the article
     * @param user      The user published the article
     */

    public Article(String Author,String Title,String Text,String user){
        super(Title,Text,user);
        this.ID=this.ID.replace("object","Article");
        this.author=Author;
    }

    /**
     * @param Text the text of the replied comment
     */
    public void Reply(String Text,String uid) {
        Comment comment = new Comment(Text, uid);
        replied_comments.add(comment);
    }

    /**
     *
     * @return all the replied comments of a specific article
     */
    public int getReplies(){
        return replied_comments.size();
    }








}
