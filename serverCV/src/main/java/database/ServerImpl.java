package database;


import util.*;

import javax.crypto.*;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.*;
import java.util.Arrays;
import java.util.Base64;
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

    private final String SECRETKEY = "MksoYbsdkyHos78";

    private static SecretKeySpec secretKey;

    private static byte[] key;

    /**
     * costruttore vuoto
     * @throws RemoteException eccezione RMI
     */
    public ServerImpl() throws RemoteException {
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
            ps.setString(2,centroVaccinale.getQualificatore().toString().toUpperCase());
            ps.setString(3,centroVaccinale.getNomeVia());
            ps.setString(4,centroVaccinale.getCivico());
            ps.setString(5,centroVaccinale.getProvincia());
            ps.setString(6,centroVaccinale.getComune());
            ps.setInt(7,centroVaccinale.getCap());
            ps.setString(8,centroVaccinale.getTipologia().toString().toUpperCase());
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

            ps.setString(1, idPadding(cittadino.getId()));
            ps.setString(2, encrypt(cittadino.getNome(),SECRETKEY));
            ps.setString(3,encrypt(cittadino.getCognome(),SECRETKEY));
            ps.setString(4,encrypt(cittadino.getCodFisc(),SECRETKEY));
            ps.setString(5, encrypt(cittadino.getEmail(),SECRETKEY));
            ps.setString(6,encrypt(cittadino.getAccount().getUserId(),SECRETKEY));
            ps.setString(7,encrypt(cittadino.getAccount().getUserId(),SECRETKEY));
            ps.setString(8,encrypt(cittadino.getCentroVaccinale(),SECRETKEY));
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
    public String registraVaccinato(Vaccinato vaccinato) throws RemoteException {
        BigInteger numero;
        try {
            PreparedStatement preparedStatement = DBManagement.getDB().connection.prepareStatement("SELECT id FROM vaccinati");
            ResultSet resultSet = preparedStatement.executeQuery();
            TreeSet<BigInteger> id = new TreeSet<>();
            while(resultSet.next()){
                id.add(new BigInteger(resultSet.getString(1))); //TreeSet ordina di default gli elementi in ordine crescente
            }
            if(!id.isEmpty())
                numero = id.last().add(new BigInteger("0000000000000001"));
            else
                numero = new BigInteger("0000000000000000");
            preparedStatement.close();

            PreparedStatement ps = DBManagement.getDB().connection.prepareStatement("INSERT INTO vaccinati(id,nome_centro_vaccinale,nome,cognome,codice_fiscale,data_vaccino,tipo_vaccino) \n" +
                    "VALUES(?,?,?,?,?,?,?)");
            ps.setString(1, idPadding(numero));
            ps.setString(2,encrypt(vaccinato.getCentroVaccinale(),SECRETKEY));
            ps.setString(3,encrypt(vaccinato.getNome(),SECRETKEY));
            ps.setString(4,encrypt(vaccinato.getCognome(),SECRETKEY));
            ps.setString(5,encrypt(vaccinato.getCodFisc(),SECRETKEY));
            ps.setString(6,encrypt(vaccinato.getDataSomministrazione().toString(),SECRETKEY));
            ps.setString(7,encrypt(vaccinato.getVaccino().toString(),SECRETKEY));
            ps.executeUpdate();
            ps.close();

        }catch (SQLException e){e.printStackTrace();return "-1";}
        return idPadding(numero);
    }

    /**
     * il metodo permette la registrazione nella tabella Eventi_Avversi le informazioni relative agli eventi avversi registrati dal cittadino.
     * le severita' e le note opzionali sono inserite come stringhe nella tabella eventi_avversi
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
            int count;
            PreparedStatement ps = DBManagement.getDB().connection.prepareStatement("INSERT INTO eventi_avversi(username,mal_di_testa,febbre,tachicardia,dolori_muscolari,linfoadenopatia,crisi_ipertensiva,note) " +
                    " VALUES (?,?,?,?,?,?,?,?)");
            // la lista che contiene sintomi e severità deve contenere tutti i sintomi, non solo quelli segnalati
            //quelli non segnalati sono riconoscibili perchè hanno severità settata a 0
            ps.setString(1,encrypt(user,SECRETKEY));
            count = 2;
            while(count<8) {
                ps.setInt(count,eventiAvversi.getSintomi().get(count-2).getSeverita());
                count++;
            }
            ps.setString(8, encrypt(eventiAvversi.getNote(),SECRETKEY));
            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {e.printStackTrace();return  false;}
        return true;
    }

    /**
     * il metodo permette di controllare se il cittadino ha un account oppure no
     * @param user account del cittadino
     * @return true/false in base all'esito dell'operazione
     * @throws RemoteException
     *
     * @author Paolo Bruscagin
     */
    @Override
    public boolean isAERegistered(String user) throws RemoteException {
        try {
            PreparedStatement ps = DBManagement.getDB().connection.prepareStatement("SELECT * FROM eventi_avversi WHERE username = ?");

            ps.setString(1, encrypt(user,SECRETKEY));

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
     * il metodo permette di avere il riepilogo degli eventi avversi già registrati di un cittadino
     * @param user account del cittadino
     * @return true/false in base all'esito dell'operazione
     * @throws RemoteException eccezione rmi
     *
     * @author Paolo Bruscagin
     */

    @Override
    public String[] getPersonAE(String user) throws RemoteException {
        PreparedStatement preparedStatement = null;
        String [] info = new String [7];
        try {
            preparedStatement = DBManagement.getDB().connection.prepareStatement("SELECT * FROM eventi_avversi WHERE username = ?");
            preparedStatement.setString(1,encrypt(user,SECRETKEY));
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                info[0] = String.valueOf(resultSet.getInt(2));
                info[1] = String.valueOf(resultSet.getInt(3));
                info[2] = String.valueOf(resultSet.getInt(4));
                info[3] = String.valueOf(resultSet.getInt(5));
                info[4] = String.valueOf(resultSet.getInt(6));
                info[5] = String.valueOf(resultSet.getInt(7));
                info[6] = resultSet.getString(8);
            }
            return info;
        } catch (SQLException e) {
            return null;
        }
    }
    /**
     * il metodo permette di avere il riepilogo dei dati generali di un cittadino
     * @param user account del cittadino
     * @return true/false in base all'esito dell'operazione
     * @throws RemoteException eccezione rmi
     *
     * @author Paolo Bruscagin
     * @author Alessandro Cassani
     */
    public String[] getInfoCittadino(String user) throws RemoteException {
        PreparedStatement preparedStatement = null;
        String [] info = new String [6];
        try {
            preparedStatement = DBManagement.getDB().connection.prepareStatement("SELECT * FROM cittadini WHERE username = ?");
            preparedStatement.setString(1,encrypt(user,SECRETKEY));
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                info[0] = decrypt(resultSet.getString(1),SECRETKEY);
                info[1] = decrypt(resultSet.getString(2),SECRETKEY);
                info[2] = decrypt(resultSet.getString(3),SECRETKEY);
                info[3] = decrypt(resultSet.getString(4),SECRETKEY);
                info[4] = decrypt(resultSet.getString(5),SECRETKEY);
                info[5] = decrypt(resultSet.getString(6),SECRETKEY);
            }
            return info;
        } catch (SQLException e) {
            return null;
        }
    }


    /**
     * il metodo permette di controllare se il cittadino ha un account oppure no
     * @param account account del cittadino
     * @return true/false in base all'esito dell'operazione
     * @throws RemoteException eccezione rmi
     *
     * @author Luca Perfetti
     * @author Damiano Ficara
     */
    @Override
    public boolean isSignedUp(Account account) throws RemoteException {
        try {
            PreparedStatement ps = DBManagement.getDB().connection.prepareStatement("SELECT * FROM cittadini " +
                    "WHERE username = '" + encrypt(account.getUserId(),SECRETKEY)+ "'" + "AND password ='" + encrypt(account.getPassword(),SECRETKEY) +"'" );

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
     * il metodo permette di controllare se il nome utente inserito e' gia' stato utilizzato
     * @param user nome utente
     * @return true/false in base all'esito dell'operazione
     * @throws RemoteException eccezione rmi
     *
     * @author Luca Perfetti
     * @author Alessandro Cassani
     */
    @Override
    public boolean isUserRegistrated(String user) throws RemoteException {
        try {
            PreparedStatement ps = DBManagement.getDB().connection.prepareStatement("SELECT * FROM cittadini WHERE username = ?");

            ps.setString(1, encrypt(user,SECRETKEY));

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
     * @throws RemoteException eccezione rmi
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
            e.printStackTrace();
            return false;
        }
        return false;
    }

    /**
     * il metodo permette il controllo della già avvenuta vaccinazione del cittadino
     * @param cf codice fiscale del vaccinato
     * @return true/false in base all'esito dell'operazione
     * @throws RemoteException eccezione rmi
     *
     * @author Luca Perfetti
     */
    @Override
    public boolean isVaccinatedRegistrated(String cf) throws RemoteException {
        try {
            PreparedStatement ps = DBManagement.getDB().connection.prepareStatement("SELECT * FROM vaccinati WHERE codice_fiscale = ?");

            ps.setString(1, encrypt(cf,SECRETKEY));

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
     * metodo che permette la verifica dell'inserimento del corretto id in fase di registrazione
     * @param id id del cittadino
     * @param codiceFiscale codice fiscale del cittadino
     * @return true se la stringa id inserita e' corretta, false altrimenti
     * @throws RemoteException eccezione rmi
     *
     * @author Alessandro Cassani
     */
    @Override
    public boolean isIdCorrect(String id,String codiceFiscale) throws RemoteException {
        try {
            PreparedStatement ps = DBManagement.getDB().connection.
                    prepareStatement("SELECT id FROM vaccinati " +
                            "WHERE codice_fiscale = ?");
            ps.setString(1, encrypt(codiceFiscale,SECRETKEY));
            BigInteger idUnivoco = new BigInteger(id);
            String idPostPadding = idPadding(idUnivoco);

            ResultSet resultSet = ps.executeQuery();
            if (resultSet.next()) {
                String risultato = idPadding(new BigInteger(resultSet.getString(1)));
                return risultato.equals(idPostPadding);
            }
        } catch (SQLException e) {
            e.printStackTrace();
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
                            "FROM eventi_avversi JOIN cittadini USING (username) WHERE nome_centro_vaccinale = '" + encrypt(nomeCentroVaccinale,SECRETKEY) + "'");
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
     * @author Damiano Ficara
     */
    @Override
    public LinkedList<CentroVaccinale> getCentriVaccinali(String comune, Tipologia tipologia) throws RemoteException {
        try {
            PreparedStatement ps =  DBManagement.getDB().connection.prepareStatement("SELECT * FROM centri_vaccinali " +
                    "WHERE comune  LIKE '%"+ comune.toUpperCase() +"%' AND tipologia LIKE'%" + tipologia.toString().toUpperCase() +"%'");
            ResultSet resultSet = ps.executeQuery();

            LinkedList<CentroVaccinale> listaCentri = new LinkedList<>();
            while(resultSet.next()) {
                String nome = resultSet.getString(1);
                String qualificatore = resultSet.getString(2);
                Qualificatore qualificatore1 = Qualificatore.getQualificatore(qualificatore);
                String via = resultSet.getString(3);
                String numCivico = resultSet.getString(4);
                String sigla = resultSet.getString(5);
                String Comune = resultSet.getString(6).toUpperCase();
                int cap = resultSet.getInt(7);
                String tipo = resultSet.getString(8).toUpperCase();
                Tipologia tipologia1 = Tipologia.getTipo(tipo);

                listaCentri.add(new CentroVaccinale(nome,qualificatore1,via,numCivico,sigla,Comune,cap,tipologia1));
            }
            ps.close();
            return listaCentri;
        } catch (SQLException e) {e.printStackTrace();return null;}
    }
    /**
     * il metodo permette la ricerca di una serie di centri vaccinali specificando il comune e la tipologia di essi
     * @param nome stringa che rappresenta il nome del centro vaccinale
     * @return lista di centri vaccinali
     * @throws RemoteException eccezione rmi
     *
     * @author Alessandro Cassani
     * @author Damiano Ficara
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
     * il metodo permette la ricerca di una serie di centri vaccinali specificando una stringa rappresentante il nome (o parte di esso)
     * @return lista di centri vaccinali
     * @author Alessandro cassani
     */

    public LinkedList<CentroVaccinale> getCentriVaccinali() throws RemoteException {
        try {
            PreparedStatement ps =  DBManagement.getDB().connection.prepareStatement("SELECT * FROM centri_vaccinali ");
            ResultSet resultSet = ps.executeQuery();
            LinkedList<CentroVaccinale> listaCentri = new LinkedList<>();
            while(resultSet.next()){
                String Nome = resultSet.getString(1);
                String qualificatore = resultSet.getString(2);
                Qualificatore qualificatore1 = Qualificatore.getQualificatore(qualificatore);
                String via = resultSet.getString(3);
                String numCivico = resultSet.getString(4);
                String sigla = resultSet.getString(5);
                String Comune = resultSet.getString(6).toUpperCase();
                int cap = resultSet.getInt(7);
                String tipo = resultSet.getString(8).toUpperCase();
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

    /**
     * metodo che permette di ottenere sigla e provincia dato un comune
     * @return sigla e provincia di un comune
     * @throws RemoteException eccezione rmi
     *
     * @author Alessandro Cassani
     * @author Damiano Ficara
     */
    @Override
    public CapProvincia getComuneInfo(String comune) throws RemoteException {
        String provincia = "";
        String cap = "";
        try {
            PreparedStatement preparedStatement =
                    DBManagement.getDB().connection.prepareStatement("SELECT provincia,cap FROM dataset_comuni WHERE comune = ?");
            preparedStatement.setString(1,comune.toUpperCase());
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                provincia = resultSet.getString(1);
                cap = resultSet.getString(2);
            }

        } catch (SQLException e) {e.printStackTrace();}
        return new CapProvincia(cap,provincia);
    }


    /**
     * metodo che permette di ottenere la lista dei comuni italiani
     * @return lista dei nomi dei comuni italiani
     * @throws RemoteException eccezione rmi
     *
     * @author Alessandro Cassani
     * @author Damiano Ficara
     */
    @Override
    public LinkedList<String> getComuniNome() throws RemoteException {
        try {
            PreparedStatement ps =  DBManagement.getDB().connection.prepareStatement("SELECT comune FROM dataset_comuni");
            ResultSet resultSet = ps.executeQuery();
            LinkedList<String> listaNomiCentri = new LinkedList<>();
            while(resultSet.next()){
                listaNomiCentri.add(resultSet.getString(1));
            }
            return listaNomiCentri;
        } catch (SQLException e) {return null;}
    }

    /**
     * il metodo permette di eseguire il padding fino a 16 cifre della stringa rappresentante l'ide del vaccinato
     * @param id id sul quale eseguire padding
     * @return id a 16 cifre
     *
     * @author Alessandro Cassani
     */
    private static String idPadding(BigInteger id){
        String str = String.valueOf(id);
        switch (str.length()){
            case 1: str = "000000000000000" + str;
                break;
            case 2: str = "00000000000000" + str;
                break;
            case 3: str = "0000000000000" + str;
                break;
            case 4: str = "000000000000" + str;
                break;
            case 5: str = "00000000000" + str;
                break;
            case 6: str = "0000000000" + str;
                break;
            case 7: str = "000000000" + str;
                break;
            case 8: str = "00000000" + str;
                break;
            case 9: str = "0000000" + str;
                break;
            case 10: str = "000000" + str;
                break;
            case 11: str = "00000" + str;
                break;
            case 12: str = "0000" + str;
                break;
            case 13: str = "000" + str;
                break;
            case 14: str = "00" + str;
                break;
            case 15: str = "0" + str;
                break;
        }
        return str;
    }

    public static void setKey(final String myKey) {
        MessageDigest sha = null;
        try {
            key = myKey.getBytes("UTF-8");
            sha = MessageDigest.getInstance("SHA-1");
            key = sha.digest(key);
            key = Arrays.copyOf(key, 16);
            secretKey = new SecretKeySpec(key, "AES");
        } catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    public static String encrypt(final String strToEncrypt, final String secret) {
        try {
            setKey(secret);
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE, secretKey);
            return Base64.getEncoder()
                    .encodeToString(cipher.doFinal(strToEncrypt.getBytes("UTF-8")));
        } catch (Exception e) {
            System.out.println("Error while encrypting: " + e.toString());
        }
        return null;
    }

    public static String decrypt(final String strToDecrypt, final String secret) {
        try {
            setKey(secret);
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5PADDING");
            cipher.init(Cipher.DECRYPT_MODE, secretKey);
            return new String(cipher.doFinal(Base64.getDecoder()
                    .decode(strToDecrypt)));
        } catch (Exception e) {
            System.out.println("Error while decrypting: " + e.toString());
        }
        return null;
    }
}