import java.io.*;  
import java.net.*;  

/**
 * @author Rick Wightman
 * December 2022.
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
        String host = "localhost";
        int port = 61340;
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
            Socket s=new Socket(host, port);  

            ObjectOutputStream oos=new ObjectOutputStream(s.getOutputStream());  
            ObjectInputStream ois=new ObjectInputStream(s.getInputStream());  
            oos.writeObject(command);  

            int  success =(int)ois.readObject();  
            if(success == 200){
                String message=(String)ois.readObject();  
                System.out.println("{'command' : '"+command+
                    "', 'message' : '"+message+"'}");
            }
            s.close();  
        }
        catch(Exception e){
            System.out.println(e);
        }  
    }  
}  