https://www.baeldung.com/java-concurrent-locks

Lock API:
void lock() – Acquire the lock if it’s available. If the lock isn’t available, a thread gets blocked until the lock is released.
void lockInterruptibly() – This is similar to the lock(), but it allows the blocked thread to be interrupted and resume the execution through a thrown java.lang.InterruptedException.
boolean tryLock() – This is a nonblocking version of lock() method. It attempts to acquire the lock immediately, return true if locking succeeds.
boolean tryLock(long timeout, TimeUnit timeUnit) – This is similar to tryLock(), except it waits up the given timeout before giving up trying to acquire the Lock.
void unlock() unlocks the Lock instance.