package com.bravedroid.main;

import com.bravedroid.work.WorkSynchronouslyInOneThread;

public class Main {

    public static void main(String[] args) {
        System.out.println("it's not because it is synchronous so it should be in an other thread.");
        TaskManager taskManager = new TaskManager();
        //just uncomment the Work strategy that you want and observe the outputs

        taskManager.work(new WorkSynchronouslyInOneThread());
        //taskManager.work(new WorkSynchronouslyInManyThread());
        //taskManager.work(new WorkAsynchronouslyInOneThread());
        //taskManager.work(new WorkAsynchronouslyInManyThread());

        System.out.println("Done!");
    }
}
