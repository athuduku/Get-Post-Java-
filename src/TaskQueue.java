/** 
Represents a Queue in LinkedList.
@author Ashokumar 3725744
*/
public class TaskQueue{
	
	/**
	*	The first node in the list.
	*/
	private Node head;
	
	/**
	*	Constructs an empty list.
	*/ 
	public TaskQueue(){
		head = null;	
	}
	
	/**
	*	An inner class that represents a node in the Queue;
	*	the public variables are accessed by the Queue class.
	*/
	private class Node{
		
		/**
		*	The Task referenced by this node.
		*/
		public Task data;
		
		/**
		*	The next node in the list.
		*/
		public Node next;
		
		/**
		*	Constructs a node containing the given task.
		*	@param dataIn - The Task reference by this node.
		*/ 
		public Node(Task dataIn){
			data = dataIn;
			next = null;
		}	
	}
	
	/**
	*	Add a new node representing a task at the end of the list
	*	@param dataIn - The task to be added to the list.
	*/
	public void enqueue(Task dataIn){
		Node newNode = new Node(dataIn);
		if(head == null){
			head =	newNode;
			return;
		}
		Node curr = head;
		while(curr.next != null){
			curr = curr.next;
		}
		curr.next = newNode;
	}
	
	/**
	*	Removes the task from front of the list.
	*	@return The task to be removed.
	*	@throws EmptyQueueException - It throws when it find the list is empty.
	*/
	public Task dequeue() throws EmptyQueueException{
		if(head == null){
			throw new EmptyQueueException("queue is empty");	
		}
		Task remNode = head.data;
		head = head.next;
		return remNode;
	}
	
	/**
	*	Returns a textual string of the contents of the list in order
	*	@return The list of tasks.
	*/
	public String toString(){
		String result = "";
		Node curr = head;
		while(curr != null){
			result = result + curr.data + "\n";
			curr = curr.next;
		}
		return result;
	}
}


