package UI;

import database.ServerInterface;
import database.UILoginToServer;

import java.rmi.registry.Registry;

public class ServerPointer {
    static Registry registry;

    static ServerInterface stub;

    public static void main(String[] args){
        new UILoginToServer();
    }

    public static Registry getRegistry(){
        return registry;
    }

    public static void setRegistry(Registry registry){
        ServerPointer.registry = registry;
    }

    public static ServerInterface getStub(){
        return stub;
    }

    public static void setStub(ServerInterface stub){
        ServerPointer.stub = stub;
    }
}
