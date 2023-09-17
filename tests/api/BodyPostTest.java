package api;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BodyPostTest {

    @Test
    void setText() {
        BodyPost bp1=new BodyPost(":)");
        assertEquals(bp1.getText(),":)");
        bp1.setText("┻━┻ \\(^.^\\)");
        assertEquals(bp1.getText(),"┻━┻ \\(^.^\\)");
    }

    @Test
    void getText() {
        BodyPost bp1=new BodyPost(":)");
        assertEquals(bp1.getText(),":)");
        bp1.setText("┻━┻ \\(^.^\\)");
        assertEquals(bp1.getText(),"┻━┻ \\(^.^\\)");

    }
}