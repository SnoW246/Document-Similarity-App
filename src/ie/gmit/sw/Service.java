// Package usage declaration
package ie.gmit.sw;

// Library imports
import java.util.concurrent.BlockingQueue;

import com.db4o.ObjectContainer;

import java.io.IOException;
import java.util.*;
import java.util.concurrent.ArrayBlockingQueue;

// Declaration of Runner class
public class Service implements Runnable{
	// Declaration of private variables for blocking queues
	private BlockingQueue<ServiceJob> inQ = new ArrayBlockingQueue<ServiceJob>(60);
	private BlockingQueue<List<Results>> outQ = new ArrayBlockingQueue<List<Results>>(60);
	private ServiceJob sj = null;
	private ObjectContainer db = null;
	private List<Document> dList = new ArrayList<Document>();
	private List<Results> results = new ArrayList<Results>();
	private MinHash mh;
	
	// Construct empty default constructor
	public Service() {
	}// End of Service constructor
	
	// Construct runner with blocking queue in & out elements
	public Service(BlockingQueue <ServiceJob> inQ, BlockingQueue<List<Results>> outQ) {
		super();
		this.inQ = inQ;
		this.outQ = outQ;
	}// End of Service constructor type 2
	
	// Declaration of Run function that runs the thread
	public void run() {
		// While loop function to run the queue if it is available
		while(true) {
			// Check queue every 6 sec.
			inQ = QueueService.getInQ();
			sj = inQ.poll();
			
			// In case there is no thread, do nothing until thread is available
			if(sj != null) {
				DatabaseRunner db;
				try {
					db = new DatabaseRunner();
					//db.addDocumentsToDatabase(j.getDoc());
					dList = db.getDocuments();
					mh = new MinHash(dList, sj.getDocument());
					results = mh.Compute();
					db.addDocumentsToDatabase(sj.getDocument());
					db.closeDB();
				}
				catch (IOException e) {
					e.printStackTrace();
				}
				
				System.out.println("List size: "+results.size());
				QueueService.addToOutQ(results);
				outQ =  QueueService.getOutQ();

			}// End of if
		}// End of while
	} // End of run
}// End of runner class