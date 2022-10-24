package database;

import util.*;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.LinkedList;

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
    boolean inserisciEventiAvversi(EventiAvversi eventiAvversi,String user) throws RemoteException;


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
     * segnatura del metodo che permette il controllo della già avvenuta vaccinazione del cittadino
     * @param user codice fiscale dle vaccinato
     * @return true o false in base all'esito dell'operazione
     * @throws RemoteException eccezione rmi
     *
     *  @author Alessandro Cassani
     */
    boolean isVaccinatedRegistrated(String user) throws RemoteException;

    /**
     * segnatura del metodo che permette di controllare se l'id inserito dal cittadino in fase
     * di registrazione combacia con il suo id registrato a sistema (tabella vaccinati)
     * @param id id del cittadino
     * @param codiceFiscale codice fiscale del cittadino
     * @return true o false in base all'esito dell'operazione
     * @throws RemoteException eccezione rmi
     *
     * @author Alessandro Cassani
     */
    boolean isIdCorrect(String id,String codiceFiscale) throws  RemoteException;

    /**
     * segnatura del metodo che permette  di avere il prospetto riassuntivo di uno specifico centro vaccinale
     * @return severita' media e numero di segnalazioni di uno specifico centro vaccinale
     * @throws RemoteException eccezione rmi
     *
     * @author Alessandro cassani
     */
    String getProspettoRiassuntivo(String nomeCentroVaccinale) throws RemoteException;

    /**
     * segnatura del metodo che permette la ricerca di centri vaccinali
     * @param comune comune dove ricercare centro vaccinale
     * @param tipologia tipologia di centro vaccinale da ricercare
     * @return lista di centri vaccinali
     * @throws RemoteException eccezione rmi
     *
     * @author Alessandro cassani
     */
    LinkedList<CentroVaccinale> getCentriVaccinali(String comune, Tipologia tipologia) throws  RemoteException;


    /**
     * segnatura del metodo che permette la ricerca di un centro vaccinale tramite nome
     * @param nome nome del centro vaccinale (anche non completa)
     * @return lista di centri vaccinali
     * @throws RemoteException eccezione rmi
     *
     * @author Alessandro cassani
     */
    LinkedList<CentroVaccinale> getCentriVaccinali(String nome) throws  RemoteException;

    /**
     * segnatura del metodo che permette la ricerca dei nomi dei centri avccinali esistenti
     * @return lista dei nomi dei centri vaccinali esistenti
     * @throws RemoteException eccezione rmi
     *
     * @author Alessandro Cassani
     */
    LinkedList<String> getNomicentriVaccinali() throws RemoteException;


    /**
     * il metodo permette di avere il cap e la provincia del comune inserito come parametro
     * @param comune comune di cui si vogliono avere i dati
     * @return cap e provincia del comune
     * @throws RemoteException eccezione rmi
     *
     * @author Alessandro Cassani
     */
    CapProvincia getComuneInfo(String comune) throws RemoteException;

}
