package it.ttlab.piloopapp;

/**
 * Created by andre on 23/05/2017.
 */

public class Primes {

    static long getPrimes(long tot) {
        long count = 0, s;
        for (long i = 3; i <= tot; i++) {
            s = (long) Math.sqrt(i);
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
