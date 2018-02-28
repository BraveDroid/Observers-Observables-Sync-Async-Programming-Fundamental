
 **Observers-Observables-Sync-Async-Programming-Fundamental**  
Samples that are here are all about the fundamentals showing the basics and the needed knowledge to learn about how to handle synchronicity/asynchronicity when it's needed.  
  
Learning this staff could be a great introduction for reactive programming.   
  
  
First of all keep in mind that:  
  
In imperative programming language (like Java) we can find :  
  
First Synchronous and Asynchronous programming model and second Single threaded and multi-threaded environments  
  
So in total we can have these 4 combinations:  
- Synchronous Single Threaded  
- Asynchronous Single Threaded  
- Synchronous Multi-Threaded  
- Asynchronous Multi-Threaded  

 As each programming model (Synchronous and Asynchronous) can run in single threaded and multi-threaded environment.
 
In this project we will have list all examples needed to understand the difference.

By default any program is running in a sync model and generally saying it have a single thread sometimes called the main thread or it can also be called the UI thread "for an Android app for example" .

In an Asynchronous Programming Model –it is in contrary to Synchronous programming model,  a thread once  start executing a task, it can hold it in mid, save the current state and start executing another task. 
In a single thread async program the thread is responsible to complete all the tasks and tasks are interleaved to each other.
In a multi-threading scenario tasks can be  handled by multiple thread, a thread start working on it and then stop it to work on another tasks, an other thread would work on it and may be finish it. This would be great to work on some prioritized tasks.
 
 **Concurrency**
Concurrency means processing multiple requests at a time.
Two scenarios where multiple requests were getting processed:

 1. Multi-threaded programming
 2. Asynchronous model (single and multi-threaded both), multiple tasks are in progress at a time, some are in hold state and some are getting executed.

Nowadays many applications and new frameworks completely rely on asynchronous model (single thread or multi-thread).
