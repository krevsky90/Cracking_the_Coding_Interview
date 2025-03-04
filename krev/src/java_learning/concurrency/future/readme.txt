info:
https://www.baeldung.com/java-future

Runnable
	void run()
Future
	get()
	cancel()
	isCancelled()
	isDone()
	
	RunnableFuture extends Runnable, Future
		void run() - не понял, зачем его переопределили?

		FutureTask extends RunnableFuture

NOTE:
if we  call future.cancel(..) then it is good to check:

if (future.isCancelled()) {
    result = f.get();
}

otherwise we will get CancellationException


			