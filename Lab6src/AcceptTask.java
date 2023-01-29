import java.io.*;  
import java.net.*;  

/** 
Represents a AcceptTask.
@author Ashokumar 3725744
*/
public class AcceptTask {  

    /**
     * AcceptTask
     *      Currently this retrieves a single string, from a 
     *      host and port that is running a TaskServer and prints it. 
     *      By the end of the lab it should retrieve a Task object and print it
     *      out. The default host of "localhost" can be altered by secifying a
     *      hostname or IP address in the commandline as args[0]. The default
     *      port of 61340 can also be altered by specifying it a 
     *      command line argument (args[1]). Note that to specify a port, 
     *      the hostname must also be specified.
     * 
     * Usage: $ java AcceptTask <host> <port>
     * 
     * @param args
     */
    public static void main(String[] args) { 
		// host name
        String host = "localhost";
		// port at specified IP address
        int port = 61340;
		// command for the AcceptTask
		// GET - retrieves the data from server
        String command = "GET";
        if(args.length > 0) host = args[0];
        if(args.length == 2){
            try{
                port = Integer.parseInt(args[1]);
            }
            catch(Exception e){
                System.err.println("Cannot use "+args[0]+ "as a port number. Using default 61340.");
            }
        }
        try{
			// creates an endpoint for communication for
			// between the client/host and server.
            Socket s=new Socket(host, port);  
	
			// creates a writer for that socket
            ObjectOutputStream oos=new ObjectOutputStream(s.getOutputStream()); 
			// creates a reader for that socket
            ObjectInputStream ois=new ObjectInputStream(s.getInputStream());  
			
			// writes a command in server
            oos.writeObject(command);  

            int  success =(int)ois.readObject();  
            if(success == 200){
				// reads the data from server
				Task removedTask = (Task)ois.readObject();
                System.out.println("{'command' : '"+command+
                    "', 'message' : '"+removedTask.toString()+"'}");
            }
            s.close();  
        }
		catch(EOFException e){
			System.out.println("empty queue exception");
		}
        catch(Exception e){
            e.printStackTrace();
        }  
    }  
}  