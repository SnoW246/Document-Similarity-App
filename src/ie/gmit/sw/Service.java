// Package usage declaration
package ie.gmit.sw;

// Library imports
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ArrayBlockingQueue;

// Declaration of Runner class
public class Service extends Thread{
	// Declaration of private variables for blocking queues
	private BlockingQueue<ServiceJob> inQ = new ArrayBlockingQueue<ServiceJob>(6);
	private BlockingQueue<ServiceJob> outQ = new ArrayBlockingQueue<ServiceJob>(6);
	private ServiceJob sj;
	
	// Construct runner with blocking queue in & out elemensts
	public Service(BlockingQueue <ServiceJob> inQ, BlockingQueue<ServiceJob> outQ) {
		super();
		this.inQ = inQ;
		this.outQ = outQ;
	}
	
	// Declaration of Run function that runs the thread
	public void run() {
		// While loop function to run the queue if it is available
		while(true) {
			// Check queue every 6 sec.
			sj = inQ.poll();
			
			// In case there is no thread, do nothing until thread is available
			if(sj != null) {
				// Offer a new thread
				outQ.offer(sj);
			}// End of if
		}// End of while
	} // End of run
}// End of runner class