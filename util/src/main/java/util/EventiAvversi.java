package util;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * classe che permette la gestione delle informazioni sugli eventi avversi registrati da un cittadino post vaccinazione
 *
 * @author Alessandro Cassani - Matricola: 744512 - Sede: VA
 * @author Paolo Bruscagin - Matricola: 744703 - Sede: VA
 * @author Damiano Ficara - Matricola 744958 - Sede: VA
 * @author Luca Perfetti - Matricola 746581 - Sede: VA
 *
 */
public class EventiAvversi implements Serializable {
    /**
     * Numero di seriale per la comunicazione RMI
     */
    private static final long serialVersionUID = 78910L;

    /**
     * note opzionali
     */
    private final String note;

    /**
     * lista di sintomi segnalati dall'utente post vaccinazione
     */
    private final ArrayList<Sintomo> sintomi;

    /**
     * metodo che modella le informazioni di un oggetto di tipo EventiAvversi
     *
     * @param note note opzionali
     * @param sintomi lista di sintomi segnalati dall'utente post vaccinazione
     *
     * @author Alessandro Cassani
     */
    public EventiAvversi(String note, ArrayList<Sintomo> sintomi){
        this.note = note;
        this.sintomi = sintomi;
    }

    /**
     *  metodo che permette di accedere al campo privato note (note opzionali)
     * @return note opzionali
     *
     * @author  Alessandro Cassani
     *
     */
    public String getNote(){
        return note;
    }

    /**
     * metodo che permette di accedere alla lista di sintomi segnalati dal cittadino
     * @return lista di sintomi segnalati dal cittadino
     *
     * @author  Alessandro Cassani
     */
    public ArrayList<Sintomo> getSintomi(){
        return sintomi;
    }
}