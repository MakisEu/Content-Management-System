package api;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

class UserTest {

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
            st.execute("INSERT INTO users (user_id,password,type) VALUES (\'Makis\',123,\'User\');");
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
        User user1=new User("Makis",controlSystem);

        assertEquals(user1.AddContent("Article","My title",new BodyArticle("Ignore this text"),null),"Added successfully.");
        assertNotNull( new Helper(controlSystem.connection).getContent("Article#Makis#0"));//  ControlSystem.content.get(user1.userID).get("Article#Makis#0"));
        assertEquals(user1.AddContent("Article","My title",new BodyArticle("Ignore this text"),null),"You already have content with the same title!");
        assertEquals(user1.AddContent("Article","My title1",new BodyArticle("Ignore this text"),null),"Added successfully.");

    }

    @Test
    void addComment() {
        ControlSystem controlSystem=new ControlSystem();
        User user1=new User("Makis",controlSystem);
        user1.AddContent("Article","My title",new BodyArticle("Ignore this text"),null);
        String id=user1.AddComment("My comment", user1.getContent("Article#Makis#0").getID());

        assertNotNull(user1.getComment("Makis#0"));
    }

    @Test
    void editComment() {
        ControlSystem controlSystem=new ControlSystem();
        User user1=new User("Makis",controlSystem);
        user1.AddContent("Article","My title",new BodyArticle("Ignore this text"),null);
        user1.AddComment("My comment",user1.getContent("Article#Makis#0").getID());

        user1.EditComment("Makis#0","Don't ignore this though");

        assertEquals(user1.getComment("Makis#0").getText(),"Don't ignore this though");
    }

    @Test
    void deleteComment() {
        ControlSystem controlSystem=new ControlSystem();
        User user1=new User("Makis",controlSystem);
        user1.AddContent("Article","My title",new BodyArticle("Ignore this text"),null);
        user1.AddComment("My comment",user1.getContent("Article#Makis#0").getID());

        assertNotNull(user1.getComment("Makis#0"));
        assertEquals(user1.DeleteComment("Makis#0","Article#Makis#0"),"Comment has been deleted.");

    }

    @Test
    void deleteContent() {
        ControlSystem controlSystem=new ControlSystem();
        User user1=new User("Makis",controlSystem);
        user1.AddContent("Article","My title",new BodyArticle("Ignore this text"),null);
        //user1.AddComment("My comment",ControlSystem.content.get("Makis").get("Article#Makis#0").getID());

        assertEquals(user1.DeleteContent("Article#Makis#0"),"Content has been deleted.");
        assertEquals(user1.DeleteContent("Article#Makis#0"),"You do not have sufficient access right to delete this content or it doesn't exist.");
    }

    @Test
    void editContent() {
        ControlSystem controlSystem=new ControlSystem();
        User user1=new User("Makis",controlSystem);
        user1.AddContent("Article","My title",new BodyArticle("Ignore this text"),null);
        //user1.AddComment("My comment",ControlSystem.content.get("Makis").get("Article#Makis#0").getID());
        HashMap<String,String> extras=new HashMap<>();
        extras.put("Author","Somebody else");
        assertEquals(user1.EditContent("Article#Makis#0",new BodyArticle("Because it will be replaced"),extras),"Content has been edited.");
        Article a=(Article) user1.getContent("Article#Makis#0");
        assertEquals(a.getUser(),"Makis");
        //assertEquals(a.getTitle(),"New title");
        assertEquals(a.getAuthor(),"Somebody else");
        assertEquals(((BodyArticle)new Helper(controlSystem.connection).getContent(a.getID()).getBody()).getText(),"Because it will be replaced");

    }


    @Test
    void likeContent() {
        ControlSystem controlSystem=new ControlSystem();
        User user1=new User("Makis",controlSystem);
        user1.AddContent("Article","My title",new BodyArticle("Ignore this text"),null);
        Content content=user1.getContent("Article#Makis#0");
        Helper h=new Helper(controlSystem.connection);
        assertEquals(user1.LikeContent(content.getID(),true),true);
        assertEquals(h.getContent(content.getID()).getVotes(),1);
        assertEquals(user1.LikeContent(content.getID(),false),true);
        assertEquals(h.getContent(content.getID()).getVotes(),0);
        assertEquals(user1.LikeContent(content.getID(),false),true);
        assertEquals(h.getContent(content.getID()).getVotes(),-1);
        assertEquals(user1.LikeContent(content.getID(),true),true);
        //assertNull(user1.liked.get(user1.getContent(content.getID())));

    }

    @Test
    void getComment() {
        ControlSystem controlSystem=new ControlSystem();
        User user1=new User("Makis",controlSystem);
        user1.AddContent("Article","My title",new BodyArticle("Ignore this text"),null);
        user1.AddComment("My comment", user1.getContent("Article#Makis#0").getID());

        assertNotNull(user1.getComment("Makis#0"));
    }

    @Test
    void getContent() {
        ControlSystem controlSystem=new ControlSystem();
        User user1=new User("Makis",controlSystem);
        user1.AddContent("Article","My title",new BodyArticle("Ignore this text"),null);
        //user1.AddComment("My comment",ControlSystem.content.get("Makis").get("Article#Makis#0").getID());

        assertNotNull(user1.getContent("Article#Makis#0"));
    }
}