package it.ttlab.piloopapp;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */

public class ExampleUnitTest {
    public static final double PI = 3.141592653589793d;
    public static final double EPSILON = 0.000000001d;
    public static final long STEPS1 = 10000;
    public static final long STEPS2 = 100000;
    public static final long STEPS3 = 1000000;
    public static final long NUMBER1 = 1000;
    public static final long PRIMES1 = 167;
    public static final long NUMBER2 = 10000;
    public static final long PRIMES2 = 1228;
    public static final long NUMBER3 = 100000;
    public static final long PRIMES3 = 9591;

    @Test
    public void pi_isCorrect() throws Exception {
        //System.setProperty("java.library.path", "... path to the library .../libs/x86");
        //assertEquals(4, 2 + 2);
        //MainActivity mainActivity = new MainActivity();
        assertTrue(Math.abs(Pi.getPI(STEPS1) - PI) < EPSILON);
        assertTrue(Math.abs(Pi.getPI(STEPS2) - PI) < EPSILON);
        assertTrue(Math.abs(Pi.getPI(STEPS3) - PI) < EPSILON);
        //assertTrue(Math.abs(Pi.getPI(STEPS3) - PI) < EPSILON);
    }

    @Test
    public void primes_isCorrect() throws Exception {
        assertEquals(PRIMES1,Primes.getPrimes(NUMBER1));
        assertEquals(PRIMES2,Primes.getPrimes(NUMBER2));
        assertEquals(PRIMES3,Primes.getPrimes(NUMBER3));
    }
}