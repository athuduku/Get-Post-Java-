/**
Represents EmptyQueueException
which throw Exception when it finds the
the empty list.
@author Ashokumar 3725744
*/
public class EmptyQueueException extends Exception{
	// default constructor
	public EmptyQueueException(){}
	
	/**
	*	Constructs EmptyQueueException.
	*	@param messageIn The exception message.
	*/
	public EmptyQueueException(String messageIn){
		super(messageIn);
	}
}
