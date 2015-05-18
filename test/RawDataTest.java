



import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * The test class RawDataTest.
 *
 * @author  (your name)
 * @version (a version number or a date)
 */
public class RawDataTest
{
    /**
     * Default constructor for test class RawDataTest
     */
    public RawDataTest()
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
    public void Raw()
    {
        RawData rawData1 = new RawData(2, 10);
        assertEquals(2, rawData1.getPosition());
        assertEquals(10, rawData1.getValue());
    }

}


