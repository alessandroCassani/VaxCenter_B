package util;

import java.math.BigInteger;
import java.util.Date;

    /**
     * classe che permette la creazione di un oggetto di tipo Cittadino
     *
     * @author Alessandro Cassani
     */
public class Cittadino extends Persona {
        /**
         * Numero di seriale per la comunicazione RMI
         */
    private static final long serialVersionUID = 78910L;
        /**
         * Account associato al cittadino
         */
    private final Account account;



        /**
         * metodo che permette la modellazione delle informazioni di un oggetto di tipo Cittadino
         *
         * @param nome nome del cittadino
         * @param cognome cognome del cittadino
         * @param codFisc codice fiscale del cittadino
         * @param email email del cittadino
         * @param id id del cittadino
         * @param dataNascita data di nascita del cittadino
         * @param centroVaccinale centro vaccinale in cui si e' vaccinato il cittadino
         * @param account account del cittadino
         *
         * @author Alessandro Cassani
         */
    public Cittadino(String nome, String cognome, String codFisc, String email, BigInteger id, Date dataNascita, CentroVaccinale centroVaccinale, Account account){
        super(nome,cognome,codFisc,email,id,dataNascita,centroVaccinale);
        this.account = account;
    }

        /**
         * metodo che permette di accedere all'account privato del cittadino
         * @return account del cittadino
         *
         * @author Alessandro Cassani
         */
    public Account getAccount(){
        return account;
    }
}

