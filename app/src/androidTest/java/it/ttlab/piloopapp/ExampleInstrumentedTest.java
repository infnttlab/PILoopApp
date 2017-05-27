package it.ttlab.piloopapp;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static org.junit.Assert.*;
import static android.support.test.espresso.Espresso.onView;

/**
 * Instrumentation test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {
    public static final double PI = 3.141592653589793d;
    public static final double EPSILON = 0.0000001d;
    public static final long STEPS1 = 10000;
    public static final long STEPS2 = 100000;
    public static final long STEPS3 = 1000000;
    public static final long NUMBER1 = 1000;
    public static final long PRIMES1 = 167;
    public static final long NUMBER2 = 10000;
    public static final long PRIMES2 = 1228;
    public static final long NUMBER3 = 100000;
    public static final long PRIMES3 = 9591;


    //@Rule
    //public ActivityTestRule<MainActivity> mActivityRule
    //        = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void useAppContext() throws Exception {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getTargetContext();
        assertEquals("it.ttlab.piloopapp", appContext.getPackageName());

        //MainActivity activity =new MainActivity();
        //assertTrue(Math.abs(Pi.piFromJNI(STEPS1,1) - PI) < EPSILON);
        //assertTrue(Math.abs(mActivityRule.getActivity().piFromJNI(STEPS1,1) - PI) < EPSILON);
    }

    @Test
    public void pi_isCorrect() throws Exception {
        assertTrue(Math.abs(Pi.piFromJNI(STEPS1,4) - PI) < EPSILON);
        assertTrue(Math.abs(Pi.piFromJNI(STEPS1,4) - PI) < EPSILON);
        assertTrue(Math.abs(Pi.piFromJNI(STEPS1,4) - PI) < EPSILON);
        assertTrue(Math.abs(Pi.getPI(STEPS1) - PI) < EPSILON);
        assertTrue(Math.abs(Pi.getPI(STEPS2) - PI) < EPSILON);
        assertTrue(Math.abs(Pi.getPI(STEPS3) - PI) < EPSILON);
    }

    @Test
    public void primes_isCorrect() throws Exception {
        assertEquals(PRIMES1,Primes.primesFromJNI(NUMBER1,4));
        assertEquals(PRIMES2,Primes.primesFromJNI(NUMBER2,4));
        assertEquals(PRIMES3,Primes.primesFromJNI(NUMBER3,4));
        assertEquals(PRIMES1,Primes.getPrimes(NUMBER1));
        assertEquals(PRIMES2,Primes.getPrimes(NUMBER2));
        assertEquals(PRIMES3,Primes.getPrimes(NUMBER3));
    }
        //@Test
    //public void testGreet() {
    //    onView(withId(R.id.PiTextView))
    //            .perform(typeText(String.valueOf("15")), closeSoftKeyboard());
    //}
}
