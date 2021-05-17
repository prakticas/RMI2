
import java.rmi.RemoteException;
import java.rmi.Naming;
import java.rmi.server.UnicastRemoteObject;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class ServerA extends UnicastRemoteObject{

    public ServerA() throws RemoteException{
        super();
    }

    public String dar_hora() throws RemoteException
    {
        LocalDateTime lD = LocalDateTime.now();
        int horas  = lD.getHour();
        int minutos = lD.getMinute();
        int segundos = lD.getSecond();
        return horas  + ":"+ minutos + ":" + segundos; 
    }
    public String dar_fecha() throws RemoteException{
        Calendar c = new GregorianCalendar();
        String dia = Integer.toString(c.get(Calendar.DATE));
        String mes = Integer.toString(c.get(Calendar.MONTH)+1); //+1 porque sino cuenta desde 0
        String anyo = Integer.toString(c.get(Calendar.YEAR));
        return dia+"/"+mes+"/"+anyo;
    }
    public static void main(String[] args) {
        //Fijar el directorio donde se encuentra el java.policy
        //El segundo argumento es la ruta al java.policy
        System.setProperty("java.security.policy", "../configuration/java.policy");
        //Crear administrador de seguridad
        System.setSecurityManager(new SecurityManager());
        //nombre o IP del host donde reside el objeto servidor
        String hostName = "127.0.0.1"; //se puede usar "IPhostremoto:puerto"
        //Por defecto RMI usa el puerto 1099
        try {
            // Crear objeto remoto
            ServerA obj = new ServerA();
            System.out.println("Creado!");
            //Registrar el objeto remoto
            Naming.rebind("//" + hostName + "/ServerA", obj);
            System.out.println("Estoy registrado!");
        }
        catch (Exception ex) {
            System.out.println(ex);
        }
    }
}
