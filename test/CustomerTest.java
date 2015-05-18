
import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * The test class CustomerTest.
 *
 * @author  (your name)
 * @version (a version number or a date)
 */
public class CustomerTest
{
    /**
     * Default constructor for test class CustomerTest
     */
    public CustomerTest()
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
    public void testgetName()
    {
        Customer customer1 = new Customer("n", "t");
        assertEquals("n", customer1.getName());
    }




    @Test
    public void testAddOrder()
    {
        Customer customer1 = new Customer("n", "t");
        Order order1 = new Order(customer1);
        java.util.ArrayList<Order> tableau1 = (java.util.ArrayList<Order>)customer1.getOrders();
        assertEquals(0, tableau1.size());
        customer1.addOrder(order1);
        java.util.ArrayList<Order> tableau2 = (java.util.ArrayList<Order>)customer1.getOrders();
        assertEquals(1, tableau2.size());
    }

    @Test
    public void testGetTown()
    {
        Customer customer1 = new Customer("n", "t");
        assertEquals("t", customer1.getTown());
    }

    @Test
    public void testGetOrders()
    {
        Customer customer1 = new Customer("n", "t");
        Order order1 = new Order(customer1);
        Order order2 = new Order(customer1);
        java.util.ArrayList<Order> arrayLis1 = (java.util.ArrayList<Order>)customer1.getOrders();
        assertEquals(true, arrayLis1.isEmpty());
        customer1.addOrder(order1);
        customer1.addOrder(order2);
        java.util.ArrayList<Order> arrayLis2 = (java.util.ArrayList<Order>)customer1.getOrders();
        assertEquals(false, arrayLis2.isEmpty());
        assertEquals(2, arrayLis2.size());
    }
}








