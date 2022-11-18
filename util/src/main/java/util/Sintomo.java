package util;

import java.io.Serializable;

/**
 * classe che gestisce le informazioni sul singolo sintomo segnalato
 *
 * @author Alessandro Cassani - Matricola: 744512 - Sede: VA
 * @author Paolo Bruscagin - Matricola: 744703 - Sede: VA
 * @author Damiano Ficara - Matricola 744958 - Sede: VA
 * @author Luca Perfetti - Matricola 746581 - Sede: VA
 *
 */

public class Sintomo implements Serializable {

    /**
     * Numero di seriale per la comunicazione RMI
     */
    private static final long serialVersionUID = 78910L;
    /**
     * sintomatologia segnalata
     */
    private final Sintomatologia sintomatologia;

    /**
     * severita' in scala 1-10 della sintomatologia segnalata
     */
    private final int severita;

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