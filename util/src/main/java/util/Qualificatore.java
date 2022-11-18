package util;

/**
 * classe enumerativa che rappresenta i possibili qualificatori dell'indirizzo
 *
 * @author Alessandro Cassani - Matricola: 744512 - Sede: VA
 * @author Paolo Bruscagin - Matricola: 744703 - Sede: VA
 * @author Damiano Ficara - Matricola 744958 - Sede: VA
 * @author Luca Perfetti - Matricola 746581 - Sede: VA
 *
 */
public enum Qualificatore {

    VIA,VIALE,PIAZZA;

    public static Qualificatore getQualificatore(String qualificatore){
        switch(qualificatore.toLowerCase()){
            case "via": return VIA;
            case "viale": return VIALE;
            case "piazza": return PIAZZA;
        }
        return null;
    }

    /**
     * metodo che ritorna la stringa rappresentante il nome del qualificatore in minuscolo
     * @return nome qualificatore
     *
     * @author Alessandro Cassani
     */
    @Override
    public String toString() {
        return this.name().toUpperCase();
    }
}