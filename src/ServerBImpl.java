import java.rmi.server.UnicastRemoteObject;
import java.util.Vector;
import java.rmi.Naming;
import java.rmi.RemoteException;

public class ServerBImpl extends UnicastRemoteObject implements ServerB{
    private Integer saldo;
    public ServerBImpl() throws RemoteException{
        super();
       saldo = 0;
    }
    public Respuesta anyadir_saldo(Vector<Integer> args)throws RemoteException{
        saldo+= args.get(0);
        return null;
    }
    public Respuesta obtener_saldo(Vector args) throws RemoteException{
        return new Respuesta(saldo.toString());
    }

    public static void main(String[] args) {
        System.setProperty("java.security.policy", "../configuration/java.policy");

        System.setSecurityManager(new SecurityManager());
         String name = "Bini";
        String hostName = "127.0.0.1"; //se puede usar "IPhostremoto:puerto"
        //Por defecto RMI usa el puerto 1099
        try {
            // Crear objeto remoto
            ServerB obj = new ServerBImpl();
            System.out.println("Creado server B");
            //Registrar el objeto remoto
            Naming.rebind("//" + hostName + "/"+ name, obj);
            System.out.println("Estoy registrado en RMI como "+name);


            //registrarse
            String brokerHost ="155.210.154.202:32001";
            String brokerName="Brokerini";
            Broker broker = (Broker) Naming.lookup("//"+ brokerHost + "/"+brokerName);
            System.out.println("conexión con broker");
            broker.registrar_servidor(name, hostName);
            System.out.println("Estoy registrado en broker!");
            broker.registrar_servicio(name, "anyadir_saldo");
            System.out.println("servicio anyadir_saldo registrado");
            broker.registrar_servicio(name, "obtener_saldo");
            System.out.println("servicio obtener_saldo registrado");

        }
        catch (Exception ex) {
            System.out.println(ex);
        }
    }
}
