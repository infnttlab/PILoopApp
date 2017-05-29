package it.ttlab.piloopapp;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */

public class ExampleUnitTest {
    private static final double PI = 3.141592653589793d;
    private static final double EPSILON = 0.000000001d;
    private static final long STEPS1 = 10000;
    private static final long STEPS2 = 100000;
    private static final long STEPS3 = 1000000;
    private static final long NUMBER1 = 1000;
    private static final long PRIMES1 = 167;
    private static final long NUMBER2 = 10000;
    private static final long PRIMES2 = 1228;
    private static final long NUMBER3 = 100000;
    private static final long PRIMES3 = 9591;

    @Test
    public void pi_isCorrect() throws Exception {
        assertTrue(Math.abs(Pi.getPI(STEPS1) - PI) < EPSILON);
        assertTrue(Math.abs(Pi.getPI(STEPS2) - PI) < EPSILON);
        assertTrue(Math.abs(Pi.getPI(STEPS3) - PI) < EPSILON);
    }

    @Test
    public void primes_isCorrect() throws Exception {
        assertEquals(PRIMES1,Primes.getPrimes(NUMBER1));
        assertEquals(PRIMES2,Primes.getPrimes(NUMBER2));
        assertEquals(PRIMES3,Primes.getPrimes(NUMBER3));
    }
}