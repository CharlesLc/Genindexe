



import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * The test class SampleTest.
 *
 * @author  (your name)
 * @version (a version number or a date)
 */
public class SampleTest
{
    private Customer Custo;
    private Order Order;
    private Specie Specie;
    private SexingTest Sexing;

    /**
     * Default constructor for test class SampleTest
     */
    public SampleTest()
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
        Custo = new Customer("Michel", "Paris");
        Order = new Order(Custo);
        Specie = new Specie("F�lin");
        Sexing = new SexingTest(Specie, 8, 12, 0, 0);
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
    public void Test_Creation()
    {
        Sample sample1 = new Sample(Sexing, Specie, Order);
    }

    @Test
    public void Test_addResult_getResult()
    {
        Sample sample1 = new Sample(Sexing, Specie, Order);
        SexingResult sexingRe1 = new SexingResult();
        RawData rawData1 = new RawData(1, 1);
        sexingRe1.setMaleValue(rawData1);
        sample1.addResult(sexingRe1);
        assertNotNull(sample1.getResult());
    }
    
    @Test
    public void Test_addResult_getResults()
    {
        Sample sample1 = new Sample(Sexing, Specie, Order);
        SexingResult sexingRe1 = new SexingResult();
        RawData rawData1 = new RawData(1, 1);
        sexingRe1.setMaleValue(rawData1);
        sample1.addResult(sexingRe1);
        assertNotNull(sample1.getResults());
    }
    

    @Test
    public void Test_isCompleted_Empty()
    {
        Sample sample1 = new Sample(Sexing, Specie, Order);
        assertEquals(false, sample1.isCompleted());
    }

    @Test
    public void Test_isCompleted_Validated()
    {
        Sample sample1 = new Sample(Sexing, Specie, Order);
        SexingResult sexingRe1 = new SexingResult();
        RawData male = new RawData(1, 1);
        RawData female = new RawData(2, 2);
        sexingRe1.setFemaleValue(female);
        sexingRe1.setMaleValue(male);
        sexingRe1.firstRead(true, "Male");
        sexingRe1.validate(true);
        sample1.addResult(sexingRe1);
        assertEquals(true, sample1.isCompleted());
    }
    
    @Test
    public void Test_isCompleted_OneUnreadable()
    {
        Sample sample1 = new Sample(Sexing, Specie, Order);
        SexingResult sexingRe1 = new SexingResult();
        RawData male = new RawData(1, 1);
        RawData female = new RawData(2, 2);
        sexingRe1.setFemaleValue(female);
        sexingRe1.setMaleValue(male);
        sexingRe1.firstRead(true, "Male");
        sexingRe1.validate(false);
        sample1.addResult(sexingRe1);
        assertEquals(false, sample1.isCompleted());
    }
    
    @Test
    public void Test_isCompleted_ThreeUnreadable()
    {
        Sample sample1 = new Sample(Sexing, Specie, Order);
        RawData male = new RawData(1, 1);
        RawData female = new RawData(2, 2);
        SexingResult sexingRe1 = new SexingResult();
        sexingRe1.setFemaleValue(female);
        sexingRe1.setMaleValue(male);
        sexingRe1.firstRead(true, "Male");
        sexingRe1.validate(false);
        SexingResult sexingRe2 = new SexingResult();
        sexingRe2.setFemaleValue(female);
        sexingRe2.setMaleValue(male);
        sexingRe2.firstRead(true, "Male");
        sexingRe2.validate(false);
        SexingResult sexingRe3 = new SexingResult();
        sexingRe3.setFemaleValue(female);
        sexingRe3.setMaleValue(male);
        sexingRe3.firstRead(true, "Male");
        sexingRe3.validate(false);
        sample1.addResult(sexingRe1);
        sample1.addResult(sexingRe2);
        sample1.addResult(sexingRe3);
        assertEquals(true, sample1.isCompleted());
    }
    
    @Test
    public void Test_isCompleted_Unreadable_After_Validated()
    {
        //Dans un cas ideal, on ne devrait pas pouvoir ajouter un result alors que le
        // dernier effectue est valide (true). Il faut empecher cela.
        // Neanmoins, le test est tout de meme complete, ce qui est satisfaisant.
        Sample sample1 = new Sample(Sexing, Specie, Order);
        RawData male = new RawData(1, 1);
        RawData female = new RawData(2, 2);
        SexingResult sexingRe1 = new SexingResult();
        sexingRe1.setFemaleValue(female);
        sexingRe1.setMaleValue(male);
        sexingRe1.firstRead(true, "Male");
        sexingRe1.validate(false);
        SexingResult sexingRe2 = new SexingResult();
        sexingRe2.setFemaleValue(female);
        sexingRe2.setMaleValue(male);
        sexingRe2.firstRead(true, "Male");
        sexingRe2.validate(true);
        SexingResult sexingRe3 = new SexingResult();
        sexingRe3.setFemaleValue(female);
        sexingRe3.setMaleValue(male);
        sexingRe3.firstRead(false, "Male");
        sexingRe3.validate(false);
        sample1.addResult(sexingRe1);
        sample1.addResult(sexingRe2);
        sample1.addResult(sexingRe3);
        assertEquals(true, sample1.isCompleted());
    }
}





