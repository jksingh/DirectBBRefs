# GC tuning for cache backed by DirectByteBuffers 
This is the repo for test code that is used to simulate cache.

It has one **Main.java** class. It keeps a cache of 10 million DirectByteBuffer object. In each iteration it updates 1024 of them creating some garbage objects that needs cleanup. To reduce machine RAM needed for these tests, DirectByteBuffer size is set to only 4 Bytes.

To run this,
* edit bin/run.sh with suitable Java Garbage Collection Flags
* and run bin/run.sh to compile and run Main. Test is run for 5 minutes.

In results folder, you can find the result of running tests on i2.xlarge instance of Amazon Web Service (AWS) which has 4 CPU cores and 30.5GB RAM.
* g1-gc.log.0.current is result of JVM Flag -XX:+UseG1GC
* g1-ref-proc-par-gc.log.0.current is for -XX:+UseG1GC -XX:+ParallelRefProcEnabled
* par-gc.log.0.current is for default server ParallelGC without any flags.
* Snapshots of GCViewer used to compare these logs.
