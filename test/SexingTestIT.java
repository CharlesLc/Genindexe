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
public class SexingTestIT {
    
    public SexingTestIT() {
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
     * Test of newResult method, of class SexingTest.
     */
    @Test
    public void testNewResult() {
        System.out.println("newResult");
        SexingTest instance = null;
        Result expResult = null;
        Result result = instance.newResult();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getName method, of class SexingTest.
     */
    @Test
    public void testGetName() {
        System.out.println("getName");
        SexingTest instance = null;
        String expResult = "";
        String result = instance.getName();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getMaleValue method, of class SexingTest.
     */
    @Test
    public void testGetMaleValue() {
        System.out.println("getMaleValue");
        SexingTest instance = null;
        RawData expResult = null;
        RawData result = instance.getMaleValue();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getFemaleValue method, of class SexingTest.
     */
    @Test
    public void testGetFemaleValue() {
        System.out.println("getFemaleValue");
        SexingTest instance = null;
        RawData expResult = null;
        RawData result = instance.getFemaleValue();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
