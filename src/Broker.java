import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Vector;

public interface Broker extends Remote{
    //API server TODO
    void registrar_servidor(String nombre_servidor, String host_remoto_IP_puerto) throws RemoteException;
    void registrar_servicio(String nombre_servidor, String nom_servicio, Vector<String> lista_param,
                            String tipo_retorno) throws RemoteException;
    void baja_servicio(String nombre_servidor, String nom_servicio) throws RemoteException;
    
    //API cliente TODO
    //Respuesta ejecutar_servicio(String nom_servicio, Vector parametros_servicio) throws RemoteException;
    //Respuesta ejecutar_servicio_asinc(String nom_servicio, Vector parametros_servicio) throws RemoteException;
    //Servicios lista_servicios() throws RemoteException;
    //Respuesta obtener_respuesta_asinc(String nom_servicio) throws RemoteException;
}