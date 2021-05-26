
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Vector;

public interface Broker extends Remote{
    //API server 
    //===============

    /**
  * Registra el servidor en el broker
  * @param  nombre_servidor El nombre con el que se registra el server.
  * @param  host_remoto_IP_puerto La dirección ip y puerto. 
  * @throws RemoteException Si falla la conexión.
  */
    void registrar_servidor(String nombre_servidor, String host_remoto_IP_puerto) throws RemoteException;

    /**
  * Registra el servidor en el broker
  * @param  nombre_servidor El nombre del server en el que esta el servicio.
  * @param  nom_servicio Nombre del servcio a registrar 
  * @throws RemoteException Si falla la conexión.
  */
    void registrar_servicio(String nombre_servidor, String nom_servicio) throws RemoteException;

    /**
  * Eliminar un servico del broker
  * @param  nombre_servidor El nombre del server en el que esta el servicio.
  * @param  nom_servicio Nombre del servcio a dar de baja 
  * @throws RemoteException Si falla la conexión.
  */
    void baja_servicio(String nombre_servidor, String nom_servicio) throws RemoteException;
    
    //API cliente 
    //===================

     /**
  * Eliminar un servico del broker
  * @param  nom_servicio Nombre del servcio a ejecutar
  * @param  parametros_servicio Vector de parámetros necesarios
  * @throws RemoteException Si falla la conexión.
  * @return Respuesta 
  */
    Respuesta ejecutar_servicio(String nom_servicio, Vector<?> parametros_servicio) throws RemoteException;

    //TODO
    Respuesta ejecutar_servicio_asinc(String nom_servicio, Vector<?> parametros_servicio) throws RemoteException;
    /**
  * Lista todos los servicios registrados en el broker
  * @throws RemoteException Si falla la conexión.
  * @return Respuesta 
  */
    Respuesta lista_servicios() throws RemoteException;

    //TODO
    Respuesta obtener_respuesta_asinc(String nom_servicio) throws RemoteException;
}
