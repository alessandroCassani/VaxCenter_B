package UI.graphics;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Composite;
import java.awt.Cursor;
import java.awt.FontMetrics;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Insets;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Area;
import java.awt.geom.Path2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.awt.geom.RoundRectangle2D;
import java.util.ArrayList;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;

import UI.graphics.InfoSearch;
import UI.graphics.InfoSearchEvent;
import org.jdesktop.animation.timing.Animator;
import org.jdesktop.animation.timing.TimingTargetAdapter;

/**
 * La classe SearchField è responsabile della gestione grafica del campo di ricerca
 @author Damiano Ficara
 */
public class SearchField extends JTextField {

    /**
     * metodo che consente di ottenere il suggerimento del campo di ricerca
     * @return stringa rappresentante il suggerimento
     * @author Damiano Ficara
     */
    public String getHint() {
        return hint;
    }

    /**
     * metodo che consente di impostare il suggerimento del campo di ricerca
     * @return stringa rappresentante il nuovo suggerimento
     * @author Damiano Ficara
     */
    public void setHint(String hint) {
        this.hint = hint;
    }


    /**
     * metodo che consente di ottenere il primo colore di sovrapposizione
     * @return colore di sovrapposizione
     * @author Damiano Ficara
     */
    public Color getColorOverlay1() {
        return colorOverlay1;
    }

    /**
     * metodo che consente di impostare il primo colore di sovrapposizione
     * @return nuovo colore di sovrapposizione
     * @author Damiano Ficara
     */
    public void setColorOverlay1(Color colorOverlay1) {
        this.colorOverlay1 = colorOverlay1;
    }

    /**
     * metodo che consente di ottenere il secondo colore di sovrapposizione
     * @return colore di sovrapposizione
     * @author Damiano Ficara
     */
    public Color getColorOverlay2() {
        return colorOverlay2;
    }

    /**
     * metodo che consente di impostare il secondo colore di sovrapposizione
     * @return nuovo colore di sovrapposizione
     * @author Damiano Ficara
     */
    public void setColorOverlay2(Color colorOverlay2) {
        this.colorOverlay2 = colorOverlay2;
    }

    /**
     * Variabile che rappresenta l'animazione alla selezione della tipologia di ricerca
     */
    private Animator animator;
    /**
     * Variabile di appoggio allo scorrimento della barra di animazione
     */
    private float animate;
    /**
     * Variabile che identifica la selezione o meno della tipologia di ricerca
     */
    private boolean option = false;
    /**
     * Variabile responsabile della gestione della forma in uso
     */
    private Shape shape;
    /**
     * Variabile che identifica la selezione o meno dell'evento collegato all'utilizzo del cursore
     */
    private boolean mousePressed = false;
    /**
     * Lista di oggetti di <code>InfoSearch</code>
     */
    private final List<InfoSearch> items = new ArrayList<>();
    /**
     * Lista di oggetti di <code>InfoSearchEvent</code>
     */
    private final List<InfoSearchEvent> events = new ArrayList<>();
    /**
     * Variabile che identifica indice delle tipologia di ricerca presenti
     */
    private int selectedIndex = -1;
    /**
     * Variabile che identifica indice del tipo di ricerca scelta
     */
    private int pressedIndex = -1;
    /**
     * Primo colore di sovrapposizione
     */
    private Color colorOverlay1 = new Color(40, 170, 240);
    /**
     * Secondo colore di sovrapposizione
     */
    private Color colorOverlay2 = new Color(138, 39, 232);
    /**
     * Stringa rappresentante il suggerimento di default
     */
    private String hint = "Search...";

    /**
     * Costruttore responsabile dell'inizializzazione dei componenti grafici, dell'animazione e degli eventi collegati al campo di ricerca
     * @author Damiano Ficara
     */
    public SearchField() {
        setBorder(new EmptyBorder(10, 10, 10, 40));
        setSelectionColor(new Color(25, 141, 255));
        MouseAdapter mouseEvent = new MouseAdapter() {
            @Override
            public void mouseMoved(MouseEvent me) {
                if (isOver(me.getPoint())) {
                    setCursor(new Cursor(Cursor.HAND_CURSOR));
                } else {
                    if (option) {
                        setCursor(new Cursor(Cursor.HAND_CURSOR));
                    } else {
                        setCursor(new Cursor(Cursor.TEXT_CURSOR));
                    }
                }
            }

            @Override
            public void mousePressed(MouseEvent me) {
                if (SwingUtilities.isLeftMouseButton(me)) {
                    mousePressed = isOver(me.getPoint());
                    if (!mousePressed) {
                        pressedIndex = checkPress(me.getPoint());
                    }
                }
            }

            @Override
            public void mouseReleased(MouseEvent me) {
                if (SwingUtilities.isLeftMouseButton(me)) {
                    if (!animator.isRunning()) {
                        if (mousePressed && isOver(me.getPoint())) {
                            startAnimate();
                        } else {
                            int index = checkPress(me.getPoint());
                            if (index != -1) {
                                if (index == pressedIndex) {
                                    selectedIndex = index;
                                    runEvent();
                                    startAnimate();
                                }
                            }
                        }
                    }
                }
            }
        };
        addMouseMotionListener(mouseEvent);
        addMouseListener(mouseEvent);
        addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent fe) {
                if (option) {
                    startAnimate();
                }
            }
        });
        initAnimator();
    }
    /**
     * metono che permette di aggiungere un'opzione di ricerca
     * @author Damiano Ficara
     */
    public void addOption(InfoSearch option) {
        items.add(option);
        if (selectedIndex == -1) {
            selectedIndex = 0;
            runEvent();
        }
    }

    /**
     * metono che permette di aggiungere le specifiche di un tipo di ricerca
     * @author Damiano Ficara
     */
    public void addEventOptionSelected(InfoSearchEvent event) {
        events.add(event);
    }

    /**
     * metono che permette di ottenere l'oggetto <code>InfoSearch</code>  della ricerca scelta
     * @author Damiano Ficara
     * @return tipologia di ricerca scelta
     */
    public InfoSearch getSelectedOption() {
        if (selectedIndex == -1) {
            return null;
        } else {
            return items.get(selectedIndex);
        }
    }

    /**
     * metono che permette di analizzare se è stata scelta una specifica modalità di ricerca
     * @author Damiano Ficara
     * @return true o false selezione
     */
    public boolean isSelected() {
        return selectedIndex >= 0;
    }

    /**
     * metono che permette evidenziare la ricerca selezionata
     * @author Damiano Ficara
     */
    public void setSelectedIndex(int index) {
        selectedIndex = index;
        runEvent();
        repaint();
    }

    /**
     * metono che permette di ottenere indice della ricerca selezionata
     * @author Damiano Ficara
     * @return indice rappresentante la selezione
     */
    public int getSelectedIndex() {
        return selectedIndex;
    }

    /**
     * metono privato che permette di gestire gli eventi collegati alla ricerca
     * @author Damiano Ficara
     */
    private void runEvent() {
        for (InfoSearchEvent event : events) {
            event.optionSelected(getSelectedOption(), selectedIndex);
        }
    }

    /**
     * metono privato che permette di gestire gli eventi collegati alle animazioni
     * @author Damiano Ficara
     */
    private void startAnimate() {
        if (animator.isRunning()) {
            float f = animator.getTimingFraction();
            animator.stop();
            animator.setStartFraction(1f - f);
        } else {
            animator.setStartFraction(0f);
        }
        option = !option;
        animator.start();
    }

    /**
     * metono privato che si occupa di gestire gli eventi collegati alla posizione del cursore
     * @author Damiano Ficara
     */
    private boolean isOver(Point mouse) {
        if (!option) {
            return shape.contains(mouse);
        }
        return false;
    }

    /**
     * metono privato che si occupa di gestire gli eventi collegati all'eventuale pressione del cursore
     * @author Damiano Ficara
     * @return indice tipologia di ricerca
     */
    private int checkPress(Point mouse) {
        int index = -1;
        if (!items.isEmpty() && option) {
            double width = getWidth() / items.size();
            for (int i = 0; i < items.size(); i++) {
                if (new Rectangle2D.Double(width * i, 0, width, getHeight()).contains(mouse)) {
                    index = i;
                    break;
                }
            }
        }
        return index;
    }
    /**
     * metono privato che inizializza l'animazione
     * @author Damiano Ficara
     */
    private void initAnimator() {
        animator = new Animator(500, new TimingTargetAdapter() {
            @Override
            public void begin() {
                setEditable(!option);
            }

            @Override
            public void timingEvent(float fraction) {
                if (option) {
                    animate = fraction;
                } else {
                    animate = 1f - fraction;
                }
                repaint();
            }
        });
        animator.setResolution(0);
        animator.setDeceleration(0.5f);
        animator.setAcceleration(0.5f);
    }

    /**
     * Metodo responsabile dell'operazione di disegno del componente grafico
     * @param grphcs componente grafico considerato
     * @author Damiano Ficara
     */
    @Override
    protected void paintComponent(Graphics grphcs) {
        Graphics2D g2 = (Graphics2D) grphcs.create();
        g2.setColor(new Color(151, 151, 151));
        g2.drawRect(1, 1, getWidth() - 3, getHeight() - 3);
        if (isFocusOwner()) {
            g2.setColor(new Color(60, 158, 255));
            g2.drawRect(0, 0, getWidth() - 1, getHeight() - 1);
        }
        g2.dispose();
        super.paintComponent(grphcs);
    }

    /**
     * Metodo responsabile dell'operazione di disegno
     * @param grphcs componente grafico considerato
     * @author Damiano Ficara
     */
    @Override
    public void paint(Graphics grphcs) {
        super.paint(grphcs);
        Graphics2D g2 = (Graphics2D) grphcs.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        paintHint(g2);
        double x = getWidth() - 35;
        double y = 2;
        x -= (x - 2) * animate;
        double height = getHeight() - 4;
        double round = height - height * animate;
        Area area = new Area(new RoundRectangle2D.Double(x, y, height, height, round, round));
        Path2D p = new Path2D.Double();
        p.moveTo(x + height / 2, y);
        p.lineTo(getWidth() - 2, y);
        p.lineTo(getWidth() - 2, y + height);
        p.lineTo(x + height / 2, y + height);
        area.add(new Area(p));
        g2.setPaint(new GradientPaint(new Point2D.Double(x, 0), colorOverlay1, new Point2D.Double(getWidth(), 0), colorOverlay2));
        g2.fill(area);
        shape = area;
        drawItem(g2, x, y, getWidth() - 2, height);
        g2.dispose();
    }
    /**
     * Metodo responsabile dell'operazione di disegno del suggerimento
     * @param g2 componente grafico considerato
     * @author Damiano Ficara
     */
    private void paintHint(Graphics2D g2) {
        if (getText().length() == 0) {
            int h = getHeight();
            Insets ins = getInsets();
            FontMetrics fm = g2.getFontMetrics();
            int c0 = getBackground().getRGB();
            int c1 = getForeground().getRGB();
            int m = 0xfefefefe;
            int c2 = ((c0 & m) >>> 1) + ((c1 & m) >>> 1);
            g2.setColor(new Color(c2, true));
            g2.drawString(hint, ins.left, h / 2 + fm.getAscent() / 2 - 2);
        }
    }
    /**
     * Metodo responsabile dell'operazione di disegno dell'oggetto di ricerca consierato
     * @param g2 componente grafico considerato
     * @param x cordinata dell'oggetto
     * @param y cordinata dell'oggetto
     * @param width dimensione dell'oggetto di ricerca
     * @param height dimensione dell'oggetto di ricerca
     * @author Damiano Ficara
     */
    private void drawItem(Graphics2D g2, double x, double y, double width, double height) {
        double w = width - x;
        double per = w / items.size();
        for (int i = 0; i < items.size(); i++) {
            drawIcon(g2, x + i * per, y, per, height, i);
        }
    }

    /**
     * Metodo responsabile dell'operazione di disegno dell'icona dell'oggetto di ricerca consierato
     * @param g2 componente grafico considerato
     * @param x cordinata dell'oggetto
     * @param y cordinata dell'oggetto
     * @param width dimensione dell'oggetto di ricerca
     * @param height dimensione dell'oggetto di ricerca
     * @param index indice dell'oggetto di ricerca a cui assegnare icona
     * @author Damiano Ficara
     */
    private void drawIcon(Graphics2D g2, double x, double y, double width, double height, int index) {
        Composite oldComposite = g2.getComposite();
        if (index != selectedIndex) {
            g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, animate));
        } else {
            width = (width <= 35 ? 35 : width);
            x = (x > getWidth() - 34 ? getWidth() - 34 : x);
        }
        ImageIcon image = toImage(index);
        double ix = x + ((width - image.getIconWidth()) / 2);
        double iy = y + ((height - image.getIconHeight()) / 2);
        g2.drawImage(image.getImage(), (int) ix, (int) iy, null);
        g2.setComposite(oldComposite);
    }

    /**
     * Metodo che permette di ottenere l'immagine avendo a disposizione indice
     * @param index indice dell'oggetto di ricerca
     * @author Damiano Ficara
     */
    private ImageIcon toImage(int index) {
        return (ImageIcon) items.get(index).getIcon();
    }
}