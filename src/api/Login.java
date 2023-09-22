package api;

public class Login {
   private ControlSystem system;

   public Login(ControlSystem system){
      this.system=system;
   }

   public String Log_in(String username,String password){
      if (system.getAll_users().containsKey(username)){
         if (system.getAll_users().get(username).equals(password)){
            return "Logged in successfully";
         }
      }
      return "Wrong username or password!";
   }

   public String Sign_up(String username,String password){
      if (system.getAll_users().containsKey(username)){
         return "This username is already taken";
      }
      system.getAll_users().put(username,password);
      return "You registered successfully";
   }


}
