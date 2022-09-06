package common;

import java.util.Date;

    /**
     * classe che gestisce le informazioni di un Vaccinato
     *
     * @author Alessandro Cassani
     */
public class Vaccinato extends Persona {

     /**
     * data di somministrazione del vaccino
     */
    private Date dataSomministrazione;

     /**
     * tipologia di vaccino somministrato
     */
    private Vaccino vaccino;

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
         */
    public Vaccinato(String nome,String cognome, String codFisc,String email, String id, Date dataNascita, CentroVaccinale centroVaccinale, Date dataSomministrazione, Vaccino vaccino) {
        super(nome,cognome,codFisc,email,id,dataNascita,centroVaccinale);
        this.dataSomministrazione = dataSomministrazione;
        this.vaccino = vaccino;
    }

        /**
         * metodo che permette di accedere al campo privato dataSomministrazione
         * @return
         */
    public Date getDataSomministrazione(){
        return dataSomministrazione;
    }

        /**
         * metodo che permette di accedere al campo privato vaccino
         * @return
         */
    public  Vaccino getVaccino(){
        return vaccino;
    }
}
