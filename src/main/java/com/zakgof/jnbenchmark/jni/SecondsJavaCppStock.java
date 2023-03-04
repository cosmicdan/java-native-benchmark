package com.zakgof.jnbenchmark.jni;

import com.zakgof.jnbenchmark.IBenchmarkSystemSeconds;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.systems.global.windows;
import org.bytedeco.systems.windows.SYSTEMTIME;

public class SecondsJavaCppStock implements IBenchmarkSystemSeconds {
	// precalculated as new SYSTEMTIME().sizeof();
	private static final long SYSTEMTIME_STRUCT_LENGTH = 16;

	private final SYSTEMTIME systemTimePreAlloc;

	public SecondsJavaCppStock() {
		systemTimePreAlloc = mallocSystemTime();
	}

	@Override
	public void dispose() {
		systemTimePreAlloc.close();
	}

	@Override
	public short getSeconds_alloc() {
		SYSTEMTIME systemTime = mallocSystemTime();
		windows.GetSystemTime(systemTime);
		short systemTimeSeconds = systemTime.wSecond();
		systemTime.close();
		return systemTimeSeconds;
	}

	@Override
	public short getSeconds_preAlloc() {
		windows.GetSystemTime(systemTimePreAlloc);
		return systemTimePreAlloc.wSecond();
	}

	private SYSTEMTIME mallocSystemTime() {
		Pointer systemTimePointer = Pointer.malloc(SYSTEMTIME_STRUCT_LENGTH);
		SYSTEMTIME systemTime = new SYSTEMTIME(Pointer.malloc(SYSTEMTIME_STRUCT_LENGTH));
		systemTimePointer.close();
		return systemTime;
	}
}
