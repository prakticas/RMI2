import java.lang.reflect.Method;
import java.rmi.Remote;
import java.util.Hashtable;
import java.util.Vector;
import java.util.*;

public class Servicios {

    private class Servicio {

        private String nom_servicio;
        private Method method;
        private Remote cls;

        Servicio( String nom_servicio,  Remote cls) throws NoSuchMethodException, SecurityException{
            this.nom_servicio = nom_servicio;
            this.cls = cls;
          
                this.method = cls.getClass().getMethod(nom_servicio,Vector.class);
                System.out.println("Servicio creado: "+this.method.getName());
            
               
            
            
        }

        private Respuesta ejecutar(Vector<?> params){
            try {    
              Respuesta r = (Respuesta) this.method.invoke(cls, params);
              return r;
            } catch (Exception e) {
                System.err.println(e.getCause());
                System.err.println("El método "+nom_servicio+" no se pudo invocar");
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
                String key = nom_servicio;
               
                if (!lista_servicios.containsKey(key)){
                    Servicio s = new Servicio(nom_servicio, servidor);
                    lista_servicios.put(key, s);
                }
            } catch (SecurityException e) {
              
                System.err.println("No se pudo añadir el servicio "+ nom_servicio);
            } catch (NoSuchMethodException e){
                System.err.println("No existe el servicio "+ nom_servicio);
            }

    }

    public void deleteServicio(String nom_servicio){
        lista_servicios.remove(nom_servicio);
    }

    public Respuesta ejecutar(String servicio, Vector<?> params){
        return lista_servicios.get(servicio).ejecutar(params);
    }

    @Override
    public String toString() {
        //TODO devolverl 
        //iterar y ponerlos en el valor
        String valor="";
        Iterator it = lista_servicios.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry pair = (Map.Entry)it.next();
            valor += pair.getKey() + "\n";
           
        }
        return valor;
    }
}
