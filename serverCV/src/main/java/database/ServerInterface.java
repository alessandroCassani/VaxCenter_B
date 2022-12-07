package database;

import util.*;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.LinkedList;

/**
 * interfaccia dei metodi del server, che saranno utilizzati nel sistema distribuito tramite RMI
 *
 * @author Alessandro Cassani - Matricola: 744512 - Sede: VA
 * @author Paolo Bruscagin - Matricola: 744703 - Sede: VA
 * @author Damiano Ficara - Matricola 744958 - Sede: VA
 * @author Luca Perfetti - Matricola 746581 - Sede: VA
 *
 */
public interface ServerInterface extends Remote {

    /**
     * segnatura del metodo che permette la registrazione di un centro vaccinale
     * @param centroVaccinale centro vaccinale
     * @return true o false, in base all'esito dell'operazione
     * @throws RemoteException eccezione rmi
     * @throws SQLException eccezione sql
     *
     *  @author Alessandro Cassani
     */
    boolean registraCentroVaccinale(CentroVaccinale centroVaccinale) throws RemoteException, SQLException;

    /**
     * segnatura del emtodo che permette la registrazione di un cittadino
     * @param cittadino cittadino
     * @return true o false, in base all'esito dell'operazione
     * @throws RemoteException eccezione rmi
     * @throws SQLException eccezione database
     *
     *  @author Alessandro Cassani
     */
    boolean registraCittadino(Cittadino cittadino) throws RemoteException,SQLException;

    /**
     * segnatura del metodo che permette la registrazione di un vaccinato
     * @param vaccinato persona vaccinata
     * @return stringa rappresentante id del vaccinato registrato
     * @throws RemoteException eccezione rmi
     *
     *  @author Alessandro Cassani
     */

    String registraVaccinato(Vaccinato vaccinato) throws RemoteException;

    /**
     * segbatura del metodo che permette la registrazione di una serie di eventi avversi segnalatyi dal cittadino registrato
     * @param eventiAvversi serie di eventi avversi segnalati
     * @param user utente collegato
     * @return true o false, in base all'esito dell'operazione
     * @throws RemoteException eccezione rmi
     *
     *  @author Alessandro Cassani
     */
    boolean inserisciEventiAvversi(EventiAvversi eventiAvversi,String user) throws RemoteException;

    /**
     * segnatura del metodo che permette di controllare se gli eventi avversi sono già stati registrati per quel cittadino
     * @param user account del cittadino
     * @return true o false, in base all'esito dell'operazione
     * @throws RemoteException eccezione rmi
     *
     *  @author Paolo Bruscagin
     */
    boolean isAERegistrated(String user) throws RemoteException;

    /**
     * segnatura del metodo che permette la restituzione degli eventi avversi già segnalati
     * @param id id del cittadino
     * @return lista di eventi avversi già inseriti
     * @throws RemoteException eccezione rmi
     *
     * @author Paolo Bruscagin
     */
    String[] getPersonAE(String id) throws RemoteException;

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
     * @param nomeCentroVaccinale nome del centro vaccinale di cui si vuole conoscere il prospetto
     * @return severita' media e numero di segnalazioni di uno specifico centro vaccinale
     * @throws RemoteException eccezione rmi
     * @author Alessandro cassani
     */
    String[] getProspettoRiassuntivo(String nomeCentroVaccinale) throws RemoteException;

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

    /**
     * il metodo permette di avere la lista di tutti i nomi dei comuni italiani
     * @return lista comuni
     * @throws RemoteException eccezione rmi
     *
     * @author Damiano Ficara
     */
    LinkedList<String> getComuniNome() throws RemoteException;

    /**
     * il metodo permette di cercare i centri vaccinali registrati nel DB
     * @return lista di centri vaccinali
     * @throws RemoteException eccezione rmi
     *
     * @author Alessandro Cassani
     */

    LinkedList<CentroVaccinale>  getCentriVaccinali() throws RemoteException;

    /**
     * segnature del metodo che permette la restituzione dei dati personali di un cittadino
     * @param user comune di cui si vogliono avere i dati
     * @return dati cittadino
     * @throws RemoteException eccezione rmi
     *
     * @author Paolo Bruscagin
     */

    String[] getInfoCittadino(String user) throws RemoteException;

    /**
     * il metodo permette di recuperare l'identificativo del cittadino
     * @param acc account del cittadino
     * @return id del cittadino
     * @throws RemoteException eccezione rmi
     *
     * @author Damiano Ficara
     */
    String getID(Account acc) throws RemoteException;
}