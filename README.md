# PILoopApp
This is the code repository of [PI Loop](https://play.google.com/store/apps/details?id=it.ttlab.piloopapp) an Android app by INFN TTLab (https://ttlab.infn.it) providing syntetic benchmarks built for OPEN-NEXT project (http://www.open-next.it/en/homepage-en)

**PI Loop** contains syntetic benchmarks ported by INFN TTLab from Linux OS (using GNU C library) to Android OS (using Bionic C library).


The original Linux C++ code is hosted in (https://github.com/infnttlab/benchmarks)

The benchmarks are coded in both JAVA and C++ with the latter using OpenMP API to exploit the multi-core architecture of the Android SoC (System-on-Chip)

Current syntetic benchmarks:
  * pi_loop (PI-computation in a fixed number of iterations)
  * primes  (computation of prime numbers within a given integers interval)


