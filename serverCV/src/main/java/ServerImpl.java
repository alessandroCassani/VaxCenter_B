
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
            Connection con = DBManagement.getDB().connection;
            PreparedStatement ps = con.prepareStatement("INSERT INTO centrovaccinale(nomeCentro,indirizzo,tipologia) "
                    + "VALUES (?,?,?)");
            ps.setString(1, centroVaccinale.getNome());
            ps.setString(2,centroVaccinale.getIndirizzo().toString());
            ps.setString(3,centroVaccinale.getTipologia().toString());
            ps.executeUpdate();
            ps.close();
        } catch(SQLException e){ e.printStackTrace();return false;}
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
            PreparedStatement preparedStatement = DBManagement.getDB().connection.prepareStatement("SELECT Id FROM vaccinati");
            ResultSet resultSet = preparedStatement.executeQuery();
            TreeSet<BigInteger> id = new TreeSet<>();
            while(resultSet.next()){
                id.add(new BigInteger(resultSet.getString(1))); //TreeSet ordina di default gli elementi in ordine crescente
            }
            BigInteger numero;
            if(!id.isEmpty())
                numero = id.last();
            else
                numero = new BigInteger("0000000000000000");
            preparedStatement.close();

            PreparedStatement ps = DBManagement.getDB().connection.prepareStatement("INSERT INTO Vaccinati(id,nome,cognome,codiceFiscale,dataVaccino,vaxTipo,nomecentro) \n" +
                    "VALUES(?,?,?,?,?,?,?)");
            ps.setString(1, numero.toString());
            ps.setString(2,vaccinato.getNome());
            ps.setString(3,vaccinato.getCognome());
            ps.setString(4,vaccinato.getCodFisc());
            ps.setString(5,vaccinato.getDataSomministrazione().toString());  //controllo cast!!
            ps.setString(6,vaccinato.getVaccino().toString());
            ps.setString(7,vaccinato.getCentroVaccinale().getNome());
            ps.executeUpdate();
            ps.close();

        }catch (SQLException e){e.printStackTrace();return false;}
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

    /**
     * il metodo permette di controllare se il cittadino è già registrato oppure no
     * @param account account del cittadino
     * @return true/false in base all'esito dell'operazione
     * @throws RemoteException
     *
     * @author Luca Perfetti
     */
    @Override
    public boolean isSignedUp(Account account) throws RemoteException {
        try {
            PreparedStatement ps = DBManagement.getDB().connection.prepareStatement("SELECT * FROM Cittadini_Registrati WHERE username = ? AND password = ?");

            ps.setString(1, String.valueOf(account));

            ResultSet resultSet = ps.executeQuery();
            if(resultSet.next()){
                return false;
            }
        }catch (SQLException e){
            return false;
        }
        return true;
    }

    /**
     * il metodo permette il controllo in fase di accesso e registrazione dell'utente dell'avvenuta registrazione del cittadino
     * @param user nome utente
     * @return true/false in base all'esito dell'operazione
     * @throws RemoteException
     *
     * @author Luca Perfetti
     */
    @Override
    public boolean isUserRegistrated(String user) throws RemoteException {
        try {
            PreparedStatement ps = DBManagement.getDB().connection.prepareStatement("SELECT * FROM Cittadini_Registrati WHERE username = ?");

            ps.setString(1, user);

            ResultSet resultSet = ps.executeQuery();
            if(resultSet.next()){
                return false;
            }
        }catch (SQLException e){
            return false;
        }
        return true;
    }

    /**
     * il metodo permette il controllo della già avvenuta registrazione di un centro vaccinale
     * @param VaxCenterName nome del centro vaccinale
     * @return true/false in base all'esito dell'operazione
     * @throws RemoteException
     *
     * @author Luca Perfetti
     */
    @Override
    public boolean isVaxcenterRegistrated(String VaxCenterName) throws RemoteException {
        try {
            PreparedStatement ps = DBManagement.getDB().connection.prepareStatement("SELECT * FROM CentroVaccinale WHERE nomeCentro = ?");

            ps.setString(1, VaxCenterName);

            ResultSet resultSet = ps.executeQuery();
            if(resultSet.next()){
                return false;
            }
        }catch (SQLException e){
            return false;
        }
        return true;
    }

    /**
     * metodo che permette il controllo della doppia registrazione di un cittadino
     * @param citizen codice fiscale del cittadino
     * @return true o false in base all'esito dell'operazione
     * @throws RemoteException eccezione rmi
     *
     * @author Alessandro Cassani
     */
    @Override
    public boolean isCitizenRegistrated(String citizen) throws RemoteException {
        try {
            PreparedStatement ps = DBManagement.getDB().connection.prepareStatement("SELECT * FROM Cittadini_Registrati WHERE cf = ?");
            ps.setString(1,citizen);
            ResultSet resultSet = ps.executeQuery();
            ps.close();
            if(resultSet.next())
                return false;

        } catch (SQLException e) {return false;}
        return true;
    }

    /**
     * il metodo permette il controllo della già avvenuta vaccinazione del cittadino
     * @param user codice fiscale del vaccinato
     * @return true/false in base all'esito dell'operazione
     * @throws RemoteException
     *
     * @author Luca Perfetti
     */
    @Override
    public boolean isVaccinatedRegistrated(String user) throws RemoteException {
        try {
            PreparedStatement ps = DBManagement.getDB().connection.prepareStatement("SELECT * FROM Vaccinati WHERE codicefiscale = ?");

            ps.setString(1, user);

            ResultSet resultSet = ps.executeQuery();
            if(resultSet.next()){
                return false;
            }
        }catch (SQLException e){
            return false;
        }
        return true;
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

    /**
     * il metodo permette la ricerca di una serie di centri vaccinali specificando il comune e la tipologia di essi
     * @param comune comune dove ricercare centro vaccinale
     * @param tipologia tipologia di centro vaccinale da ricercare
     * @return lista di centri vaccinali
     * @throws RemoteException eccezione rmi
     *
     * @author Alessandro Cassani
     */
    @Override
    public LinkedList<CentroVaccinale> getCentriVaccinali(String comune, Tipologia tipologia) throws RemoteException {
        try {
            PreparedStatement ps =  DBManagement.getDB().connection.prepareStatement("SELECT * FROM CentriVaccinali(nomeCentro,Comune,qualificatore,via,numCivico,sigla,cap,tipologia)" +
                    "WHERE Comune = " + comune + " AND tipologia = " + tipologia.toString());
            ResultSet resultSet = ps.executeQuery();
            ps.close();
            LinkedList<CentroVaccinale> listaCentri = new LinkedList<>();
            Indirizzo indirizzo = null;
            while(resultSet.next()) {
                String nome = resultSet.getString(1);
                String Comune = resultSet.getString(2);
                String qualificatore = resultSet.getString(3);
                Qualificatore qualificatore1 = Qualificatore.getQualificatore(qualificatore);
                String via = resultSet.getString(4);
                String numCivico = resultSet.getString(5);
                String sigla = resultSet.getString(6);
                int cap = resultSet.getInt(7);
                String tipo = resultSet.getString(8);
                Tipologia tipologia1 = Tipologia.getTipo(tipo);

                indirizzo = new Indirizzo(qualificatore1,via,numCivico,Comune,sigla,cap);
                listaCentri.add(new CentroVaccinale(nome,indirizzo,tipologia1));
            }
            if(listaCentri.isEmpty())
                return null;
            else
                return listaCentri;
        } catch (SQLException e) {return null;}
    }

    /**
     * il metodo permette la ricerca di una serie di centri vaccinali specificando una stringa rappresentante il nome (o parte di esso)
     * @param nome nome del centro vaccinale (anche non completa)
     * @return lista di centri vaccinali
     * @throws RemoteException eccezione rmi
     *
     * @author Alessandro cassani
     */
    @Override
    public LinkedList<CentroVaccinale> getCentriVaccinali(String nome) throws RemoteException {
        try {
            PreparedStatement ps =  DBManagement.getDB().connection.prepareStatement("SELECT * FROM CentriVaccinali(nomeCentro,Comune,qualificatore,via,numCivico,sigla,cap,tipologia)" +
                    "WHERE nomeCentro LIKE %" + nome + "%" );
            ResultSet resultSet = ps.executeQuery();
            ps.close();
            LinkedList<CentroVaccinale> listaCentri = new LinkedList<>();
            Indirizzo indirizzo = null;
            while(resultSet.next()){
                String nomeCentro = resultSet.getString(1);
                String comune = resultSet.getString(2);
                String qualificatore = resultSet.getString(3);
                Qualificatore qualificatore1 = Qualificatore.getQualificatore(qualificatore);
                String via = resultSet.getString(4);
                String numCivico = resultSet.getString(5);
                String sigla = resultSet.getString(6);
                int cap = resultSet.getInt(7);
                String tipo = resultSet.getString(8);
                Tipologia tipologia1 = Tipologia.getTipo(tipo);

                indirizzo = new Indirizzo(qualificatore1,via,numCivico,comune,sigla,cap);
                listaCentri.add(new CentroVaccinale(nomeCentro,indirizzo,tipologia1));
            }
            if(listaCentri.isEmpty())
                return null;
            else
                return listaCentri;
        } catch (SQLException e) {return null;}
    }

    /**
     * metodo che permette la ricerca dei nomi dei centri vaccinali esistenti
     * @return lista dei nomi dei centri vaccinali esistenti
     * @throws RemoteException eccezione rmi
     *
     * @author Alessandro Cassani
     */
    @Override
    public LinkedList<String> getNomicentriVaccinali() throws RemoteException {
        try {
            PreparedStatement ps =  DBManagement.getDB().connection.prepareStatement("SELECT nomeCentro FROM CentriVaccinali(nomeCentro,Comune,qualificatore,via,numCivico,sigla,cap,tipologia)");
            ResultSet resultSet = ps.executeQuery();
            LinkedList<String> listaNomiCentri = new LinkedList<>();
            while(resultSet.next()){
                listaNomiCentri.add(resultSet.getString(1));
            }
            if(listaNomiCentri.isEmpty())
                return null;
            else
                return listaNomiCentri;
        } catch (SQLException e) {return null;}
    }
}
