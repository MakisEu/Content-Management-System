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
        new ControlSystem().Clear();
    }

    @AfterEach
    void tearDown() {
        new ControlSystem().Clear();
        //ControlSystem.liked=null;
    }

    @Test
    void ban() {
        ControlSystem system=new ControlSystem();
        User user1=new User("Jim",system);
        Login login=new Login(system);
        login.Sign_up("Jim","12345","User");
        Admin admn=new Admin("JimAdmin",system);
        assertEquals(admn.Ban(user1.userID),"You banned this user successfully!");
        assertEquals(admn.Ban(user1.userID),"This user is already banned!");

    }

    @Test
    void unBan() {
        ControlSystem system=new ControlSystem();
        User user1=new User("Jim",system);
        Login login=new Login(system);
        login.Sign_up("Jim","12345","User");
        Admin admn=new Admin("JimAdmin",system);
        assertEquals(admn.Ban(user1.userID),"You banned this user successfully!");
        assertEquals(admn.UnBan(user1.userID),"This user got unbanned successfully!");
        assertEquals(admn.UnBan(user1.userID),"You cannot unban an unbanned user!");
    }
}