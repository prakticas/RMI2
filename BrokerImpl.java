import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Vector;

public class BrokerImpl extends UnicastRemoteObject
implements Broker{

    public BrokerImpl() throws RemoteException
    {
        super();    //Llama al constructor de UnicastRemoteObject
    }

    //API server TODO
    public void registrar_servidor(String nombre_servidor, String host_remoto_IP_puerto) throws RemoteException{
        
    }
    public void registrar_servicio(String nombre_servidor, String nom_servicio, Vector<String> lista_param,
                            String tipo_retorno) throws RemoteException{

    }
    public void baja_servicio(String nombre_servidor, String nom_servicio) throws RemoteException{

    }

    //API cliente TODO
    /*
    public Respuesta ejecutar_servicio(String nom_servicio, Vector parametros_servicio) throws RemoteException{

    }
    public Respuesta ejecutar_servicio_asinc(String nom_servicio, Vector parametros_servicio) throws RemoteException{

    }
    public Servicios lista_servicios() throws RemoteException{

    }
    public Respuesta obtener_respuesta_asinc(String nom_servicio) throws RemoteException{
        
    }
    */
}
