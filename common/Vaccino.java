package common;

/**
 * classe enumerativa sui possibili vaccini selezionabili
 *
 * @author Alessandro Cassani
 */
public enum Vaccino {
    PFIZER, ASTRAZENECA, MODERNA, JOHNSON;

    @Override
    public String toString() {
        if(this.name().equals("JOHNSON"))
            return "j&j";
        else
            return this.name().toLowerCase();
    }
}
