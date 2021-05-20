
import java.rmi.RemoteException;
import java.rmi.Naming;
import java.rmi.server.UnicastRemoteObject;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Vector;

public class ServerA extends UnicastRemoteObject{

    public ServerA() throws RemoteException{
        super();
    }

    public String dar_hora(Vector<String> args) throws RemoteException
    {
        LocalDateTime lD = LocalDateTime.now();
        int horas  = lD.getHour();
        int minutos = lD.getMinute();
        int segundos = lD.getSecond();
        return horas  + ":"+ minutos + ":" + segundos; 
    }
    public String dar_fecha(Vector<String> args) throws RemoteException{
        Calendar c = new GregorianCalendar();
        String dia = Integer.toString(c.get(Calendar.DATE));
        String mes = Integer.toString(c.get(Calendar.MONTH)+1); //+1 porque sino cuenta desde 0
        String anyo = Integer.toString(c.get(Calendar.YEAR));
        return dia+"/"+mes+"/"+anyo;
    }
    public static void main(String[] args) {
        System.setProperty("java.security.policy", "../configuration/java.policy");

        System.setSecurityManager(new SecurityManager());

        String hostName = "127.0.0.1"; //se puede usar "IPhostremoto:puerto"
        //Por defecto RMI usa el puerto 1099
        try {
            // Crear objeto remoto
            ServerA obj = new ServerA();
            System.out.println("Creado server A");
            //Registrar el objeto remoto
            Naming.rebind("//" + hostName + "/ServerA", obj);
            System.out.println("Estoy registrado!");


            //registrarse
            String brokerName ="127.0.0.1";
            Broker broker = (Broker) Naming.lookup("//"+ brokerName + "/Broker");
            broker.registrar_servidor("A", hostName);
            broker.registrar_servicio("A", "dar_fecha");
            broker.registrar_servicio("A", "dar_hora");

        }
        catch (Exception ex) {
            System.out.println(ex);
        }
    }
}
