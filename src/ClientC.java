import java.rmi.Naming;
import java.util.Vector;
import java.util.Scanner;

public class ClientC {
    public static void main(String[] args){
        //Fijar el directorio donde se encuentra el java.policy
        System.setProperty("java.security.policy", "../configuration/java.policy");
        String brokerName = "Brokerini";
        if (System.getSecurityManager() == null) {
            //Crear administrador de seguridad
            System.setSecurityManager(new SecurityManager());
        }
        try {
            
            String hostname = "155.210.154.202:32101"; //se puede usar "IP:puerto"
            Broker broker = (Broker) Naming.lookup("//"+ hostname + "/"+brokerName);

            String respuesta = "";
            Scanner teclado = new Scanner(System.in);
            String numero = "";
            String[] palabras;
            
            do{
                System.out.println("Tenemos los siguientes servicios: ");
                System.out.println(broker.lista_servicios());
                System.out.print("¿Qué servicio desea ejecutar?: ");
                respuesta = teclado.nextLine();
                palabras = respuesta.split(" ");
                Vector<String> vPalabras = new Vector<String>();
                
                for(String item : palabras){
                    vPalabras.addElement(item);
                }
                vPalabras.removeElementAt(0);
                try{
                    System.out.println(broker.ejecutar_servicio(palabras[0],vPalabras)+"\n"); 
                }catch(Exception e){
                    System.out.println("No existe dicho servicio XD");
                }
            } while( !respuesta.equals(""));
            teclado.close();
        } 
        catch (Exception ex) {
            System.out.println(ex);
        }
    }
}
