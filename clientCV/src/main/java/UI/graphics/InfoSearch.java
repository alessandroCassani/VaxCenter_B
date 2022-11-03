package UI.graphics;

import javax.swing.Icon;

/**
 * La classe InfoSearch permette la realizzazione di un oggetto avente nome e icona responsabile per il campo SearchField
 @author Damiano Ficara
 */
public class InfoSearch {

    /**
     * metodo che consente di ottenere il nome dell'oggetto InfoSearch
     * @author Damiano Ficara
     * @return nome oggetto di ricerca
     */
    public String getName() {
        return name;
    }

    /**
     * metodo che consente di impostare il nome dell'oggetto InfoSearch
     * @author Damiano Ficara
     * @return nuovo nome oggetto di ricerca
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * metodo che consente di ottenre l'icona impostata nell'oggetto InfoSearch
     * @author Damiano Ficara
     * @return icona impostata
     */

    public Icon getIcon() {
        return icon;
    }

    /**
     * metodo che consente di impostare l'icona nell'oggetto InfoSearch
     * @author Damiano Ficara
     * @return nuova icona impostata
     */

    public void setIcon(Icon icon) {
        this.icon = icon;
    }
    /**
     * costruttore che inizializza i valori dell'oggetto InfoSearch
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