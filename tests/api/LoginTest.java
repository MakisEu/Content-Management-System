package api;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LoginTest {

    @Test
    void log_in() {
        ControlSystem controlSystem=new ControlSystem();
        Login login=new Login(controlSystem);
        assertEquals(login.Log_in("Jim","12345"),"Wrong username or password!");
        controlSystem.getAll_users().put("Jim","12345");
        assertEquals(login.Log_in("Jim","12345"),"Logged in successfully");

    }

    @Test
    void sign_up() {
        ControlSystem controlSystem=new ControlSystem();
        Login login=new Login(controlSystem);
        assertEquals(login.Sign_up("Jim","12345"),"You registered successfully");
        controlSystem.getAll_users().put("Jim","12345");
        assertEquals(login.Sign_up("Jim","12345"),"This username is already taken");


    }
}