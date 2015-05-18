

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * The test class SexingTestTest.
 *
 * @author  (your name)
 * @version (a version number or a date)
 */
public class SexingTestTest
{
    /**
     * Default constructor for test class SexingTestTest
     */
    public SexingTestTest()
    {
    }

    /**
     * Sets up the test fixture.
     *
     * Called before every test case method.
     */
    @Before
    public void setUp()
    {
    }

    /**
     * Tears down the test fixture.
     *
     * Called after every test case method.
     */
    @After
    public void tearDown()
    {
    }


    @Test
    public void Get_MaleValue()
    {
        Specie Chien = new Specie("Chien");
        SexingTest sexingTe1 = new SexingTest(Chien, 2, 10, 3, 9);
        RawData MaleV = sexingTe1.getMaleValue();
        assertEquals(MaleV, sexingTe1.getMaleValue());
    }
    
    @Test
    public void Get_FemaleValue()
    {
        Specie Chien = new Specie("Chien");
        SexingTest sexingTe1 = new SexingTest(Chien, 2, 10, 3, 9);
        RawData FemaleV = sexingTe1.getFemaleValue();
        assertEquals(FemaleV, sexingTe1.getFemaleValue());
    }
}




