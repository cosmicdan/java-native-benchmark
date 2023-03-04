package com.zakgof.jnbenchmark.java;

import com.zakgof.jnbenchmark.IBenchmarkSystemSeconds;

import java.util.Date;

/**
 * @author Daniel 'CosmicDan' Connolly
 */
public class SecondsJavaDateBenchmark implements IBenchmarkSystemSeconds {
    @Override
    public void dispose() {

    }

    @Override
    public short getSeconds_alloc() {
        //noinspection deprecation
        return (short) new Date().getSeconds();
    }

    @Override
    public short getSeconds_preAlloc() {
        return 0;
    }
}
