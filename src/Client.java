package example.hello; 
 
import java.rmi.registry.LocateRegistry; 
import java.rmi.registry.Registry; 
import java.io.FileOutputStream;
 
public class Client { 
 
    private Client() {} 
 
    public static void main(String[] args) { 
        int port = 3000;
        if(args.length == 1)
            port = Integer.parseInt( args[0] );

        System.out.println("on port " + port);
 
        try { 
            Registry registry = LocateRegistry.getRegistry("127.0.0.1",port); 
            Hello stub = (Hello) registry.lookup("Hello"); 
            String response = stub.sayHello(); 
            System.out.println("response: " + response); 

            byte[] bytes = stub.get("./bin/MANIFEST"); 
            FileOutputStream fos = new FileOutputStream("test");
            fos.write( bytes );
            
        } catch (Exception e) { 
            System.err.println("Client exception: " + e.toString()); 
            e.printStackTrace(); 
        } 
    } 
}
