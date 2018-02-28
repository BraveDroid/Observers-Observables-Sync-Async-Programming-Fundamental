package com.bravedroid.work;

import com.bravedroid.main.ProcessMethod;
import com.bravedroid.util.FibonacciSequence;

public class WorkSynchronouslyInManyThread implements ProcessMethod {
    @Override
    public void work() {
        doTask1();
        doTask2();
        doTask3();
        doTask4();
    }

    private void doTask1() {
        new Thread(new Runnable() {
            public void run() {
                print("task1", FibonacciSequence.generateNumber(35));
            }
        }).start();
    }

    private void doTask2() {
        new Thread(new Runnable() {
            public void run() {
                print("task2", FibonacciSequence.generateNumber(22));
            }
        }).start();
    }

    private void doTask3() {
        new Thread(new Runnable() {
            public void run() {
                print("task3", FibonacciSequence.generateNumber(10));
            }
        }).start();
    }

    private void doTask4() {
        new Thread(new Runnable() {
            public void run() {
                print("task4", FibonacciSequence.generateNumber(4));
            }
        }).start();
    }

    private void print(String taskName, int number) {
        System.out.println(taskName + " " + number);
    }
}
