package common;

import java.io.Serializable;
import java.util.Date;

/**
 * classe astratta che gestisce le informazioni comuni alle sottoclassi di oggetti di tipo "Vaccinato" e di tipo "Cittadino"
 *
 * @author Alessandro Cassani
 */
public abstract class Persona implements Serializable {

    /**
     * nome della persona
     */
    private String nome;
    /**
     * cognome della persona
     */
    private String cognome;
    /**
     * codice fiscale della persona
     */
    private String codFisc;
    /**
     * email della persona
     */
    private String email;

    /**
     * id della persona
     */
    private String id;

    /**
     * data di dascita della persona
     */
    private Date dataNascita;

    /**
     * centro vaccinale in cui si e' vaccinata la persona
     */
    private CentroVaccinale centroVaccinale;

    /**
     * metodo che permette la modellazione delle informazioni di un oggetto di tipo persona, il quale non sara' creato direttamente (classe astratta) ma sarà utilizzato nei costruttori delle sue sottoclassi
     * @param nome nome della persona
     * @param cognome cognome della persona
     * @param codFisc codice fiscale della persona
     * @param email email della persona
     * @param id id della persona
     * @param dataNascita data di nascita della persona
     * @param centroVaccinale centro vaccinale in cui si e' vaccinata la persona
     *
     * @author Alessandro Cassani
     */
    public Persona(String nome,String cognome, String codFisc,String email, String id, Date dataNascita, CentroVaccinale centroVaccinale){
        this.nome = nome;
        this.cognome = cognome;
        this.codFisc = codFisc;
        this.email = email;
        this.id = id;
        this.dataNascita = dataNascita;
        this.centroVaccinale = centroVaccinale;
    }

    /**
     * metodo che permette di accedere al campo privato nome
     * @return nome della persona
     *
     *  @author Alessandro Cassani
     */
    public String getNome(){
        return nome;
    }

    /**
     * metodo che permette di accedere al campo privato cognome
     * @return cognome della persona
     *
     * @author Alessandro Cassani
     */
    public String getCognome(){
        return cognome;
    }

    /**
     * metodo che permette di accedere al campo privato codice fiscale
     * @return codice fiscale della persona
     *
     * @author Alessandro Cassani
     */
    public String getCodFisc(){
        return codFisc;
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
     * metodo che permette di accedere al campo privato id
     * @return id numerico della persona
     *
     * @author Alessandro Cassani
     */
    public String getId(){
        return id;
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

    /**
     * metodo che permette di accedere al campo privato centro vaccinale
     * @return centro vaccinale in cui si è vaccinata la persona
     *
     * @author Alessandro Cassani
     */
    public CentroVaccinale getCentroVaccinale(){
        return centroVaccinale;
    }
}
