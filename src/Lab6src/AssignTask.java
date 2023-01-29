import java.io.*;  
import java.net.*;
import java.util.Scanner;  
/**
 * @author Rick Wightman
 * December 2022.
 */
public class AssignTask {  
    
    /**
     * AssignTask 
     *      Currently this collects a single string, and sends it to a 
     *      host and port that is running a TaskServer. By the end
     *      of the lab it should collect iformation and send a Task object.
     *      The default host of "localhost" can be altered by secifying a
     *      hostname or IP address in the commandline as args[0]. The default
     *      port of 61340 can also be altered by specifying it a 
     *      command line argument (args[1]). Note that to specify a port, 
     *      the hostname must also be specified.
     * 
     * Usage: $ java AssignTask <host> <port>
     * 
     * @param args
     */
        public static void main(String[] args) {  
        String host = "localhost";
        int port = 61340;
        String command = "POST";
        
        if(args.length > 0) host = args[0];
        if(args.length == 2){
            try{
                port = Integer.parseInt(args[1]);
            }
            catch(Exception e){
                System.err.println("Cannot use "+args[0]+ "as a port number. Using default 61340.");
            }
        }
        Scanner scanner = new Scanner(System.in);
        System.out.print("Message: ");
        String message = scanner.nextLine();
        try{      
            Socket s=new Socket(host,port);  
            ObjectOutputStream oos=new ObjectOutputStream(s.getOutputStream());  
            oos.writeObject(command);  
            oos.writeObject(message);  
            oos.flush();  
            oos.close();  
            s.close();  
        }
        catch(Exception e){
            System.out.println(e);
        }  
    }  
}  