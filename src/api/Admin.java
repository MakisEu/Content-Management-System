package api;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.BufferedReader;


public class Admin extends User{

    public Admin(String id,ControlSystem system){
        super(id,system);
        isAdmin=true;
    }

    public String Ban(String userID) {
        if (system.getBanned().containsKey(userID)){
            return "This user is already banned!";
        }
        system.getBanned().put(userID,true);
        return "You banned this user successfully!";
    }

    public String UnBan(String userID){
        if (system.getBanned().containsKey(userID)){
            system.getBanned().remove(userID);
            return "This user got unbanned successfully!";
        }
        return "You cannot unban an unbanned user!";
    }

}

