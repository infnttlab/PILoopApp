package it.ttlab.piloopapp;

/**
 * Created by andre on 07/05/2017.
 */

public class Pi {
    //private double x, pi, sum, step ;

    static double getPI(long num_steps){
        double x=0, pi=0, sum =0;
        double step = 1.0d / (double) num_steps;
        for (int i=1;i<= num_steps; i++){
            x = (i-0.5)*step;
            sum = sum + 4.0/(1.0+x*x);
        }
        pi = step * sum;
        return pi;
    }

}
