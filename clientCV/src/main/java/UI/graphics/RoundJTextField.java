package UI.graphics;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.RoundRectangle2D;

/**
 * classe che permette la personalizzazione delle textField componenti l'interfaccia grafica
 *
 * @author Alessandro Cassani
 */
public class RoundJTextField extends JPasswordField {
    private Shape shape;

    /**
     * costruttore che permettela creazione di un oggetto di tipo RoundJTextField
     * @param size numero di colonne allocate alla RoundJTextField
     */
    public RoundJTextField(int size) {
        super(size);
        setOpaque(false);
    }
    protected void paintComponent(Graphics g) {
       g.setColor(getBackground());
        g.fillRoundRect(0, 0, getWidth()-1, getHeight()-1, 20, 20);
        super.paintComponent(g);
    }
    protected void paintBorder(Graphics g) {
        g.setColor(new Color(65, 102, 245));
        g.drawRoundRect(0, 0, getWidth()-1, getHeight()-1, 20, 20);
    }
    public boolean contains(int x, int y) {
        if (shape == null || !shape.getBounds().equals(getBounds())) {
            shape = new RoundRectangle2D.Float(0, 0, getWidth()-1, getHeight()-1, 15, 15);
        }
        return shape.contains(x, y);
    }
}