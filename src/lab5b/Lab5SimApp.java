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
			teller[i] = new Thread(tg, new BankTellerThread1(i, queue, producer));
			teller[i].start();
		}
		
		while(producer.isAlive()) {
			producer.run();
		}
		System.out.println("The producer thread has finished...");
		
		while (tg.activeCount() > 0) {
			tg.wait();
		}
		System.out.println("The tellers have completed all transactions...");
		System.out.println("End of program...");
		
	}
}
