import java.rmi.RemoteException;
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
        try{
        ServerA sa = new ServerA();
        String dia = sa.dar_fecha();
        System.out.println(dia);
        String hora = sa.dar_hora();
        System.out.println(hora);
        }
        catch(Exception e){}
    }
}
