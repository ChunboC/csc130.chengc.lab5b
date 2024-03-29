package lab5b;

public class Lab5SimApp {

	public static void main(String[] args) {
		final int inputVal = 5;		// 5 input values
		int[] input = new int[5];	
		int tellers;	// number of tellers
		int queueSize;	// size of the queue
		int avgArrivalTime;		// mean customer arrival time
		long simulationTime;	// simulation time
		ThreadGroup tg = new ThreadGroup("tellers");
		Thread[] teller = null;
		ProducerThread producer;
		ArrayQueue<Customer> queue;
		
		// validate input
		try {
			if (input.length != 5) {
				System.out.println("Invalid number of input values.");
				return;
			}
		} catch (NumberFormatException ex){
			System.out.println("Invalid number format, expecting int[]");
		}
		
		// distribute inputs
		tellers = input[0];
		queueSize = input[1];
		queue = new ArrayQueue<Customer>(queueSize);
		avgArrivalTime = input[2] * 1000;	// in milliseconds
		teller = new Thread[tellers];
		simulationTime = input[4];
		SimulationTime.minutesToMilisecs(simulationTime);
		producer = new ProducerThread(queue, simulationTime, avgArrivalTime);
		
		System.out.println("Tellers are getting ready. The bank will open in 10 minutes...");
		
		SimulationTime smTime = new SimulationTime();
		producer.start();
		for (int i = 0; i < tellers; i++) {
			teller[i] = new Thread(tg, new BankTellerThread(i, queue, producer));
			teller[i].start();
		}
		
		while(producer.isAlive()) {
			
		}
		System.out.println("The producer thread has finished...");
		
		//step25
		
	}
}
