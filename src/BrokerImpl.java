

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Vector;
import java.rmi.Naming;
 

public class BrokerImpl extends UnicastRemoteObject
implements Broker{

    private Servicios service;

    public BrokerImpl() throws RemoteException
    {
        super();    //Llama al constructor de UnicastRemoteObject

        System.setProperty("java.security.policy", "../configuration/java.policy");
        System.setSecurityManager(new SecurityManager());
      
    }

    //API server TODO
    public void registrar_servidor(String nombre_servidor, String host_remoto_IP_puerto) throws RemoteException{
        try
        {      
          Server server =
          (Server) Naming.lookup("//"+ host_remoto_IP_puerto+ "/" + nombre_servidor);  
        }
        catch (Exception e)
        {
          System.out.println(e);
        }
    }
    public void registrar_servicio(String nombre_servidor, String nom_servicio, Vector lista_param,
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
