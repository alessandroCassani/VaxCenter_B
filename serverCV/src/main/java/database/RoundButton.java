package database;


import org.jdesktop.animation.timing.Animator;
import org.jdesktop.animation.timing.TimingTarget;
import org.jdesktop.animation.timing.TimingTargetAdapter;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

/**
 * La classe RoundButton è responsabile di disegnare i bottoni con uno stile circolare
 *
 * @author Alessandro Cassani - Matricola: 744512 - Sede: VA
 * @author Paolo Bruscagin - Matricola: 744703 - Sede: VA
 * @author Damiano Ficara - Matricola 744958 - Sede: VA
 * @author Luca Perfetti - Matricola 746581 - Sede: VA
 *
 */

public class RoundButton extends JButton {

    /**
     * metodo che consente di ottenere il colore di effetto alla pressione del bottone
     * @return colore di scelta
     * @author Damiano Ficara
     */

    public Color getEffectColor() {
        return effectColor;
    }
    /**
     * metodo che consente di impostare il colore di effetto della pressione del bottone
     * @author Damiano Ficara
     */

    public void setEffectColor(Color effectColor) {
        this.effectColor = effectColor;
    }

    /**
     * Variabile che rappresenta l'animazione alla selezione del bottone
     */

    private final Animator animator;
    /**
     * Variabile che rappresenta la dimensione dell'obiettivo
     */
    private int targetSize;
    /**
     * Variabile che rappresenta la dimensione dell'animazione
     */
    private float animatSize;
    /**
     * Point che rappresenta il punto selezionato nel bottone
     */
    private Point pressedPoint;
    /**
     * Variabile che rappresenta la trasparenza
     */
    private float alpha;
    /**
     * Variabile che rappresenta il colore di selezione del bottone
     */
    private Color effectColor = new Color(255, 255, 255);

    /**
     * Costruttore responsabile dell'inizializzazione dei componenti grafici, dell'animazione e degli eventi collegati ad esso
     * @author Damiano Ficara
     */
    public RoundButton() {
        setContentAreaFilled(false);
        setBorder(new EmptyBorder(5, 0, 5, 0));
        setBackground(Color.WHITE);
        setCursor(new Cursor(Cursor.HAND_CURSOR));
        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent me) {
                targetSize = Math.max(getWidth(), getHeight()) * 2;
                animatSize = 0;
                pressedPoint = me.getPoint();
                alpha = 0.5f;
                if (animator.isRunning()) {
                    animator.stop();
                }
                animator.start();
            }
        });
        TimingTarget target = new TimingTargetAdapter() {
            @Override
            public void timingEvent(float fraction) {
                if (fraction > 0.5f) {
                    alpha = 1 - fraction;
                }
                animatSize = fraction * targetSize;
                repaint();
            }
        };
        animator = new Animator(700, target);
        animator.setAcceleration(0.5f);
        animator.setDeceleration(0.5f);
        animator.setResolution(0);
    }

    /**
     * Costruttore responsabile dell'inizializzazione dei componenti grafici, dell'animazione e degli eventi collegati ad esso
     * @param icon icona da impostare nel bottone
     * @author Damiano Ficara
     */
    public RoundButton(Icon icon) {
        super(null, icon);
        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent me) {
                targetSize = Math.max(getWidth(), getHeight()) * 2;
                animatSize = 0;
                pressedPoint = me.getPoint();
                alpha = 0.5f;
                if (animator.isRunning()) {
                    animator.stop();
                }
                animator.start();
            }
        });
        TimingTarget target = new TimingTargetAdapter() {
            @Override
            public void timingEvent(float fraction) {
                if (fraction > 0.5f) {
                    alpha = 1 - fraction;
                }
                animatSize = fraction * targetSize;
                repaint();
            }
        };
        animator = new Animator(700, target);
        animator.setAcceleration(0.5f);
        animator.setDeceleration(0.5f);
        animator.setResolution(0);
    }

    /**
     * Costruttore responsabile dell'inizializzazione dei componenti grafici, dell'animazione e degli eventi collegati ad esso
     * @param text testo da impostare nel bottone
     * @author Damiano Ficara
     */
    public RoundButton(String text) {
        super(text);
        setContentAreaFilled(false);
        setBorder(new EmptyBorder(5, 0, 5, 0));
        setBackground(Color.WHITE);
        setCursor(new Cursor(Cursor.HAND_CURSOR));
        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent me) {
                targetSize = Math.max(getWidth(), getHeight()) * 2;
                animatSize = 0;
                pressedPoint = me.getPoint();
                alpha = 0.5f;
                if (animator.isRunning()) {
                    animator.stop();
                }
                animator.start();
            }
        });
        TimingTarget target = new TimingTargetAdapter() {
            @Override
            public void timingEvent(float fraction) {
                if (fraction > 0.5f) {
                    alpha = 1 - fraction;
                }
                animatSize = fraction * targetSize;
                repaint();
            }
        };
        animator = new Animator(700, target);
        animator.setAcceleration(0.5f);
        animator.setDeceleration(0.5f);
        animator.setResolution(0);
    }

    /**
     * Metodo responsabile dell'operazione di disegno del componente grafico
     * @param grphcs componente grafico considerato
     * @author Damiano Ficara
     */
    @Override
    protected void paintComponent(Graphics grphcs) {
        int width = getWidth();
        int height = getHeight();
        BufferedImage img = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = img.createGraphics();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(getBackground());
        g2.fillRoundRect(0, 0, width, height, height, height);
        if (pressedPoint != null) {
            g2.setColor(effectColor);
            g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_ATOP, alpha));
            g2.fillOval((int) (pressedPoint.x - animatSize / 2), (int) (pressedPoint.y - animatSize / 2), (int) animatSize, (int) animatSize);
        }
        g2.dispose();
        grphcs.drawImage(img, 0, 0, null);
        super.paintComponent(grphcs);
    }

    /**
     * metodo che permette di impostare il testo presente nel bottone
     * @author Damiano Ficara
     */
    @Override
    public void setText(String text) {
        super.setText(text);
    }

    /**
     * Metodo che permette di modificare la dimensione dell'immagine del bottone
     * @param ic immagine in ingresso
     * @param x cordinata relativa alle ascisse
     * @param y cordinata relativa alle ordinate
     * @author Damiano Ficara
     */
    public ImageIcon resizeImage(ImageIcon ic, int x, int y) {
        Image img = ic.getImage() ;
        Image newimg = img.getScaledInstance( x, y,  Image.SCALE_SMOOTH ) ;
        ic = new ImageIcon( newimg );
        return ic;
    }
}