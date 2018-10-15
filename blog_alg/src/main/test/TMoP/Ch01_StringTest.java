package TMoP;

import org.junit.Test; 
import org.junit.Before; 
import org.junit.After;
import static org.junit.Assert.*;

import TMoP.Ch01_String;

/** 
* Ch01_String Tester. 
* 
* @author <Authors name> 
* @since <pre>ʮ�� 12, 2018</pre> 
* @version 1.0 
*/ 
public class Ch01_StringTest { 

@Before
public void before() throws Exception { 
} 

@After
public void after() throws Exception { 
} 

/** 
* 
* Method: LeftRotateString(String s, int m) 
* 
*/ 
@Test
public void testLeftRotateString() throws Exception { 
//TODO: Test goes here...
    String test = "abcdef";
    String result = Ch01_String.LeftRotateString(test,2);
    System.out.println(result);
} 

/** 
* 
* Method: LeftShiftOne(String s) 
* 
*/ 
@Test
public void testLeftShiftOne() throws Exception { 
//TODO: Test goes here... 
}

    /**
     *
     * Method: LeftRotateString_v2(String s)
     *
     */
    @Test
    public void testLeftRotateString_v2() throws Exception {
//TODO: Test goes here...
        String test = "abcdef";
        String result = Ch01_String.LeftRotateString_v2(test,2);
        System.out.println("LeftRotateString_v2 result : " + result);
    }

    /**
     *
     * Method: ReverseWords(String s)
     *
     */
    @Test
    public void testReverseWords() throws Exception {
//TODO: Test goes here...
        String test = "I am a student.";
        String result = Ch01_String.ReverseWords(test);
        System.out.println("ReversePharse result : " + result);
        assertEquals("student. a am I ",result);
    }

    /**
     *
     * Method: isSubStringContain(String s)
     *
     */
    @Test
    public void testisSubStringContain() throws Exception {
        //TODO: Test goes here...
        String a = "I am a student.";
        String b = "am";
        String c = "abc";
        assertEquals(true,Ch01_String.isSubStringContain(a,b));
        assertEquals(false,Ch01_String.isSubStringContain(a,c));

    }

}
