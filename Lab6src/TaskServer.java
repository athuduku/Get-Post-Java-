import java.net.*;  
import java.io.*;
/** 
Represents a TaskServer.
@author Ashokumar 3725744
*/
public class TaskServer {

    /**
     * TaskServer 
     *      Currently this deals in a single string, but by
     *      the end of the lab it should be a queue for Task objects.
     *      The default port of 61340 can be altered by specifying it a 
     *      command line argument (args[0]).
     * 
     * Usage: $ java TaskServer <port>
     * 
     * @param args
     */
    public static void main(String[] args){ 
        ServerSocket ss = null;
		// port number
        int port = 61340;
		// host name
        String host = "localhost";
        try{
			// gets the host address
            host = InetAddress.getLocalHost().getHostName();
        }
        catch(UnknownHostException e){
            // Do nothing
        }
        if(args.length == 1){
            try{
                port = Integer.parseInt(args[0]);
            }
            catch(Exception e){
                System.err.println("Cannot use "+args[0]+ "as a port number. Using default 61340.");
            }
        }
        try{
			// create an object ServerSocket using port
            ss=new ServerSocket(61340);  
        }
        catch(Exception e){
            System.out.println(e);
            System.exit(1);
        }
        System.out.println("TaskServer started on "+host+":"+port);

		// object for task
        Task newTask = null;
		// list of tasks
		TaskQueue queue = new TaskQueue();
		// message of the task
		String message = null;
        while(true){
            try{
                Socket s=ss.accept();//establishes connection				
				// Binary File Reader
                ObjectInputStream ois=new ObjectInputStream(s.getInputStream());
				
				// Binary File Writer
                ObjectOutputStream oos=new ObjectOutputStream(s.getOutputStream());
				
				// Read POST/GET from the AssignTask or AcceptTask
                String  command=(String)ois.readObject();
				
                switch(command){
					// client sends the data to server
                    case "POST":
						// server reads the data from client
						newTask = (Task)ois.readObject();
						message = newTask.toString();
						// store the data into the queue
						queue.enqueue(newTask);
                        break;
					//  requesting data from server
                    case "GET":
                        try{
							// removes the first item in the list
							newTask = queue.dequeue();
                            //message = message.toLowerCase();
                        } 
						catch(EmptyQueueException e){
							System.out.println("empty queue exception");
							break;
						}
                        catch(Exception e){
                            oos.writeObject(404);
                            break;
                        }
                        oos.writeObject(200);
						// server sends data to the client
                        oos.writeObject(newTask);  
                        message = null;
                        break;
                }  
                s.close();
            }
            catch(Exception e){
                System.out.println(e);
            } 
        }  
    }
}
