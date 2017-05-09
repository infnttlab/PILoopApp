#include <jni.h>
#include <string>
#include <chrono>
#include <omp.h>

using namespace std;

double mytime;

extern "C"
JNIEXPORT jdouble JNICALL
Java_it_ttlab_piloopapp_MainActivity_piFromJNI(
        JNIEnv *env,
        jobject /* this */,
        jlong num_steps,
        jint num_threads) {
    double step;
    //long num_steps = 10000;
    int verbose = 0;
    omp_set_num_threads(num_threads);
        double pi, sum = 0.0;
        double start_time, run_time;
        chrono::high_resolution_clock::time_point t01, t02;
        chrono::duration<double> dt1;
        step = 1.0 / (double) num_steps;
        sum = 0.0;
        t01 = chrono::high_resolution_clock::now();
#pragma omp parallel
    {
        double x;
#pragma omp for reduction(+:sum)
        for (int i = 1; i <= num_steps; i++) {
            x = (i - 0.5) * step;
            sum = sum + 4.0 / (1.0 + x * x);
        }
    }
        pi = step * sum;
    t02 = chrono::high_resolution_clock::now();
    dt1 = chrono::duration_cast<chrono::duration<double>>(t02 - t01);
    mytime = dt1.count();
    return pi;
}

extern "C"
JNIEXPORT jdouble JNICALL
Java_it_ttlab_piloopapp_MainActivity_timeFromJNI(
        JNIEnv *env,
        jobject){
    return mytime;
}