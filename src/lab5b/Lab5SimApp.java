package lab5b;

/**
 * <b>Title:</b> Application class for ProducerThread and BankTellerThread to
 * simulate a bank's workflow<br>
 * <b>Filename:</b> Lab5SimApp.java<br>
 * <b>Date Written:</b> March 30, 2024<br>
 * <b>Due Date:</b> March 30, 2024<br>
 * <p>
 * <b>Description:</b><br>
 * main class to simulate a bank workflow using ProducerThread for customer and
 * BankTellerThread for bank tellers<br>
 * 
 * @author Chunbo Cheng
 */
public class Lab5SimApp {

	public static void main(String[] args) throws InterruptedException {
		final int inputVal = 5; // 5 total input values
		int[] input = new int[inputVal];
		int tellers; // number of tellers
		int queueSize; // size of the queue
		int avgArrivalTime; // mean customer arrival time
		long simulationTime; // simulation time
		ThreadGroup tg = new ThreadGroup("tellers");
		Thread[] teller = null;
		ProducerThread producer;
		ArrayQueue<Customer> queue;

		// validate input while importing data from args
		try {
			for (int i = 0; i < inputVal; i++) {
				input[i] = Integer.parseInt(args[i]);
				if (input[i] == 0) {
					System.out.println("Invalid input. Program is ending...");
					return;
				}
			}
		} catch (NumberFormatException ex) {
			System.out.println("Invalid number format, expecting int[]");
		}

		// distribute inputs
		tellers = input[0]; // number of tellers
		queueSize = input[1]; // customer queue capacity
		queue = new ArrayQueue<Customer>(queueSize);
		avgArrivalTime = input[2] * 1000; // in milliseconds
		teller = new Thread[tellers];
		simulationTime = SimulationTime.minutesToMilisecs(input[4]); // duration the program runs
		producer = new ProducerThread(queue, simulationTime, avgArrivalTime); // initializing producer thread

		// program starts
		System.out.println("Tellers are getting ready. The bank will open in 10 minutes...\n");
		// initializing start time
		SimulationTime sm = new SimulationTime();
		// producer thread starts
		producer.start();
		// bank teller threads start
		for (int i = 0; i < tellers; i++) {
			teller[i] = new Thread(tg, new BankTellerThread(i, queue, producer));
			teller[i].start();
		}
		// pause while producer thread is running
		while (producer.isAlive()) {
			Thread.sleep(10000);
		}
		System.out.println("The producer thread has finished...\n");
		// pause while any bank teller thread is running
		while (tg.activeCount() > 0) {
			Thread.sleep(10000);
		}
		System.out.println("The tellers have completed all transactions...\n");
		System.out.println("End of program...");
	}
}
