package UI.graphics;


import java.awt.Color;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Insets;
import java.awt.RenderingHints;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JPasswordField;

/**
 * La classe MyPwdField permette di definire il campo relativo alla password nel login
 @author Damiano Ficara
 */
public class MyPwdField extends JPasswordField {

    /**
     * metodo che consente di ottenere il suggerimento circa il valore da inserire
     * @return suggerimento del campo
     * @author Damiano Ficara
     */
    public String getHint() {
        return hint;
    }

    /**
     * metodo che consente di impostare il suggerimento circa il valore da inserire
     * @return nuovo suggerimento del campo
     * @author Damiano Ficara
     */
    public void setHint(String hint) {
        this.hint = hint;
    }

    /**
     * metodo che consente di ottenere l'icona di riferimento del campo
     * @return icona del campo
     * @author Damiano Ficara
     */
    public Icon getPrefixIcon() {
        return prefixIcon;
    }

    /**
     * metodo che consente di impostare l'icona di riferimento del campo e impostare i bordi con uno stile prioritario
     * @return icona del campo
     * @author Damiano Ficara
     */
    public void setPrefixIcon(Icon prefixIcon) {
        this.prefixIcon = prefixIcon;
        initBorder();
    }

    /**
     * Icona che rappresenta il riferimento del campo
     */
    private Icon prefixIcon;
    /**
     * Stringa che rappresenta il suggerimento del campo
     */
    private String hint = "";

    /**
     * Costruttore responsabile del disegno del campo di password e dell'inizializzazione di font e componenti grafici
     * @author Damiano Ficara
     */
    public MyPwdField() {
        setBorder(javax.swing.BorderFactory.createEmptyBorder(10, 10, 10, 10));
        setBackground(new Color(0, 0, 0, 0));
        setForeground(Color.decode("#7A8C8D"));
        setFont(new java.awt.Font("sansserif", 0, 16));
        setSelectionColor(new Color(75, 175, 152));
    }

    /**
     * Metodo responsabile dell'operazione di disegno del componente grafico
     * @param g componente grafico considerato
     * @author Damiano Ficara
     */
    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(new Color(230, 245, 241));
        g2.fillRoundRect(0, 0, getWidth(), getHeight() , 5, 5);
        paintIcon(g);
        super.paintComponent(g);
    }

    /**
     * Metodo responsabile del disegno del suggerimento all'avvio
     * @param g componente grafico considerato
     * @author Damiano Ficara
     */
    @Override
    public void paint(Graphics g) {
        super.paint(g);
        if (getPassword().length == 0) {
            int h = getHeight();
            ((Graphics2D) g).setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
            Insets ins = getInsets();
            FontMetrics fm = g.getFontMetrics();
            g.setColor(new Color(200, 200, 200));
            g.drawString(hint, ins.left, h / 2 + fm.getAscent() / 2 - 2);
        }
    }

    /**
     * Metodo responsabile del disegno dell'icona di riferimento del campo
     * @param g componente grafico considerato
     * @author Damiano Ficara
     */
    private void paintIcon(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        if (prefixIcon != null) {
            Image prefix = ((ImageIcon) prefixIcon).getImage();
            int y = (getHeight() - prefixIcon.getIconHeight()) / 2;
            g2.drawImage(prefix, 10, y, this);
        }

    }
    /**
     * Metodo responsabile del disegno del bordo del campo
     * @param g componente grafico considerato
     * @author Damiano Ficara
     */
    protected void paintBorder(Graphics g) {
        g.setColor(new Color(65, 102, 245));
        g.drawRoundRect(0, 0, getWidth(), getHeight(), 6, 6);
    }

    /**
     * Metodo responsabile dell'inizializzazione del bordo del campo
     * @author Damiano Ficara
     */
    private void initBorder() {
        int left = 15;
        int right = 15;
        //  5 is default
        if (prefixIcon != null) {
            //  prefix is left
            left = prefixIcon.getIconWidth() + 15;
        }

        setBorder(javax.swing.BorderFactory.createEmptyBorder(10, left, 10, right));
    }
}