package api;

import java.sql.*;
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
            //Statement st=con.createStatement();
            //ResultSet rs=st.executeQuery("SELECT type FROM contentdb WHERE content_id = \'"+content_id+"\';");
            PreparedStatement st=con.prepareStatement("SELECT type FROM contentdb WHERE content_id = ?;");
            st.setString(1, content_id);
            ResultSet rs=st.executeQuery();
            String s="";
            String[] s2;
            if (rs.next()) {
                //s=rs.getString(0);
                s=rs.getString(1);
                //rs=st.executeQuery("SELECT * FROM content t WHERE t.content_id =\'"+content_id+"\';");
                st=con.prepareStatement("SELECT * FROM content t WHERE t.content_id = ?;");
                st.setString(1, content_id);
                rs=st.executeQuery();
                if (rs.next()) {
                    content.put("Title", rs.getString(1));
                    content.put("User", rs.getString(5));
                    content.put("Charlimit",rs.getString(2));
                    content.put("Votes",rs.getString(3));
                    content.put("ID", content_id);
                    content.put("Type",s);
                    int cl=Integer.parseInt(content.get("Charlimit")),votes=Integer.parseInt(content.get("Votes"));
                    if (s.toLowerCase().equals("article")){
                        //rs=st.executeQuery("SELECT * FROM article a WHERE a.content_id=\'"+content_id+"\';");
                        st=con.prepareStatement("SELECT * FROM article a WHERE a.content_id= ?;");
                        st.setString(1, content_id);
                        rs=st.executeQuery();
                        if (rs.next()){
                            content.put("Author",rs.getString(3));
                            content.put("Body",rs.getString(1));
                            Article c=new Article(content.get("Title"),content.get("Body"),content.get("User"),cl,content.get("ID"),votes,content.get("Author"));
                            return c;
                        }
                    }
                    else if (s.toLowerCase().equals("post")){
                        //rs=st.executeQuery("SELECT * FROM post p WHERE p.content_id=\'"+content_id+"\';");
                        st=con.prepareStatement("SELECT * FROM post p WHERE p.content_id= ?;");
                        st.setString(1, content_id);
                        rs=st.executeQuery();
                        if (rs.next()){
                            content.put("Body",rs.getString(1));
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
            //Statement st=con.createStatement();
            PreparedStatement st=con.prepareStatement("SELECT * FROM comment WHERE unique_id = ? ;");
            st.setString(1, commentId);
            ResultSet rs=st.executeQuery();
            //ResultSet rs=st.executeQuery("SELECT * FROM comment WHERE unique_id = \'"+commentId+"\';");
            if (rs.next()) {
                comment.put("Text",rs.getString(1));
                comment.put("Owner",rs.getString(2));
                comment.put("ID",commentId);
                comment.put("Charlimit",rs.getString(4));
                comment.put("Votes",rs.getString(5));
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
