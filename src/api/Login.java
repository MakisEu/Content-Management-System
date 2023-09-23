package api;

import java.util.Vector;

public class Login {
   private ControlSystem system;

   public Login(ControlSystem system){
      this.system=system;
   }

   public String Log_in(String username,String password){
      if (system.getAll_users().containsKey(username)){
         if (system.getAll_users().get(username).get(0).equals(password)){
            return "Logged in successfully#"+system.getAll_users().get(username).get(1);
         }
      }
      return "Wrong username or password!";
   }

   public String Sign_up(String username,String password,String type){
      if (system.getAll_users().containsKey(username)){
         return "This username is already taken";
      }
      Vector<String> v=new Vector<String>();
      v.add(password);
      v.add(type);
      system.getAll_users().put(username,v);
      return "You registered successfully";
   }


}
