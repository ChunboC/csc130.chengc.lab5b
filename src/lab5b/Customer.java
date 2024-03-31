package lab5b;

/**
 * <p>
 * <b>Title:</b> Customer
 * </p>
 * 
 * <p>
 * <b>Description:</b> Represents a customer at a bank with method such as ID
 * number, arrival time, transaction start time, transaction end time, as well
 * as to retrieve and update customer information.
 * </p>
 * 
 * @author Nathanielle Onchengco, Chunbo Cheng and Mariagabriela Yanez Raga
 */
public class Customer {
	private int number; // The customer’s id number starting with 0
	private long transactionStartTime; // the time the transaction starts
	private long arrivalTime; // the time the customer arrived at the bank
	private long transactionEndTime; // the time the transaction end and the customer exits the bank

	/**
	 * Customer - Constructs a new Customer object with the given ID number and
	 * arrival time.
	 * 
	 * @param number      (The customer's ID number)
	 * @param arrivalTime
	 */
	public Customer(int number, long arrivalTime) {
		this.number = number;
		this.arrivalTime = arrivalTime;
	}

	/**
	 * getIdNumber - Retrieves the ID number of the customer.
	 * 
	 * @return the customer's ID number
	 */
	public int getIdNumber() {
		return number;
	}

	/**
	 * getWaitTime - Calculates and retrieves the wait time of the customer.
	 * 
	 * @return the wait time in seconds
	 */
	public long getWaitTime() {
		return (transactionStartTime - arrivalTime) / 1000;
	}

	/**
	 * getTransactionStartTime - Retrieves the transaction start time of the
	 * customer.
	 * 
	 * @return the transaction start time
	 */
	public long getTransactionStartTime() {
		return transactionStartTime;
	}

	/**
	 * getArrivalTime - Retrieves the arrival time of the customer.
	 * 
	 * @return the arrival time
	 */
	public long getArrivalTime() {
		return arrivalTime;
	}

	/**
	 * getEndTime - etrieves the transaction end time of the customer.
	 * 
	 * @return the transaction end time
	 */
	public long getEndTime() {
		return transactionEndTime;
	}

	/**
	 * setTransactionStartTime - Sets the transaction start time for the customer.
	 * 
	 * @param start (the transaction start time)
	 */
	public void setTransactionStartTime(long start) {
		transactionStartTime = start;
	}

	/**
	 * setArrivalTime - Sets the arrival time for the customer.
	 * 
	 * @param arrival (the arrival time)
	 */
	public void setArrivalTime(long arrival) {
		arrivalTime = arrival;
	}

	/**
	 * setEndTime - Sets the transaction end time for the customer.
	 * 
	 * @param end (the transaction end time)
	 */
	public void setEndTime(long end) {
		transactionEndTime = end;
	}

	/**
	 * getTransaction - Calculates and retrieves the transaction duration of the
	 * customer.
	 * 
	 * @return - the transaction duration in seconds
	 */
	public long getTransaction() {
		return (transactionEndTime - transactionStartTime) / 1000;
	}

	/**
	 * toString - Returns a string representation of the customer object.
	 * 
	 * @return - a string containing customer information
	 */
	public String toString() {
		return "Customer: " + number + ", " + "entered the bank at minute " + SimulationTime.timeSinceStart(arrivalTime)
				+ ", [Transaction Time: " + getTransaction() + " mins, Wait Time: " + getWaitTime() + " mins]\n";
	}
}