

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Vector;


public interface ServerA extends Remote{
  /** 
  * da la hora actual
  * @param  args Vector con los argumentos que son necesarios, en este caso ninguno
  * @throws RemoteException Si falla la conexión.
  * @return Respuesta 
  */
    public Respuesta dar_hora(Vector args) throws RemoteException;
   
    /** 
  * da la fecha actual
  * @param  args Vector con los argumentos que son necesarios, en este caso ninguno
  * @throws RemoteException Si falla la conexión.
  * @return Respuesta 
  */
    public Respuesta dar_fecha(Vector args) throws RemoteException;

}