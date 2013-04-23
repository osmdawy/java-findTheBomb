package findthebomb;
import java.util.ArrayList;

public class PriorityQueue 
{

	ArrayList<TermPQ> perQueue;
	
	public PriorityQueue()
	{
		perQueue = new ArrayList<TermPQ>();
	}
	
	
	public void enqueue(TermPQ item) 
	{
		perQueue.add(item);
		
	}
	
	
	public TermPQ dequeue() 
	{
		int minValue = Integer.MAX_VALUE;
		int index = -1;
		for(int i =0 ; i < perQueue.size() ;i++)
		{
			if(perQueue.get(i).getKeyValue()<minValue)
			{
				minValue = perQueue.get(i).getKeyValue();
				index=i;
			}
		}
		TermPQ answer =  perQueue.get(index);
		perQueue.remove(index);
		return answer;
	}

	public boolean isEmpty() {
		return perQueue.isEmpty();
	}


	public int size() {
		return perQueue.size();
	}
	

}
