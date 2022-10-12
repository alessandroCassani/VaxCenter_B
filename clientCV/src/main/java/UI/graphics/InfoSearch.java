package UI.graphics;

import javax.swing.Icon;

public class InfoSearch {

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Icon getIcon() {
        return icon;
    }

    public void setIcon(Icon icon) {
        this.icon = icon;
    }

    public InfoSearch(String name, Icon icon) {
        this.name = name;
        this.icon = icon;
    }

    public InfoSearch() {
    }

    private String name;
    private Icon icon;
}