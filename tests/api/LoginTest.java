package api;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.Vector;

import static org.junit.jupiter.api.Assertions.*;

class LoginTest {


    @AfterEach
    void tearDown() {
        new ControlSystem().Clear();
        //ControlSystem.liked=null;
    }
    @Test
    void log_in() {
        ControlSystem controlSystem=new ControlSystem();
        Login login=new Login(controlSystem);
        assertEquals(login.Log_in("Jim","12345"),"Wrong username or password!");
        Vector<String> v=new Vector<>();
        v.add("12345");
        v.add("User");
        login.Sign_up("Jim","12345","User");
        assertEquals(login.Log_in("Jim","12345").split("#")[0],"Logged in successfully");

    }

    @Test
    void sign_up() {
        ControlSystem controlSystem=new ControlSystem();
        Login login=new Login(controlSystem);
        assertEquals(login.Sign_up("Jim","12345","User"),"You registered successfully");
        Vector<String> v=new Vector<>();
        v.add("12345");
        v.add("User");
        login.Sign_up("Jim","12345","User");
        assertEquals(login.Sign_up("Jim","12345","Admin"),"This username is already taken");


    }
}