package graphics;

import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Paint;

import javax.swing.JPanel;

/**
 * La classe GradientPanel permette la creazione di un pannello con l'utilizzo di colori gradienti
 *
 * @author Alessandro Cassani - Matricola: 744512 - Sede: VA
 * @author Paolo Bruscagin - Matricola: 744703 - Sede: VA
 * @author Damiano Ficara - Matricola 744958 - Sede: VA
 * @author Luca Perfetti - Matricola 746581 - Sede: VA
 *
 */
public class GradientPanel extends JPanel {

    /**
     * Colore primario del pannello
     */
    private final Color gradientStart;
    /**
     * Colore secondario del pannello
     */
    private final Color gradientEnd;

    /**
     * Costruttore responsabile dell'inizializzazione dei colori
     * @author Damiano Ficara
     */
    public GradientPanel(Color gradientStart, Color gradientEnd) {
        this.gradientStart = gradientStart;
        this.gradientEnd = gradientEnd;
    }

    /**
     * Metodo responsabile dell'operazione di disegno del componente grafico
     * @param g componente grafico considerato
     * @author Damiano Ficara
     */
    @Override
    public void paintComponent(Graphics g) {
        int height = getHeight();
        Graphics2D g2 = (Graphics2D) g;
        GradientPaint painter = new GradientPaint(0, 0, gradientStart,
                0, height, gradientEnd);
        Paint oldPainter = g2.getPaint();
        g2.setPaint(painter);
        g2.fill(g2.getClip());
        painter = new GradientPaint(0, 0, gradientEnd,
                0, height / 2, gradientStart);
        g2.setPaint(painter);
        g2.fill(g2.getClip());

        painter = new GradientPaint(0, height / 2, gradientStart,
                0, height, gradientEnd);
        g2.setPaint(painter);
        g2.fill(g2.getClip());

        g2.setPaint(oldPainter);
    }
}