package UI;

import database.ServerInterface;

import javax.swing.*;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

/**
 * Classe che permette la comunicazione con il server remoto
 *
 * @author Luca Perfetti
 */
public class ServerPointer {
    /**
     * Porta sulla quale si ascolterà il server
     */
    private static int PORT = 1099;
    /**
     * Nome del servizio caricato sul registry
     */
    static final String SERVICE_NAME = "VaxCenter";
    /**
     * Oggetto che permette la comunicazione tra client e server
     */
    static Registry registry;

    /**
     * Oggetto che permette l'invocazione dei metodi remoti sul server
     */
    static ServerInterface stub;

    /**
     * Metodo che per mette di avviare il programma
     * @param args
     *
     * @author Luca Perfetti
     */
    // Punto di avvio del server
    public static void main(String[] args){

    }

    /**
     * Metodo che permette di ottenere un riferimento al registro sull'host corrente
     * @return l'oggetto registry
     *
     * @author Luca Perfetti
     */
    public static Registry getRegistry(){
        return registry;
    }

    /**
     * Metodo che permette di aggiornare l'oggetto registry
     * @param registry l'oggetto registry
     *
     * @author Luca Perfetti
     */
    public static void setRegistry(Registry registry){
        ServerPointer.registry = registry;
    }

    /**
     * Metodo che ritorna l'oggetto stub
     * @return l'oggetto stub
     *
     * @author Luca Perfetti
     */
    public static ServerInterface getStub(){
        return stub;
    }

    /**
     * Metodo che permette di aggiornare l'oggetto stub
     * @param stub l'oggetto stub
     *
     * @author Luca Perfetti
     */
    public static void setStub(ServerInterface stub){
        ServerPointer.stub = stub;
    }


    public static void connectToRMI()
    {
        try {
            ServerPointer.setRegistry(LocateRegistry.getRegistry("localhost",PORT));
            ServerPointer.setStub((ServerInterface) ServerPointer.getRegistry().lookup(SERVICE_NAME));
        }catch (RemoteException | NotBoundException e) {
            e.printStackTrace();
        }



    }

}
