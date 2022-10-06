import util.*;

import java.math.BigInteger;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.*;
import java.util.ArrayList;

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
     * il metodo permette di registrare nel DB nell'oppurtuna tabella un centro vaccinale
     * @param centroVaccinale centro vaccinale
     * @return true/false in base all'esito dell'operazione
     * @throws RemoteException eccezione RMI
     *
     * @author Alessandro Cassani
     */
    @Override
    public synchronized boolean registraCentroVaccinale(CentroVaccinale centroVaccinale) throws RemoteException{
        try {
            PreparedStatement ps = DBManagement.getDB().connection.prepareStatement("INSERT INTO CentroVaccinale(nomeCentro,indirizzo,tipologia,numeroSegnalazioni,avgSeverita \n"
                    + "VALUES (?,?,?,?,?)");
            ps.setString(1, centroVaccinale.getNome());
            ps.setString(2,centroVaccinale.getIndirizzo().toString());
            ps.setString(3,centroVaccinale.getTipologia().toString());
            ps.setNull(4, Types.NULL);
            ps.setNull(5,Types.NULL);
            ps.executeUpdate();
            ps.close();
        } catch(SQLException e){return false;}
        return true;
    }

    /**
     * il metodo permette la registrazione nell'opportuna tabella del DB di un oggetto di tipo cittadino
     * @param cittadino cittadino
     * @return true/false in base all'esito dell'operazione
     * @throws RemoteException eccezione RMI
     *
     * @author Alessandro Cassani
     */
    @Override
    public boolean registraCittadino(Cittadino cittadino) throws RemoteException {
        try{
            PreparedStatement ps = DBManagement.getDB().connection.prepareStatement("INSERT INTO Cittadini_Registrati(id,nome,cognome,codFisc,email,username,password) \n"
                                                                                    + "VALUES (?,?,?,?,?,?,?)");

            //da modificare struttura cittadini registrati
            ps.setString(1, cittadino.getId());
            ps.setString(2, cittadino.getNome());
            ps.setString(3,cittadino.getCognome());
            ps.setString(4,cittadino.getCodFisc());
            ps.setString(5, cittadino.getEmail());
            ps.setString(6,cittadino.getAccount().getUserId());
            ps.setString(7,cittadino.getAccount().getPassword());
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
            ArrayList<BigInteger> id = new ArrayList<>();
            while(resultSet.next()){
                id.add(new BigInteger(resultSet.getString(1)));            }
            preparedStatement.close();


            PreparedStatement ps = DBManagement.getDB().connection.prepareStatement("INSERT INTO Vaccinati(id,nome,cognome,codiceFiscale,dataVaccino,vaxTipo,nomecentro) \n" +
                    "VALUES(?,?,?,?,?,?,?");
            ps.setString(1, vaccinato.getId());
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

    @Override
    public boolean inserisciEventiAvversi(EventiAvversi eventiAvversi) throws RemoteException {

        return false;
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
}
