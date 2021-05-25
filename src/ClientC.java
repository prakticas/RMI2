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
            
            String hostname = "155.210.154.202:32001"; //se puede usar "IP:puerto"
            Broker broker = (Broker) Naming.lookup("//"+ hostname + "/"+brokerName);

            String respuesta = "";
            Scanner teclado = new Scanner(System.in);
            String numero = "";
            
            do{
                System.out.println("Tenemos los siguientes servicios: ");
                System.out.println(broker.lista_servicios());
                System.out.print("¿Qué servicio desea ejecutar?: ");
                respuesta = teclado.nextLine();
                if(respuesta.equals("anyadir_saldo")){
                    System.out.print("Introduzca el saldo a añadir: ");
                    numero = teclado.nextLine();
                    Vector<Integer> salario = new Vector<Integer>(Integer.parseInt(numero));
                    try{
                        broker.ejecutar_servicio(respuesta,salario);
                    }catch(Exception e){
                        System.out.println("No existe dicho servicio XD");
                    }
            
                }
                else{
                    System.out.println("OK, intentando ejecutar: " + respuesta);
                    Respuesta serverResponse = broker.ejecutar_servicio(respuesta,null);
                    System.out.println("El servidor nos ha devuelto: " + serverResponse);
                }
            } while( !respuesta.equals(""));
            teclado.close();
            /*
            util.Respuesta today = broker.ejecutar_servicio("dar_fecha",null);
            util.Respuesta thisHour = broker.ejecutar_servicio("dar_hora",null);
            System.out.println("Hoy es: " + today + " y la hora actual es: " + thisHour);
            util.Respuesta Saldo = broker.ejecutar_servicio("obtener_saldo",null);
            System.out.println("mi saldo es "+ Saldo);
            Vector<Integer> salario = new Vector<Integer>(1);
            salario.add(15);
            broker.ejecutar_servicio("anyadir_saldo",salario);
            Saldo = broker.ejecutar_servicio("obtener_saldo",null);
            System.out.println("mi saldo es "+ Saldo);*/
        } 
        catch (Exception ex) {
            System.out.println(ex);
        }
    }
}
