package com.zakgof.jnbenchmark.java;

import com.zakgof.jnbenchmark.IBenchmarkSetter;

/**
 * @author Daniel 'CosmicDan' Connolly
 */
public class SetterJavaBenchmark implements IBenchmarkSetter {
    private int someValue;

    @Override
    public void dispose() {

    }

    @Override
    public void setValue() {
        LastError.set(0);
    }
}
