package com.zakgof.jnbenchmark;

import java.util.concurrent.TimeUnit;

import org.openjdk.jmh.annotations.*;

import com.zakgof.jnbenchmark.bridj.BridjBenchmark;
import com.zakgof.jnbenchmark.jna.JnaBenchmark;
import com.zakgof.jnbenchmark.jna.JnaDirectBenchmark;
import com.zakgof.jnbenchmark.jni.JavaCppStock;
import com.zakgof.jnbenchmark.jnr.JnrBenchmark;
import com.zakgof.jnbenchmark.panama.PanamaBenchmark;

@BenchmarkMode(Mode.AverageTime)
@Warmup(iterations = 5, time = 500, timeUnit = TimeUnit.MILLISECONDS)
@Measurement(iterations = 10, time = 500, timeUnit = TimeUnit.MILLISECONDS)
@State(Scope.Thread)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
public class JmhCallOnly {

	private PanamaBenchmark panama;
	private JavaCppStock javacppjni;
	private BridjBenchmark bridj;
	private JnaBenchmark jna;
	private JnaDirectBenchmark jnaDirect;
	private JnrBenchmark jnr;

	@Setup(Level.Trial)
	public void setup() {
		panama = new PanamaBenchmark();
		javacppjni = new JavaCppStock();
		bridj = new BridjBenchmark();
		jna = new JnaBenchmark();
		jnaDirect = new JnaDirectBenchmark();
		jnr = new JnrBenchmark();
	}

	@TearDown
	public void dispose() {
		panama.dispose();
	}

	@Benchmark
	public void jni_javacpp() throws InterruptedException {
		javacppjni.callOnly();
	}

	@Benchmark
	public void panama() throws InterruptedException {
		panama.callOnly();
	}

	@Benchmark
	public void bridj() throws InterruptedException {
		bridj.callOnly();
	}

	@Benchmark
	public void jna() throws InterruptedException {
		jna.callOnly();
	}
	
	@Benchmark
	public void jna_direct() throws InterruptedException {
		jnaDirect.callOnly();
	}

	@Benchmark
	public void jnr() throws InterruptedException {
		jnr.callOnly();
	}

}
