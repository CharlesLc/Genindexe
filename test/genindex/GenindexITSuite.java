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
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 *
 * @author Charles
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({AnalysisIT.class, SexingResultIT.class, SampleIT.class, OrderIT.class, ScrapieResultIT.class, CustomerIT.class, SexingTestIT.class, IntegrationTestIT.class, SpecieCategoryIT.class, OrderStatusIT.class, ResultStatusIT.class, SpecieIT.class, ResultIT.class, RawDataIT.class, UniqueIdSampleIT.class, GenindexIT.class, ScrapieTestIT.class})
public class GenindexITSuite {

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }
    
}
