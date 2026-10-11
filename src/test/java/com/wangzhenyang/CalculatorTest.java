package com.wangzhenyang;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.*;

class CalculatorTest {
    Calculator c = new Calculator();

    @Test void addWorks() {
        assertEquals(4.0, c.eval("2", "+", "2"));
    }

    @Test void divideWorks() {
        assertEquals(2.0, c.eval("10", "/", "5"));
    }

    @Test void blankInputThrows() {
        assertThrows(IllegalArgumentException.class,
                () -> c.eval("", "+", "2"));
    }

    // 以下为自己新增的测试用例
    @Test void subtractWorks() {
        assertEquals(1.0, c.eval("5", "-", "4"));
    }

    @Test void multiplyWorks() {
        assertEquals(6.0, c.eval("2", "*", "3"));
    }

    @Test void badOperatorThrows() {
        assertThrows(IllegalArgumentException.class,
                () -> c.eval("2", "&", "2"));
    }

    @Test void divideByZeroThrows() {
        assertThrows(ArithmeticException.class,
                () -> c.eval("5", "/", "0"));
    }

    // Lab 4 新增：边界值与错误路径测试
    @Test void blankSecondOperandThrows() {
        assertThrows(IllegalArgumentException.class,
                () -> c.eval("2", "+", " "));
    }

    @Test void nonNumericInputThrows() {
        assertThrows(NumberFormatException.class,
                () -> c.eval("abc", "+", "2"));
    }

    @Test void zeroOperandsWork() {
        assertEquals(0.0, c.eval("0", "*", "100"));
    }

    @Test void negativeNumbersWork() {
        assertEquals(-7.0, c.eval("-3", "+", "-4"));
    }

    @Test void largeNumbersWork() {
        assertEquals(1.0E308, c.eval("1e308", "*", "1"), 1e293);
    }

    // 边界值测试：parseDouble 会不会静默接受 Infinity / NaN 这类非法输入？
    @Test void nonFiniteInputThrows() {
        assertThrows(IllegalArgumentException.class,
                () -> c.eval("Infinity", "+", "2"));
    }

    @Test void nanInputThrows() {
        assertThrows(IllegalArgumentException.class,
                () -> c.eval("NaN", "*", "2"));
    }

    @Test void nonFiniteSecondOperandThrows() {
        assertThrows(IllegalArgumentException.class,
                () -> c.eval("2", "+", "Infinity"));
    }

    // 参数化测试：同一个方法用多组输入反复运行
    @ParameterizedTest
    @CsvSource({
            "2, +, 3, 5.0",
            "10, -, 4, 6.0",
            "3, *, 7, 21.0",
            "20, /, 5, 4.0",
            "-8, /, 2, -4.0",
            "0, *, 100, 0.0",
            "1.5, +, 2.5, 4.0",
            "1e308, *, 1, 1.0E308"
    })
    void arithmeticWorks(String a, String op, String b, double expected) {
        assertEquals(expected, c.eval(a, op, b), 1e-9);
    }
}
