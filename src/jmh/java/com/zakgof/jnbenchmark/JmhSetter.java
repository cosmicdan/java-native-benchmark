package com.zakgof.jnbenchmark;

import org.openjdk.jmh.annotations.*;

import java.util.concurrent.TimeUnit;

/**
 * @author Daniel 'CosmicDan' Connolly
 */
@BenchmarkMode(Mode.AverageTime)
@Warmup(iterations = 5, time = 500, timeUnit = TimeUnit.MILLISECONDS)
@Measurement(iterations = 10, time = 500, timeUnit = TimeUnit.MILLISECONDS)
@State(Scope.Thread)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
public class JmhSetter extends BenchmarkBase {
    @Setup(Level.Trial)
    public void setup() {
        super.setup();
    }

    @TearDown
    public void dispose() {
        super.dispose();
    }

    @Benchmark
    public void java_preAlloc() throws InterruptedException {
        setterJava.setValue();
    }

    @Benchmark
    public void jni_javaCpp_preAlloc() throws InterruptedException {
        setterJavaCppStock.setValue();
    }

    @Benchmark
    public void panama_preAlloc() throws InterruptedException {
        setterPanama.setValue();
    }
}
