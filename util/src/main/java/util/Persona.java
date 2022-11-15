package util;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;

/**
 * classe astratta che gestisce le informazioni comuni alle sottoclassi di oggetti di tipo "Vaccinato" e di tipo "Cittadino"
 *
 * @author Alessandro Cassani
 */
public abstract class Persona implements Serializable {
    /**
     * Numero di seriale per la comunicazione RMI
     */
    private static final long serialVersionUID = 78910L;

    /**
     * nome della persona
     */
    private final String nome;

    /**
     * cognome della persona
     */
    private final String cognome;

    /**
     * codice fiscale della persona
     */
    private final String codFisc;

    /**
     * id della persona
     */
    private final BigInteger id;

    /**
     * centro vaccinale in cui si e' vaccinata la persona
     */
    private final String centroVaccinale;

    /**
     * metodo che permette la modellazione delle informazioni di un oggetto di tipo persona, il quale non sara' creato direttamente (classe astratta) ma sarà utilizzato nei costruttori delle sue sottoclassi
     * @param nome nome della persona
     * @param cognome cognome della persona
     * @param codFisc codice fiscale della persona
     * @param id id della persona
     * @param centroVaccinale centro vaccinale in cui si e' vaccinata la persona
     *
     * @author Alessandro Cassani
     */
    public Persona(String nome,String cognome, String codFisc, BigInteger id,String centroVaccinale){
        this.nome = nome;
        this.cognome = cognome;
        this.codFisc = codFisc;
        this.id = id;
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
     * metodo che permette di accedere al campo privato id
     * @return id numerico della persona
     *
     * @author Alessandro Cassani
     */
    public BigInteger getId(){
        return id;
    }

    /**
     * metodo che permette di accedere al campo privato centro vaccinale
     * @return centro vaccinale in cui si è vaccinata la persona
     *
     * @author Alessandro Cassani
     */
    public String getCentroVaccinale(){
        return centroVaccinale;
    }
}