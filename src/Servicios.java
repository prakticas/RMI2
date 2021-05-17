import java.util.Hashtable;
import java.util.Vector;

public class Servicios {

    private class Servicio {

        private String nombre_servidor, nom_servicio, tipo_retorno;
        private Vector lista_param;

        Servicio(String nombre_servidor, String nom_servicio, Vector lista_param,String tipo_retorno){
            this.nombre_servidor = nombre_servidor;
            this.nom_servicio = nom_servicio;
            this.lista_param = lista_param;
            this.tipo_retorno = tipo_retorno;
        }

        private Respuesta ejecutar(){

            return null;
        }
    }

    private Hashtable<String, Servicio> lista_servicios = null;

    //crea un nuevo servicio y lo añade
    public void newServicio(String nombre_servidor, String nom_servicio, Vector<String> lista_param,
                            String tipo_retorno){

    }

    public Servicios(){
        lista_servicios = new Hashtable<String,Servicio>();

    }

    public Respuesta ejecutar(String servicio){
        return lista_servicios.get(servicio).ejecutar();
    }
    
}
