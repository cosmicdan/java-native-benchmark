package com.zakgof.jnbenchmark.jna;

import com.sun.jna.platform.win32.Kernel32;
import com.sun.jna.platform.win32.WinBase;
import com.zakgof.jnbenchmark.IBenchmarkSystemSeconds;

public class SecondsJnaBenchmark implements IBenchmarkSystemSeconds {

	private WinBase.SYSTEMTIME preallocatedSystemtime;

	public SecondsJnaBenchmark() {
		preallocatedSystemtime = new WinBase.SYSTEMTIME();
	}

	@Override
	public void dispose() {

	}

	@Override
	public short getSeconds_alloc() {
		WinBase.SYSTEMTIME systemtime = new WinBase.SYSTEMTIME();
		Kernel32.INSTANCE.GetSystemTime(systemtime);
		return systemtime.wSecond;
	}

	@Override
	public short getSeconds_preAlloc() {
		return 0;
	}
}
