package lab5b;

public class BankTellerThread1 implements Runnable{
	private int idNumber; // teller's id number
	private ArrayQueue<Customer> queue; // the queue for the customers
	private long startIdleTime = System.currentTimeMillis(); // start of idle time
	private long endIdleTime = startIdleTime; // end of idle time
	private int count; // number of customers processed by this teller
	private ProducerThread producer; // the producer thread
	
	public BankTellerThread1(int id, ArrayQueue<Customer> q, ProducerThread pt) {
		idNumber = id;
		q = queue;
		producer = pt;
	}

	@Override
	public void run() {
		while (producer.isAlive()) {
			try {
				if (!queue.isEmpty()) {
					
				}
			} catch (QueueEmptyException qe){
				
			} catch (InterruptedException ie) {
				
			}
		}
	}
}
