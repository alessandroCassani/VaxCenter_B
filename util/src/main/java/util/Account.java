package util;
import java.io.Serializable;

/**
 * classe che gestisce le informazioni di accesso ad un account
 *
 * @author Alessandro Cassani - Matricola: 744512 - Sede: VA
 * @author Paolo Bruscagin - Matricola: 744703 - Sede: VA
 * @author Damiano Ficara - Matricola 744958 - Sede: VA
 * @author Luca Perfetti - Matricola 746581 - Sede: VA
 *
 */
public class Account implements Serializable {

    /**
     * Numero di seriale per la comunicazione RMI
     */
    private static final long serialVersionUID = 78910L;
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