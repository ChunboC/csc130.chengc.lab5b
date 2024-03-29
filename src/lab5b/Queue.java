package lab5b;

public interface Queue <T>{
	void enqueue(T data) throws QueueFullException;
	T dequeue() throws QueueEmptyException;
	T front() throws QueueEmptyException;
	int getSize();
	boolean isFull();
	boolean isEmpty();
}
