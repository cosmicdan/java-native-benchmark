package com.zakgof.jnbenchmark.java;

import com.zakgof.jnbenchmark.IBenchmarkSystemSeconds;

import java.util.Calendar;

/**
 * @author Daniel 'CosmicDan' Connolly
 */
public class SecondsJavaCalendarBenchmark implements IBenchmarkSystemSeconds {
    @Override
    public void dispose() {

    }

    @Override
    public short getSeconds_alloc() {
        return (short) Calendar.getInstance().get(Calendar.SECOND);
    }

    @Override
    public short getSeconds_preAlloc() {
        return 0;
    }
}
