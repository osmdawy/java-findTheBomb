
package findthebomb;


import java.io.IOException;

public class QueueArray implements MyQueue 
{
	private Object[] queue;
	private int capacity;
	public static final int CAPACITY = 100000;
	private int f = 0;
	private int r = 0;

	public QueueArray() 
	{
		queue = new Object[CAPACITY];
		capacity = CAPACITY;
	}

	public QueueArray(int cap) 
	{
		queue = new Object[cap];
		capacity = cap;
	}

	@Override
	public Object dequeue() 
	{
		// TODO Auto-generated method stub
		if (isEmpty()) 
		{
			throw new RuntimeException("Empty Queue");
		}
		Object temp = queue[f];
		queue[f] = null;
		f = (f + 1) % capacity;
		return temp;

	}

	@Override
	public void enqueue(Object item) 
	{
		// TODO Auto-generated method stub
		if (size() == capacity - 1) 
		{
			throw new RuntimeException("FullQueueException");
		}
		queue[r] = item;
		r = (r + 1) % capacity;
	}

	@Override
	public boolean isEmpty() 
	{
		// TODO Auto-generated method stub
		return (f == r);

	}

	@Override
	public int size() 
	{
		// TODO Auto-generated method stub
		return (capacity - f + r) % capacity;
	}

	public static void main(String[] args) throws IOException 
	{
		QueueArray q = new QueueArray(3);
		q.enqueue(1);
		q.enqueue(3);
		q.dequeue();
		q.enqueue(5);

		System.out.println(q.size());
		System.out.println(q.dequeue());
	}

}
