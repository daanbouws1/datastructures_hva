package nl.hva.ict.ds;

import org.junit.Before;
import org.junit.Test;


import static com.sun.tools.internal.ws.wsdl.parser.Util.fail;
import static org.junit.Assert.*;

public class LinkedListTest {
    protected LinkedList<String> testList;

    @Before
    public void setup() {
        testList = new LinkedList<>();
    }

    @Test
    public void describeSettingJVMArguments() {
//        fail("Please describe in you report how you set the JVM arguments for your favorite IDE.");
    }

    @Test
    public void addToEmptyList() {
        testList.add("one");

        assertTrue(testList.size() != 0);
        assertEquals("one", testList.get(0));
    }

    @Test
    public void addToListWithOneElement() {
        testList.add("one");
        testList.add("two");

        assertTrue(testList.size() != 0);
        assertEquals("two", testList.get(1));
    }

    @Test
    public void getFirstElement() {
        testList.add("one");

        assertEquals("one", testList.get(0));
    }

    @Test
    public void getLastElement() {
        testList.add("one");

        assertEquals("one", testList.get(1));
    }

    @Test(expected = IllegalArgumentException.class)
    public void negativeIndexIsNotAllowed() {
        testList.get(-1);
    }

    @Test
    public void deleteFromListWithSingleElement() {
        testList.add("one");
        testList.delete("one");

        assertEquals(0, testList.size());
    }

    @Test
    public void deleteFromListWithMultipleOccurences() {
        testList.add("one");
        testList.add("one");
        testList.delete("one");

        assertEquals(0, testList.size());
    }

    @Test
    public void deleteFromListWithMultipleOccurrencesAndMoreElements() {
        testList.add("one");
        testList.add("two");
        testList.add("one");
        testList.delete("one");

        assertEquals(1, testList.size());
    }

    @Test
    public void sizeOfEmptyList() {
        assertEquals(0, testList.size());
    }

    @Test
    public void sizeOfListWithOneElement() {
        testList.add("one");
        assertEquals(1, testList.size());
    }

    // Extra unit tests go here

    @Test
    public void deleteFromListWithMultipleOccurrencesAndMoreElements2() {
        testList.add("one");
        testList.add("two");
        testList.add("two");
        testList.add("one");
        testList.delete("one");

        assertEquals(2, testList.size());
    }

    @Test
    public void deleteFromEmptyList() {
        testList.delete("one");

        assertEquals(0, testList.size());
    }

    @Test(expected = IllegalArgumentException.class)
    public void cantGetTooBigIndex() {
        testList.add("one");
        testList.get(10);
    }

    @Test
    public void cantDeleteNodeThatIsntThere() {
        testList.add("one");
        testList.delete("two");

        assertEquals(1, testList.size());
    }

    @Test(expected = IllegalArgumentException.class)
    public void cantAddNullNode() {
        testList.add(null);
    }
}
