package example.hello; 
         
import java.rmi.registry.Registry; 
import java.rmi.registry.LocateRegistry; 
import java.rmi.RemoteException; 
import java.rmi.server.UnicastRemoteObject; 

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
         
public class Server implements Hello { 
         
    public Server() {} 
 
    public String sayHello() { 
        return "Hello, world!"; 
    }

    public byte[] get(String file) {
        try {
            return Files.readAllBytes( Paths.get(file) );
        } catch (Exception e) {
        }
        return null;
    }

    public void put(String file, byte data) {
    }
         
    public static void main(String args[]) {
        int port = 3000;
        String racine = "./";
        if(args.length == 1)
            port = Integer.parseInt( args[0] );

        System.out.println("on port " + port);

        try { 
            Server obj = new Server(); 
            // Declare le stub surlequel sera expose l’objet distribue 
            Hello stub = (Hello) UnicastRemoteObject.exportObject(obj, 0); 
 
            // Permet de lier au Registre l’objet distribue  
            Registry registry = LocateRegistry.createRegistry( port ); 
            registry.bind("Hello", stub); 
 
            System.err.println("Server ready"); 
        } catch (Exception e) { 
            System.err.println("Server exception: " + e.toString()); 
            e.printStackTrace(); 
        } 
    } 
} 
