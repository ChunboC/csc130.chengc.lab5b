package lab5b;
/**
 * Title: BankTellerThread
 * Description: Class that simulates bank tellers and takes in customers from the producerThread class
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
	
	//Should run as a loop and return after the loop ends
	@Override
	public void run() throws QueueEmptyException {
		while(producer.isAlive()) {
			if(!queue.isEmpty()) {
				try {
					//Gets the the customer from the front of the queue
					Customer customer = queue.dequeue();
					//Starts the teller's idle time and sets it as the current time
					long currentTime = System.currentTimeMillis();
					startIdleTime = currentTime;
					//Updates the customer's wait and start time with the same current time
					customer.setTransactionStartTime(currentTime);
					customer.setArrivalTime(currentTime);
					
					//Generates a random a random number inclusive of 1000 and 15000 
					int max = 15000;
					int min = 1000;
					int range = max - min + 1;
					int rand = 0;
					rand = (int)(Math.random() * range) + min;
					
					//Displays the teller's id number and idle time, as well as id of current customer
					System.out.println("Minute: " + currentTime);
					long totalIdleTime = (endIdleTime - startIdleTime) / 1000;
					System.out.println("Teller "  + idNumber + "[idle time: " + totalIdleTime + " minutes, processing transaction for customer: " + customer.getIdNumber());
					//Pauses the thread based on the randomly generated number
					 try {
						 Thread.sleep(rand);
					 } catch (InterruptedException ie) {
						 System.out.println("Interrupted Exception");
					 }
					 //Saves the current time
					 currentTime = System.currentTimeMillis();
					 
					 //Calls the customer class's setEndTime method to update the time the transaction ended
					 customer.setEndTime(currentTime);
					 System.out.println("Minute " + currentTime);
					 System.out.println("Teller "  + idNumber + "[idle time: " + totalIdleTime + ", processing transaction for customer: " + producer.getId());
					 System.out.println("Customer: " + customer);
					 //Add 1 to the count
					 count++;
					 startIdleTime = System.currentTimeMillis();
				} catch (QueueEmptyException qe) {
					throw new QueueEmptyException("Queue Empty Exception...Telle " + this.idNumber + "waitimg for customer");
				} 
			}
		}
		
	}

}
