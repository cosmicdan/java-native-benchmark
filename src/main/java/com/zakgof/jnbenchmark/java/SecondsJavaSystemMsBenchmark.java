package com.zakgof.jnbenchmark.java;

import com.zakgof.jnbenchmark.IBenchmarkSystemSeconds;

/**
 * Benchmark for getting system seconds via System.currentTimeMillis().
 *
 * We are getting UTC timezone from everything else already, so this is a good "should be the
 * fastest" benchmark.
 *
 * This does perform a modulo check every call; but tests have shown that this is faster
 * than manually comparing the current epoch-second to a cached value.
 *
 * @author Daniel 'CosmicDan' Connolly
 */
public class SecondsJavaSystemMsBenchmark implements IBenchmarkSystemSeconds {
    private long lastSecondsSinceEpoch = 0;
    private short lastSeconds = 0;

    @Override
    public void dispose() {

    }

    @Override
    public short getSeconds_alloc() {
        return 0;
    }

    @Override
    public short getSeconds_preAlloc() {
        return (short) (System.currentTimeMillis() / 1000 % 60);
    }
}
