package util;

/**
 *  classe enumerativa sulle possibili tipologie di centri vaccinali
 *
 * @author Alessandro Cassani - Matricola: 744512 - Sede: VA
 * @author Paolo Bruscagin - Matricola: 744703 - Sede: VA
 * @author Damiano Ficara - Matricola 744958 - Sede: VA
 * @author Luca Perfetti - Matricola 746581 - Sede: VA
 *
 */
public enum Tipologia {

    OSPEDALIERO,HUB,AZIENDALE;

    /**
     * metodo che visualizza la stringa in minuscolo della tipologia di centro vaccinale associata
     * @return nome della tipologia di centro vaccinale
     */
    @Override
    public String toString() {
        return this.name().toUpperCase();
    }

    public static Tipologia getTipo(String tipologia){
        switch(tipologia.toUpperCase()){
            case "HUB": return HUB;
            case "OSPEDALIERO": return OSPEDALIERO;
            case "AZIENDALE": return AZIENDALE;
        }
        return null;
    }
}