package com.zakgof.jnbenchmark.jnr;

import com.zakgof.jnbenchmark.IBenchmarkSystemSeconds;

public class SecondsJnrBenchmark implements IBenchmarkSystemSeconds {
	
	private SYSTEMTIME preallocatedSystemTime;

	public SecondsJnrBenchmark() {
		preallocatedSystemTime = new SYSTEMTIME(Kernel32.RUNTIME);
	}

	@Override
	public void dispose() {

	}

	@Override
	public short getSeconds_alloc() {
		SYSTEMTIME systemtime = new SYSTEMTIME(Kernel32.RUNTIME);
		Kernel32.INSTANCE.GetSystemTime(systemtime);
		return systemtime.wSecond.shortValue();
	}

	@Override
	public short getSeconds_preAlloc() {
		return 0;
	}

}
