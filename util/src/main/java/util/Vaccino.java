package util;

/**
 * classe enumerativa sui possibili vaccini selezionabili
 *
 * @author Alessandro Cassani
 */
public enum Vaccino {
    PFIZER, ASTRAZENECA, MODERNA, JOHNSON;

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
            return this.name().toLowerCase();
    }
}
