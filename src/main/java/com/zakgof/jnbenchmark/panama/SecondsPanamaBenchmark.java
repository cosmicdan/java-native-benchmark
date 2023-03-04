package com.zakgof.jnbenchmark.panama;

import com.zakgof.jnbenchmark.IBenchmarkSystemSeconds;
import jdk.internal.foreign.PlatformLayouts;

import java.lang.foreign.*;
import java.lang.invoke.MethodHandle;
import java.lang.invoke.VarHandle;

public class SecondsPanamaBenchmark implements IBenchmarkSystemSeconds {
	private final Linker linker;
	private final MemoryLayout structSYSTEMTIME;
	private final VarHandle wSecondGetter;
	private final MethodHandle handleGetSystemTime;
	private final MemorySession session;

	private final MemorySegment outSystemTimePrealloc;

	public SecondsPanamaBenchmark() {
		linker = Linker.nativeLinker();
		// https://learn.microsoft.com/en-us/windows/win32/api/minwinbase/ns-minwinbase-systemtime
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
		// getter for wSecond of the struct (we don't care about other values)
		wSecondGetter = structSYSTEMTIME.varHandle(MemoryLayout.PathElement.groupElement("wSecond"));
		session = MemorySession.openConfined(); // NOTE: must be closed when done!
		handleGetSystemTime = getMethodHandle(getKernel32(session));

		outSystemTimePrealloc = session.allocate(structSYSTEMTIME);
	}

	@Override
	public void dispose() {
		session.close();
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
	@Override
	public short getSeconds_alloc() {
		try {
			MemorySegment systemTimeOutPointer = session.allocate(structSYSTEMTIME);
			handleGetSystemTime.invokeExact((Addressable) systemTimeOutPointer);
			return (short) wSecondGetter.get(systemTimeOutPointer);
		} catch (Throwable e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public short getSeconds_preAlloc() {
		try {
			handleGetSystemTime.invokeExact((Addressable) outSystemTimePrealloc);
			return (short) wSecondGetter.get(outSystemTimePrealloc);
		} catch (Throwable e) {
			throw new RuntimeException(e);
		}
	}
}
