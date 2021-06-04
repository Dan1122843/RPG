/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rpgGUI;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import rpgCharacters.Demon;
import rpgCharacters.Enemy;

/**
 *
 * @author danielwillis
 */
public class GameSetupTest {
    
    public GameSetupTest() {
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
     * Test of checkForEnemy method, of class GameSetup.
     */
    @Test
    public void testCheckForEnemy() {
        try {
            System.out.println("checkForEnemy");
            String direction = "right";
            GameSetup instance = new GameSetup();
            instance.setHorisMove(150);
            instance.y = 425;
            Enemy enemyInstance = instance.checkForEnemy(direction);
            String expResult = "Demon";
            enemyInstance.getName();
            String result = enemyInstance.getName();
            assertEquals(expResult, result);
        } catch (IOException ex) {
            Logger.getLogger(GameSetupTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
