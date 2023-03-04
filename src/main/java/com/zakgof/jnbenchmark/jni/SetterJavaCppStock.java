package com.zakgof.jnbenchmark.jni;

import com.zakgof.jnbenchmark.IBenchmarkSetter;
import org.bytedeco.systems.global.windows;

/**
 * @author Daniel 'CosmicDan' Connolly
 */
public class SetterJavaCppStock implements IBenchmarkSetter {
    @Override
    public void dispose() {

    }

    @Override
    public void setValue() {
        windows.SetLastError(0);
    }
}
