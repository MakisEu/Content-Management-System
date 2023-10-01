package api;

import api.Comment;

import java.util.ArrayList;

/**
 * Class for an article
 */
public class Article extends Post{
    private static int char_limit_Article=1000;
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
        super(Title,Text,user,char_limit_Article);
        Body=new BodyArticle(Text);
        this.ID=this.ID.replace("Post","Article");
        this.author=Author;
        replied_comments=new ArrayList<>();
    }

    /**
     * @param Text the text of the replied comment
     */
    public void Reply(String Text,String uid) {
        Comment comment = new Comment(Text, uid);
        replied_comments.add(comment);
    }
    public 	Article(String Title, String text,String user, int charLimit, String id, int votes,String author){
        super(Title, text,user, charLimit, id, votes);
        this.Body=new BodyArticle(text);
        this.author=author;
    }

    /**
     *
     * @return all the replied comments of a specific article
     */
    public int getReplies(){
        return replied_comments.size();
    }

    /**
     * @param author The author of the article. Not necessarily the owner
     */
    public void setAuthor(String author) {
        this.author = author;
    }

    /**
     * @return The author of the article.
     */
    public String getAuthor() {
        return author;
    }

    public ArrayList<Comment> getReplied_comments(){
        return replied_comments;
    }
}
