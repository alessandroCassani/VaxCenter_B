package graphics;

/**
 * L'interfaccia InfoSearchEvent consente di specificare le informazioni della tipologia di ricerca
 *
 * @author Alessandro Cassani - Matricola: 744512 - Sede: VA
 * @author Paolo Bruscagin - Matricola: 744703 - Sede: VA
 * @author Damiano Ficara - Matricola 744958 - Sede: VA
 * @author Luca Perfetti - Matricola 746581 - Sede: VA
 *
 */
public interface InfoSearchEvent {

    /**
     * Metodo responsabile della configurazione dell'opzione da ricercare
     * @param option informazioni sulla ricerca da compoere
     * @param index tipologia di ricerca da compiere
     * @author Damiano Ficara
     */
    void optionSelected(InfoSearch option, int index);
}