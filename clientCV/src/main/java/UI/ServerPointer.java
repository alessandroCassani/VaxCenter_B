package UI;

import database.ServerInterface;

import java.rmi.registry.Registry;

public class ServerPointer {
    static Registry registry;

    static ServerInterface stub;

    public static Registry getRegistry(){
        return registry;
    }

    public static void setRegistry(Registry registry){
        ServerPointer.registry = registry;
    }
}
