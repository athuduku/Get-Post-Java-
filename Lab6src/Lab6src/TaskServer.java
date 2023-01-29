import java.net.*;  
import java.io.*;
/**
 * @author Rick Wightman
 * December 2022.
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
        int port = 61340;
        String host = "localhost";
        try{
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
            ss=new ServerSocket(61340);  
        }
        catch(Exception e){
            System.out.println(e);
            System.exit(1);
        }
        System.out.println("TaskServer started on "+host+":"+port);

        String message = null;
        while(true){
            try{
                Socket s=ss.accept();//establishes connection   
                ObjectInputStream ois=new ObjectInputStream(s.getInputStream());
                ObjectOutputStream oos=new ObjectOutputStream(s.getOutputStream());
                String  command=(String)ois.readObject();
                switch(command){
                    case "POST":
                        message=(String)ois.readObject();
                        System.out.println("{'command' : '"+command+
                            "', 'message' : '"+message+"'}"); //DEBUG
                        break;
                    case "GET":
                        try{
                            message = message.toLowerCase();
                        } 
                        catch(Exception e){
                            oos.writeObject(404);
                            break;
                        }
                        oos.writeObject(200);
                        oos.writeObject(message);  
                        message = null;
                        System.out.println("{'command' : '"+command+"'}"); //DEBUG
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
