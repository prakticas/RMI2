

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Vector;
import java.util.Hashtable;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.Remote;
 

public class BrokerImpl extends UnicastRemoteObject
implements Broker{

    private Servicios services;
    private Hashtable<String,Remote> lista_servidores = null; //valdría linked list pero hago un map pensando en dinamico

    public BrokerImpl() throws RemoteException
    {
        super();    //Llama al constructor de UnicastRemoteObject
        System.setProperty("java.security.policy", "../configuration/java.policy");
        System.setSecurityManager(new SecurityManager());
        lista_servidores = new Hashtable<String,Remote>();
      
    }


    public void registrar_servidor(String nombre_servidor, String host_remoto_IP_puerto) throws RemoteException{
       
        if (nombre_servidor=="A")
        { 
            try {
              ServerA serverA = (ServerA) Naming.lookup("//"+host_remoto_IP_puerto + "/"+ nombre_servidor);
              lista_servidores.put(nombre_servidor, serverA);
            } catch (MalformedURLException | NotBoundException e) {
              System.err.println("No se puedo acceder a server A");
            }

        } 
        else if (nombre_servidor=="B")
        {
          try
          {
          ServerB serverB =  (ServerB) Naming.lookup("//"+host_remoto_IP_puerto + "/"+ nombre_servidor);
          lista_servidores.put(nombre_servidor, serverB);
        } catch (MalformedURLException | NotBoundException e) {

         System.err.println("No se puedo acceder a server B");
        }

        }
        
    }

    public void registrar_servicio(String nombre_servidor, String nom_servicio) throws RemoteException{
        

          
          services.newServicio(lista_servidores.get(nombre_servidor), nom_servicio);
    }



    public void baja_servicio(String nombre_servidor, String nom_servicio) throws RemoteException{

      services.deleteServicio(nom_servicio);

    }

    //API cliente TODO
    
    public Respuesta ejecutar_servicio(String nom_servicio, Vector parametros_servicio) throws RemoteException{
      return null;
    }

    //TODO
    public Respuesta ejecutar_servicio_asinc(String nom_servicio, Vector parametros_servicio) throws RemoteException{
      return null;
    }

    //TODO
    public Servicios lista_servicios() throws RemoteException{
      return services;
    }

    //TODO
    public Respuesta obtener_respuesta_asinc(String nom_servicio) throws RemoteException{
      return null;  
    }


    public static void main(String[] args) {

      //registrarse en rmi
      System.setProperty("java.security.policy", "../configuration/java.policy");
      System.setSecurityManager(new SecurityManager());
      String hostName = "127.0.0.1"; //se puede usar "IPhostremoto:puerto"
      //Por defecto RMI usa el puerto 1099

      try {
          // Crear objeto remoto
          Broker obj = new BrokerImpl();
          System.out.println("Broker registrado creado!");
          //Registrar el objeto remoto
          Naming.rebind("//" + hostName + "/Broker", obj);
          System.out.println("Btokrt registrado registrado!");
      }
      catch (Exception ex) {
          System.out.println(ex);
      }
  }
    
}
