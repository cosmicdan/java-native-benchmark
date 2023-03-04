package com.zakgof.jnbenchmark.jna;

import com.sun.jna.Native;
import com.sun.jna.platform.win32.WinBase;
import com.sun.jna.platform.win32.WinBase.SYSTEMTIME;
import com.zakgof.jnbenchmark.IBenchmarkSystemSeconds;

public class SecondsJnaDirectBenchmark implements IBenchmarkSystemSeconds {

	static {
		Native.register("kernel32");
	}

	private WinBase.SYSTEMTIME preallocatedSystemtime;

	native static void GetSystemTime(SYSTEMTIME lpSystemTime);

	public SecondsJnaDirectBenchmark() {
		preallocatedSystemtime = new WinBase.SYSTEMTIME();
	}

	@Override
	public void dispose() {

	}

	@Override
	public short getSeconds_alloc() {
		WinBase.SYSTEMTIME systemtime = new WinBase.SYSTEMTIME();
		GetSystemTime(systemtime);
		return systemtime.wSecond;
	}

	@Override
	public short getSeconds_preAlloc() {
		return 0;
	}

}
