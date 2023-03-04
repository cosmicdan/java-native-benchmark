package com.zakgof.jnbenchmark.panama;

import com.zakgof.jnbenchmark.IBenchmarkSetter;

import java.lang.foreign.*;
import java.lang.invoke.MethodHandle;

/**
 * @author Daniel 'CosmicDan' Connolly
 */
public class SetterPanamaBenchmark implements IBenchmarkSetter {
    private final MemorySession session;
    private final MethodHandle handleSetLastError;

    public SetterPanamaBenchmark() {
        Linker linker = Linker.nativeLinker();
        session = MemorySession.openConfined(); // NOTE: must be closed when done!
        SymbolLookup kernel32 = SymbolLookup.libraryLookup("Kernel32", session);
        handleSetLastError = linker.downcallHandle(
                kernel32.lookup("SetLastError").orElseThrow(),
                FunctionDescriptor.ofVoid(ValueLayout.JAVA_INT));
    }

    @Override
    public void dispose() {
        session.close();
    }

    @Override
    public void setValue() {
        try {
            handleSetLastError.invokeExact(0);
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }
    }
}
