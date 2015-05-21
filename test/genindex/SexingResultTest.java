/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package genindex;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author User
 */
public class SexingResultTest {
    
    public SexingResultTest() {
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
     * Test of getMaleValue method, of class SexingResult.
     */
    @Test
    public void testGetMaleValue() {
        System.out.println("getMaleValue");
        SexingResult instance = new SexingResult();
        RawData expResult = null;
        RawData result = instance.getMaleValue();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getFemaleValue method, of class SexingResult.
     */
    @Test
    public void testGetFemaleValue() {
        System.out.println("getFemaleValue");
        SexingResult instance = new SexingResult();
        RawData expResult = null;
        RawData result = instance.getFemaleValue();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setMaleValue method, of class SexingResult.
     */
    @Test
    public void testSetMaleValue() {
        System.out.println("setMaleValue");
        RawData value = null;
        SexingResult instance = new SexingResult();
        instance.setMaleValue(value);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setFemaleValue method, of class SexingResult.
     */
    @Test
    public void testSetFemaleValue() {
        System.out.println("setFemaleValue");
        RawData value = null;
        SexingResult instance = new SexingResult();
        instance.setFemaleValue(value);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
