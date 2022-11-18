package util;


/**
 * classe enumerativa sulla possibile sintomatologia segnalabile dal cittadino post vaccinazione
 *
 * @author Alessandro Cassani - Matricola: 744512 - Sede: VA
 * @author Paolo Bruscagin - Matricola: 744703 - Sede: VA
 * @author Damiano Ficara - Matricola 744958 - Sede: VA
 * @author Luca Perfetti - Matricola 746581 - Sede: VA
 *
 */
public enum Sintomatologia{

    MALDITESTA("Mal di Testa"),FEBBRE("Febbre"), DOLORI_MA("Dolori Muscolari e Articolari"),
    LINFOADENOPATIA("Linfoadenopatia"), TACHICARDIA("Tachicardia"), CRISIPERTENSIVA("Crisi Ipertensiva");

    /**
     * nome user friendly e in minuscolo della sintomatologia segnalata
     */
    private final String nome;

    /**
     * metodo che associa un nome alla sintomatologia
     * @param nome nome della possibile sintomatologia
     */
    Sintomatologia(String nome){
        this.nome = nome;
    }

    /**
     * metodo che visualizza una stringa rappresentante il nome della sintomatologia associata
     * @return nome user friendly e minuscolo della sintomatologia segnalata
     */
    @Override
    public String toString() {
        return nome;
    }
}