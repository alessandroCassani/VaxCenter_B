package TEST;

import database.ServerInterface;
import util.CentroVaccinale;
import util.Qualificatore;
import util.Tipologia;

import java.rmi.RemoteException;
import java.sql.SQLException;

public class TesterAddCenter extends Thread{
    ServerInterface stub;

    int myId;

    public TesterAddCenter(ServerInterface stub, int i){
        this.stub = stub;
        myId = i;

        start();
    }

    public void run(){
        boolean response;
        CentroVaccinale nuovocv = new CentroVaccinale("Centro"+myId, Qualificatore.getQualificatore("via"),
                "indirizzo","civico", "provincia","comune",
                21234, Tipologia.getTipo("hub"));
        System.out.println(nuovocv);

        try {
            response = stub.registraCentroVaccinale(nuovocv);

            if (response){
                System.out.println("test " + myId + " centro aggiunto");
            }else {
                System.out.println("test " + myId + " non centro aggiunto");
            }
        } catch (SQLException | RemoteException ex) {
            throw new RuntimeException(ex);
        }
    }
}
