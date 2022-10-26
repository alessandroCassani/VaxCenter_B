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
         * Account associato al cittadino
         */
    private final Account account;

    /**
     * * data di dascita della persona
     */
    private final Date dataNascita;

    /**
     * email della persona
     */
    private final String email;



        /**
         * metodo che permette la modellazione delle informazioni di un oggetto di tipo Cittadino
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
        super(nome,cognome,codFisc,id,centroVaccinale);
        this.email = email;
        this.dataNascita = dataNascita;
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

        /**
         * metodo che permette di accedere al campo privato email
         * @return email della persona
         *
         * @author Alessandro Cassani
         */
        public String getEmail(){
            return email;
        }

        /**
         * metodo che permette di accedere al campo privato data di nascita
         * @return data di nascita della persona
         *
         * @author Alessandro Cassani
         */
        public Date getDataNascita(){
            return dataNascita;
        }
}

