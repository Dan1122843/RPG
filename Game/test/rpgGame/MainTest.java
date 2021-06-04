package rpgGame;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author danielwillis
 */
public class MainTest {
    
    public MainTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of ConvertSecondsToTimeString method, of class Main.
     */
    @Test
    public void testConvertSecondsToTimeString() {
        System.out.println("ConvertSecondsToTimeString");
        int seconds = 200;
        String expResult = "3 minutes, 20 seconds";
        String result = Main.ConvertSecondsToTimeString(seconds);
        assertEquals(expResult, result);
    }
    
}
