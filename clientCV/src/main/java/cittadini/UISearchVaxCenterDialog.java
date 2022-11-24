package cittadini;

import javax.swing.*;
import java.util.Objects;

/**
 * La classe UISearchVaxCenterDialog permette di avere un prospetto riassuntivo del centro vaccinale selezionato
 *
 * @author Alessandro Cassani - Matricola: 744512 - Sede: VA
 * @author Paolo Bruscagin - Matricola: 744703 - Sede: VA
 * @author Damiano Ficara - Matricola 744958 - Sede: VA
 * @author Luca Perfetti - Matricola 746581 - Sede: VA
 *
 */
public class UISearchVaxCenterDialog extends javax.swing.JFrame{

    /**
     * costruttore che permette la creazione dei componenti di interfaccia grafica della schermata di prospetto riassuntivo di
     * uno specifico centro vaccinale
     *
     * @author Luca Perfetti
     */
    public UISearchVaxCenterDialog() {
        initComponents();
        setTitle("Centro Vaccinale - Info");
        ImageIcon logo = new ImageIcon(Objects.requireNonNull(getClass().getResource("/images/logo.png")));
        setIconImage(logo.getImage());
        setSize(500,510);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
        setResizable(false);
    }

    /**
     * Questo metodo viene chiamato dall'interno del costruttore per inizializzare il form.
     * ATTENZIONE: NON modificare questo codice. Il contenuto di questo metodo è sempre
     * rigenerato dal Forditor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabelTitle = new javax.swing.JLabel();
        jLabelNome = new javax.swing.JLabel();
        jLabelinsNome = new javax.swing.JLabel();
        jLabelMalDiTesta = new javax.swing.JLabel();
        jLabelfebbre = new javax.swing.JLabel();
        jLabelTachicardia = new javax.swing.JLabel();
        jLabeldolori = new javax.swing.JLabel();
        jLabelLinfo = new javax.swing.JLabel();
        jLabelcrisi = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(181, 226, 232));

        jPanel1.setBackground(new java.awt.Color(181, 226, 232));

        jLabelTitle.setFont(new java.awt.Font("Segoe Script", 3, 30)); // NOI18N
        jLabelTitle.setText("Centro Vaccinale");

        jLabelNome.setFont(new java.awt.Font("Sitka Small", 1, 16)); // NOI18N
        jLabelNome.setForeground(new java.awt.Color(0, 0, 153));
        jLabelNome.setText("Nome:");

        jLabelinsNome.setFont(new java.awt.Font("Sitka Small", 2, 16)); // NOI18N

        jLabelMalDiTesta.setFont(new java.awt.Font("Sitka Small", 0, 14)); // NOI18N

        jLabelfebbre.setFont(new java.awt.Font("Sitka Small", 0, 14)); // NOI18N

        jLabelTachicardia.setFont(new java.awt.Font("Sitka Small", 0, 14)); // NOI18N

        jLabeldolori.setFont(new java.awt.Font("Sitka Small", 0, 14)); // NOI18N

        jLabelLinfo.setFont(new java.awt.Font("Sitka Small", 0, 14)); // NOI18N

        jLabelcrisi.setFont(new java.awt.Font("Sitka Small", 0, 14)); // NOI18N

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addGap(35, 35, 35)
                                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                                        .addComponent(jLabelLinfo, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                        .addComponent(jLabeldolori, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                        .addComponent(jLabelTachicardia, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                        .addComponent(jLabelfebbre, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                        .addComponent(jLabelMalDiTesta, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                        .addComponent(jLabelcrisi, javax.swing.GroupLayout.DEFAULT_SIZE, 446, Short.MAX_VALUE)))
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addGap(32, 32, 32)
                                                .addComponent(jLabelNome)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(jLabelinsNome, javax.swing.GroupLayout.DEFAULT_SIZE, 380, Short.MAX_VALUE)
                                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                                                .addComponent(jLabelTitle, javax.swing.GroupLayout.DEFAULT_SIZE, 331, Short.MAX_VALUE)
                                                                .addGap(49, 49, 49)))))
                                .addGap(12, 12, 12))
        );
        jPanel1Layout.setVerticalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(46, 46, 46)
                                .addComponent(jLabelTitle, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(38, 38, 38)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(jLabelNome, javax.swing.GroupLayout.DEFAULT_SIZE, 29, Short.MAX_VALUE)
                                        .addComponent(jLabelinsNome, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabelMalDiTesta, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabelfebbre, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabelTachicardia, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabeldolori, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabelLinfo, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabelcrisi, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(55, 55, 55))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, 0))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGap(0, 0, 0)
                                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, 0))
        );

        pack();
    }// </editor-fold>

    /**
     * Label che indica la severita' media e numero di segnalazioni della linfoadenopatia di uno specifico centro vaccinale
     */
    public javax.swing.JLabel jLabelLinfo;

    /**
     * Label che indica la severita' media e numero di segnalazioni del mal di testa di uno specifico centro vaccinale
     */
    public javax.swing.JLabel jLabelMalDiTesta;

    /**
     * Label che contiene la scritta 'Nome' nella dialog
     */
    private javax.swing.JLabel jLabelNome;

    /**
     * Label che indica la severita' media e numero di segnalazioni della tachicardia di uno specifico centro vaccinale
     */
    public javax.swing.JLabel jLabelTachicardia;

    /**
     * Label che contiene il titolo della dialog
     */
    private javax.swing.JLabel jLabelTitle;

    /**
     * Label che indica la severita' media e numero di segnalazioni delle crisi ipertensive di uno specifico centro vaccinale
     */
    public javax.swing.JLabel jLabelcrisi;

    /**
     * Label che indica la severita' media e numero di segnalazioni dei dolori muscolari di uno specifico centro vaccinale
     */
    public javax.swing.JLabel jLabeldolori;

    /**
     * Label che indica la severita' media e numero di segnalazioni della febbre di uno specifico centro vaccinale
     */
    public javax.swing.JLabel jLabelfebbre;

    /**
     * Label che contiene il nome del centro vaccinale
     */
    public javax.swing.JLabel jLabelinsNome;

    /**
     * Panel che contiene i componenti relativi all'interfaccia
     */
    private javax.swing.JPanel jPanel1;
    // End of variables declaration
}