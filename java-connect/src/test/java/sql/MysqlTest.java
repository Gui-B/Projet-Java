package sql;


import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Types;
import java.util.*;


import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class MysqlTest extends TestCase {
	/**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public MysqlTest( String testName )
    {
        super( testName );
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return new TestSuite( MysqlTest.class );
    }
    
    public void testTest()
    { 
    	assertTrue(true);
    }

}
