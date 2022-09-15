package util;

/**
 * classe enumerativa che rappresenta i possibili qualificatori dell'indirizzo
 *
 * @author Alessandro Cassani
 */
public enum Qualificatore {

    VIA,VIALE,PIAZZA;

    /**
     * metodo che ritorna la stringa rappresentante il nome del qualificatore in minuscolo
     * @return nome qualificatore
     *
     * @author Alessandro Cassani
     */
    @Override
    public String toString() {
        return this.name().toLowerCase();
    }
}
