
import java.rmi.server.UnicastRemoteObject;
import java.rmi.RemoteException;
public class ServerB extends UnicastRemoteObject{
    private String horaAct; 
    public ServerB() throws RemoteException{
        super();
        horaAct = "No se :C";
    }
    public void cambiar_hora(String newHora){
        horaAct = newHora;
    }
    public String dar_hora(){
        return horaAct;
    }
}
