
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Hashtable;
import java.util.Vector;
import java.rmi.Naming;
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
        services = new Servicios();
      
    }


    public void registrar_servidor(String nombre_servidor, String host_remoto_IP_puerto) throws RemoteException{

            try {
              
              Remote server = Naming.lookup("//"+host_remoto_IP_puerto+ "/"+ nombre_servidor);

              lista_servidores.put(nombre_servidor, server);
             
            } catch (Exception e) {
              System.err.println("No se puedo acceder a server "+nombre_servidor);
            }
  
    }

    public void registrar_servicio(String nombre_servidor, String nom_servicio) throws RemoteException{

          //System.out.println(lista_servidores.get(nombre_servidor));
          services.newServicio(lista_servidores.get(nombre_servidor), nom_servicio);
    }


    public void baja_servicio(String nombre_servidor, String nom_servicio) throws RemoteException{

      services.deleteServicio(nom_servicio);

    }

    //API cliente 
    
    public Respuesta ejecutar_servicio(String nom_servicio, Vector<?> parametros_servicio) throws RemoteException{
      return services.ejecutar(nom_servicio, parametros_servicio);
    }

    //TODO
    public Respuesta ejecutar_servicio_asinc(String nom_servicio, Vector<?> parametros_servicio) throws RemoteException{
      return null;
    }

    
    public Servicios lista_servicios() throws RemoteException{
      return services;
    }

    //TODO
    public Respuesta obtener_respuesta_asinc(String nom_servicio) throws RemoteException{
      return null;  
    }


    public static void main(String[] args) {

      //registrarse en rmi
      System.setProperty("java.security.policy", "./configuration/java.policy");
      System.setSecurityManager(new SecurityManager());
      String name = "Brokerini";
      String hostName = "127.0.0.1"; //se puede usar "IPhostremoto:puerto"
      //Por defecto RMI usa el puerto 1099

      try {
          // Crear objeto remoto
          Broker obj = new BrokerImpl();
          System.out.println("broker.Broker  creado!");
          //Registrar el objeto remoto
          Naming.rebind("//" + hostName + "/"+name, obj);
          System.out.println("broker.Broker  registrado!");
      }
      catch (Exception ex) {
          System.out.println(ex);
      }
  }
    
}
