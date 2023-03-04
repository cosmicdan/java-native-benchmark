package com.zakgof.jnbenchmark.java;

import com.zakgof.jnbenchmark.IBenchmarkGetter;

/**
 * @author Daniel 'CosmicDan' Connolly
 */
public class GetterJavaBenchmark implements IBenchmarkGetter {
    @Override
    public void dispose() {

    }

    @Override
    public int getValue() {
        return LastError.get();
    }
}
