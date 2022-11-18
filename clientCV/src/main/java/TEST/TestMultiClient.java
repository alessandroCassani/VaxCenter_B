package TEST;

import UI.ServerPointer;
import database.UIServerHome;

public class TestMultiClient {
    public static void main(String[] args) {
        ServerPointer.getRegistry();
        UIServerHome.startServer();

        for(int i=0; i<9; i++){
            new TesterAddCenter(UIServerHome.getServer(), i);
        }
    }
}
