package api;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserTest {

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
        User user1=new User("Makis",controlSystem);


        assertEquals(user1.AddContent("Article","My title",new BodyArticle("Ignore this text"),null),"Added successfully.");
        assertNotNull(ControlSystem.content.get(user1.userID).get("My title"));
        assertEquals(user1.AddContent("Article","My title",new BodyArticle("Ignore this text"),null),"You already have content with the same title.");

    }

    @Test
    void addComment() {
        ControlSystem controlSystem=new ControlSystem();
        User user1=new User("Makis",controlSystem);
        user1.AddContent("Article","My title",new BodyArticle("Ignore this text"),null);
        user1.AddComment("My comment",ControlSystem.content.get("Makis").get("My title").getID());

        assertNotNull(ControlSystem.comments.get(user1.myComments.get(0).get(0)).get(user1.myComments.get(0).get(1).split("#")[0]));
    }

    @Test
    void editComment() {
        ControlSystem controlSystem=new ControlSystem();
        User user1=new User("Makis",controlSystem);
        user1.AddContent("Article","My title",new BodyArticle("Ignore this text"),null);
        user1.AddComment("My comment",ControlSystem.content.get("Makis").get("My title").getID());

        user1.EditComment(user1.myComments.get(0).get(1),"Don't ignore this though");


        assertEquals(user1.getComment(user1.myComments.get(0).get(1)).getText(),"Don't ignore this though");
    }

    @Test
    void deleteComment() {
        ControlSystem controlSystem=new ControlSystem();
        User user1=new User("Makis",controlSystem);
        user1.AddContent("Article","My title",new BodyArticle("Ignore this text"),null);
        user1.AddComment("My comment",ControlSystem.content.get("Makis").get("My title").getID());

        assertNotNull(ControlSystem.comments.get(ControlSystem.content.get("Makis").get("My title").getID()).get(user1.myComments.get(0).get(1).split("#")[0]));
        assertEquals(user1.DeleteComment(user1.myComments.get(0).get(1),ControlSystem.content.get("Makis").get("My title").getID()),"Comment has been deleted.");


    }

    @Test
    void deleteContent() {
        ControlSystem controlSystem=new ControlSystem();
        User user1=new User("Makis",controlSystem);
        user1.AddContent("Article","My title",new BodyArticle("Ignore this text"),null);
        user1.AddComment("My comment",ControlSystem.content.get("Makis").get("My title").getID());
    }

    @Test
    void editContent() {
    }

    @Test
    void like() {
    }

    @Test
    void dislike() {
    }
}