

import java.util.Vector;
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ServerB extends Remote{
  

     /** 
  * Suma el valor del primer elemento del vector al saldo
  * @param  args Vector con los argumentos que son necesarios, en este un string que simbolice un numero
  * @throws RemoteException Si falla la conexión.
  * @return Respuesta 
  */
    public Respuesta anyadir_saldo(Vector<String> args) throws RemoteException;

       /** 
  * Devuelve el saldo
  * @param  args Vector con los argumentos que son necesarios, en este caso ninguno
  * @throws RemoteException Si falla la conexión.
  * @return Respuesta 
  */
    public Respuesta obtener_saldo(Vector args) throws RemoteException;
}
