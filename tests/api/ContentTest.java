package api;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ContentTest {

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
    void getCharLimit() {

    }

    @Test
    void getTitle() {
    }

    @Test
    void getBody() {
    }

    @Test
    void setTitle() {
    }

    @Test
    void setBody() {
    }

    @Test
    void like() {
    }

    @Test
    void dislike() {
    }

    @Test
    void getID() {
    }

    @Test
    void getUser() {
    }
}