package com.zakgof.jnbenchmark.bridj;

import com.zakgof.jnbenchmark.IBenchmarkSystemSeconds;
import org.bridj.Pointer;

public class SecondsBridjBenchmark implements IBenchmarkSystemSeconds {
	
	private Pointer<SYSTEMTIME> preallocatedPointer;
	private static Kernel32 kernel32 = new Kernel32();

	public SecondsBridjBenchmark() {
		SYSTEMTIME systemtime = new SYSTEMTIME();
		preallocatedPointer = Pointer.getPointer(systemtime);
	}
	
	public void callOnly() {
		kernel32.getSystemTime(preallocatedPointer);
	}

	@Override
	public void dispose() {

	}

	@Override
	public short getSeconds_alloc() {
		SYSTEMTIME systime = new SYSTEMTIME();
		kernel32.getSystemTime(Pointer.getPointer(systime));
		return systime.wSecond();
	}

	@Override
	public short getSeconds_preAlloc() {
		return 0;
	}
}
