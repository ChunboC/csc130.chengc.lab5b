package lab5b;

public class ArrayQueue<T> implements Queue<T> {
	private T[] items;
	private int size, front, rear;	// front and rear are POSITIONS!
	private final int CAPACITY = 100;
	
	public ArrayQueue() {
		items = (T[])new Object[CAPACITY];
	}
	
	public ArrayQueue(int size) {
		items = (T[])new Object[size];
	}
	
	@Override
	public synchronized void enqueue(T data) throws QueueFullException {
		if (isFull())
			throw new QueueFullException("Queue full exception");
		items[rear] = data;
		rear = (rear + 1) % items.length;
		size++;
	}

	@Override
	public synchronized T dequeue() throws QueueEmptyException {
		if (isEmpty())
			throw new QueueEmptyException("Queue empty exception");
		T item = items[front];
		front = (front + 1) % items.length;
		size--;
		return item;
	}

	@Override
	public synchronized T front() throws QueueEmptyException {
		if (isEmpty())
			throw new QueueEmptyException("Queue empty exception");
		return items[front];
	}

	@Override
	public synchronized int getSize() {
		return size;
	}

	@Override
	public synchronized boolean isFull() {
		return size == items.length;
	}

	@Override
	public synchronized boolean isEmpty() {
		return size == 0;
	}
	
	public synchronized String toString() {
		String str = new String();
		for (int i = 0; i < size; i++) {
			str += items[(front + i) % items.length];
			str += (i < size - 1) ? ", " : ""; 
		}
		return str;
	}
}
