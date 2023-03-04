package com.zakgof.jnbenchmark;

/**
 * @author Daniel 'CosmicDan' Connolly
 */
public interface IBenchmarkGetter {
    void dispose();

    /**
     * Call something that just gets an int value.
     *
     * For native implementations, calls GetErrorCode.
     *
     * For JVM implementations, calls LastError.get.
     */
    int getValue();
}
