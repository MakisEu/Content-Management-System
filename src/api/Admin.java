package api;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.BufferedReader;
public class Admin extends User{
    boolean isAdmin=true;

    public Admin(String id,ControlSystem system){
        super(id,system);
    }

    public String Ban(User user,ControlSystem controlSystem) {
        if (controlSystem.getBanned().containsKey(user.userID)){
            return "This user is already banned!";
        }
        controlSystem.getBanned().put(user.userID,true);
        return "You banned this user successfully!";
    }

    public String UnBan(User user,ControlSystem controlSystem){
        if (controlSystem.getBanned().containsKey(user.userID)){
            controlSystem.getBanned().remove(user.userID);
            return "This user got unbanned successfully!";
        }
        return "You cannot unban an unbanned user!";
    }










}
