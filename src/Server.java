package example.hello; 
         
import java.rmi.registry.Registry; 
import java.rmi.registry.LocateRegistry; 
import java.rmi.RemoteException; 
import java.rmi.server.UnicastRemoteObject; 
         
public class Server implements Hello { 
         
    public Server() {} 
 
    public String sayHello() { 
        return "Hello, world!"; 
    } 
         
    public static void main(String args[]) {
        int port = 3000;
        System.out.println(args[0]);
        if(args.length > 1)
            port = Integer.parseInt( args[0] );
         
        try { 
            Server obj = new Server(); 
            // Declare le stub surlequel sera expose l’objet distribue 
            Hello stub = (Hello) UnicastRemoteObject.exportObject(obj, 0); 
 
            // Permet de lier au Registre l’objet distribue  
            Registry registry = LocateRegistry.createRegistry(1099); 
            registry.bind("Hello", stub); 
 
            System.err.println("Server ready"); 
        } catch (Exception e) { 
            System.err.println("Server exception: " + e.toString()); 
            e.printStackTrace(); 
        } 
    } 
} 
