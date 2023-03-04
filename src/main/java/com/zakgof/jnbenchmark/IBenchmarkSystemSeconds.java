package com.zakgof.jnbenchmark;

/**
 * @author Daniel 'CosmicDan' Connolly
 */
public interface IBenchmarkSystemSeconds {
    void dispose();

    /**
     * Benchmark implementation for getting current seconds, from UTC.
     *
     * For native implementations, this consists of allocating memory and getting wSecond
     * via SYSTEMTIME struct (consists of eight shorts).
     *
     * For JVM implementations, this consists of temporary object creation.
     *
     * If neither of the above are possible, just return 0 and see if {@link #getSeconds_preAlloc}
     * is more suitable.
     *
     * @return Current seconds as returned from the Benchmark implementation.
     */
    short getSeconds_alloc();

    /**
     * Benchmark implementation for getting current seconds.
     *
     * For native implementations, this consists of using pre-allocated memory space
     *
     * For JVM implementations, this consists of NO object creation.
     *
     * If neither of the above are possible, just return 0 and see if {@link #getSeconds_alloc}
     * is more suitable.
     *
     * @return Current seconds as returned from the Benchmark implementation.
     */
    short getSeconds_preAlloc();
}
