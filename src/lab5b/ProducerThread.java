package lab5b;

/**
 * <p>
 * <b>Title:</b> ProducerThread
 * </p>
 * 
 * <p>
 * <b>Description:</b> Represents a producer thread that generates customers
 * arriving at the bank and adds them to a queue. If the queue is full, the
 * customer will leave. If it's not full, the customer will wait in line.
 * </p>
 * 
 * </p>
 * 
 * @author Mariagabriela Yanez Raga
 */
public class ProducerThread extends Thread {

	private ArrayQueue<Customer> line; // the line the customers wait in
	private long simulationTime; // how long to run the simulation
	private int averageArrivalTime; // average time between customer arrivals
	private int count; // number of customers handled
	private final long startTime = System.currentTimeMillis(); // current time

	/**
	 * ProducerThread - Constructs a new ProducerThread object with the given
	 * parameters.
	 * 
	 * @param line       - the queue where customers wait
	 * @param simTime    - simTime the simulation time
	 * @param avgArrTime - avgArrTime the average arrival time between customers
	 */
	public ProducerThread(ArrayQueue<Customer> line, long simTime, int avgArrTime) {
		this.line = line;
		simulationTime = simTime;
		averageArrivalTime = avgArrTime;
	}

	/**
	 * run - Runs the producer thread to generate customers and add them to the
	 * queue.
	 * 
	 */
	public void run() {
		try {
			sleep(10000); // Initial delay before starting to produce customers (wait until bank is open)
			while (System.currentTimeMillis() - startTime < simulationTime) {
				try {
					long arrivalTime = System.currentTimeMillis();
					Customer customer = new Customer(count, arrivalTime); // Creates the customer
					System.out.println(
							"Customer " + count + " arrived at minute " + SimulationTime.timeSinceStart(arrivalTime)); //customer's arrival
					if (line.isFull()) {
						System.out.println("Queue is full. Customer " + count + " left the bank.\n"); //The queue is full and the customer left the bank
						long waitTime = SimulationTime.timeTillNext(averageArrivalTime);
						sleep(waitTime);
						count++;
					} else {
						line.enqueue(customer);
						System.out.println("Customer " + count + " added to the queue.\n"); //customer was added to the queue.
						long waitTime = SimulationTime.timeTillNext(averageArrivalTime);
						sleep(waitTime); // waiting for the next customer to arrive
						count++; //Increment the count for the next Customer.
					}
				} catch (QueueFullException qfe) {
					System.out.println("Queue is full.");
				} catch (InterruptedException ie) {
					System.out.println("Producer thread was Interrupted.");
				}
			}
		} catch (InterruptedException e) {
			System.out.println("Queue is interrupted.");
		}
		System.out.println("The Stimulation has ended, Number of Customers produced: " + count + ".\n");
	}
}