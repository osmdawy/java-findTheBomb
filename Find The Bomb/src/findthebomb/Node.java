package findthebomb;
public class Node 
{
	private Object element;
	private Node next;

	public Node(Object o, Node n) 
	{
		element = o;
		next = n;

	}

	public Object getElement() 
	{
		return element;
	}

	public Node getNext() 
	{
		return next;
	}

	public void setElement(Object newElement) 
	{
		element = newElement;
	}

	public void setNext(Node newNext) 
	{
		next = newNext;
	}
}
