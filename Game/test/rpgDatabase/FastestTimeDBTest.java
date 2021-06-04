/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rpgDatabase;

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
public class FastestTimeDBTest {
    
    public FastestTimeDBTest() {
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
     * Test of establishConnection method, of class FastestTimeDB.
     */
    @Test
    public void testEstablishConnection() {
        System.out.println("establishConnection");
        FastestTimeDB instance = new FastestTimeDB();
        instance.establishConnection();
        String result = "";
        String expResult = "Connected";
        if (instance.conn != null){
            result = "Connected";
        }
        assertEquals(expResult, result);
    }

    /**
     * Test of transferFastestTimeData method, of class FastestTimeDB.
     */
    @Test
    public void testTransferFastestTimeData() {
        System.out.println("transferFastestTimeData");
        FastestTimeDB instance = new FastestTimeDB();
        instance.establishConnection();
        instance.createTable();
        instance.insertPlayerData("Daniel", 200);
        instance.transferFastestTimeData();
        String result = instance.getFastestPlayerName();
        String expResult = "Daniel";
        assertEquals(expResult, result);
    }

    /**
     * Test of closeConnections method, of class FastestTimeDB.
     */
    @Test
    public void testCloseConnections() {
        System.out.println("closeConnections");
        FastestTimeDB instance = new FastestTimeDB();
        instance.establishConnection();
        instance.closeConnections();
        boolean result = instance.connectionClosed;
        boolean expResult = true;
        assertEquals(expResult, result);
    }
    
}
