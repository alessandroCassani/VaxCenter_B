
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
            PreparedStatement ps = con.prepareStatement("INSERT INTO centri_vaccinali(nome_centro_vaccinale,qualificatore,nome_via,civico,provincia,comune,cap,tipologia) "
                    + "VALUES (?,?,?,?,?,?,?,?)");
            ps.setString(1, centroVaccinale.getNome());
            ps.setString(2,centroVaccinale.getQualificatore().toString());
            ps.setString(3,centroVaccinale.getNomeVia());
            ps.setString(4,centroVaccinale.getCivico());
            ps.setString(5,centroVaccinale.getProvincia());
            ps.setString(6,centroVaccinale.getComune());
            ps.setInt(7,centroVaccinale.getCap());
            ps.setString(8,centroVaccinale.getTipologia().toString());
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
            PreparedStatement ps = DBManagement.getDB().connection.prepareStatement("INSERT INTO cittadini(id,nome,cognome,codice_fiscale,email,username,password,nome_centro_vaccinale) \n"
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
        } catch (SQLException e){e.printStackTrace();return false;}
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
            PreparedStatement preparedStatement = DBManagement.getDB().connection.prepareStatement("SELECT id FROM vaccinati");
            ResultSet resultSet = preparedStatement.executeQuery();
            TreeSet<BigInteger> id = new TreeSet<>();
            while(resultSet.next()){
                id.add(new BigInteger(resultSet.getString(1))); //TreeSet ordina di default gli elementi in ordine crescente
            }
            BigInteger numero;
            if(!id.isEmpty())
                numero = id.last().add(new BigInteger("0000000000000001"));
            else
                numero = new BigInteger("0000000000000000");
            preparedStatement.close();

            PreparedStatement ps = DBManagement.getDB().connection.prepareStatement("INSERT INTO vaccinati(id,nome_centro_vaccinale,nome,cognome,codice_fiscale,data_nascita,data_vaccino,tipo_vaccino) \n" +
                    "VALUES(?,?,?,?,?,?,?,?)");
            ps.setString(1, numero.toString());
            ps.setString(2,vaccinato.getNome());
            ps.setString(3,vaccinato.getCognome());
            ps.setString(4,vaccinato.getCodFisc());
            ps.setString(5,vaccinato.getDataNascita().toString());
            ps.setString(6,vaccinato.getDataSomministrazione().toString());
            ps.setString(7,vaccinato.getVaccino().toString());
            ps.setString(8,vaccinato.getCentroVaccinale().getNome());
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
    public boolean inserisciEventiAvversi(EventiAvversi eventiAvversi,String user) throws RemoteException {
        try {
            PreparedStatement preparedStatement = DBManagement.getDB().connection.prepareStatement("INSERT INTO eventi_avversi(username,mal_di_testa,febbre,tachicardia,dolori_muscolari,linfoadenopatia,crisi_ipertensiva) " +
                    "VALUES(?,?,?,?,?,?,?)");

            // la lista che contiene sintomi e severità deve contenere tutti i sintomi, non solo quelli segnalati
            //quelli non segnalati sono riconoscibili perchè hanno severità settata a 0
            int count;
            preparedStatement.setString(1,user);
            for (count=2;count<8;count++) {
                preparedStatement.setBoolean(count, eventiAvversi.getSintomi().get(count-1).getSeverita() != 0);
            }
            preparedStatement.executeUpdate();
            preparedStatement.close();


            PreparedStatement ps = DBManagement.getDB().connection.prepareStatement("INSERT INTO severita(username,mal_di_testa,febbre,tachicardia,dolori_muscolari,linfoadenopatia,crisi_ipertensiva,note) " +
                    " VALUES (?,?,?,?,?,?,?,?)");
            // la lista che contiene sintomi e severità deve contenere tutti i sintomi, non solo quelli segnalati
            //quelli non segnalati sono riconoscibili perchè hanno severità settata a 0
            ps.setString(1,user);
            count = 2;
            while(count<8) {
                ps.setInt(count,eventiAvversi.getSintomi().get(count-1).getSeverita());
                count++;
            }
            ps.setString(8, eventiAvversi.getNote());
            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {e.printStackTrace();return  false;}
        return true;
    }

    /**
     * il metodo permette di controllare se il cittadino ha un account oppure no
     * @param account account del cittadino
     * @return true/false in base all'esito dell'operazione
     * @throws RemoteException
     *
     * @author Luca Perfetti
     */
    @Override
    public boolean isSignedUp(Account account) throws RemoteException {
        try {
            PreparedStatement ps = DBManagement.getDB().connection.prepareStatement("SELECT * FROM cittadini WHERE username = ? AND password = ?");

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
     * il metodo permette il controllo del nome utente gia' utilizzato
     * @param user nome utente
     * @return true/false in base all'esito dell'operazione
     * @throws RemoteException eccezione rmi
     *
     * @author Luca Perfetti
     */
    @Override
    public boolean isUserRegistrated(String user) throws RemoteException {
        try {
            PreparedStatement ps = DBManagement.getDB().connection.prepareStatement("SELECT * FROM cittadini WHERE username = ?");

            ps.setString(1, user);

            ResultSet resultSet = ps.executeQuery();
            if(resultSet.next()){
                return true;
            }
        }catch (SQLException e){
            return false;
        }
        return false;
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

            PreparedStatement ps = DBManagement.getDB().connection.prepareStatement("SELECT * FROM centri_vaccinali WHERE nome_centro_vaccinale = ?");
            ps.setString(1, VaxCenterName);

            ResultSet resultSet = ps.executeQuery();
            if(resultSet.next()){
                return true;
            }
        }catch (SQLException e){
            return false;
        }
        return false;
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
            PreparedStatement ps = DBManagement.getDB().connection.prepareStatement("SELECT * FROM vaccinati WHERE codice_fiscale = ?");

            ps.setString(1, user);

            ResultSet resultSet = ps.executeQuery();
            if(resultSet.next()){
                return true;
            }
        }catch (SQLException e){
            return false;
        }
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
            PreparedStatement preparedStatement = DBManagement.getDB().connection.prepareStatement(
                    "SELECT COUNT(mal_di_testa) AS segnalazioni_mdt, AVG(mal_di_testa) AS media_mdt, " +
                            "COUNT(febbre) AS segnalazioni_febbre, AVG(febbre) AS media_febbre, " +
                            "COUNT(dolori_muscolari) AS segnalazioni_dm, AVG(dolori_muscolari) AS media_dm, " +
                            "COUNT(linfoadenopatia) AS segnalazioni_linfoadenopatia, AVG(linfoadenopatia) AS media_linfoadenopatia, " +
                            "COUNT(crisi_ipertensiva) AS segnalazioni_ci, AVG(crisi_ipertensiva) AS media_ci " +
                    "FROM severita JOIN cittadini USING (username) WHERE nome_centro_vaccinale = '" + nomeCentroVaccinale + "'");
            ResultSet resultSet = preparedStatement.executeQuery();

            if(resultSet.next()) {
                return "CEFALEA: " + resultSet.getString(1) + " segnalazioni | Intensità media " +Math.floor(Double.parseDouble(resultSet.getString(2))*100)/100 + "\n" +
                        "FEBBRE: " + resultSet.getString(3) + " segnalazioni | Intensità media " + Math.floor(Double.parseDouble(resultSet.getString(4))*100)/100 + "\n" +
                        "DOLORI MUSCOLARI: " + resultSet.getString(5) + " segnalazioni | Intensità media " + Math.floor(Double.parseDouble(resultSet.getString(6))*100)/100+ "\n" +
                        "LINFOADENOPATIA " + resultSet.getString(7) + " segnalazioni | Intensità media " + Math.floor(Double.parseDouble(resultSet.getString(8))*100)/100 + "\n" +
                        "CRISI IPERTENSIVA " + resultSet.getString(9) + " segnalazioni | Intensità media " + Math.floor(Double.parseDouble(resultSet.getString(10))*100)/100;
            }

        } catch (SQLException e) {e.printStackTrace();return null;}
        return null;
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
            PreparedStatement ps =  DBManagement.getDB().connection.prepareStatement("SELECT * FROM centri_vaccinali \n" +
                    "WHERE comune = '"+ comune +"' AND tipologia = '" + tipologia.toString() +"'");
            ResultSet resultSet = ps.executeQuery();

            LinkedList<CentroVaccinale> listaCentri = new LinkedList<>();
            while(resultSet.next()) {
                String nome = resultSet.getString(1);
                String qualificatore = resultSet.getString(2);
                Qualificatore qualificatore1 = Qualificatore.getQualificatore(qualificatore);
                String via = resultSet.getString(3);
                String numCivico = resultSet.getString(4);
                String sigla = resultSet.getString(5);
                String Comune = resultSet.getString(6);
                int cap = resultSet.getInt(7);
                String tipo = resultSet.getString(8);
                Tipologia tipologia1 = Tipologia.getTipo(tipo);

                listaCentri.add(new CentroVaccinale(nome,qualificatore1,via,numCivico,sigla,Comune,cap,tipologia1));
            }
            ps.close();
            return listaCentri;
        } catch (SQLException e) {e.printStackTrace();return null;}
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
            PreparedStatement ps =  DBManagement.getDB().connection.prepareStatement("SELECT * FROM centri_vaccinali " +
                    "WHERE nome_centro_vaccinale LIKE '%"+ nome + "%'" );
           // ps.setString(1,nome);
            ResultSet resultSet = ps.executeQuery();
            LinkedList<CentroVaccinale> listaCentri = new LinkedList<>();
            while(resultSet.next()){
                String Nome = resultSet.getString(1);
                String qualificatore = resultSet.getString(2);
                Qualificatore qualificatore1 = Qualificatore.getQualificatore(qualificatore);
                String via = resultSet.getString(3);
                String numCivico = resultSet.getString(4);
                String sigla = resultSet.getString(5);
                String Comune = resultSet.getString(6);
                int cap = resultSet.getInt(7);
                String tipo = resultSet.getString(8);
                Tipologia tipologia1 = Tipologia.getTipo(tipo);

                listaCentri.add(new CentroVaccinale(Nome,qualificatore1,via,numCivico,sigla,Comune,cap,tipologia1));
            }
            ps.close();
            return listaCentri;
        } catch (SQLException e) {e.printStackTrace();return null;}
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
            PreparedStatement ps =  DBManagement.getDB().connection.prepareStatement("SELECT nome_centro_vaccinale FROM centri_vaccinali ");
            ResultSet resultSet = ps.executeQuery();
            LinkedList<String> listaNomiCentri = new LinkedList<>();
            while(resultSet.next()){
                listaNomiCentri.add(resultSet.getString(1));
            }
            return listaNomiCentri;
        } catch (SQLException e) {return null;}
    }
}
