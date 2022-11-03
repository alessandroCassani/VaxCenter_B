package UI.graphics;

/**
 * L'interfaccia InfoSearchEvent consente di specificare le informazioni della tipologia di ricerca
 @author Damiano Ficara
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