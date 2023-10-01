package api;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CommentTest {

    @BeforeEach
    void setUp() {
        Content.nextID=0;
        Comment.nextID=0;
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void reply() {
        Comment comment=new Comment("Great!","Jim");
        comment.Reply("I agree","George");
        assertEquals(comment.getReplied_comments().get(0).getUser(),"George");
        assertEquals(comment.getReplied_comments().get(0).getText(),"I agree");

    }

    @Test
    void edit() {
        Comment comment=new Comment("Great!","Jim");
        comment.Edit("Great! It works perfect for me");
        assertEquals(comment.getText(),"Great! It works perfect for me");

    }

    @Test
    void getLikes() {
        Comment comment=new Comment("Great!","Jim");
        for (int i=0;i<3;i++){
            comment.AddLike();
        }
        assertEquals(comment.getLikes(),3);
    }

    @Test
    void getReplies() {
        Comment comment=new Comment("Great!","Jim");
        comment.Reply("I agree","George");
        comment.Reply("I don't think so","Nick");
        assertEquals(comment.getReplies(),2);
    }

    @Test
    void getText() {
        Comment comment=new Comment("Great!","Jim");
        assertEquals(comment.getText(),"Great!");
    }

    @Test
    void check_char_count() {
        Comment comment=new Comment("Great!","Jim");
        assertEquals(comment.Check_char_count("Great!"),"Great!");
    }

    @Test
    void getUser() {
        Comment comment=new Comment("Great!","Jim");
        assertEquals(comment.getUser(),"Jim");
    }

    @Test
    void getId() {
        Comment comment=new Comment("Great!","Jim");
        assertEquals(comment.getId(),"Jim#0");
    }
}