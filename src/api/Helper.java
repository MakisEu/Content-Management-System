package api;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;

public class Helper {
    Connection con;
    public Helper(){}
    public Helper(Connection connection){
        con=connection;
    }
    public Content getContent(String content_id){
        HashMap<String,String> content=new HashMap<>();
        try {
            Statement st=con.createStatement();
            ResultSet rs=st.executeQuery("SELECT type FROM contentdb WHERE content_id = \'"+content_id+"\';");
            String s="";
            String[] s2;
            if (rs.next()) {
                //s=rs.getString(0);
                s=rs.getString(1);
                rs=st.executeQuery("SELECT * FROM content t WHERE t.content_id =\'"+content_id+"\';");
                if (rs.next()) {
                    content.put("Title", Decode(rs.getString(1)));
                    content.put("User", Decode(rs.getString(5)));
                    content.put("Charlimit", Decode(rs.getString(2)));
                    content.put("Votes", Decode(rs.getString(3)));
                    content.put("ID", Decode(content_id));
                    content.put("Type",s);
                    int cl=Integer.parseInt(content.get("Charlimit")),votes=Integer.parseInt(content.get("Votes"));
                    if (s.toLowerCase().equals("article")){
                        rs=st.executeQuery("SELECT * FROM article a WHERE a.content_id=\'"+content_id+"\';");
                        if (rs.next()){
                            content.put("Author",Decode(rs.getString(3)));
                            content.put("Body",Decode(rs.getString(1)));
                            Article c=new Article(content.get("Title"),content.get("Body"),content.get("User"),cl,content.get("ID"),votes,content.get("Author"));
                            return c;
                        }
                    }
                    else if (s.toLowerCase().equals("post")){
                        rs=st.executeQuery("SELECT * FROM post p WHERE p.content_id=\'"+content_id+"\';");
                        if (rs.next()){
                            content.put("Body",Decode(rs.getString(1)));
                            Post c=new Post(content.get("Title"),content.get("Body"),content.get("User"),cl,content.get("ID"),votes);
                            return  c;
                        }
                    }

                    return null;
                }
            }

            st.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }
    public Comment getComment(String commentId){
        HashMap<String,String> comment=new HashMap<>();
        try {
            Statement st=con.createStatement();
            ResultSet rs=st.executeQuery("SELECT * FROM comment WHERE unique_id = \'"+commentId+"\';");
            if (rs.next()) {
                comment.put("Text",Decode(rs.getString(1)));
                comment.put("Owner",Decode(rs.getString(2)));
                comment.put("ID",commentId);
                comment.put("Charlimit",Decode(rs.getString(4)));
                comment.put("Votes",Decode(rs.getString(5)));
                Comment c=new Comment(comment.get("Text"),comment.get("Owner"),comment.get("ID"),Integer.parseInt(comment.get("Votes")));
                return c;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return null;
    }
    public String Encode(String s){
        return s.replace("'","{SINGLEQUOTE}").replace("\'","{SINGLEQUOTE}");
    }
    public String Decode(String s){
        return s.replace("{SINGLEQUOTE}","\'");
    }

}
