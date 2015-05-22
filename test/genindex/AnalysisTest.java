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
public class AnalysisTest {
    
    public AnalysisTest() {
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
     * Test of getName method, of class Analysis.
     */
    @Test
    public void testGetName() {
        System.out.println("getName");
        Analysis instance = null;
        String expResult = "";
        String result = instance.getName();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of newResult method, of class Analysis.
     */
    @Test
    public void testNewResult() {
        System.out.println("newResult");
        Analysis instance = null;
        Result expResult = null;
        Result result = instance.newResult();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getSpecie method, of class Analysis.
     */
    @Test
    public void testGetSpecie() {
        System.out.println("getSpecie");
        Analysis instance = null;
        Specie expResult = null;
        Specie result = instance.getSpecie();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    public class AnalysisImpl extends Analysis {

        public AnalysisImpl() {
            super(null);
        }

        public String getName() {
            return "";
        }

        public Result newResult() {
            return null;
        }
    }
    
}
