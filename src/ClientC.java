
import java.rmi.Naming;
public class ClientC {
    public static void main(String[] args){
        //Fijar el directorio donde se encuentra el java.policy
        System.setProperty("java.security.policy", "../configuration/java.policy");
        if (System.getSecurityManager() == null) {
            //Crear administrador de seguridad
            System.setSecurityManager(new SecurityManager());
        }
        try {
            
            String hostname = "127.0.0.1"; //se puede usar "IP:puerto"
            Broker broker = (Broker) Naming.lookup("//"+ hostname + "/Broker");
       
            Respuesta today = broker.ejecutar_servicio("dar_fecha",null);
            Respuesta thisHour = broker.ejecutar_servicio("dar_hora",null);
            System.out.println("Hoy es: " + today + " y la hora actual es: " + thisHour);
        } 
        catch (Exception ex) {
            System.out.println(ex);
        }
    }
}
