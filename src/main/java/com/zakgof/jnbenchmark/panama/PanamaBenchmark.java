package com.zakgof.jnbenchmark.panama;

import jdk.internal.foreign.PlatformLayouts;

import java.lang.foreign.*;
import java.lang.invoke.MethodHandle;
import java.lang.invoke.VarHandle;

public class PanamaBenchmark {
	private final Linker linker;
	private final MemoryLayout structSYSTEMTIME;
	private final VarHandle wSecondGetter;

	// for cached test(s); allWithPreLayout for Panama
	private final MemorySession sessionCached;
	private final MethodHandle getSystemTimeCached;
	private final MemorySegment systemTimeOutPointerCached;

	public PanamaBenchmark() {
		linker = Linker.nativeLinker();
		structSYSTEMTIME = MemoryLayout.structLayout(
				PlatformLayouts.Win64.C_SHORT.withName("wYear"),
				PlatformLayouts.Win64.C_SHORT.withName("wMonth"),
				PlatformLayouts.Win64.C_SHORT.withName("wDayOfWeek"),
				PlatformLayouts.Win64.C_SHORT.withName("wDay"),
				PlatformLayouts.Win64.C_SHORT.withName("wHour"),
				PlatformLayouts.Win64.C_SHORT.withName("wMinute"),
				PlatformLayouts.Win64.C_SHORT.withName("wSecond"),
				PlatformLayouts.Win64.C_SHORT.withName("wMilliseconds")
		).withName("SYSTEMTIME");
		wSecondGetter = structSYSTEMTIME.varHandle(MemoryLayout.PathElement.groupElement("wSecond"));

		// for cached test(s)
		sessionCached = MemorySession.openConfined(); // NOTE: must be closed when done!
		getSystemTimeCached = getMethodHandle(getKernel32(sessionCached));
		systemTimeOutPointerCached = sessionCached.allocate(structSYSTEMTIME);
	}

	public void dispose() {
		sessionCached.close();
	}

	private SymbolLookup getKernel32(MemorySession session) {
		return SymbolLookup.libraryLookup("Kernel32", session);
	}

	private MethodHandle getMethodHandle(SymbolLookup kernel32) {
		return linker.downcallHandle(
				kernel32.lookup("GetSystemTime").orElseThrow(),
				FunctionDescriptor.ofVoid(ValueLayout.ADDRESS));
	}

	/**
	 * Perform a complete "Get Seconds" call, that is with all references, holders and handles constructed then disposed.
	 */
	public short all() {
		try {
			MemorySegment systemTimeOutPointer = sessionCached.allocate(structSYSTEMTIME);
			getSystemTimeCached.invokeExact((Addressable) systemTimeOutPointer);
			return (short) wSecondGetter.get(systemTimeOutPointer);
		} catch (Throwable e) {
			throw new RuntimeException(e);
		}
	}

	public short allWithPreLayout() {
		try {
			getSystemTimeCached.invokeExact((Addressable) systemTimeOutPointerCached);
			return (short) wSecondGetter.get(systemTimeOutPointerCached);
		} catch (Throwable e) {
			throw new RuntimeException(e);
		}
	}

	public void callOnly() {
		try {
			getSystemTimeCached.invokeExact((Addressable) systemTimeOutPointerCached);
		} catch (Throwable e) {
			throw new RuntimeException(e);
		}
	}
}
