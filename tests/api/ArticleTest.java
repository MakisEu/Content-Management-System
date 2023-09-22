package api;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ArticleTest {

    @BeforeEach
    void setUp() {
        Content.nextID=0;
        Comment.nextID=0;
    }

    @AfterEach
    void tearDown() {
        ControlSystem.comments=null;
        ControlSystem.content=null;
        //ControlSystem.liked=null;
    }

    @Test
    void reply() {
        Article article=new Article("JN","The article","This is my article","Jim");
        article.Reply("Great!","Jim");
        assertEquals(article.getReplied_comments().get(0).getUser(),"Jim");
        assertEquals(article.getReplied_comments().get(0).getText(),"Great!");
    }

    @Test
    void getReplies() {
        Article article=new Article("JN","The article","This is my article","Jim");
        article.Reply("Great!","Jim");
        article.Reply("I don't think so","Nick");
        article.Reply("I agree","John");
        assertEquals(article.getReplies(),3);
    }

    @Test
    void setAuthor() {
        Article article=new Article("JN","The article","This is my article","Jim");
        article.setAuthor("Jim Author");
        assertEquals(article.getAuthor(),"Jim Author");
    }

    @Test
    void getAuthor() {
        Article article=new Article("JN","The article","This is my article","Jim");
        assertEquals(article.getAuthor(),"JN");
    }
}