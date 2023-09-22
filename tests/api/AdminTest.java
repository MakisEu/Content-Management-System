package api;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AdminTest {

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
    void ban() {
        ControlSystem system=new ControlSystem();
        User user1=new User("Jim",system);
        assertEquals(system.getBanned().containsKey(user1.userID),false);
        system.getBanned().put(user1.userID,true);
        assertEquals(system.getBanned().containsKey(user1.userID),true);


    }

    @Test
    void unBan() {
        ControlSystem system=new ControlSystem();
        User user1=new User("Jim",system);
        system.getBanned().put(user1.userID,true);
        assertEquals(system.getBanned().containsKey(user1.userID),true);

        system.getBanned().remove(user1.userID);
        assertEquals(system.getBanned().containsKey(user1.userID),false);

    }
}