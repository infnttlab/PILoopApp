#include <jni.h>
#include <string>
#include <chrono>
#include <omp.h>
#include <cmath>

using namespace std;

double mytime;

extern "C"
JNIEXPORT jdouble JNICALL
Java_it_ttlab_piloopapp_Pi_getJNIPI(
        JNIEnv *env,
        jobject /* this */,
        jlong num_steps,
        jint num_threads) {
    chrono::high_resolution_clock::time_point t01, t02;
    chrono::duration<double> dt1;
    //double start_time, run_time;
    //int verbose = 0;
    double pi, sum = 0.0;
    double step = 1.0 / (double) num_steps;
    omp_set_num_threads(num_threads);
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
JNIEXPORT jlong JNICALL
Java_it_ttlab_piloopapp_Primes_getJNIPrimes(
        JNIEnv *env,
        jobject /* this */,
        jlong tot,
        jint num_threads) {

    //double start_time, run_time;
    //milliseconds tot_ms;
    //if(tot > 1) count++;
    //for (int nthread = minthreads ; nthread <= maxthreads ; nthread++){
    //auto t0 = high_resolution_clock::now();
    //start_time = omp_get_wtime();
    unsigned int count = 0;
    omp_set_num_threads(num_threads);
    #pragma omp parallel for reduction(+:count)
    for (unsigned int i = 3; i <= tot; i++) {
        unsigned int s = sqrt(i);
        bool isprime = true;
        if (i % 2 == 0) isprime = false;
        for (unsigned int j = 3; (j <= s) && (isprime); j += 2)
            if (i % j == 0) isprime = false;
        if (isprime)
            count++;
    }
    //auto t1 = high_resolution_clock::now();
    //milliseconds tot_ms = std::chrono::duration_cast<milliseconds>(t1 - t0);
    //run_time = omp_get_wtime() - start_time;
    //tot_ms = std::chrono::duration_cast<milliseconds>(t1 - t0);
    //std::cout << "##########################################################\n";
    //std::cout << "time OMP in seconds is " << run_time << std::endl;
    //std::cout << "high_resolution_clock milliseconds = " << tot_ms.count() << std::endl;
    //std::cout << "N Threads = " << Nth << std::endl;
    //std::cout << "N Prime  = " << count << std::endl;
    //return (jlong) (unsigned long long) count;
    return (jlong) count;
    //}

}

extern "C"
JNIEXPORT jdouble JNICALL
Java_it_ttlab_piloopapp_MainActivity_timeFromJNI(
        JNIEnv *env,
        jobject) {
    return mytime;
}