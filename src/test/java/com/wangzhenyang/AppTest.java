package com.wangzhenyang;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

// 静态分析发现 App 入口的两个缺陷（不校验参数个数、异常裸奔）后，修复并补的回归测试
class AppTest {
    private final ByteArrayOutputStream out = new ByteArrayOutputStream();
    private final ByteArrayOutputStream err = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    private final PrintStream originalErr = System.err;

    @AfterEach
    void restoreStreams() {
        System.setOut(originalOut);
        System.setErr(originalErr);
    }

    private void captureStreams() {
        out.reset();
        err.reset();
        System.setOut(new PrintStream(out));
        System.setErr(new PrintStream(err));
    }

    @Test void printsResultForTwoPlusTwo() {
        captureStreams();
        App.main(new String[]{"2", "+", "2"});
        assertEquals("4.0", out.toString().trim());
    }

    @Test void printsUsageWhenTooFewArgs() {
        captureStreams();
        App.main(new String[]{"2", "+"});
        assertTrue(err.toString().contains("Usage:"));
    }

    @Test void printsFriendlyErrorOnDivisionByZero() {
        captureStreams();
        App.main(new String[]{"5", "/", "0"});
        assertEquals("Error: division by zero", err.toString().trim());
    }
}
