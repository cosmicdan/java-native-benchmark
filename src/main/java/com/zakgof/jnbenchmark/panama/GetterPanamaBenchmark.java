package com.zakgof.jnbenchmark.panama;

import com.zakgof.jnbenchmark.IBenchmarkGetter;

import java.lang.foreign.*;
import java.lang.invoke.MethodHandle;

/**
 * @author Daniel 'CosmicDan' Connolly
 */
public class GetterPanamaBenchmark implements IBenchmarkGetter {
    private final MemorySession session;
    private final MethodHandle handleGetLastError;

    public GetterPanamaBenchmark() {
        Linker linker = Linker.nativeLinker();
        session = MemorySession.openConfined(); // NOTE: must be closed when done!
        SymbolLookup kernel32 = SymbolLookup.libraryLookup("Kernel32", session);
        handleGetLastError = linker.downcallHandle(
                kernel32.lookup("GetLastError").orElseThrow(),
                FunctionDescriptor.of(ValueLayout.JAVA_INT));
    }

    @Override
    public void dispose() {
        session.close();
    }

    @Override
    public int getValue() {
        try {
            return (int) handleGetLastError.invokeExact();
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }
    }
}
