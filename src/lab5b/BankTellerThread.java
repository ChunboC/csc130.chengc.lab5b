package lab5b;

/**
 * <p>
 * <b>Title:</b> BankTellerThread
 * </p>
 * 
 * <p>
 * <b>Description:</b> Class that simulates bank tellers and
 * takes in customers from the producerThread class
 * </p>
 * 
 * @author Nathanielle Onchengco
 */

public class BankTellerThread implements Runnable {
	private int idNumber;
	private ArrayQueue<Customer> queue;
	private long startIdleTime = System.currentTimeMillis();
	private long endIdleTime = startIdleTime;
	private int count;
	private ProducerThread producer;

	public BankTellerThread(int id, ArrayQueue<Customer> que, ProducerThread producer) {
		this.idNumber = id;
		this.queue = que;
		this.producer = producer;
	}

	// Should run as a loop and return after the loop ends
	@Override
	public void run() throws QueueEmptyException {
		while (producer.isAlive()) {
			if (!queue.isEmpty()) {
				try {
					// Gets the the customer from the front of the queue
					Customer customer = queue.dequeue();
					// Starts the teller's idle time and sets it as the current time
					long currentTime = System.currentTimeMillis();
					endIdleTime = currentTime;
					// Updates the start time with the same current time
					customer.setTransactionStartTime(currentTime); // must be in milliseconds
					// Generates a random a random number inclusive of 1000 and 15000
					// rand: transaction time
					int max = 15000;
					int min = 1000;
					int range = max - min + 1;
					long rand = 0;
					rand = (long) (Math.random() * range) + min;
					// Displays the teller's id number and idle time, as well as id of current
					// customer
					long totalIdleTime = (endIdleTime - startIdleTime) / 1000;
					System.out.println("Minute: " + SimulationTime.timeSinceStart(currentTime) + "\nTeller "
							+ this.idNumber + ": Idle time: " + totalIdleTime
							+ " minutes, Processing transaction for customer: " + customer.getIdNumber() + "\n");
					// Pauses the thread based on the randomly generated number - for how long the
					// transaction lasted
					try {
						Thread.sleep(rand);
					} catch (InterruptedException ie) {
						System.out.println("Interrupted Exception");
					}
					// Saves the current time
					currentTime = System.currentTimeMillis();
					// Calls the customer class's setEndTime method to update the time the
					// transaction ended
					customer.setEndTime(currentTime);
					System.out.println("\033[0;1mMinute:" + SimulationTime.timeSinceStart(currentTime) + "\nTeller "
							+ this.idNumber + ": Idle time: " + totalIdleTime + " minutes, Processed:\n" + customer
							+ "\033[0;0m");
					// Add 1 to the count
					count++;
					startIdleTime = System.currentTimeMillis();
				} catch (QueueEmptyException qe) {
					System.out.println("Queue is empty... Teller " + this.idNumber + " waiting for customer\n");
				}
			} 
		}
		System.out.println("[Teller " + idNumber + " finished. Processed " + count + " customers.]\n");
	}

}
