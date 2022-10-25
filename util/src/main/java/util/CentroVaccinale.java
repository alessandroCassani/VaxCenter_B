package util;

import java.io.Serializable;

/**
 * classe che gestisce le informazioni di un centro vaccinale
 *
 * @author Alessandro Cassani
 */
public class CentroVaccinale implements Serializable {
    /**
     * Numero di seriale per la comunicazione RMI
     */
    private static final long serialVersionUID = 78910L;

    /**
     * nome del centro vaccinale
     */
    private final String nome;

    /**
     * comune dove risiede il centro vaccinale
     */
    private String comune;
    /**
     * cap del comune del centro vaccinale
     */
    private int cap;

    /**
     * tipologia di centro vaccinale
     */
    private final Tipologia tipologia;

    /**
     * qualificatore dell'indirizzo (via/viale/piazza)
     */
    private final Qualificatore qualificatore;

    /**
     * nome indirizzo
     */
    private final String nomeVia;

    /**
     * numero civico
     */
    private final String civico;

    /**
     * provincia dove e' situata l'indirizzo
     */
    private final String provincia;

    /**
     * metodo che crea un oggetto di tipo centro vaccinale
     * @param nome  nome del centro vaccinale
     * @param qualificatore qualificatore (via/viale/piazza) indirizzo del centro vaccinale
     * @param nomeVia nome via del centro vaccinale
     * @param civico numero civico del centor vaccinale
     * @param provincia provincia del centro vaccinale
     * @param cap  cap del centor vaccinale
     * @param comune comune del centro vaccinale
     * @param tipologia tipologia di centro vaccinale
     *
     * @author Alessandro Cassani
     */
    public CentroVaccinale(String nome,Qualificatore qualificatore,String nomeVia,String civico,String provincia,String comune,int cap,Tipologia tipologia){
        this.nome = nome;
        this.cap = cap;
        this.comune = comune;
        this.qualificatore = qualificatore;
        this.nomeVia = nomeVia;
        this.civico = civico;
        this.provincia = provincia;
        this.tipologia = tipologia;
    }

    /**
     * metodo che accede al campo privato nome
     * @return nome del centro vaccinale
     *
     *  @author Alessandro Cassani
     */
    public String getNome(){
        return nome.toUpperCase();
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
     * metodo che accede al campo privato comune
     * @return comune del centro vaccinale
     *
     *  @author Alessandro Cassani
     */
    public String getComune(){return comune.toUpperCase();}

    /**
     * metodo che accede al campo tipologia
     * @return la tipologia di centro vaccinale
     *
     *  @author Alessandro Cassani
     */
    public Tipologia getTipologia(){
        return tipologia;
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
     * metodo che accede al campo privato nomeVia
     * @return nome indirizzo
     *
     *  @author Alessandro Cassani
     */

    public String getNomeVia(){
        return nomeVia.toUpperCase();
    }

    /**
     * metodo che accede al campo privato civico
     * @return numero civico
     *
     *  @author Alessandro Cassani
     */
    public String getCivico(){
        return civico.toUpperCase();
    }

    /**
     *  metodo che accede al campo privato provincia
     * @return provincia dell'indirizzo
     *
     *  @author Alessandro Cassani
     */

    public String getProvincia(){
        return provincia.toUpperCase();
    }

    /**
     * metodo che ritorno la stringa rappresentante le informazioni del centro vaccinale
     * @return stringa contenente informazioni su centro vaccinale
     *
     *  @author Alessandro Cassani
     */
    public String toString(){
        return nome.toUpperCase() + qualificatore + nomeVia.toUpperCase() + civico.toUpperCase() + comune.toUpperCase() + provincia.toUpperCase() + cap + tipologia;
    }
}