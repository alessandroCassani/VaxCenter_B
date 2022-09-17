import util.*;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * interfaccia dei metodi del server, che andranno utilizzati nel sistema distribuito tramite RMI
 *
 * @author Alessandro Cassani
 */
public interface ServerInterface extends Remote {

    /**
     * segnatura del metodo che permette la registrazione di un centro vaccinale
     * @param centroVaccinale centro vaccinale
     * @return true o false, in base all'esito dell'operazione
     * @throws RemoteException
     */
    boolean registraCentroVaccinale(CentroVaccinale centroVaccinale) throws RemoteException;

    /**
     * segnatura del emtodo che permette la registrazione di un cittadino
     * @param cittadino cittadino
     * @return true o false, in base all'esito dell'operazione
     * @throws RemoteException
     */

    boolean registraCittadino(Cittadino cittadino) throws RemoteException;

    /**
     * segnatura del metodo che permette la registrazione di un vaccinato
     * @param vaccinato persona vaccinata
     * @return true o false, in base all'esito dell'operazione
     * @throws RemoteException
     */
    boolean registraVacinato(Vaccinato vaccinato) throws RemoteException;

    /**
     * segbatura del metodo che permette la registrazione di una serie di eventi avversi segnalatyi dal cittadino registrato
     * @param eventiAvversi serie di eventi avversi segnalati
     * @return true o false, in base all'esito dell'operazione
     * @throws RemoteException
     */
    boolean inserisciEventiAvversi(EventiAvversi eventiAvversi) throws RemoteException;

    /**
     * segnatura del metodo che permette di registrare a sistema l'account di un cittadino
     * @param account account del cittadino
     * @return true o false, in base all'esito dell'operazione
     * @throws RemoteException
     */
    boolean signUp(Account account) throws RemoteException;
}
