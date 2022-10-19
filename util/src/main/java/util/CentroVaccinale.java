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
     * indirizzo del centro vaccinale
     */
    private final Indirizzo indirizzo;

    /**
     * tipologia di centro vaccinale
     */
    private final Tipologia tipologia;

    /**
     * metodo che crea un oggetto di tipo centro vaccinale
     * @param nome  nome del centro vaccinale
     * @param indirizzo indirizzo del centro vaccinale
     * @param tipologia tipologia di centro vaccinale
     *
     * @author Alessandro Cassani
     */
    public CentroVaccinale(String nome,Indirizzo indirizzo,Tipologia tipologia){
        this.nome = nome;
        this.indirizzo = indirizzo;
        this.tipologia = tipologia;
    }

    /***
     * metodo che accede al campo privato nome
     * @return nome del centro vaccinale
     *
     *  @author Alessandro Cassani
     */
    public String getNome(){
        return nome;
    }

    /***
     * metodo che accede al campo privato indirizzo
     * @return l'indirizzo del centro vaccinale
     *
     *  @author Alessandro Cassani
     */
    public Indirizzo getIndirizzo(){
        return indirizzo;
    }

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
     * metodo che ritorno la stringa rappresentante le informazioni del centro vaccinale
     * @return nome tipologia e indirizzo del centro vaccinale
     *
     *  @author Alessandro Cassani
     */
    public String toString(){
        return nome + " tipologia: " + tipologia + "\n" + indirizzo;
    }
}
