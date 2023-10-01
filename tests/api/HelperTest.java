package api;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.sql.Statement;

import static org.junit.jupiter.api.Assertions.*;

class HelperTest {

    @BeforeEach
    void setUp() {
        Content.nextID=0;
        Comment.nextID=0;
        ControlSystem cs=new ControlSystem();
        try {
            Statement st=cs.connection.createStatement();
            st.execute("INSERT INTO users (user_id,password,type) VALUES (\'me\',123,\'User\');");
            st.execute("INSERT INTO users (user_id,password,type) VALUES (\'you\',123,\'User\');");
            st.execute("INSERT INTO users (user_id,password,type) VALUES (\'guess?\',123,\'User\');");
            st.execute("INSERT INTO users (user_id,password,type) VALUES (\'Noone\',123,\'User\');");

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @AfterEach
    void tearDown() {
        new ControlSystem().Clear();
        //ControlSystem.liked=null;
    }

    @Test
    void getContent() {
        ControlSystem controlSystem=new ControlSystem();
        Post post1=new Post("This is a post that has no title","The post contains nothing","Noone");
        Article article1=new Article("Totally legit author","No title","A text","me");
        Post post2=new Post("title","LaText","you");
        Article article2=new Article("The user above","why is writing tests so boring?","title is self-explanatory","guess?");

        assertEquals(controlSystem.AddContent(post1,"Post"),true);
        assertNotNull(new Helper(controlSystem.connection).getContent(post1.getID()));


    }

    @Test
    void getComment() {
        ControlSystem controlSystem=new ControlSystem();
        Post post1=new Post("This is a post that has no title","The post contains nothing","Noone");
        Comment comment1=new Comment("pppppppp","me");

        assertEquals(controlSystem.AddContent(post1,"Post"),true);
        assertEquals(comment1.getId(),controlSystem.AddComment(comment1,post1.getID()));
        assertNotNull(new Helper(controlSystem.connection).getComment(comment1.getId()));
    }
}