/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package genindex;

import java.util.List;
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
public class SampleTest {
    
    public SampleTest() {
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
     * Test of getIdSample method, of class Sample.
     */
    @Test
    public void testGetIdSample() {
        System.out.println("getIdSample");
        Sample instance = null;
        int expResult = 0;
        int result = instance.getIdSample();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of addResult method, of class Sample.
     */
    @Test
    public void testAddResult() {
        System.out.println("addResult");
        Result theResult = null;
        Sample instance = null;
        instance.addResult(theResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getResult method, of class Sample.
     */
    @Test
    public void testGetResult() {
        System.out.println("getResult");
        Sample instance = null;
        String expResult = "";
        String result = instance.getResult();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getResults method, of class Sample.
     */
    @Test
    public void testGetResults() {
        System.out.println("getResults");
        Sample instance = null;
        List<Result> expResult = null;
        List<Result> result = instance.getResults();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getAnalysis method, of class Sample.
     */
    @Test
    public void testGetAnalysis() {
        System.out.println("getAnalysis");
        Sample instance = null;
        Analysis expResult = null;
        Analysis result = instance.getAnalysis();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getOrder method, of class Sample.
     */
    @Test
    public void testGetOrder() {
        System.out.println("getOrder");
        Sample instance = null;
        Order expResult = null;
        Order result = instance.getOrder();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of isCompleted method, of class Sample.
     */
    @Test
    public void testIsCompleted() {
        System.out.println("isCompleted");
        Sample instance = null;
        boolean expResult = false;
        boolean result = instance.isCompleted();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
