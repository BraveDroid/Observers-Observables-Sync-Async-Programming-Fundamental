package com.bravedroid.main;

import com.bravedroid.work.WorkAsynchronouslyInManyThread;
import com.bravedroid.work.WorkAsynchronouslyInOneThread;
import com.bravedroid.work.WorkSynchronouslyInManyThread;
import com.bravedroid.work.WorkSynchronouslyInOneThread;

public class Main {

    public static void main(String[] args) {
        System.out.println("it's not because it is synchronous so it should be in an other thread.");

        TaskManager taskManager = new TaskManager();

        //System.out.println("WorkSynchronouslyInOneThread");
        //taskManager.work(new WorkSynchronouslyInOneThread());

       //System.out.println("WorkSynchronouslyInManyThread");
       //taskManager.work(new WorkSynchronouslyInManyThread());

        System.out.println("WorkAsynchronouslyInOneThread");
        taskManager.work(new WorkAsynchronouslyInOneThread());

       //System.out.println("WorkAsynchronouslyInManyThread");
       //taskManager.work(new WorkAsynchronouslyInManyThread());

    }
}
