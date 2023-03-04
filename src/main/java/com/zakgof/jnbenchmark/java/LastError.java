package com.zakgof.jnbenchmark.java;

/**
 * @author Daniel 'CosmicDan' Connolly
 */
public class LastError {
    private static int LAST_ERROR = 0;

    public static int get() {
        return LAST_ERROR;
    }

    public static void set(final int newError) {
        LAST_ERROR = newError;
    }
}
