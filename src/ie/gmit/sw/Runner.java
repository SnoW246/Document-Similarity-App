// Package usage declaration
package ie.gmit.sw;

// Library imports
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ArrayBlockingQueue;

// Declaration of runner class
public class Runner extends Thread{
	// Declaration of private variables for blocking queues
	private BlockingQueue<Thread> inQ = new ArrayBlockingQueue<Thread>(6);
	private BlockingQueue<Thread> outQ = new ArrayBlockingQueue<Thread>(6);
	private Thread t;
	
	// Construct runner with blocking queue in & out elemensts
	public Runner(BlockingQueue <Thread> inQ, BlockingQueue<Thread> outQ) {
		super();
		this.inQ = inQ;
		this.outQ = outQ;
	}
	
	// Declaration of Run function that runs the thread
	public void run() {
		// While loop function to run the queue if it is available
		while(true) {
			// Check queue every 6 sec.
			t = inQ.poll();
			
			// In case there is no thread, do nothing until thread is available
			if(t != null) {
				// Offer a new thread
				outQ.offer(t);
			}// End of if
		}// End of while
	} // End of while
}// End of runner class
