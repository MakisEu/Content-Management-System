package api;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

public class Login {
   private ControlSystem system;
   Helper h;

   public Login(ControlSystem system){
      this.system=system;
      h=new Helper();
   }

   public String Log_in(String username,String password){
      try {
         Statement st= system.connection.createStatement();
         ResultSet rs=st.executeQuery("SELECT type FROM users WHERE user_id=\'"+h.Encode(username)+"\' AND password =\'"+h.Decode(password)+"\'");
         if (rs.next()){
            return "Logged in successfully#"+rs.getString(1);
         }
      } catch (SQLException e) {
         throw new RuntimeException(e);
      }
      return "Wrong username or password!";
   }

   public String Sign_up(String username,String password,String type){
      try {
         Statement st= system.connection.createStatement();
         ResultSet rs=st.executeQuery("SELECT * FROM users WHERE user_id=\'"+h.Encode(username)+"\' AND password =\'"+h.Encode(password)+"\'");
         if (rs.next()){
            return "This username is already taken";
         }
         else {
            st.execute("INSERT INTO users (user_id,password,type) VALUES (\'"+h.Encode(username)+"\',\'"+h.Encode(password)+"\',\'"+type+"\') ");
            return "You registered successfully";

         }
      } catch (SQLException e) {
         throw new RuntimeException(e);
      }

   }


}
