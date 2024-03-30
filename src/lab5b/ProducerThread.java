package lab5b;

public class ProducerThread extends Thread {

	private ArrayQueue<Customer> line; // the line the customers wait in
	private long simulationTime; // how long to run the simulation
	private int averageArrivalTime; // average time between customer arrivals
	private int count; // number of customers handled
	private final long startTime = System.currentTimeMillis(); // current time

	public ProducerThread(ArrayQueue<Customer> line, long simTime, int avgArrTime) {
		this.line = line;
		simulationTime = simTime;
		averageArrivalTime = avgArrTime;
	}

	public void run() {
		try {
			sleep(10000);
		} catch (InterruptedException e) {
			System.out.println("Queue is interrupted.");
		}

		while (System.currentTimeMillis() - startTime < simulationTime) {
			try {
				long arrivalTime = System.currentTimeMillis();
				Customer customer = new Customer(count, arrivalTime);
				System.out.println("Customer " + count + " arrived at minute " + SimulationTime.timeSinceStart(arrivalTime));
				if (line.isFull()) {
					System.out.println("Queue is full. Customer " + count + " left the bank.");
					continue;
				} else {
					line.enqueue(customer);
					System.out.println("Customer " + count + " added to the queue.\n");
					long waitTime = SimulationTime.timeTillNext(averageArrivalTime);
					sleep(waitTime);
					count++;
				}
			} catch (QueueFullException qfe) {
				System.out.println("Queue is full.");
			} catch (InterruptedException ie) {
				System.out.println("Producer thread was Interrupted.");
			}

		}
		System.out.println("The Stimulation has ended, Number of Customers produced: " + count + ".\n");
	}
}
