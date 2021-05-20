
import java.rmi.server.UnicastRemoteObject;
import java.util.Vector;
import java.rmi.RemoteException;
public class ServerB extends UnicastRemoteObject{
    private String horaAct; 
    public ServerB() throws RemoteException{
        super();
        horaAct = "No se :C";
    }
    public void cambiar_hora(Vector<String> args){
        horaAct = args.get(0);
    }
    public String dar_hora(){
        return horaAct;
    }
}
