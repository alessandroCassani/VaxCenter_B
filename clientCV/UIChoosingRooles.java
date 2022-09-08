package clientCV;

import javax.swing.*;

/**
 * Nell'interfaccia grafica UIChoosingRooles l'utente sceglie se accedere come operatore o come cittadino
 */
public class UIChoosingRooles extends JFrame {
    private JPanel PanelChoosingRooles;
    private JButton cittadiniButton;
    private JButton operatoreVaccinaleButton;

    public UIChoosingRooles(String titolo) {
        super(titolo);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(PanelChoosingRooles);
        this.pack();
        this.setSize(1200, 800);
    }

    public static void main(String[] args) {
        JFrame frameTitolo = new UIChoosingRooles("VaxCenter");
        frameTitolo.setVisible(true);
        frameTitolo.setLocationRelativeTo(null);
        frameTitolo.setResizable(false);

    }

}
