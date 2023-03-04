package com.zakgof.jnbenchmark;

import java.util.concurrent.TimeUnit;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Level;
import org.openjdk.jmh.annotations.Measurement;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.annotations.TearDown;
import org.openjdk.jmh.annotations.Warmup;

@BenchmarkMode(Mode.AverageTime)
@Warmup(iterations = 5, time = 500, timeUnit = TimeUnit.MILLISECONDS)
@Measurement(iterations = 10, time = 500, timeUnit = TimeUnit.MILLISECONDS)
@State(Scope.Thread)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
public class JmhGetSystemTimeSeconds extends BenchmarkBase {

	@Setup(Level.Trial)
    public void setup() {
		super.setup();
    }

	@TearDown
	public void dispose() {
		super.dispose();
	}

	@Benchmark
	public void bridj_alloc() throws InterruptedException {
		secsBridj.getSeconds_alloc();
	}

	@Benchmark
	public void java_calendar_alloc() throws InterruptedException {
		secsJavaCalendar.getSeconds_alloc();
	}

	@Benchmark
	public void java_date_alloc() throws InterruptedException {
		secsJavaDate.getSeconds_alloc();
	}

	@Benchmark
	public void java_localDateTime_alloc() throws InterruptedException {
		secsJavaLdt.getSeconds_alloc();
	}

	@Benchmark
	public void java_systemMs_preAlloc() throws InterruptedException {
		secsJavaSystemMs.getSeconds_preAlloc();
	}

	@Benchmark
	public void jna_alloc() throws InterruptedException {
		secsJna.getSeconds_alloc();
	}

	@Benchmark
	public void jni_javaCpp_alloc() throws InterruptedException {
		secsJavaCppStock.getSeconds_alloc();
	}

	@Benchmark
	public void jni_javaCpp_preAlloc() throws InterruptedException {
		secsJavaCppStock.getSeconds_preAlloc();
	}
	
	@Benchmark
	public void jnaDirect_alloc() throws InterruptedException {
		secsJnaDirect.getSeconds_alloc();
	}

	@Benchmark
	public void jnr_alloc() throws InterruptedException {
		secsJnr.getSeconds_alloc();
	}

	@Benchmark
	public void panama_alloc() throws InterruptedException {
		secsPanama.getSeconds_alloc();
	}

	@Benchmark
	public void panama_preAlloc() throws InterruptedException {
		secsPanama.getSeconds_preAlloc();
	}

}
