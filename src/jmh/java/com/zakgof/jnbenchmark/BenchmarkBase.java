package com.zakgof.jnbenchmark;

import com.zakgof.jnbenchmark.bridj.SecondsBridjBenchmark;
import com.zakgof.jnbenchmark.java.*;
import com.zakgof.jnbenchmark.jna.SecondsJnaBenchmark;
import com.zakgof.jnbenchmark.jna.SecondsJnaDirectBenchmark;
import com.zakgof.jnbenchmark.jni.GetterJavaCppStock;
import com.zakgof.jnbenchmark.jni.SecondsJavaCppStock;
import com.zakgof.jnbenchmark.jni.SetterJavaCppStock;
import com.zakgof.jnbenchmark.jnr.SecondsJnrBenchmark;
import com.zakgof.jnbenchmark.panama.GetterPanamaBenchmark;
import com.zakgof.jnbenchmark.panama.SecondsPanamaBenchmark;
import com.zakgof.jnbenchmark.panama.SetterPanamaBenchmark;

/**
 * @author Daniel 'CosmicDan' Connolly
 */
public class BenchmarkBase {
    private static int lastError = 0; // used by getter/setter benchmarks

    protected SecondsBridjBenchmark secsBridj;
    protected SecondsJavaCalendarBenchmark secsJavaCalendar;
    protected SecondsJavaDateBenchmark secsJavaDate;
    protected SecondsJavaLdtBenchmark secsJavaLdt;
    protected SecondsJavaSystemMsBenchmark secsJavaSystemMs;
    protected SecondsJnaBenchmark secsJna;
    protected SecondsJnaDirectBenchmark secsJnaDirect;
    protected SecondsJavaCppStock secsJavaCppStock;
    protected SecondsJnrBenchmark secsJnr;
    protected SecondsPanamaBenchmark secsPanama;

    protected SetterJavaBenchmark setterJava;
    protected SetterJavaCppStock setterJavaCppStock;
    protected SetterPanamaBenchmark setterPanama;

    protected GetterJavaBenchmark getterJava;
    protected GetterJavaCppStock getterJavaCppStock;
    protected GetterPanamaBenchmark getterPanama;

    protected void setup() {
        secsBridj = new SecondsBridjBenchmark();
        secsJavaCalendar = new SecondsJavaCalendarBenchmark();
        secsJavaDate = new SecondsJavaDateBenchmark();
        secsJavaLdt = new SecondsJavaLdtBenchmark();
        secsJavaSystemMs = new SecondsJavaSystemMsBenchmark();
        secsJna = new SecondsJnaBenchmark();
        secsJnaDirect = new SecondsJnaDirectBenchmark();
        secsJavaCppStock = new SecondsJavaCppStock();
        secsJnr = new SecondsJnrBenchmark();
        secsPanama = new SecondsPanamaBenchmark();

        setterJava = new SetterJavaBenchmark();
        setterJavaCppStock = new SetterJavaCppStock();
        setterPanama = new SetterPanamaBenchmark();

        getterJava = new GetterJavaBenchmark();
        getterJavaCppStock = new GetterJavaCppStock();
        getterPanama = new GetterPanamaBenchmark();
    }

    protected void dispose() {
        secsBridj.dispose();
        secsJavaCalendar.dispose();
        secsJavaDate.dispose();
        secsJavaLdt.dispose();
        secsJavaSystemMs.dispose();
        secsJna.dispose();
        secsJnaDirect.dispose();
        secsJavaCppStock.dispose();
        secsJnr.dispose();
        secsPanama.dispose();

        setterJava.dispose();
        setterJavaCppStock.dispose();
        setterPanama.dispose();

        getterJava.dispose();
        getterJavaCppStock.dispose();
        getterPanama.dispose();
    }

    public static int getLastError() {
        return lastError;
    }

    public static void setLastError(final int lastErrorIn) {
        lastError = lastErrorIn;
    }
}
