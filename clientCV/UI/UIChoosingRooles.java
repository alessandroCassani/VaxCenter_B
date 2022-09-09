package clientCV.UI;

import javax.swing.*;

/**
 * Nell'interfaccia grafica UIChoosingRooles l'utente sceglie se accedere come operatore o come cittadino
 */
public class UIChoosingRooles extends JFrame {
    /**
     * Pannello principale di visualizzazione
     */
    private JPanel PanelChoosingRooles;
    /**
     * Bottone per accedere alla sezione Cittadini
     */
    private JButton cittadiniButton;
    /**
     * Bottone per accedere alla sezione Operatori Vaccinali
     */
    private JButton operatoreVaccinaleButton;

    public UIChoosingRooles(String title) {
        super(title);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();
        this.setSize(1280, 720);
        this.setVisible(true);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
    }
}