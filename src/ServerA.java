import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Vector;


public interface ServerA extends Remote{

    public String dar_hora(Vector<String> args) throws RemoteException;
   
    public String dar_fecha(Vector<String> args) throws RemoteException;

}