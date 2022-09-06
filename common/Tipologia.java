package common;

/**
 *  classe enumerativa sulle possibili tipologie di centri vaccinali
 * @author  Alessandro Cassani
 */
public enum Tipologia {

    OSPEDALIERO,HUB,AZIENDALE;

    /**
     * metodo che visualizza la stringa in minuscolo della tipologia di centro vaccinale associata
     * @return
     */
    @Override
    public String toString() {
        return this.name().toLowerCase();
    }
}
