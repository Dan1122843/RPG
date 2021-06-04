/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rpgDatabase;

import java.util.logging.Level;
import java.util.logging.Logger;
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
public class TimerTest {
    
    public TimerTest() {
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
     * Test of getSeconds method, of class Timer.
     */
    @Test
    public void testGetSeconds() {
        try {
            System.out.println("getSeconds");
            Timer instance = new Timer();
            instance.startTimer();
            Thread.sleep(5000);
            instance.endTimer();
            int expResult = 5;
            int result = instance.getSeconds();
            assertEquals(expResult, result);
        } catch (InterruptedException ex) {
            Logger.getLogger(TimerTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
