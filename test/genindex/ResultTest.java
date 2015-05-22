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
public class ResultTest {
    
    public ResultTest() {
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
     * Test of getStatus method, of class Result.
     */
    @Test
    public void testGetStatus() {
        System.out.println("getStatus");
        Result instance = new ResultImpl();
        ResultStatus expResult = null;
        ResultStatus result = instance.getStatus();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setStatus method, of class Result.
     */
    @Test
    public void testSetStatus() {
        System.out.println("setStatus");
        ResultStatus value = null;
        Result instance = new ResultImpl();
        instance.setStatus(value);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getInterpretation method, of class Result.
     */
    @Test
    public void testGetInterpretation() {
        System.out.println("getInterpretation");
        Result instance = new ResultImpl();
        String expResult = "";
        String result = instance.getInterpretation();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setInterpretation method, of class Result.
     */
    @Test
    public void testSetInterpretation() {
        System.out.println("setInterpretation");
        String inter = "";
        Result instance = new ResultImpl();
        instance.setInterpretation(inter);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getReadNumber method, of class Result.
     */
    @Test
    public void testGetReadNumber() {
        System.out.println("getReadNumber");
        Result instance = new ResultImpl();
        int expResult = 0;
        int result = instance.getReadNumber();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of validate method, of class Result.
     */
    @Test
    public void testValidate() {
        System.out.println("validate");
        boolean yes = false;
        Result instance = new ResultImpl();
        instance.validate(yes);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of firstRead method, of class Result.
     */
    @Test
    public void testFirstRead() {
        System.out.println("firstRead");
        boolean readable = false;
        String inter = "";
        Result instance = new ResultImpl();
        instance.firstRead(readable, inter);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    public class ResultImpl extends Result {
    }
    
}
