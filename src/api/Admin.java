package api;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.BufferedReader;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class Admin extends User{

    public Admin(String id,ControlSystem system){
        super(id,system);
        isAdmin=true;
    }

    public String Ban(String userID) {
        try{
            Statement st=con.createStatement();
            ResultSet rs=st.executeQuery("SELECT * FROM banneddb WHERE user_id=\'"+new Helper().Encode(userID)+"\'");
            if (rs.next()){
                return "This user is already banned!";
            }
            else {
                st.execute("INSERT INTO banneddb (user_id) VALUES (\'"+new Helper().Encode(userID)+"\')");
                return "You banned this user successfully!";

            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public String UnBan(String userID){
        try{
            Statement st=con.createStatement();
            ResultSet rs=st.executeQuery("SELECT * FROM banneddb WHERE user_id=\'"+new Helper().Encode(userID)+"\'");
            if (rs.next()){
                st.execute("DELETE FROM banneddb WHERE user_id=\'"+new Helper().Encode(userID)+"\'");
                return "This user got unbanned successfully!";            }
            else {
                return "You cannot unban an unbanned user!";
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}

