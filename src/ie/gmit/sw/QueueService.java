package ie.gmit.sw;

import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class QueueService {
	private static BlockingQueue<ServiceJob> inQ;
	private static BlockingQueue<List<Results>> outQ;
	
	// Construct empty default constructor
	private QueueService(){
		// Default
	}// GlobalQueue

	// Declaration of synchronized blocking queues 
	public static synchronized Boolean init(){
		inQ = new ArrayBlockingQueue<ServiceJob>(60);
		outQ = new ArrayBlockingQueue<List<Results>>(60);
		return true;
	}// End of init function
	
	// Declaration of In Queue service function
	public static BlockingQueue<ServiceJob> getInQ() {
		return inQ;
	}// End of getInQ
	
	// 
	public static void setInQ(BlockingQueue<ServiceJob> inQ) {
		QueueService.inQ = inQ;
	}

	public static BlockingQueue<List<Results>> getOutQ() {
		return outQ;
	}

	public static void setOutQ(BlockingQueue<List<Results>> outQ) {
		QueueService.outQ = outQ;
	}
	
	public static void addToInQ(ServiceJob sj)
	{
		QueueService.inQ.add(sj);
	}
	
	public static void addToOutQ(List<Results> r)
	{
		QueueService.outQ.add(r);
	}

}
