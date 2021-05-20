import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.rmi.Remote;
import java.util.Hashtable;
import java.util.Vector;

public class Servicios {

    private class Servicio {

        private String nom_servicio;
        private Method method;

        Servicio( String nom_servicio, Method m){
            this.nom_servicio = nom_servicio;
            this.method = m;
        }

        private Respuesta ejecutar(Vector<String> params){

            try {
                this.method.invoke(nom_servicio, params);
            } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
                System.err.println("El método no "+nom_servicio+" se pudo invocar");
            }

            return null;
        }

    }

    private Hashtable<String, Servicio> lista_servicios = null;

    public Servicios(){
        lista_servicios = new Hashtable<String,Servicio>();

    }

    //crea un nuevo servicio y lo añade
    public void newServicio(Remote servidor, String nom_servicio){
  
            try {
              
                Method metodo= servidor.getClass().getMethod(nom_servicio,Vector.class);
                String key = nom_servicio;
               
                if (!lista_servicios.containsKey(key)){
                    Servicio s = new Servicio(nom_servicio,metodo);
                    lista_servicios.put(key, s);
                }
            } catch (NoSuchMethodException | SecurityException e) {
              
                System.err.println("No se pudo añadi rel servicio "+ nom_servicio);
            }
            

            
           
    }

    public void deleteServicio(String nom_servicio){
        lista_servicios.remove(nom_servicio);
    }

    

    public Respuesta ejecutar(String servicio,Vector<String> params){
        return lista_servicios.get(servicio).ejecutar(params);
    }

   
    
}
