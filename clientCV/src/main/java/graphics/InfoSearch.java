package graphics;

import javax.swing.Icon;

/**
 * La classe InfoSearch permette la realizzazione di un oggetto avente nome e icona responsabile per il campo SearchField
 *
 * @author Alessandro Cassani - Matricola: 744512 - Sede: VA
 * @author Paolo Bruscagin - Matricola: 744703 - Sede: VA
 * @author Damiano Ficara - Matricola 744958 - Sede: VA
 * @author Luca Perfetti - Matricola 746581 - Sede: VA
 *
 */
public class InfoSearch {

    /**
     * metodo che consente di ottenere il nome dell'oggetto InfoSearch
     * @return nome oggetto di ricerca
     * @author Damiano Ficara
     */
    public String getName() {
        return name;
    }

    /**
     * metodo che consente di impostare il nome dell'oggetto InfoSearch
     * @param name nome dell'oggetto in uso
     * @return nuovo nome oggetto di ricerca
     * @author Damiano Ficara
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * metodo che consente di ottenre l'icona impostata nell'oggetto InfoSearch
     * @return icona impostata
     * @author Damiano Ficara
     */

    public Icon getIcon() {
        return icon;
    }

    /**
     * metodo che consente di impostare l'icona nell'oggetto InfoSearch
     *
     * @return nuova icona impostata
     * @author Damiano Ficara
     */

    public void setIcon(Icon icon) {
        this.icon = icon;
    }


    /**
     * costruttore che inizializza i valori dell'oggetto InfoSearch
     * @param name nome tipologia ricerca
     * @param icon icona della tipologia di ricerca
     * @author Damiano Ficara
     */

    public InfoSearch(String name, Icon icon) {
        this.name = name;
        this.icon = icon;
    }

    /**
     * Variabile rappresentante il nome dell'oggetto
     */
    private String name;
    /**
     * Variabile rappresentante l'icona dell'oggetto
     */
    private Icon icon;
}