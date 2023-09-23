package api;

import org.junit.jupiter.api.Test;

import java.util.Vector;

import static org.junit.jupiter.api.Assertions.*;

class LoginTest {

    @Test
    void log_in() {
        ControlSystem controlSystem=new ControlSystem();
        Login login=new Login(controlSystem);
        assertEquals(login.Log_in("Jim","12345"),"Wrong username or password!");
        Vector<String> v=new Vector<>();
        v.add("12345");
        v.add("User");
        controlSystem.getAll_users().put("Jim",v);
        assertEquals(login.Log_in("Jim","12345"),"Logged in successfully");

    }

    @Test
    void sign_up() {
        ControlSystem controlSystem=new ControlSystem();
        Login login=new Login(controlSystem);
        assertEquals(login.Sign_up("Jim","12345","User"),"You registered successfully");
        Vector<String> v=new Vector<>();
        v.add("12345");
        v.add("User");
        controlSystem.getAll_users().put("Jim",v);
        assertEquals(login.Sign_up("Jim","12345","Admin"),"This username is already taken");


    }
}