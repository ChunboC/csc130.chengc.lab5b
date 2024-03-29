package lab5b;

public class Customer {
	private int number; // The customer’s id number starting with 0
	private long transactionStartTime; // the time the transaction starts
	private long arrivalTime; // the time the customer arrived at the bank
	private long transactionEndTime; // the time the transaction end and the customer exits the bank

	public Customer(int id, long arrivalT) {
		number = id;
		arrivalTime = arrivalT;
	}

	public int getIdNumber() {
		return number;
	}

	public long getTransactionStartTime() {
		return transactionStartTime;
	}

	public long getEndTime() {
		return transactionEndTime;
	}

	public long getArrivalTime() {
		return arrivalTime;
	}

	public long getWaitTime() {
		return transactionStartTime - arrivalTime;
	}

	public long getTransation() {
		return (transactionEndTime - transactionStartTime) / 1000;
	}

	public void setArrivalTime(long arrival) {
		arrivalTime = arrival;
	}

	public void setTransactionStartTime(long start) {
		transactionStartTime = start;
	}

	public void setEndTime(long end) {
		transactionEndTime = end;
	}

	public String toString() {
		return "Customer: " + number + ", " + "entered the bank at minute " + arrivalTime + ", [Transaction Time: " + getTransation() + " mins, Wait Time: " + getWaitTime() + " mins]";
	}
}
