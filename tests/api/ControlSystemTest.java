package api;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.sql.Statement;

import static org.junit.jupiter.api.Assertions.*;

class ControlSystemTest {

    @BeforeEach
    void setUp() {
        Content.nextID=0;
        Comment.nextID=0;
       ControlSystem cs=new ControlSystem();
       try {
           Statement st=cs.connection.createStatement();
           st.execute("INSERT INTO users (user_id,password,type) VALUES (\'me\',123,\'User\');");
           st.execute("INSERT INTO users (user_id,password,type) VALUES (\'you\',123,\'User\');");
           st.execute("INSERT INTO users (user_id,password,type) VALUES (\'guess?\',123,\'User\');");
           st.execute("INSERT INTO users (user_id,password,type) VALUES (\'Noone\',123,\'User\');");

       } catch (SQLException e) {
           throw new RuntimeException(e);
       }

    }

    @AfterEach
    void tearDown() {
        new ControlSystem().Clear();
        //ControlSystem.liked=null;
    }

    @Test
    void addContent() {
        ControlSystem controlSystem=new ControlSystem();
        Post post1=new Post("This is a post that has no title","The post contains nothing","Noone");
        Article article1=new Article("Totally legit author","No title","A text","me");
        Post post2=new Post("title","LaText","you");
        Article article2=new Article("The user above","why is writing tests so boring?","title is self-explanatory","guess?");

        assertEquals(controlSystem.AddContent(post1,"Post"),true);
        assertEquals(controlSystem.AddContent(post1,"Post"),false);

        assertEquals(controlSystem.AddContent(article1,"Article"),true);
        assertEquals(controlSystem.AddContent(article1,"Article"),false);


        assertEquals(controlSystem.AddContent(post2,"post"),true);
        assertEquals(controlSystem.AddContent(post2,"post"),false);
    }

    @Test
    void addComment() {
        ControlSystem controlSystem=new ControlSystem();
        Post post1=new Post("This is a post that has no title","The post contains nothing","Noone");
        Article article1=new Article("Totally legit author","No title","A text","me");
        Post post2=new Post("title","LaText","you");
        Article article2=new Article("The user above","why is writing tests so boring?","title is self-explanatory","guess?");
        Comment comment1=new Comment("Nice comment!","me");
        Comment comment2=new Comment("Bad comment!","me");
        controlSystem.AddContent(post1,"Post");
        Helper h=new Helper(controlSystem.connection);

        assertEquals(controlSystem.AddComment(comment1,post1.getID()).contains("#"),true);
        assertNotNull(h.getComment(comment1.getId()));
        assertEquals(controlSystem.AddComment(comment1,post1.getID()),"This comment already exist.");
        assertNotNull(h.getComment(comment1.getId()));
        //assertNotNull(h.getComment() ControlSystem.comments.get(post1.getID()).get(comment1.getUser()).get(0));
        assertNotNull(controlSystem.AddComment(comment2,post1.getID()));
        assertNotNull(h.getComment(comment2.getId()));// ControlSystem.comments.get(post1.getID()).get(comment2.getUser()).get(1));
    }
    /*@Test
    void updateLiked() {
        ControlSystem controlSystem=new ControlSystem();
        Post post1=new Post("This is a post that has no title","The post contains nothing","Noone");
        Article article1=new Article("Totally legit author","No title","A text","me");
        Post post2=new Post("title","LaText","you");
        Article article2=new Article("The user above","why is writing tests so boring?","title is self-explanatory","guess?");

        assertEquals(controlSystem.updateLiked("Makis",post1,1),false);
        controlSystem.AddContent(post1);
        assertEquals(controlSystem.updateLiked("Makis",post1,1),true);
        assertEquals(controlSystem.updateLiked("John",post1,1),true);
        assertNotNull(ControlSystem.liked.get(post1.getID()).get("Makis"));
        assertEquals(controlSystem.updateLiked("Makis",post1,-1),true);
        assertNull(ControlSystem.liked.get(post1.getID()).get("Makis"));
    }*/

    @Test
    void deleteComment() {
        ControlSystem controlSystem=new ControlSystem();
        Post post1=new Post("This is a post that has no title","The post contains nothing","Noone");
        Article article1=new Article("Totally legit author","No title","A text","me");
        Post post2=new Post("title","LaText","you");
        Article article2=new Article("The user above","why is writing tests so boring?","title is self-explanatory","guess?");
        Comment comment1=new Comment("Nice comment!","me");
        Comment comment2=new Comment("Bad comment!","me");
        controlSystem.AddContent(post1,"Post");
        controlSystem.AddContent(article1,"Article");

        controlSystem.AddComment(comment1,post1.getID());
        String c1=post1.getID(),c2=article1.getID();
        assertEquals(controlSystem.DeleteComment(comment1.getId()),true);
        assertEquals(controlSystem.DeleteComment(comment1.getId()),false);
        controlSystem.AddComment(comment1,c1);
        controlSystem.AddComment(comment1,c2);
        assertEquals(controlSystem.DeleteComment(comment1.getId()),true);
        assertEquals(controlSystem.DeleteComment(comment1.getId()),false);

    }

    @Test
    void deleteContent() {
        ControlSystem controlSystem=new ControlSystem();
        Post post1=new Post("This is a post that has no title","The post contains nothing","Noone");
        Article article1=new Article("Totally legit author","No title","A text","me");
        Post post2=new Post("title","LaText","you");
        Article article2=new Article("The user above","why is writing tests so boring?","title is self-explanatory","guess?");
        controlSystem.AddContent(post1,"Post");
        controlSystem.AddContent(post2,"Post");
        controlSystem.AddContent(article1,"Article");
        controlSystem.AddContent(article2,"Article");
        Helper hp=new Helper(controlSystem.connection);
        assertNotNull(hp.getContent(post1.getID()));

        //assertNotNull(ControlSystem.content.get(post1.getUser()).get(post1.getID()));
        controlSystem.DeleteContent(post1);
        assertNull(hp.getContent(post1.getID()));
        //assertNull(ControlSystem.content.get(post1.getUser()).get(post1.getID()));
        //assertNull(ControlSystem.comments.get(post1.getID()));
    }


}