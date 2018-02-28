package com.bravedroid.util;


public class Task {
    private String taskName;
    private int fibNumber = -1;
    private int priority = -1;
    private long result = -1;

    private State state = State.INACTIVE;


    private TempData tempData;

    private Listener listener;


    private static class TempData {
        int k = 0;
        long temp = 0;
        long i = 0;
        long j = 1;
    }

    public Task(String taskName, int fibNumber, int priority, Listener listener) {
        this.taskName = taskName;
        this.fibNumber = fibNumber;
        this.priority = priority;
        this.listener = listener;

        tempData = new TempData();
    }

    private void updateState(State newState) {
        this.state = newState;
    }

    public State getState() {
        return state;
    }

    public int getPriority() {
        return priority;
    }

    public void workTask() {
        updateState(State.ACTIVE);
        if (listener != null) listener.onTaskStart(taskName, priority);

        while (tempData.k < fibNumber && state == State.ACTIVE) {
            tempData.temp = tempData.i + tempData.j;
            tempData.i = tempData.j;
            tempData.j = tempData.temp;
            tempData.k++;
        }
        tempData.i = tempData.i / 100;
        result = tempData.i;
        if (state != State.SUSPENDED) {
            updateState(State.TERMINATED);
            if (listener != null) listener.onTaskFinish(taskName, priority, result);
        }
    }

    public void suspendTask() {
        state = State.SUSPENDED;
        if (listener != null) listener.onTaskSuspended(taskName, priority);
    }


    public enum State {
        INACTIVE,
        ACTIVE,
        SUSPENDED,
        TERMINATED
    }

    public interface Listener {
        void onTaskStart(String taskName, int priority);

        void onTaskSuspended(String taskName, int priority);

        void onTaskFinish(String taskName, int priority, long resultValue);
    }

    @Override
    public String toString() {
        return "Task{" +
                "taskName='" + taskName + '\'' +
                " priority='" + priority + '\'' +
                '}';
    }
}
