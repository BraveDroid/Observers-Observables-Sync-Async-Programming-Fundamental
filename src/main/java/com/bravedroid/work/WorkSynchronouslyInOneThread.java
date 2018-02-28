package com.bravedroid.work;

import com.bravedroid.main.ProcessMethod;
import com.bravedroid.util.FibonacciSequence;

public class WorkSynchronouslyInOneThread implements ProcessMethod {
    @Override
    public void work() {
        doTask1();
        doTask2();
        doTask3();
        doTask4();
    }

    private void doTask1() {
        print("task1", FibonacciSequence.generateNumber(5));
    }

    private void doTask2() {
        print("task2", FibonacciSequence.generateNumber(22));
    }

    private void doTask3() {
        print("task3", FibonacciSequence.generateNumber(10));
    }

    private void doTask4() {
        print("task4", FibonacciSequence.generateNumber(4));
    }

    private void print(String taskName, int number) {
        System.out.println(taskName + " " + number);
    }
}
