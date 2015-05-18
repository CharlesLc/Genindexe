



import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * The test class OrderTest.
 *
 * @author  (your name)
 * @version (a version number or a date)
 */
public class OrderTest
{
    /**
     * Default constructor for test class OrderTest
     */
    public OrderTest()
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
    public void testOrder()
    {
        Customer customer1 = new Customer("bla", "Paris");
        Order order1 = new Order(customer1);
        assertSame("bla", customer1.getName());
    }
}


