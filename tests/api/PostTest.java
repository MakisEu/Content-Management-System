package api;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PostTest {

    @BeforeEach
    void setUp() {
        Content.nextID=0;
        Comment.nextID=0;
    }

    @AfterEach
    void tearDown() {

    }
    @Test
    void getText() {
        Post post=new Post("My title","This is my first post","Jim");
        assertEquals(post.getText(),"This is my first post");
    }

    @Test
    void setText() {
        Post post=new Post("My title","This is my first post","Jim");
        post.setText("My new edited text");
        assertEquals(post.getText(),"My new edited text");
    }
}