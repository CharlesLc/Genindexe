



import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * The test class SexingResultTest.
 *
 * @author  (your name)
 * @version (a version number or a date)
 */
public class SexingResultTest
{
    /**
     * Default constructor for test class SexingResultTest
     */
    public SexingResultTest()
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
    public void Get_Set_MaleValue()
    {
        RawData dataM = new RawData(2, 10);
        SexingResult sexingRe1 = new SexingResult();
        sexingRe1.setMaleValue(dataM);
        assertEquals(dataM, sexingRe1.getMaleValue());
    }

    @Test
    public void Get_Set_FemaleValue()
    {
        RawData dataF = new RawData(3, 10);
        SexingResult sexingRe1 = new SexingResult();
        sexingRe1.setFemaleValue(dataF);
        assertEquals(dataF, sexingRe1.getFemaleValue());
    }
}


