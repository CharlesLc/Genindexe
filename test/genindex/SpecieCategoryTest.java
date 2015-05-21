/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package genindex;

import java.util.Set;
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
public class SpecieCategoryTest {
    
    public SpecieCategoryTest() {
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
     * Test of getName method, of class SpecieCategory.
     */
    @Test
    public void testGetName() {
        System.out.println("getName");
        SpecieCategory instance = null;
        String expResult = "";
        String result = instance.getName();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getSpecies method, of class SpecieCategory.
     */
    @Test
    public void testGetSpecies() {
        System.out.println("getSpecies");
        SpecieCategory instance = null;
        Set<Specie> expResult = null;
        Set<Specie> result = instance.getSpecies();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of addSpecie method, of class SpecieCategory.
     */
    @Test
    public void testAddSpecie() {
        System.out.println("addSpecie");
        Specie specie = null;
        SpecieCategory instance = null;
        instance.addSpecie(specie);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
