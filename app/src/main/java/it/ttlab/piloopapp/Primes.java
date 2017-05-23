package it.ttlab.piloopapp;

/**
 * Created by andre on 23/05/2017.
 */

public class Primes {
    static long count = 0;
    static long s;

    static long getPrimes(long tot) {
        for (long i = 3; i <= tot; i++) {
            long s = (long) Math.sqrt(i);
            boolean isprime = true;
            if (i % 2 == 0)
                isprime = false;
            for (long j = 3; (j <= s) && (isprime); j += 2)
                if (i % j == 0)
                    isprime = false;
            if (isprime)
                count++;
        }
        return count;
    }
}
