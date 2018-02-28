package com.bravedroid.work;

import com.bravedroid.main.ProcessMethod;
import com.bravedroid.util.FibonacciSequence;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class WorkAsynchronouslyInManyThread implements ProcessMethod {
    private TaskWorkerObservable taskWorkerObservable;

    public WorkAsynchronouslyInManyThread() {
        taskWorkerObservable = new TaskWorkerObservable();
        taskWorkerObservable.subscribeToTask1(task1Listener);
        taskWorkerObservable.subscribeToTask2(task2Listener);
        taskWorkerObservable.subscribeToTask3(task3Listener);
        taskWorkerObservable.subscribeToTask4(task4Listener);

    }

    private TaskWorkerObservable.Task1Listener task1Listener = new TaskWorkerObservable.Task1Listener() {
        @Override
        public void onTask1Result(int result) {
            print("task1", result);
        }
    };
    private TaskWorkerObservable.Task2Listener task2Listener = new TaskWorkerObservable.Task2Listener() {
        @Override
        public void onTask2Result(int result) {
            print("task2", result);
        }
    };
    private TaskWorkerObservable.Task3Listener task3Listener = new TaskWorkerObservable.Task3Listener() {
        @Override
        public void onTask3Result(int result) {
            print("task3", result);
        }
    };
    private TaskWorkerObservable.Task4Listener task4Listener = new TaskWorkerObservable.Task4Listener() {
        @Override
        public void onTask4Result(int result) {
            print("task4", result);
        }
    };

    @Override
    public void work() {
        taskWorkerObservable.startAsyncTasks();
    }

    private void print(String taskName, int number) {
        System.out.println(taskName + " " + number);
    }

    private static class TaskWorkerObservable {
        private Task1Listener task1Listener;
        private Task2Listener task2Listener;
        private Task3Listener task3Listener;
        private Task4Listener task4Listener;

        public void subscribeToTask1(Task1Listener task1Listener) {
            this.task1Listener = task1Listener;
        }

        public void subscribeToTask2(Task2Listener task2Listener) {
            this.task2Listener = task2Listener;
        }

        public void subscribeToTask3(Task3Listener task3Listener) {
            this.task3Listener = task3Listener;
        }

        public void subscribeToTask4(Task4Listener task4Listener) {
            this.task4Listener = task4Listener;
        }

        public void startAsyncTasks() {
            new Thread(new Runnable() {
                public void run() {
                    //stimulate random event that happen in 3 seconds
                    final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
                    scheduler.schedule(new Runnable() {
                        @Override
                        public void run() {
                            if (task1Listener != null) task1Listener.onTask1Result(FibonacciSequence.generateNumber(5));
                            scheduler.shutdown();
                        }
                    }, 3, TimeUnit.SECONDS);

                    if (task2Listener != null) task2Listener.onTask2Result(FibonacciSequence.generateNumber(22));
                    if (task3Listener != null) task3Listener.onTask3Result(FibonacciSequence.generateNumber(10));
                    if (task4Listener != null) task4Listener.onTask4Result(FibonacciSequence.generateNumber(4));
                }
            }).start();

            new Thread(new Runnable() {
                public void run() {
                    if (task1Listener != null) task1Listener.onTask1Result(FibonacciSequence.generateNumber(5));
                    //stimulate random event that happen in 3 seconds
                    final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
                    scheduler.schedule(new Runnable() {
                        @Override
                        public void run() {
                            if (task2Listener != null)
                                task2Listener.onTask2Result(FibonacciSequence.generateNumber(22));
                            scheduler.shutdown();
                        }
                    }, 10, TimeUnit.SECONDS);

                    if (task3Listener != null) task3Listener.onTask3Result(FibonacciSequence.generateNumber(10));
                    if (task4Listener != null) task4Listener.onTask4Result(FibonacciSequence.generateNumber(4));
                }
            }).start();

            new Thread(new Runnable() {
                public void run() {
                    if (task1Listener != null) task1Listener.onTask1Result(FibonacciSequence.generateNumber(5));
                    if (task2Listener != null) task2Listener.onTask2Result(FibonacciSequence.generateNumber(22));
                    //stimulate random event that happen in 3 seconds
                    final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
                    scheduler.schedule(new Runnable() {
                        @Override
                        public void run() {
                            if (task3Listener != null)
                                task3Listener.onTask3Result(FibonacciSequence.generateNumber(10));
                            scheduler.shutdown();
                        }
                    }, 2, TimeUnit.SECONDS);

                    if (task4Listener != null) task4Listener.onTask4Result(FibonacciSequence.generateNumber(4));
                }
            }).start();

            new Thread(new Runnable() {
                public void run() {
                    if (task1Listener != null) task1Listener.onTask1Result(FibonacciSequence.generateNumber(5));
                    if (task2Listener != null) task2Listener.onTask2Result(FibonacciSequence.generateNumber(22));
                    if (task3Listener != null) task3Listener.onTask3Result(FibonacciSequence.generateNumber(10));
                    //stimulate random event that happen in 3 seconds
                    final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
                    scheduler.schedule(new Runnable() {
                        @Override
                        public void run() {
                            if (task4Listener != null) task4Listener.onTask4Result(FibonacciSequence.generateNumber(4));
                            scheduler.shutdown();
                        }
                    }, 5, TimeUnit.SECONDS);


                }
            }).start();
        }

        interface Task1Listener {
            void onTask1Result(int result);
        }

        interface Task2Listener {
            void onTask2Result(int result);
        }

        interface Task3Listener {
            void onTask3Result(int result);
        }

        interface Task4Listener {
            void onTask4Result(int result);
        }
    }
}
