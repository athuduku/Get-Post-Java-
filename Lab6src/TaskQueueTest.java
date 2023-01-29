/** 
Represent the test drive for TaskQueue
@author Ashokumar 3725744
*/
import java.text.ParseException;
public class TaskQueueTest{
	public static void main(String[] args){
		try{
			System.out.println("ENQUEUING THINGS\n");
			TaskQueue queue = new TaskQueue();
			queue.enqueue(new Task("Keith","Buy Groceries","2023-04-12","0531333"));
			queue.enqueue(new Task("Anderson","Ride Bike","2022-12-12","05313121"));
			queue.enqueue(new Task("Johnson","Sprint Run","2023-07-01","0531433"));
			queue.enqueue(new Task("Trish","Driving Car","2022-11-30","0532425"));
			System.out.println(queue);
		
			System.out.println("DEQUEUING THINGS\n");
			queue.dequeue();
			System.out.println(queue);
			queue.dequeue();
			System.out.println(queue);
			queue.dequeue();
			System.out.println(queue);
			queue.dequeue();
			System.out.println("Empty "+queue+"\n");
			queue.dequeue();
			System.out.println(queue);
		}
		catch(EmptyQueueException e){
			System.out.println(e.getMessage());
		}
		catch(ParseException e){
			System.out.println(e.getMessage());
		}
		
	}

}
