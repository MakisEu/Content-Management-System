package api;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

class UserTest {

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
    void addContent() {
        ControlSystem controlSystem=new ControlSystem();
        User user1=new User("Makis",controlSystem);


        assertEquals(user1.AddContent("Article","My title",new BodyArticle("Ignore this text"),null),"Added successfully.");
        assertNotNull(ControlSystem.content.get(user1.userID).get("Article#Makis#0"));
        assertEquals(user1.AddContent("Article","My title",new BodyArticle("Ignore this text"),null),"Added successfully.");

    }

    @Test
    void addComment() {
        ControlSystem controlSystem=new ControlSystem();
        User user1=new User("Makis",controlSystem);
        user1.AddContent("Article","My title",new BodyArticle("Ignore this text"),null);
        user1.AddComment("My comment",ControlSystem.content.get("Makis").get("Article#Makis#0").getID());

        assertNotNull(ControlSystem.comments.get(user1.myComments.get(0).get(0)).get(user1.myComments.get(0).get(1).split("#")[0]));
    }

    @Test
    void editComment() {
        ControlSystem controlSystem=new ControlSystem();
        User user1=new User("Makis",controlSystem);
        user1.AddContent("Article","My title",new BodyArticle("Ignore this text"),null);
        user1.AddComment("My comment",ControlSystem.content.get("Makis").get("Article#Makis#0").getID());

        user1.EditComment(user1.myComments.get(0).get(1),"Don't ignore this though");


        assertEquals(user1.getComment(user1.myComments.get(0).get(1)).getText(),"Don't ignore this though");
    }

    @Test
    void deleteComment() {
        ControlSystem controlSystem=new ControlSystem();
        User user1=new User("Makis",controlSystem);
        user1.AddContent("Article","My title",new BodyArticle("Ignore this text"),null);
        user1.AddComment("My comment",ControlSystem.content.get("Makis").get("Article#Makis#0").getID());

        assertNotNull(ControlSystem.comments.get(ControlSystem.content.get("Makis").get("Article#Makis#0").getID()).get(user1.myComments.get(0).get(1).split("#")[0]));
        assertEquals(user1.DeleteComment(user1.myComments.get(0).get(1),ControlSystem.content.get("Makis").get("Article#Makis#0").getID()),"Comment has been deleted.");

    }

    @Test
    void deleteContent() {
        ControlSystem controlSystem=new ControlSystem();
        User user1=new User("Makis",controlSystem);
        user1.AddContent("Article","My title",new BodyArticle("Ignore this text"),null);
        user1.AddComment("My comment",ControlSystem.content.get("Makis").get("Article#Makis#0").getID());

        assertEquals(user1.DeleteContent("Article#Makis#0"),"Content has been deleted.");
        assertEquals(user1.DeleteContent("Article#Makis#0"),"You do not have sufficient access right to delete this content or it doesn't exist.");

    }

    @Test
    void editContent() {
        ControlSystem controlSystem=new ControlSystem();
        User user1=new User("Makis",controlSystem);
        user1.AddContent("Article","My title",new BodyArticle("Ignore this text"),null);
        user1.AddComment("My comment",ControlSystem.content.get("Makis").get("Article#Makis#0").getID());
        HashMap<String,String> extras=new HashMap<>();
        extras.put("Author","Somebody else");
        assertEquals(user1.EditContent("Article#Makis#0","New title",new BodyArticle("Because it will be replaced"),extras),"Content has been edited.");
        Article a=(Article) user1.getContent("Article#Makis#0");
        assertEquals(a.getUser(),"Makis");
        assertEquals(a.getTitle(),"New title");
        assertEquals(a.getAuthor(),"Somebody else");
        assertEquals(((BodyArticle) a.getBody()).getText(),"Because it will be replaced");

    }


    @Test
    void likeContent() {
        ControlSystem controlSystem=new ControlSystem();
        User user1=new User("Makis",controlSystem);
        user1.AddContent("Article","My title",new BodyArticle("Ignore this text"),null);
        Content content=user1.getContent("Article#Makis#0");
        assertEquals(user1.LikeContent(content.getID(),true),true);
        assertEquals(content.getVotes(),1);
        assertEquals(user1.LikeContent(content.getID(),false),true);
        assertEquals(content.getVotes(),0);
        assertEquals(user1.LikeContent(content.getID(),false),true);
        assertEquals(content.getVotes(),-1);
        assertEquals(user1.LikeContent(content.getID(),true),true);
        assertNull(user1.liked.get(user1.getContent(content.getID())));

    }

    @Test
    void getComment() {
        ControlSystem controlSystem=new ControlSystem();
        User user1=new User("Makis",controlSystem);
        user1.AddContent("Article","My title",new BodyArticle("Ignore this text"),null);
        user1.AddComment("My comment",ControlSystem.content.get("Makis").get("Article#Makis#0").getID());

        assertNotNull(user1.getComment("Makis#0"));
    }

    @Test
    void getContent() {
        ControlSystem controlSystem=new ControlSystem();
        User user1=new User("Makis",controlSystem);
        user1.AddContent("Article","My title",new BodyArticle("Ignore this text"),null);
        user1.AddComment("My comment",ControlSystem.content.get("Makis").get("Article#Makis#0").getID());

        assertNotNull(user1.getContent("Article#Makis#0"));
    }
}