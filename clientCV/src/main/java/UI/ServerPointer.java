package UI;

import database.ServerInterface;
import database.UILoginToServer;

import java.rmi.registry.Registry;

public class ServerPointer {
    /**
     * Oggetto che permette la comunicazione tra client e server
     */
    static Registry registry;

    /**
     * Oggetto che permette l'invocazione dei metodi remoti sul server
     */
    static ServerInterface stub;

    public static void main(String[] args){
        new UILoginToServer();
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
}
