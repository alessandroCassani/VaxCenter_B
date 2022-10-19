package UI;

import java.rmi.registry.Registry;

public class ServerPointer {
    static Registry registry;

    public static Registry getRegistry(){
        return registry;
    }

    public static void setRegistry(){

    }
}
