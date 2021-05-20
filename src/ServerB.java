

import java.util.Vector;
import java.rmi.Remote;
import java.rmi.RemoteException;
public interface ServerB extends Remote{
  

    public Respuesta anyadir_saldo(Vector<Integer> args) throws RemoteException;
    public Respuesta obtener_saldo(Vector args) throws RemoteException;
}
