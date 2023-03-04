package com.zakgof.jnbenchmark;

/**
 * @author Daniel 'CosmicDan' Connolly
 */
public interface IBenchmarkSetter {
    void dispose();
    
    /**
     * Call something that just sets a value.
     *
     * For native implementations, calls SetErrorCode.
     *
     * For JVM implementations, calls LastError.set.
     */
    void setValue();
}
