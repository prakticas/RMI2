package serverA;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Vector;

import util.Respuesta;


public interface ServerA extends Remote{

    public Respuesta dar_hora(Vector args) throws RemoteException;
   
    public Respuesta dar_fecha(Vector args) throws RemoteException;

}