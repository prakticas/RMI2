import java.rmi.Naming;
public class ClientC {
    public static void main(String[] args){
        //Fijar el directorio donde se encuentra el java.policy
        System.setProperty("java.security.policy", "../java.policy");
        if (System.getSecurityManager() == null) {
            //Crear administrador de seguridad
            System.setSecurityManager(new SecurityManager());
        }
        try {
            //Paso 1 - Obtener una referencia al objeto servidor creado anteriormente
            //Nombre del host servidor o su IP. Es dónde se buscará al objeto remoto
            String hostname = "127.0.0.1"; //se puede usar "IP:puerto"
            ServerA servera = (ServerA) Naming.lookup("//"+ hostname + "/MyServerA");
            ServerB serverb = (ServerB) Naming.lookup("//"+ hostname + "/MyServerB");
            //Paso 2 - Invocar remotamente los metodos del objeto servidor:
            //Obtener el nombre de la colección y el número de libros
            String today = servera.dar_fecha();
            String thisHour = servera.dar_fecha();
            //Cambiar el nombre de la coleccion y ver que ha funcionado
            System.out.println("Hoy es: " + today + " y la hora actual es: " + thisHour);
            String hsb = serverb.dar_hora();
            System.out.println("El pobre ServerB se cree que son las: " + hsb + " vamos a darle la hora buena.");
            serverb.cambiar_hora(thisHour);
            hsb = serverb.dar_hora();
            System.out.println("Ahora el ServerB tiene la hora: " + hsb );
        }
        catch (Exception ex) {
            System.out.println(ex);
        }
    }
}
