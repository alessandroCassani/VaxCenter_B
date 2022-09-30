import util.*;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Types;

public class ServerImpl extends UnicastRemoteObject implements ServerInterface {

    /**
     * costruttore vuoto
     * @throws RemoteException
     */
    protected ServerImpl() throws RemoteException {
        super();
    }

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

    @Override
    public boolean registraCittadino(Cittadino cittadino) throws RemoteException {
        try{
            PreparedStatement ps = DBManagement.getDB().connection.prepareStatement("INSERT INTO Cittadini_Registrati(id,email,username,password) \n"
                                                                                    + "VALUES (?,?,?,?)");

            //da modificare struttura cittadini registrati
            ps.setString(1, cittadino.getId());
            ps.setString(2, cittadino.getEmail());
            ps.setString(2,cittadino.getAccount().getUserId());
            ps.setString(4,cittadino.getAccount().getPassword());



            ps.executeUpdate();
            ps.close();
        } catch (SQLException e){return false;}
        return true;
    }

    @Override
    public boolean registraVaccinato(Vaccinato vaccinato) throws RemoteException {
        try {
            PreparedStatement ps = DBManagement.getDB().connection.prepareStatement("INSERT INTO Vaccinati(id,nomeCentro,nome,cognome,codiceFiscale,dataVaccino,vaxTipo,isReg) \n" +
                                                                                                     "VALUES(?,?,?,?,?,?,?,?");
            ps.setString(1, vaccinato.getId());
            ps.setString(2,vaccinato.getCentroVaccinale().getNome());
            ps.setString(3,vaccinato.getNome());
            ps.setString(4,vaccinato.getCognome());
            ps.setString(5,vaccinato.getCodFisc());
            ps.setDate(6, (Date) vaccinato.getDataSomministrazione());  //controllo cast
            ps.setString(7,vaccinato.getVaccino().toString());
            ps.setBoolean(8,false); //alla registrazione del vaccinato è impossibile che questo sia già loggato
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
