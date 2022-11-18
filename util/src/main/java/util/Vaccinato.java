package util;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;

/**
 * classe che gestisce le informazioni di un Vaccinato
 *
 * @author Alessandro Cassani - Matricola: 744512 - Sede: VA
 * @author Paolo Bruscagin - Matricola: 744703 - Sede: VA
 * @author Damiano Ficara - Matricola 744958 - Sede: VA
 * @author Luca Perfetti - Matricola 746581 - Sede: VA
 *
 */
public class Vaccinato extends Persona implements Serializable {
    /**
     * Numero di seriale per la comunicazione RMI
     */
    private static final long serialVersionUID = 78910L;

    /**
     * data di somministrazione del vaccino
     */
    private final String dataSomministrazione;

    /**
     * tipologia di vaccino somministrato
     */
    private final Vaccino vaccino;

    /**
     * metodo che modella le informazioni di un oggetto di tipo Vaccinato
     * @param nome nome del vaccinato
     * @param cognome cognome del vaccinato
     * @param codFisc codice fiscale del vaccinato
     * @param id id numerico del vaccinato
     * @param centroVaccinale centro vaccinale in cui si e' vaccinato il vaccinato
     * @param dataSomministrazione stringa della data di somministrazione del vaccino
     * @param vaccino vaccino somministrato
     * @author  Alessandro Cassani
     *
     */
    public Vaccinato(String nome, String cognome, String codFisc, BigInteger id,
                     String centroVaccinale, String dataSomministrazione, Vaccino vaccino) {
        super(nome,cognome,codFisc,id,centroVaccinale);
        this.dataSomministrazione = dataSomministrazione;
        this.vaccino = vaccino;
    }

    /**
     * metodo che permette di accedere al campo privato dataSomministrazione
     * @return stringa di data di somministrazione del vaccino
     *
     * @author  Alessandro Cassani
     */
    public String getDataSomministrazione(){
        return dataSomministrazione;
    }

    /**
     * metodo che permette di accedere al campo privato vaccino
     * @return tipologia di vaccino somministrata
     *
     * @author  Alessandro Cassani
     */
    public  Vaccino getVaccino(){
        return vaccino;
    }
}