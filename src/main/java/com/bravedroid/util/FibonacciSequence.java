package com.bravedroid.util;

public class FibonacciSequence {
    public static int generateNumber(int number) {
        if (number == 0) {
            return 0;
        }
        if (number == 1) {
            return 1;
        }
        return generateNumber(number - 1) + generateNumber(number - 2);
    }
}
