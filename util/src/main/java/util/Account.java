package util;
import java.io.Serializable;

    /**
     * classe che gestisce le informazioni di accesso ad un account
     *
     * @author Alessandro Cassani
     */
 public class Account implements Serializable {

    /**
     * userId dell'account
     */
    private final String userId;

    /**
     * password dell'account
     */
    private final String password;

    /**
     * metodo che permette la creazione di un oggetto di tipo Account
     *
     * @param userId userId della persona associata all'account
     * @param password password della persona associata all'account
     *
     * @author Alessandro Cassani
     */
    public Account(String userId,String password) {
        this.userId = userId;
        this.password = password;
    }

    /**
     * metodo che permette di accedere al campo privato userId
     * @return userId dell'account
     *
     * @author Alessandro Cassani
     */
    public String getUserId(){
        return userId;
    }

    /**
     * metodo che permette di accedere al campo privato password
     * @return password dell'account
     *
     * @author Alessandro Cassani
     */
    public String getPassword(){
        return password;
    }
 }
