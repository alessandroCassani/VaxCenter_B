package util;

import java.util.Date;

/**
 * classe che gestisce le informazioni di un Vaccinato
 *
 * @author Alessandro Cassani
 */
public class Vaccinato extends Persona {
    /**
     * Numero di seriale per la comunicazione RMI
     */
    private static final long serialVersionUID = 78910L;

    /**
     * data di somministrazione del vaccino
     */
    private final Date dataSomministrazione;

    /**
     * tipologia di vaccino somministrato
     */
    private final Vaccino vaccino;

    /**
     * metodo che modella le informazioni di un oggetto di tipo Vaccinato
     * @param nome nome del vaccinato
     * @param cognome cognome del vaccinato
     * @param codFisc codice fiscale del vaccinato
     * @param email email del vaccinato
     * @param id id numerico del vaccinato
     * @param dataNascita data di nascita del vaccinato
     * @param centroVaccinale centro vaccinale in cui si e' vaccinato il vaccinato
     * @param dataSomministrazione data di somministrazione del vaccino
     * @param vaccino vaccino somministrato
     * @author  Alessandro Cassani
     *
     */
    public Vaccinato(String nome, String cognome, String codFisc, Date dataSomministrazione,
                     Vaccino vaccino, CentroVaccinale centroVaccinale) {
        super(nome,cognome,codFisc,centroVaccinale);
        this.dataSomministrazione = dataSomministrazione;
        this.vaccino = vaccino;
    }

    /**
     * metodo che permette di accedere al campo privato dataSomministrazione
     * @return data di somministrazione del vaccino
     *
     * @author  Alessandro Cassani
     */
    public static Date getDataSomministrazione(String dataSomministrazione){

        return dataSomministrazione;
    }

    /**
     * metodo che permette di accedere al campo privato vaccino
     * @return tipologia di vaccino somministrata
     *
     * @author  Alessandro Cassani
     */
    public static Vaccino getVaccino(String vaccino){
        return vaccino;
    }
}
