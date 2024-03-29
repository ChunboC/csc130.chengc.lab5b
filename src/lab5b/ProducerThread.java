package lab5b;

public class ProducerThread extends Thread {
	private ArrayQueue<Customer> line; // the line the customers wait in
	private long simulationTime; // how long to run the simulation
	private int averageArrivalTime; // average time between customer arrivals
	private int count; // number of customers handled
	private final long startTime = System.currentTimeMillis(); // current time
	
	public ProducerThread(ArrayQueue<Customer> theQueue, long simTime, int avgTime) {
		line = theQueue;
		simulationTime = simTime;
		averageArrivalTime = avgTime;
	}
	@Override
	public void run() {
		try {
			sleep(10000);
		} catch (InterruptedException ie){
			System.out.println("The Producer thread was Interrupted.");
		}
		while (System.currentTimeMillis() - startTime < simulationTime) {
			try {
				long arrival = System.currentTimeMillis();
				Customer c = new Customer(count, arrival);
				System.out.println("Customer " + count + " arrived at minute " + arrival / 1000 + ".");
				if (line.isFull())
					System.out.println("Queue is full. Customer " + count + " left the bank.");
				else
					System.out.println("Customer " + count + " added to the queue.");
				long waitTime = SimulationTime.timeTillNext(averageArrivalTime);
				sleep(waitTime); // waiting for the next customer to arrive
				count++;
			} catch (QueueFullException qfe) {
				System.out.println("Queue is full.");
			} catch (InterruptedException ie) {
				System.out.println("The Producer thread was Interrupted.");
			}
		}
		System.out.println("The Producer thread has stopped, " + count + " customers produced.");
	}
}
