import util.*;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ServerInterface extends Remote {

    boolean registraCentroVaccinale(CentroVaccinale centroVaccinale) throws RemoteException;

    boolean registraCittadino(Cittadino cittadino) throws RemoteException;

    boolean registraVacinato(Vaccinato vaccinato) throws RemoteException;

    boolean inserisciEventiAvversi(EventiAvversi eventiAvversi) throws RemoteException;

    boolean signUp(Account account) throws RemoteException;
}
