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
        setHorizontalAlignment(JTextField.CENTER);
    }

    /**
     * metodo che permette la colorazione e la gestione dell'angolatura della parte interna del rettangolo associato alla RoundJTextField
     * @param g the <code>Graphics</code> object to protect
     *
     * @author Alessandro Cassani
     */
    protected void paintComponent(Graphics g) {
       g.setColor(getBackground());
        g.fillRoundRect(0, 0, getWidth()-1, getHeight()-1, 20, 20);
        super.paintComponent(g);
    }

    /**
     * metodo che permette la colorazione e la gestione dell'angolatura del bordo del rettangolo associato alla RoundJTextField
     * @param g  the <code>Graphics</code> context in which to paint
     *
     * @author Alessandro Cassaniv
     */
    protected void paintBorder(Graphics g) {
        g.setColor(new Color(65, 102, 245));
        g.drawRoundRect(0, 0, getWidth()-1, getHeight()-1, 20, 20);
    }

    /**
     *
     * @param x   the <i>x</i> coordinata del punto
     * @param y   the <i>y</i> coordinata del punto
     * @return true o false in base all'esito del controllo
     *
     * @author Alessandro Cassani
     */
    public boolean contains(int x, int y) {
        if (shape == null || !shape.getBounds().equals(getBounds())) {
            shape = new RoundRectangle2D.Float(0, 0, getWidth()-1, getHeight()-1, 15, 15);
        }
        return shape.contains(x, y);
    }
}