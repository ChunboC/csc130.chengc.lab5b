package lab5b;

public class Lab5SimApp {

	public static void main(String[] args) throws InterruptedException {
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
		
		input[0] = 2; // tellers
		input[1] = 15; // queue size
		input[2] = 5; // avg arrival
		input[3] = 5; //
		input[4] = 1; // simulation time
		
		// validate input
		try {
			int count = 0;
			for (int i : input) {
				if (i != 0)
					count++;
			}
			if (count != 5) {
				System.out.println("invalid input.");
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
		simulationTime = SimulationTime.minutesToMilisecs(input[4]);
		producer = new ProducerThread(queue, simulationTime, avgArrivalTime);
		System.out.println("Tellers are getting ready. The bank will open in 10 minutes...\n");
		SimulationTime sm = new SimulationTime();
		producer.start();
		for (int i = 0; i < tellers; i++) {
			teller[i] = new Thread(tg, new BankTellerThread(i, queue, producer));
			teller[i].start();
		}
		
		while(producer.isAlive()) {
			Thread.sleep(10000);
		}
		System.out.println("The producer thread has finished...");
		
		while (tg.activeCount() > 0) {
			Thread.sleep(10000);
		}
		System.out.println("The tellers have completed all transactions...");
		System.out.println("End of program...");
	}
}
