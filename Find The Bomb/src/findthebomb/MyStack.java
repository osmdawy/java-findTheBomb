package findthebomb;
public interface MyStack
{
	/*
	 * Return the number of the elements in the stack 
	 * @return the number of elements in the stack
	 */
	public int size();
	
	/*
	 * Tests if this stack is empty
	 * @return true if the stack is empty and false if the stack is not empty
	 */
	public boolean isEmpty();
	
	/*
	 * Get the object at the top of this stack without removing it from the stack.
	 * @return the object
	 * @return exception if the stack is empty
	 */
	public Object top();
	
	/*
	 * Removes the object at the top of this stack and 
	 * @returns the object as the value of this function.
	 * @return exception if the stack is empty
	 */
	public Object pop();
	
	/*
	 * Pushes an item onto the top of this stack
	 * @parameter object to be add at the stack
	 */
	public void push(Object o);
	
	
}
