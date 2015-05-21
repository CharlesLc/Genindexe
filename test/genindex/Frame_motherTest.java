/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package genindex;

import javax.swing.JPanel;
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
public class Frame_motherTest {
    
    public Frame_motherTest() {
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
     * Test of setFrameLogin method, of class Frame_mother.
     */
    @Test
    public void testSetFrameLogin() {
        System.out.println("setFrameLogin");
        JPanel newPan = null;
        Login logout = null;
        Frame_mother instance = new Frame_mother();
        instance.setFrameLogin(newPan, logout);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setFrame method, of class Frame_mother.
     */
    @Test
    public void testSetFrame() {
        System.out.println("setFrame");
        JPanel newPan = null;
        Frame_mother instance = new Frame_mother();
        instance.setFrame(newPan);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
