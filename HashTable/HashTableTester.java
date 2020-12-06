/*
 * NAME: Jackie Yuan
 */
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
/**
 * This class contains the test of Hash table.
 * @author Shuibenyang Yuan
 * @Since {May 4 2018}
 */
public class HashTableTester {
    // Initializing the setups
    HashTable mytest1;

    @Before
    public void setup() {
        mytest1 = new HashTable(10);
    }

    /**
     * the test to test insert method.
     */
    @org.junit.Test
    public void insert() {
        mytest1.insert("add");
        assertTrue(mytest1.contains("add"));
        mytest1.insert("syeehyn");
        assertTrue(mytest1.contains("syeehyn"));
        mytest1.insert("sq");
        assertTrue(mytest1.contains("sq"));
        mytest1.insert("sw");
        assertTrue(mytest1.contains("sw"));
        mytest1.insert("sr");
        mytest1.insert("sy");
        mytest1.insert("st");
        mytest1.insert("a");
        assertEquals(8, mytest1.getSize());
        mytest1.insert("b");
        mytest1.insert("c");
        mytest1.insert("ab");
        mytest1.insert("aa");
        mytest1.insert("dd");
        mytest1.insert("dq");
        assertEquals(14, mytest1.getSize());
    }

    /**
     * the test to test the delete method.
     */
    @org.junit.Test
    public void delete() {
        mytest1.insert("add");
        mytest1.insert("syeehyn");
        mytest1.insert("sq");
        mytest1.delete("add");
        assertFalse(mytest1.contains("add"));
        mytest1.delete("syeehyn");
        assertFalse(mytest1.contains("syeehyn"));
        mytest1.delete("sq");
        assertFalse(mytest1.contains("sq"));
        mytest1.insert("add");
        mytest1.insert("syeehyn");
        mytest1.insert("sq");
        mytest1.insert("sw");
        mytest1.insert("sw");
        mytest1.insert("sr");
        mytest1.insert("sy");
        mytest1.insert("st");
        mytest1.insert("a");
        mytest1.insert("b");
        mytest1.insert("c");
        mytest1.insert("ab");
        mytest1.insert("aa");
        mytest1.insert("dd");
        mytest1.insert("dq");
        mytest1.delete("add");
        assertFalse(mytest1.contains("add"));
        mytest1.delete("syeehyn");
        assertFalse(mytest1.contains("syeehyn"));
    }

    /**
     * the test to test contains method.
     */
    @org.junit.Test
    public void contains() {
        mytest1.insert("add");
        assertTrue(mytest1.contains("add"));
        mytest1.insert("syeehyn");
        assertTrue(mytest1.contains("syeehyn"));
        mytest1.insert("sq");
        assertTrue(mytest1.contains("sq"));
        mytest1.insert("sw");
        assertTrue(mytest1.contains("sw"));
        assertFalse(mytest1.contains("a"));
        assertFalse(mytest1.contains("b"));
    }

    /**
     * the test to test getsize method.
     */
    @org.junit.Test
    public void getSize() {
        assertEquals(0, mytest1.getSize());
        mytest1.insert("add");
        mytest1.insert("syeehyn");
        mytest1.insert("sq");
        mytest1.insert("sw");
        mytest1.insert("sw");
        mytest1.insert("sr");
        mytest1.insert("sy");
        mytest1.insert("st");
        mytest1.insert("a");
        mytest1.insert("b");
        mytest1.insert("c");
        assertEquals(10, mytest1.getSize());
    }

    /**
     * the test to test exception.
     */
    @Test(expected = NullPointerException.class)
    public void testNullPointerException() {
        mytest1.insert(null);
        mytest1.delete(null);
        mytest1.contains(null);
    }
}
