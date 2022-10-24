package util;

import java.io.Serializable;

/**
 * classe che gestisce le informazioni componenti di un indirizzo
 */
public class Indirizzo implements Serializable  {

    /**
     * Numero di seriale per la comunicazione RMI
     */
    private static final long serialVersionUID = 78910L;
    /**
     * qualificatore dell'indirizzo (via/viale/piazza)
     */
    private final Qualificatore qualificatore;

    /**
     * nome indirizzo
     */
    private final String nome;

    /**
     * numero civico
     */
    private final String civico;

    /**
     * comune dove e' situato l'indirizzo
     */
    private final String comune;

    /**
     * provincia dove e' situata l'indirizzo
     */
    private final String provincia;

    /**
     * cap dove e' situato l'indirizzo
     */
    private final int cap;

    /**
     * metodo che gestisce e associa le informazioni di un indirizzo
     * @param qualificatore qualificatore dell'indirizzo (via/viale/piazza)
     * @param nome nome indirizzo
     * @param civico numero civico
     * @param comune comune dove e' situato l'indirizzo
     * @param provincia provincia dove e' situata l'indirizzo
     * @param cap  cap dove e' situato l'indirizzo
     *
     * @author Alessandro Cassani
     */
    public Indirizzo(Qualificatore qualificatore,String nome,String civico,String comune,String provincia,int cap){
            this.qualificatore = qualificatore;
            this.nome = nome;
            this.civico = civico;
            this.comune = comune;
            this.provincia = provincia;
            this.cap = cap;
    }

    /**
     * metodo che accede al campo privato qualificatore
     * @return qualificatore indirizzo
     *
     *  @author Alessandro Cassani
     */
    public Qualificatore getQualificatore(){
        return qualificatore;
    }

    /**
     * metodo che accede al campo privato nome
     * @return nome indirizzo
     *
     *  @author Alessandro Cassani
     */

    public String getNome(){
        return nome;
    }

    /**
     * metodo che accede al campo privato civico
     * @return numero civico
     *
     *  @author Alessandro Cassani
     */
    public String getCivico(){
        return civico;
    }

    /**
     *  metodo che accede al campo privato comune
     * @return comune dell'indirizzo
     *
     *  @author Alessandro Cassani
     */

    public String getComune(){
        return comune;
    }

    /**
     *  metodo che accede al campo privato provincia
     * @return provincia dell'indirizzo
     *
     *  @author Alessandro Cassani
     */

    public String getProvincia(){
        return provincia;
    }

    /**
     *  metodo che accede al campo privato cap
     * @return cap dell'indirizzo
     *
     *  @author Alessandro Cassani
     */

    public int getCap(){
        return cap;
    }

    /**
     * metodo che ritorna la stringa rappresentante l'indirizzo
     * @return qualificatore, nome, numero civico, comune , provincia e cap
     *
     *  @author Alessandro Cassani
     */
    public String toString(){
        return qualificatore + " " + nome + " " + civico + " " + comune + " (" + provincia + ") " + cap;
    }
}
