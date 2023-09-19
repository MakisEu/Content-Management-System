package api;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ControlSystemTest {

    @BeforeEach
    void setUp() {
        Content.nextID=0;

    }

    @AfterEach
    void tearDown() {
        ControlSystem.comments=null;
        ControlSystem.content=null;
        ControlSystem.liked=null;
    }
    @Test
    void addContent() {
        ControlSystem controlSystem=new ControlSystem();
        Post post1=new Post("This is a post that has no title","The post contains nothing","Noone");
        Article article1=new Article("Totally legit author","No title","A text","me");
        Post post2=new Post("title","LaText","you");
        Article article2=new Article("The user above","why is writing tests so boring?","title is self-explanatory","guess?");

        assertEquals(controlSystem.AddContent(post1),true);
        assertEquals(controlSystem.AddContent(post1),false);

        assertEquals(controlSystem.AddContent(article1),true);
        assertEquals(controlSystem.AddContent(article1),false);
        article1.user="you";

        assertEquals(controlSystem.AddContent(article1),true);
        assertEquals(controlSystem.AddContent(article1),false);



    }

    @Test
    void addComment() {
    }

    @Test
    void updateLiked() {
    }

    @Test
    void deleteComment() {
    }

    @Test
    void deleteContent() {
        ControlSystem controlSystem=new ControlSystem();
        Post post1=new Post("This is a post that has no title","The post contains nothing","Noone");
        Article article1=new Article("Totally legit author","No title","A text","me");
        Post post2=new Post("title","LaText","you");
        Article article2=new Article("The user above","why is writing tests so boring?","title is self-explanatory","guess?");


        controlSystem.AddContent(post1);
        controlSystem.AddContent(post2);
        controlSystem.AddContent(article1);
        controlSystem.AddContent(article2);
        assertNotNull(ControlSystem.content.get(post1.getUser()).get(post1.getTitle()));
        controlSystem.DeleteContent(post1);
        assertNull(ControlSystem.content.get(post1.getUser()).get(post1.getTitle()));
    }

}