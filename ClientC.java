public class ClientC {
    public static void main(String[] args){
        //Fijar el directorio donde se encuentra el java.policy
        System.setProperty("java.security.policy", "./java.policy");
        if (System.getSecurityManager() == null) {
            //Crear administrador de seguridad
            System.setSecurityManager(new SecurityManager());
        }
        try {
            //Paso 1 - Obtener una referencia al objeto servidor creado anteriormente
            //Nombre del host servidor o su IP. Es d´onde se buscar´a al objeto remoto
            //String hostname = "127.0.0.1"; //se puede usar "IP:puerto"
            //Collection server = (Collection) Naming.lookup("//"+ hostname + "/MyCollection");
            //Paso 2 - Invocar remotamente los metodos del objeto servidor:
            //Obtener el nombre de la colecci´on y el n´umero de libros
            //String colecName = server.name_of_collection();
            //int nbooks = server.number_of_books();
            //Cambiar el nombre de la coleccion y ver que ha funcionado
            //System.out.println("Nombre anterior: " + colecName + ", Número de libros: " + nbooks);
            //server.name_of_collection("NuevaColección");
            //System.out.println("Nombre nuevo: " + server.name_of_collection());
        }
        catch (Exception ex) {
            System.out.println(ex);
        }
    }
}
