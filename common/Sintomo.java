package common;

import java.io.Serializable;

/**
 * classe che gestisce le informazioni sul singolo sintomo segnalato
 *
 * @author  Alessandro Cassani
 */

public class Sintomo implements Serializable {

    /**
     * sintomatologia segnalata
     */
    private Sintomatologia sintomatologia;

    /**
     * severita' in scala 1-10 della sintomatologia segnalata
     */
    private int severita;

    /**
     * metodo che registra le informazioni del sintomo segnalato
     * @param severita severita' in scala 1-10 della sintomatologia segnalata
     * @param sintomatologia sintomatologia segnalata
     *
     * @author  Alessandro Cassani
     */
    public Sintomo(int severita,Sintomatologia sintomatologia){
        this.severita = severita;
        this.sintomatologia = sintomatologia;
    }

    /**
     * metodo che permette di accedere al campo privato severita
     * @return severita' evento avverso associato
     *
     * @author  Alessandro Cassani
     */
    public int getSeverita(){
        return severita;
    }

    /**
     * metodo che ritorna le informazioni della sintomatologia segnalata
     * @return nome sintomatologia e severita' associata
     *
     * @author  Alessandro Cassani
     */
    public String toString(){
        return sintomatologia + " severità: " + severita;
    }
}
