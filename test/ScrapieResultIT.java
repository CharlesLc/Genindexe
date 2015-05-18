/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Charles
 */
public class ScrapieResultIT {
    
    public ScrapieResultIT() {
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
     * Test of getScrapieValue method, of class ScrapieResult.
     */
    @Test
    public void testGetScrapieValue() {
        System.out.println("getScrapieValue");
        ScrapieResult instance = new ScrapieResult();
        RawData expResult = null;
        RawData result = instance.getScrapieValue();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setScrapieValue method, of class ScrapieResult.
     */
    @Test
    public void testSetScrapieValue() {
        System.out.println("setScrapieValue");
        RawData value = null;
        ScrapieResult instance = new ScrapieResult();
        instance.setScrapieValue(value);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
