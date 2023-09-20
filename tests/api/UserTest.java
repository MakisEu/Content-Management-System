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
    }

    @Test
    void editComment() {
    }

    @Test
    void deleteComment() {
    }

    @Test
    void deleteContent() {
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