package util;

/**
 * classe enumerativa sui possibili vaccini selezionabili
 *
 * @author Alessandro Cassani - Matricola: 744512 - Sede: VA
 * @author Paolo Bruscagin - Matricola: 744703 - Sede: VA
 * @author Damiano Ficara - Matricola 744958 - Sede: VA
 * @author Luca Perfetti - Matricola 746581 - Sede: VA
 *
 */
public enum Vaccino {
    /**
    * Tipologie di vaccini presenti
     */
    PFIZER, ASTRAZENECA, MODERNA, JOHNSON;
    /**
     * metodo che permette di ricreare un oggetto di tipo Vaccino partendo
     * da una stringa equivalente
     * @param vax nome vaccino
     * @return oggetto di tipo Vaccino
     *
     * @author Alessandro Cassani
     */
    public static Vaccino getVaccino(String vax){
        switch (vax){
            case "Pfizer": return PFIZER;
            case "AstraZeneca": return ASTRAZENECA;
            case "Moderna": return MODERNA;
            case "J&J": return JOHNSON;
        }
        return null;
    }


    /**
     * metodo che visualizza nome del vaccino considerato
     * @return nome del vaccino
     *
     * @author Alessandro Cassani
     */
    @Override
    public String toString() {
        if(this.name().equals("JOHNSON"))
            return "j&j";
        else
            return this.name().toUpperCase();
    }
}