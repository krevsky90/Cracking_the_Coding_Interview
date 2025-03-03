info:
https://youtu.be/m-7EljqdxpA?t=5000

ExecutorService extends Executor
that's why ExecutorService also has method execute (from Executor interface)
but execute(..) returns nothing!
ExecutorService #submit(..) returns Future<T> and does it immediately!

The thread (name it as main thread) that submitted the tasks can get the results of these tasks by Future#get() method!
BUT at this code line main thread will wait the task-thread!

NOTE: before Java 21 we need shutdown executorService manually
    like this (https://www.baeldung.com/java-executor-service-tutorial)

    executorService.shutdown();
    try {
        if (!executorService.awaitTermination(800, TimeUnit.MILLISECONDS)) {
            executorService.shutdownNow();  //returns List<Runnable> notExecutedTasks, let's just skip it
        }
    } catch (InterruptedException e) {
        //this catch block exists because awaitTermination method might throw InterruptedException
        executorService.shutdownNow();  //returns List<Runnable> notExecutedTasks, let's just skip it
    }

    see example in SingleThreadExecutorExample.java

    BUT starting from Java 21 ExecutorService implements AutoCloseable
    So we can write
    try (ExecutorService executorService = Executors.newXXX(...)) {
        ...
    }

---
ScheduledExecutorService extends ExecutorService
1) it has schedule(0 method
2) The scheduleAtFixedRate() method lets us run a task periodically after a fixed delay. The code above delays for one second before executing callableTask.
     The following block of code will run a task after an initial delay of 100 milliseconds. And after that, it will run the same task every 450 milliseconds:
     executorService.scheduleAtFixedRate(runnableTask, 100, 450, TimeUnit.MILLISECONDS);
