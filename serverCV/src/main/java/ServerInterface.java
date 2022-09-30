import util.*;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.sql.SQLException;

/**
 * interfaccia dei metodi del server, che saranno utilizzati nel sistema distribuito tramite RMI
 *
 * @author Alessandro Cassani
 */
public interface ServerInterface extends Remote {

    /**
     * segnatura del metodo che permette la registrazione di un centro vaccinale
     * @param centroVaccinale centro vaccinale
     * @return true o false, in base all'esito dell'operazione
     * @throws RemoteException eccezione rmi
     *
     *  @author Alessandro Cassani
     */
    boolean registraCentroVaccinale(CentroVaccinale centroVaccinale) throws RemoteException, SQLException;

    /**
     * segnatura del emtodo che permette la registrazione di un cittadino
     * @param cittadino cittadino
     * @return true o false, in base all'esito dell'operazione
     * @throws RemoteException eccezione rmi
     *
     *  @author Alessandro Cassani
     */
    boolean registraCittadino(Cittadino cittadino) throws RemoteException;

    /**
     * segnatura del metodo che permette la registrazione di un vaccinato
     * @param vaccinato persona vaccinata
     * @return true o false, in base all'esito dell'operazione
     * @throws RemoteException eccezione rmi
     *
     *  @author Alessandro Cassani
     */

    boolean registraVaccinato(Vaccinato vaccinato) throws RemoteException;

    /**
     * segbatura del metodo che permette la registrazione di una serie di eventi avversi segnalatyi dal cittadino registrato
     * @param eventiAvversi serie di eventi avversi segnalati
     * @return true o false, in base all'esito dell'operazione
     * @throws RemoteException eccezione rmi
     *
     *  @author Alessandro Cassani
     */
    boolean inserisciEventiAvversi(EventiAvversi eventiAvversi) throws RemoteException;

    /**
     * segnatura del metodo che permette di registrare a sistema l'account di un cittadino
     * @param account account del cittadino
     * @return true o false, in base all'esito dell'operazione
     * @throws RemoteException eccezione rmi
     *
     *  @author Alessandro Cassani
     */
    boolean isSignedUp(Account account) throws RemoteException;

    /**
     * segnatura del metodo che permette il controllo in fase di accesso e registrazione dell'utente dell'avvenuta registrazione del cittadino
     * @param user nome utente
     * @return true o false, in base all'esito dell'operazione
     * @throws RemoteException eccezione rmi
     *
     *  @author Alessandro Cassani
     */
    boolean isUserRegistrated(String user) throws RemoteException;

    /**
     * segnatura del metodo che permette il controllo della già avvenuta registrazione di un centro vaccinale
     * @param VaxCenterName nome del centro vaccinale
     * @return true o false in base all'esito dell'operazione
     * @throws RemoteException eccezione rmi
     *
     *  @author Alessandro Cassani
     */
    boolean isVaxcenterRegistrated(String VaxCenterName) throws RemoteException;

    /**
     *  segnatura del metodo che permette il controllo della già avvenuta registrazione di un cittadino a sistema
     * @param citizen codice fiscale dle cittadino
     * @return true o false in base all'esito dell'operazione
     * @throws RemoteException eccezione rmi
     *
     *  @author Alessandro Cassani
     */
    boolean isCitizenRegistrated(String citizen) throws RemoteException;

    /**
     * segbatura del metodo che permette il controllo della già avvenuta vaccinazione del cittadino
     * @param user codice fiscale dle vaccinato
     * @return true o false in base all'esito dell'operazione
     * @throws RemoteException eccezione rmi
     *
     *  @author Alessandro Cassani
     */
    boolean isVaccinatedRegistrated(String user) throws RemoteException;
}
