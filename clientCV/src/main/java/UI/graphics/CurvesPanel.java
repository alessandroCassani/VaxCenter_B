package UI.graphics;

import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Paint;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.AffineTransform;
import java.awt.geom.Area;
import java.awt.geom.CubicCurve2D;
import java.awt.geom.GeneralPath;
import javax.swing.Timer;

/**
 * La classe CurvesPanel permette la creazione di un pannello con l'utilizzo di colori gradienti e animazioni visive della WelcomeScreen
 *
 * @author Alessandro Cassani - Matricola: 744512 - Sede: VA
 * @author Paolo Bruscagin - Matricola: 744703 - Sede: VA
 * @author Damiano Ficara - Matricola 744958 - Sede: VA
 * @author Luca Perfetti - Matricola 746581 - Sede: VA
 *
 */
public class CurvesPanel extends GradientPanel {

    /**
     * Oggetto che permette di compiere efficacemente il rendering della parte grafica
     */
    private final RenderingHints hints;
    /**
     * Variabile che tiene traccia della progressione dell'animazione grafica
     */
    private int counter = 0;
    /**
     * Timer per la durata dell'animazione grafica
     */
    private final Timer timer = new Timer(20, ae -> repaint());

    /**
     * metodo che consente l'avvio del timer
     * @author Damiano Ficara
     */

    public void start() {
        timer.start();
    }

    /**
     * Costruttore responsabile della scelta dei colori e dell'inizializzazione delle costanti di rendering e avvio del timer
     * @author Damiano Ficara
     */
    public CurvesPanel() {
        super(Color.decode("#099773"),Color.decode("#0f68a9"));
        hints = new RenderingHints(RenderingHints.KEY_ALPHA_INTERPOLATION,
                RenderingHints.VALUE_ALPHA_INTERPOLATION_QUALITY);
        hints.put(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
        hints.put(RenderingHints.KEY_COLOR_RENDERING,
                RenderingHints.VALUE_COLOR_RENDER_QUALITY);
        hints.put(RenderingHints.KEY_INTERPOLATION,
                RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        hints.put(RenderingHints.KEY_RENDERING,
                RenderingHints.VALUE_RENDER_QUALITY);
        start();
    }

    /**
     * Metodo responsabile dell'operazione di disegno del componente grafico
     * @param g componente grafico considerato
     * @author Damiano Ficara
     */
    @Override
    public void paintComponent(Graphics g) {
        counter++;

        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHints(hints);
        super.paintComponent(g2);

        float width = getWidth();
        float height = getHeight();

        g2.translate(0, -30);
        drawCurve(g2,
                20.0f, -10.0f, 20.0f, -10.0f,
                width / 2.0f - 40.0f, 10.0f,
                0.0f, -5.0f,
                width / 2.0f + 40, 1.0f,
                0.0f, 5.0f,
                50.0f, 5.0f, false);
        g2.translate(0, 30);

        g2.translate(0, height - 60);
        drawCurve(g2,
                30.0f, -15.0f, 50.0f, 15.0f,
                width / 2.0f - 40.0f, 1.0f,
                15.0f, -25.0f,
                width / 2.0f, 1.0f / 2.0f,
                0.0f, 25.0f,
                15.0f, 6.0f, false);
        g2.translate(0, -height + 60);

        drawCurve(g2,
                height - 35.0f, -5.0f, height - 50.0f, 10.0f,
                width / 2.0f - 40.0f, 1.0f,
                height - 35.0f, -25.0f,
                width / 2.0f, 1.0f / 2.0f,
                height - 20.0f, 25.0f,
                25.0f, 4.0f, true);
    }

    /**
     * Metodo che permette di disegnare l'animazione di avvio dell'applicazione
     * @param g2 <code>Oggetto Grafico</code> di riferimento
     * @param y1   the <i>y1</i> coordinata del punto
     * @param y1_offset   the <i>y1</i> coordinata del punto
     * @param y2   the <i>y2</i> coordinata del punto
     * @param y2_offset   the <i>y2</i> coordinata del punto
     * @param cy1   the <i>cy1</i> coordinata del punto
     * @param cy1_offset   the <i>cy1</i> coordinata del punto
     * @param thickness   spessore della curva
     * @param speed   velocità della curva
     * @param invert   inversione della curva a seconda del risultato
     *
     * @author Damiano Ficara
     */
    private void drawCurve(Graphics2D g2,
                           float y1, float y1_offset,
                           float y2, float y2_offset,
                           float cx1, float cx1_offset,
                           float cy1, float cy1_offset,
                           float cx2, float cx2_offset,
                           float cy2, float cy2_offset,
                           float thickness,
                           float speed,
                           boolean invert) {

        float width = getWidth();
        float height = getHeight();

        double offset = Math.sin(counter / (speed * Math.PI));
        float start_x = 0.0f;
        float start_y = y1 + (float) (offset * y1_offset);
        float end_x = width;
        float end_y = y2 + (float) (offset * y2_offset);
        float ctrl1_x = (float) offset * cx1_offset + cx1;
        float ctrl1_y = cy1 + (float) (offset * cy1_offset);
        float ctrl2_x = (float) (offset * cx2_offset) + cx2;
        float ctrl2_y = (float) (offset * cy2_offset) + cy2;

        CubicCurve2D curve = new CubicCurve2D.Double(start_x, start_y, ctrl1_x, ctrl1_y, ctrl2_x, ctrl2_y, end_x, end_y);

        GeneralPath path = new GeneralPath(curve);
        path.lineTo(width, height);
        path.lineTo(0, height);
        path.closePath();

        Area thickCurve = new Area((Shape) path.clone());
        AffineTransform translation = AffineTransform.getTranslateInstance(0, thickness);
        path.transform(translation);
        thickCurve.subtract(new Area(path));

        Color start = new Color(255, 255, 255, 200);
        Color end = new Color(255, 255, 255, 0);

        Rectangle bounds = thickCurve.getBounds();
        GradientPaint painter = new GradientPaint(0, curve.getBounds().y,
                invert ? end : start,
                0, bounds.y + bounds.height,
                invert ? start : end);
        Paint oldPainter = g2.getPaint();
        g2.setPaint(painter);

        g2.fill(thickCurve);

        g2.setPaint(oldPainter);
    }
}