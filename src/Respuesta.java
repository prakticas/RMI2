import java.io.Serializable;

public class Respuesta implements Serializable {

    String valor;

    public Respuesta(String val) {
        valor = val;

    }

    @Override
    public String toString() {
        
        return valor;
    }
    
}
