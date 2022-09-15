package util;

/**
 *  classe enumerativa sulle possibili tipologie di centri vaccinali
 * @author  Alessandro Cassani
 */
public enum Tipologia {

    OSPEDALIERO,HUB,AZIENDALE;

    /**
     * metodo che visualizza la stringa in minuscolo della tipologia di centro vaccinale associata
     * @return nome della tipologia di centro vaccinale
     */
    @Override
    public String toString() {
        return this.name().toLowerCase();
    }
}
