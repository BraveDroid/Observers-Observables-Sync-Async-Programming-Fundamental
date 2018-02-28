package com.bravedroid.work;

import com.bravedroid.main.ProcessMethod;
import com.bravedroid.util.Task;

import java.util.*;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class WorkAsynchronouslyInOneThread implements ProcessMethod {
    private TaskWorker taskWorker;

    public WorkAsynchronouslyInOneThread() {
        taskWorker = new TaskWorker();
    }


    @Override
    public void work() {
        taskWorker.startAsyncTasks();
    }


    private static class TaskWorker {


        private PrioritisedTaskList prioritisedTaskList;

        private TaskWorker() {
            prioritisedTaskList = new PrioritisedTaskList();

            final Task task2 = new Task("Task_2", 7, 4, prioritisedTaskList);
            final Task task3 = new Task("Task_3", 18, 5, prioritisedTaskList);
            final Task task4 = new Task("Task_4", 200, 3, prioritisedTaskList);
            prioritisedTaskList.add(task2);
            prioritisedTaskList.add(task3);
            prioritisedTaskList.add(task4);

        }


        public void startAsyncTasks() {
            final Task task0 = new Task("Task_0", 100, 5, prioritisedTaskList);
            final Task task1 = new Task("Task_1", 110, 2, prioritisedTaskList);


            final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
            scheduler.schedule(new Runnable() {
                @Override
                public void run() {
                    prioritisedTaskList.addAndStart(task1);
                    scheduler.shutdown();
                }
            }, 10, TimeUnit.MILLISECONDS);
            final ScheduledExecutorService scheduler1 = Executors.newScheduledThreadPool(1);
            scheduler.schedule(new Runnable() {
                @Override
                public void run() {
                    prioritisedTaskList.addAndStart(task0);
                    scheduler1.shutdown();
                }
            }, 5, TimeUnit.MILLISECONDS);


            //prioritisedTaskList.addAndStart(task0);
            //prioritisedTaskList.addAndStart(task1);
            //prioritisedTaskList.addAndStart(task2);
            //prioritisedTaskList.addAndStart(task3);
            //prioritisedTaskList.addAndStart(task4);

            ////stimulate random event that happen in 3 seconds
            //final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
            //scheduler.schedule(new Runnable() {
            //    @Override
            //    public void run() {
            //        if (task1Listener != null) task1Listener.onTask1Result(FibonacciSequence.generateNumber(5));
            //        scheduler.shutdown();
            //    }
            //}, 3, TimeUnit.SECONDS);

        }

    }

    private static class PrioritisedTaskList implements Task.Listener {
        private List<Task> tasks = new Vector<>();
        private Task currentTask = null;
        private final Object LOCK = new Object();

        public PrioritisedTaskList(Task... task) {
            tasks.addAll(Arrays.asList(task));
        }

        public void add(Task task) {
            tasks.add(task);
        }

        public void addAndStart(Task task) {
            tasks.add(task);
            suspendCurrentTask();
            Collections.sort(tasks, Comparator.comparingInt(Task::getPriority));
            System.out.println(tasks.toString());
            startMostPrioritizedTask();


        }


        private void suspendCurrentTask() {
            if (currentTask != null) {
                if (currentTask.getState() != Task.State.TERMINATED)
                    currentTask.suspendTask();
            }
        }


        private void startMostPrioritizedTask() {
            for (Task task : tasks) {
                synchronized (task) {
                    Task.State state = task.getState();
                    switch (state) {
                        case ACTIVE:
                            currentTask = task;
                            return;
                        case TERMINATED:
                            break;
                        case SUSPENDED:
                        case INACTIVE:
                            task.workTask();
                            currentTask = task;
                            break;
                        default:
                            throw new RuntimeException("unknown tast");
                    }
                }
            }
        }

        @Override
        public void onTaskStart(String taskName, int priority) {
            log("START: " + taskName + " with  priority " + priority);
        }

        @Override
        public void onTaskSuspended(String taskName, int priority) {
            log("SUSPEND: " + taskName + " with  priority " + priority);
        }

        @Override
        public void onTaskFinish(String taskName, int priority, long resultValue) {
            log("FINISHED: " + taskName + " with  priority " + priority + " result of sequence is :" + resultValue);
            startMostPrioritizedTask();
        }

        private void log(String text) {
            Calendar calendar = Calendar.getInstance();
            System.out.println(calendar.get(Calendar.MINUTE) + " " + calendar.get(Calendar.MILLISECOND) + " " + text);
        }


    }
}
