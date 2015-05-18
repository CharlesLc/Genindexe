

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * The test class ScrapieResultTest.
 *
 * @author  (your name)
 * @version (a version number or a date)
 */
public class ScrapieResultTest
{
    /**
     * Default constructor for test class ScrapieResultTest
     */
    public ScrapieResultTest()
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
    public void Get_Set_Scrapie()
    {
        ScrapieResult Result = new ScrapieResult();
        RawData Data = new RawData(2, 10);
        Result.setScrapieValue(Data);
        assertEquals(Data, Result.getScrapieValue());
    }
}

