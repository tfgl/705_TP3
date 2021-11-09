package example.hello; 
 
import java.rmi.Remote; 
import java.rmi.RemoteException; 
 
public interface Hello extends Remote { 
    String sayHello() throws RemoteException; 
    byte[] get(String file) throws RemoteException;
    void put(String file, byte data) throws RemoteException;
}
