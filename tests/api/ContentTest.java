package api;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ContentTest {

    @BeforeEach
    void setUp() {
        Content.nextID=0;
    }

    @AfterEach
    void tearDown() {
        ControlSystem.comments=null;
        ControlSystem.content=null;
        ControlSystem.liked=null;
    }

    @Test
    void getCharLimit() {
        BodyPost bp=new BodyPost("This is my text");
        Content con1=new Content("This title is mine",bp,"Christian bale",500);
        assertEquals(con1.getCharLimit(),500);
        Content con2=new Content("This title is mine1",bp,"Christian bale2",50000000);
        assertEquals(con2.getCharLimit(),50000000);
    }

    @Test
    void getTitle() {
        BodyPost bp=new BodyPost("This is my text");
        Content con1=new Content("This title is mine",bp,"Christian bale",500);
        assertEquals(con1.getTitle(),"This title is mine");
        Content con2=new Content("This title is not mine",bp,"Christian bale",50000000);
        assertEquals(con2.getTitle(),"This title is not mine");
    }

    @Test
    void getBody() {
        BodyPost bp=new BodyPost("This is my text");
        Content con1=new Content("This title is mine",bp,"Christian bale",500);
        BodyPost b1= (BodyPost) con1.getBody();
        assertEquals(con1.getBody(),bp);

        assertEquals(b1.getText(),bp.getText());
        b1.setText("This text is mine");
        assertEquals(((BodyPost) con1.getBody()).getText(),"This text is mine");
        assertEquals(con1.getBody(),bp);

        BodyPost bp2=new BodyPost("Just a normal text");
        Content con2=new Content("This title is not mine",bp2,"Christian bale",50000000);
        assertEquals(con2.getBody(),bp2);
    }

    @Test
    void setTitle() {
        BodyPost bp=new BodyPost("This is my text");
        Content con1=new Content("This title is mine",bp,"Christian bale",500);

        assertEquals(con1.getTitle(),"This title is mine");
        con1.setTitle("A");
        assertEquals(con1.getTitle(),"A");
        Content con2=new Content("This title is not mine",bp,"Christian bale",50000000);
        con2.setTitle(con1.getTitle());
        assertEquals(con2.getTitle(),"A");
    }

    @Test
    void setBody() {
        BodyPost bp=new BodyPost("This is my text");
        Content con1=new Content("This title is mine",bp,"Christian bale",500);
        BodyPost b1= (BodyPost) con1.getBody();
        assertEquals(con1.getBody(),bp);
        assertEquals(b1.getText(),bp.getText());
        Body b= new BodyPost(":)");
        con1.setBody(b);
        assertEquals(con1.getBody(),b);
        assertEquals(((BodyPost) con1.getBody()).getText(),":)");
    }

    @Test
    void like() {
        BodyPost bp=new BodyPost("This is my text");
        Content con1=new Content("This title is mine",bp,"Christian bale",500);
        assertEquals(con1.getVotes(),0);
        con1.Like();
        con1.Like();
        assertEquals(con1.getVotes(),2);
        for (int i=0;i<99;i++){
            con1.Like();
        }
        assertEquals(con1.getVotes(),101);
    }

    @Test
    void dislike() {
        BodyPost bp=new BodyPost("This is my text");
        Content con1=new Content("This title is mine",bp,"Christian bale",500);
        assertEquals(con1.getVotes(),0);
        con1.Dislike();
        con1.Dislike();
        assertEquals(con1.getVotes(),-2);
        for (int i=0;i<99;i++){
            con1.Dislike();
        }
        assertEquals(con1.getVotes(),-101);
    }
    @Test
    void getVotes() {
        BodyPost bp=new BodyPost("This is my text");
        Content con1=new Content("This title is mine",bp,"Christian bale",500);
        assertEquals(con1.getVotes(),0);
        con1.Like();
        con1.Like();
        con1.Dislike();
        assertEquals(con1.getVotes(),1);
        con1.Dislike();
        con1.Dislike();
        con1.Like();
        con1.Dislike();
        assertEquals(con1.getVotes(),-1);
    }

    @Test
    void getID() {
        BodyPost bp=new BodyPost("This is my text");
        Content con1=new Content("This title is mine",bp,"Christian bale",500);
        assertEquals(con1.getID(),"object#"+"Christian bale"+"#"+"0");
        Content con2=new Content("This title is mine1",bp,"Christian bale2",50000000);
        assertEquals(con2.getID(),"object#"+"Christian bale2"+"#"+"1");
    }

    @Test
    void getUser() {
        BodyPost bp=new BodyPost("This is my text");
        Content con1=new Content("This title is mine",bp,"Christian bale",500);
        assertEquals(con1.getUser(),"Christian bale");
        Content con2=new Content("This title is mine1",bp,"Christian bale2",50000000);
        assertEquals(con2.getUser(),"Christian bale2");
    }
}