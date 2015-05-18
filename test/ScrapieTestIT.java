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
public class ScrapieTestIT {
    
    public ScrapieTestIT() {
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
     * Test of newResult method, of class ScrapieTest.
     */
    @Test
    public void testNewResult() {
        System.out.println("newResult");
        ScrapieTest instance = null;
        Result expResult = null;
        Result result = instance.newResult();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getName method, of class ScrapieTest.
     */
    @Test
    public void testGetName() {
        System.out.println("getName");
        ScrapieTest instance = null;
        String expResult = "";
        String result = instance.getName();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getScrapieSensibility method, of class ScrapieTest.
     */
    @Test
    public void testGetScrapieSensibility() {
        System.out.println("getScrapieSensibility");
        ScrapieTest instance = null;
        RawData expResult = null;
        RawData result = instance.getScrapieSensibility();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
