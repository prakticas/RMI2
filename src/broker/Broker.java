package broker;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Vector;

import util.Respuesta;

public interface Broker extends Remote{
    //API server 
    void registrar_servidor(String nombre_servidor, String host_remoto_IP_puerto) throws RemoteException;
    void registrar_servicio(String nombre_servidor, String nom_servicio) throws RemoteException;
    void baja_servicio(String nombre_servidor, String nom_servicio) throws RemoteException;
    
    //API cliente 
    Respuesta ejecutar_servicio(String nom_servicio, Vector<?> parametros_servicio) throws RemoteException;
    Respuesta ejecutar_servicio_asinc(String nom_servicio, Vector<?> parametros_servicio) throws RemoteException;
    Servicios lista_servicios() throws RemoteException;
    Respuesta obtener_respuesta_asinc(String nom_servicio) throws RemoteException;
}
