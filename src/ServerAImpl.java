import java.rmi.RemoteException;
import java.rmi.Naming;
import java.rmi.server.UnicastRemoteObject;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Vector;
import java.util.Scanner;

public class ServerAImpl extends UnicastRemoteObject implements ServerA{

    public ServerAImpl() throws RemoteException {
        super();
    }

     /** 
  * da la hora actual
  * @param  args Vector con los argumentos que son necesarios, en este caso ninguno
  * @throws RemoteException Si falla la conexión.
  * @return Respuesta 
  */
    public Respuesta dar_hora(Vector args) throws RemoteException
    {
        LocalDateTime lD = LocalDateTime.now();
        int horas  = lD.getHour();
        int minutos = lD.getMinute();
        int segundos = lD.getSecond();
        return new Respuesta(horas  + ":"+ minutos + ":" + segundos); 
      
        
    }


      /** 
  * da la fecha actual
  * @param  args Vector con los argumentos que son necesarios, en este caso ninguno
  * @throws RemoteException Si falla la conexión.
  * @return Respuesta 
  */
    public Respuesta dar_fecha(Vector args) throws RemoteException{
        Calendar c = new GregorianCalendar();
        String dia = Integer.toString(c.get(Calendar.DATE));
        String mes = Integer.toString(c.get(Calendar.MONTH)+1); //+1 porque sino cuenta desde 0
        String anyo = Integer.toString(c.get(Calendar.YEAR));
        return new Respuesta(dia+"/"+mes+"/"+anyo);
    }
    public static void main(String[] args) {
        System.setProperty("java.security.policy", "../configuration/java.policy");

        System.setSecurityManager(new SecurityManager());
         String name = "Aini";
        String hostName = "155.210.154.203:32101"; //se puede usar "IPhostremoto:puerto"
        //Por defecto RMI usa el puerto 1099
        try {
            // Crear objeto remoto
            ServerA obj = new ServerAImpl();
            System.out.println("Creado server A");
            //Registrar el objeto remoto
            Naming.rebind("//" + hostName + "/"+ name, obj);
            System.out.println("Estoy registrado en RMI como "+name);

            //registrarse
            String brokerHost ="155.210.154.202:32101";
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
