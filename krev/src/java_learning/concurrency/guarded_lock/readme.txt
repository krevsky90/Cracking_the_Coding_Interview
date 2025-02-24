https://www.baeldung.com/java-wait-notify
https://docs.oracle.com/javase/tutorial/essential/concurrency/guardmeth.html

To suspend thread which needs current object:
wait()
wait(timeout)

NOTE:
1) the thread is waiting for intrinsic lock of the object => wait() can be called ONLY from synchronized block of code (that creates intrinsic lock)
2) call of wait() should be inside while-loop (otherwise there may be situation when the thread is awakened, but shared field is still is inappropriate state)

To notify (hey! wake up!) some of (or all) threads that are waiting for monitor of the object
notify() and notifyAll()

NOTE: in fact, notify() is not popular since it notifies only one random thread, that is waiting.
i.e. it fits when we have multiple threads that do the same work => does not matter which thread will be awakened
BUT usually we use notifyAll()

---------
An intrinsic lock - is a mechanism in Java that is used to enforce mutual exclusion when accessing shared resources.
Every object in Java has an intrinsic lock (also known as a monitor lock ) associated with it.

Monitor - is a higher-level concept that refers to the mechanism that controls access to an object's synchronized methods and blocks
The monitor is implemented using the intrinsic lock of an object. The monitor uses the intrinsic lock to control access to synchronized methods and blocks.
