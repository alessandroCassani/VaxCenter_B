package common;

import java.io.Serializable;

/**
 * classe enumerativa sulla possibile sintomatologia segnalabile dal cittadino post vaccinazione
 *
 * @author  Alessandro Cassani
 */
public enum Sintomatologia{

    MALDITESTA("Mal di Testa"),FEBBRE("Febbre"), DOLORI_MA("Dolori Muscolari e Articolari"),
    LINFOADENOPATIA("Linfoadenopatia"), TACHICARDIA("Tachicardia"), CRISIPERTENSIVA("Crisi Ipertensiva");

    /**
     * nome user friendly e minuscolo della sintomatologia segnalata
     */
    private String nome;

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
