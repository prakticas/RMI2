import java.rmi.server.UnicastRemoteObject;
import java.util.Vector;
import java.util.Scanner;
import java.rmi.Naming;
import java.rmi.RemoteException;

public class ServerBImpl extends UnicastRemoteObject implements ServerB{
    private Integer saldo;
    public ServerBImpl() throws RemoteException{
        super();
       saldo = 0;
    }
    public Respuesta anyadir_saldo(Vector<String> args)throws RemoteException{
        
        saldo+= Integer.parseInt(args.get(0));
        return null;
    }
    public Respuesta obtener_saldo(Vector args) throws RemoteException{
        return new Respuesta(saldo.toString());
    }

    public static void main(String[] args) {
        System.setProperty("java.security.policy", "../configuration/java.policy");

        System.setSecurityManager(new SecurityManager());
         String name = "Bini";
        String hostName = "155.210.154.203:32001"; //se puede usar "IPhostremoto:puerto"
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
            System.out.println("Conexión con broker");
            broker.registrar_servidor(name, hostName);

            String respuesta = "";
            Scanner teclado = new Scanner(System.in);
            String numero = "";
            
            do{
                System.out.print("Pulse 1 para registrar servicio o 2 para dar de baja: ");
                numero = teclado.nextLine();
                if(numero.equals("1")){
                    System.out.print("¿Qué servicio desea registar?: ");
                    respuesta = teclado.nextLine();
                    //respuesta="dar_fecha";
                    System.out.println(respuesta);
                    broker.registrar_servicio(name, respuesta);
                }
                else if(numero.equals("2")){
                    System.out.print("¿Qué servicio desea dar de baja?: ");
                    respuesta = teclado.nextLine();
                    broker.baja_servicio(name, respuesta);
                }
            } while( !numero.equals("") );
            teclado.close();

        }
        catch (Exception ex) {
            System.out.println(ex);
        }
    }
}
