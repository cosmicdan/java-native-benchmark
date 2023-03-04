package com.zakgof.jnbenchmark.jni;

import com.zakgof.jnbenchmark.IBenchmarkGetter;
import org.bytedeco.systems.global.windows;

/**
 * @author Daniel 'CosmicDan' Connolly
 */
public class GetterJavaCppStock implements IBenchmarkGetter {
    @Override
    public void dispose() {

    }

    @Override
    public int getValue() {
        return windows.GetLastError();
    }
}
