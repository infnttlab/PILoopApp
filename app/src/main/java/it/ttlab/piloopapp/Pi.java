package it.ttlab.piloopapp;

/**
 * Created by Andrea Ferraro on 07/05/2017.
 */

class Pi {
    //private double x, pi, sum, step ;

    // Used to load the 'native-lib' library on application startup.
    static {
        System.loadLibrary("native-lib");
    }

    public static native double getJNIPI(long steps, int threads);

    static double getPI(long num_steps){
        double x, pi, sum =0;
        double step = 1.0d / (double) num_steps;
        for (int i=1;i<= num_steps; i++){
            x = (i-0.5)*step;
            sum = sum + 4.0/(1.0+x*x);
        }
        pi = step * sum;
        return pi;
    }

}
