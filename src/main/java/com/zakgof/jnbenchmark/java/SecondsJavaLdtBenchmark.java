package com.zakgof.jnbenchmark.java;

import com.zakgof.jnbenchmark.IBenchmarkSystemSeconds;

import java.time.Clock;
import java.time.LocalDateTime;

/**
 * @author Daniel 'CosmicDan' Connolly
 */
public class SecondsJavaLdtBenchmark implements IBenchmarkSystemSeconds {
    @Override
    public void dispose() {

    }

    @Override
    public short getSeconds_alloc() {
        return (short) LocalDateTime.now(Clock.systemUTC()).getSecond();
    }

    @Override
    public short getSeconds_preAlloc() {
        return 0;
    }
}
