import util.*;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class ServerImpl extends UnicastRemoteObject implements ServerInterface {

    /**
     * costruttore vuoto
     * @throws RemoteException
     */
    protected ServerImpl() throws RemoteException {
        super();
    }

    @Override
    public boolean registraCentroVaccinale(CentroVaccinale centroVaccinale) throws RemoteException {
        return false;
    }

    @Override
    public boolean registraCittadino(Cittadino cittadino) throws RemoteException {
        return false;
    }

    @Override
    public boolean registraVacinato(Vaccinato vaccinato) throws RemoteException {
        return false;
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
