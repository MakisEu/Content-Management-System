package api;

import api.Comment;

import java.util.ArrayList;
public class Article {
    private static int char_limit=1000;
    private int likes;
    private String text,title,author;
    private ArrayList<Comment> replied_comments;

    public Article(String AUTHOR,String TITLE,String TEXT){
        this.author=AUTHOR;
        this.title=TITLE;
        TEXT=Check_char_count(TEXT);
        this.text=TEXT;
        likes=0;
    }

    public String Check_char_count(String TEXT){
        if (TEXT.length()>char_limit){
            return TEXT.substring(0,char_limit-1);
        }
        return TEXT;
    }

    public void Reply(String TEXT,String uid){
        TEXT=Check_char_count(TEXT);
        Comment comment=new Comment((TEXT),uid);
        replied_comments.add(comment);
    }

    public int getLikes(){
        return likes;
    }

    public int getReplies(){
        return replied_comments.size();
    }








}
