import util.*;

import java.math.BigInteger;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.*;
import java.util.LinkedList;
import java.util.TreeSet;

/**
 * la classe serverImpl contiene l'implementazione dei metodi del server, ovvero quei metodi che direttamente sia per inserimenti che per controlli interagiscono
 * con il DB. l'oggetto creato da questa classe corrispondera' all'oggetto server che sara' caricato su registry tramite RMI
 *
 * @author Alessandro Cassani
 * @author Luca Perfetti
 */
public class ServerImpl extends UnicastRemoteObject implements ServerInterface {

    /**
     * costruttore vuoto
     * @throws RemoteException eccezione RMI
     */
    protected ServerImpl() throws RemoteException {
        super();
    }

    /**
     * il metodo permette di registrare nel DB nella tabella  centriVaccinali le informazioni relative ad un centro vaccinale
     * @param centroVaccinale centro vaccinale
     * @return true/false in base all'esito dell'operazione
     * @throws RemoteException eccezione RMI
     *
     * @author Alessandro Cassani
     */
    @Override
    public synchronized boolean registraCentroVaccinale(CentroVaccinale centroVaccinale) throws RemoteException{
        try {
            PreparedStatement ps = DBManagement.getDB().connection.prepareStatement("INSERT INTO CentroVaccinale(nomeCentro,indirizzo,tipologia) \n"
                    + "VALUES (?,?,?)");
            ps.setString(1, centroVaccinale.getNome());
            ps.setString(2,centroVaccinale.getIndirizzo().toString());
            ps.setString(3,centroVaccinale.getTipologia().toString());
            ps.executeUpdate();
            ps.close();
        } catch(SQLException e){return false;}
        return true;
    }

    /**
     * il metodo permette la registrazione nella tabella del DB Cittadini_Registrati di un oggetto di tipo cittadino
     * @param cittadino cittadino
     * @return true/false in base all'esito dell'operazione
     * @throws RemoteException eccezione RMI
     *
     * @author Alessandro Cassani
     */
    @Override
    public boolean registraCittadino(Cittadino cittadino) throws RemoteException {
        try{
            PreparedStatement ps = DBManagement.getDB().connection.prepareStatement("INSERT INTO Cittadini_Registrati(id,nome,cognome,codFisc,email,username,password,nomeCentroVaccinale) \n"
                    + "VALUES (?,?,?,?,?,?,?,?)");

            ps.setString(1, cittadino.getId().toString());
            ps.setString(2, cittadino.getNome());
            ps.setString(3,cittadino.getCognome());
            ps.setString(4,cittadino.getCodFisc());
            ps.setString(5, cittadino.getEmail());
            ps.setString(6,cittadino.getAccount().getUserId());
            ps.setString(7,cittadino.getAccount().getPassword());
            ps.setString(8,cittadino.getCentroVaccinale().getNome());
            ps.executeUpdate();
            ps.close();
        } catch (SQLException e){return false;}
        return true;
    }

    /**
     * il metodo permette la registrazione nell'opportuna tabella del DB di un oggetto di tipo Vaccinato
     * @param vaccinato persona vaccinata
     * @return true/false in base all'esito dell'operazione
     * @throws RemoteException eccezione RMI
     *
     * @author Alessandro Cassani
     */
    @Override
    public boolean registraVaccinato(Vaccinato vaccinato) throws RemoteException {
        try {
            PreparedStatement preparedStatement = DBManagement.getDB().connection.prepareStatement("SELECT Id FROM Vaccinati");
            ResultSet resultSet = preparedStatement.executeQuery();
            preparedStatement.close();
            TreeSet<BigInteger> id = new TreeSet<>();
            while(resultSet.next()){
                id.add(new BigInteger(resultSet.getString(1))); //TreeSet ordina di default gli elementi in ordine crescente
            }
            BigInteger numero;
            if(!id.isEmpty())
                numero = id.last();
            else
                numero = new BigInteger("0000000000000000");

            PreparedStatement ps = DBManagement.getDB().connection.prepareStatement("INSERT INTO Vaccinati(id,nome,cognome,codiceFiscale,dataVaccino,vaxTipo,nomecentro) \n" +
                    "VALUES(?,?,?,?,?,?,?");
            ps.setString(1, numero.toString());
            ps.setString(2,vaccinato.getNome());
            ps.setString(3,vaccinato.getCognome());
            ps.setString(4,vaccinato.getCodFisc());
            ps.setDate(5, (Date) vaccinato.getDataSomministrazione());  //controllo cast!!
            ps.setString(6,vaccinato.getVaccino().toString());
            ps.setString(7,vaccinato.getCentroVaccinale().getNome());
            ps.executeUpdate();
            ps.close();

        }catch (SQLException e){return false;}
        return true;
    }

    /**
     * il metodo permette la registrazione nelle tabelle Severita e Eventi_Avversi le informazioni relative agli eventi avversi registrati dal cittadino.
     * le severita' sono inserite come interi nella tabella Severita, le note opzionali come ultimo campo sotto forma di stringhe.
     * le sintomatologie registrate sono registrate nella tabella Eventi_Avversi sotto forma di booleani. true se sintomo riscontrato, altrimenti false
     *
     * @param eventiAvversi serie di eventi avversi segnalati
     * @return true o false in base all'esito dell'operazione
     * @throws RemoteException eccezione rmi
     *
     * @author Alessandro Cassani
     */
    @Override
    public boolean inserisciEventiAvversi(EventiAvversi eventiAvversi) throws RemoteException {
        try {
            PreparedStatement preparedStatement = DBManagement.getDB().connection.prepareStatement("INSERT INTO Eventi_Avversi(username,mal_di_testa,febbre,dolori_muscolari,linfoadenopatia,crisi_ipertensiva) \n +" +
                    " VALUE (?,?,?,?,?,?");

            int count = 1;
            for (Sintomo sintomo: eventiAvversi.getSintomi()) {
                preparedStatement.setBoolean(count, sintomo.getSeverita() != 0);
                count++;
            }
            preparedStatement.executeUpdate();
            preparedStatement.close();


            PreparedStatement ps = DBManagement.getDB().connection.prepareStatement("INSERT INTO Severita(username,mal_di_testa,febbre,dolori_muscolari,linfoadenopatia,crisi_ipertensiva,note) \n +" +
                    " VALUE (?,?,?,?,?,?,?");
            int size = eventiAvversi.getSintomi().size();
            count = 1;
            while(count<size-1) {  //-1 perchè ultimo campo ci sono le note
                    ps.setInt(count,eventiAvversi.getSintomi().get(count).getSeverita());
                    count++;
            }
            ps.setString(size, eventiAvversi.getNote());
            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {return  false;}
        return true;
    }

    @Override
    public boolean isSignedUp(Account account) throws RemoteException {
        return false;
    }

    @Override
    public boolean isUserRegistrated(String user) throws RemoteException {
        return false;
    }

    @Override
    public boolean isVaxcenterRegistrated(String VaxCenterName) throws RemoteException {
        return false;
    }

    @Override
    public boolean isCitizenRegistrated(String citizen) throws RemoteException {
        return false;
    }

    @Override
    public boolean isVaccinatedRegistrated(String user) throws RemoteException {
        return false;
    }

    /**
     * il metodo permette  di avere il prospetto riassuntivo di uno specifico centro vaccinale
     *
     * @param nomeCentroVaccinale nome del centro vaccinale di cui si viuole avere il prospetto riassuntivo
     * @return severita' media e numero di segnalazioni di uno specifico centro vaccinale
     * @throws RemoteException eccezione rmi
     *
     * @author Alessandro cassani
     */
    @Override
    public String getProspettoRiassuntivo(String nomeCentroVaccinale) throws RemoteException {
        try {
            PreparedStatement preparedStatement = DBManagement.getDB().connection.prepareStatement("SELECT COUNT(mal_di_testa) AVG(mal_dit_esta) COUNT(febbre) AVG(febbre) COUNT(dolori_muscolari) AVG(dolori_muscolari)" +
                    "COUNT(linfoadenopatia) AVG(linfoadenopatia) COUNT(crisi_ipertensiva) AVG(crisi_ipertensiva) \n" +
                    "FROM Severita JOIN Cittadini_registrati USING username WHERE nomeCentro =" + nomeCentroVaccinale);
            ResultSet resultSet = preparedStatement.executeQuery();

            return "mal di testa: " + resultSet.getString(1) + " segnalazioni media intensita' " + resultSet.getString(2)  +"\n"+
                        " febbre: " + resultSet.getString(3) + " segnalazioni media intensita' " + resultSet.getString(4) + "\n"+
                        " dolori muscolari: " + resultSet.getString(5) + " segnalazioni media intensita' " + resultSet.getString(6) + "\n"+
                        " linfoadenopatia " +resultSet.getString(7) + " segnalazioni media intensita' " + resultSet.getString(8) + "\n"+
                        " crisi ipertensiva " + resultSet.getString(9) + " segnalazioni media intensita' " + resultSet.getString(10);

        } catch (SQLException e) {}
        return "";
    }

    @Override
    public LinkedList<CentroVaccinale> getCentriVaccinali(String comune, Tipologia tipologia) throws RemoteException {
        return null;
    }

    @Override
    public LinkedList<CentroVaccinale> getCentriVaccinali(String nome) throws RemoteException {
        return null;
    }
}
