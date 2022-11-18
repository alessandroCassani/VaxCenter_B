/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package UI;

import UI.graphics.GradientPanel;
import UI.graphics.MyPwdField;
import UI.graphics.MyTextField;
import database.RoundButton;
import util.Account;
import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.rmi.RemoteException;
import java.util.Objects;


/**
 *
 * Classe che permette al Cittadino di compiere il Login
 *
 * @author Alessandro Cassani - Matricola: 744512 - Sede: VA
 * @author Paolo Bruscagin - Matricola: 744703 - Sede: VA
 * @author Damiano Ficara - Matricola 744958 - Sede: VA
 * @author Luca Perfetti - Matricola 746581 - Sede: VA
 *
 */
public class UILogin extends JFrame  {

    /**
     * costruttore che permette la creazione dei componenti di interfaccia grafica della schermata di login del cittadino
     *
     * @author Paolo Bruscagin
     * @author Damiano Ficara
     */
    public UILogin() {

        initComponents();
        setSize(1000,600);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
        setLocationRelativeTo(null);
        setVisible(true);
        setTitle("Login");
        ImageIcon logo = new ImageIcon(Objects.requireNonNull(getClass().getResource("/images/logo.png")));
        setIconImage(logo.getImage());
        setResizable(false);

        //Popup "Se sicuro di uscire?"
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent evt) {
                UIManager.put("OptionPane.yesButtonText", "Si");
                UIManager.put("OptionPane.noButtonText", "No");

                int resp = JOptionPane.showConfirmDialog(null, "Sei sicuro di uscire?",
                        "Esci?", JOptionPane.YES_NO_OPTION);

                if (resp == JOptionPane.YES_OPTION) {
                    setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                    System.exit(0);
                } else {
                    setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
                }
            }
        });

    }

    /**
     *
     *Questo metodo viene chiamato dall'interno del costruttore per inizializzare il form.
     *ATTENZIONE: NON modificare questo codice. Il contenuto di questo metodo è sempre
     *rigenerato dal Forditor.
     *
     */
    private void initComponents() {

        rightPanel = new GradientPanel(Color.decode("#099779"),Color.decode("#0f88f9"));
        leftPanel = new GradientPanel(Color.decode("#099773"),Color.decode("#0f68a9"));
        intro = new javax.swing.JLabel();
        userPanel = new javax.swing.JPanel();
        imgUser = new javax.swing.JLabel();
        username = new MyTextField();
        pwdPanel = new javax.swing.JPanel();
        imgKey = new javax.swing.JLabel();
        password = new MyPwdField();
        back = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(1000, 600));

        intro.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        intro.setIcon(new javax.swing.ImageIcon(Objects.requireNonNull(getClass().getResource("/images/citizen.png")))); // NOI18N

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(leftPanel);
        leftPanel.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
                jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel2Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(back, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(intro, javax.swing.GroupLayout.DEFAULT_SIZE, 351, Short.MAX_VALUE)
                                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
                jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel2Layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(jPanel2Layout.createSequentialGroup()
                                                .addComponent(back, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                        .addGroup(jPanel2Layout.createSequentialGroup()
                                                .addComponent(intro, javax.swing.GroupLayout.DEFAULT_SIZE, 393, Short.MAX_VALUE)
                                                .addGap(50, 50, 50))))
        );

        username.setHint("Username");
        username.setPrefixIcon(new javax.swing.ImageIcon(Objects.requireNonNull(getClass().getResource("/images/user-5865.png"))));

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(userPanel);
        userPanel.setOpaque(false);
        userPanel.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
                jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(imgUser, javax.swing.GroupLayout.PREFERRED_SIZE, 0, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(username, javax.swing.GroupLayout.DEFAULT_SIZE, 0, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
                jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(imgUser, javax.swing.GroupLayout.DEFAULT_SIZE, 0, Short.MAX_VALUE)
                        .addComponent(username, javax.swing.GroupLayout.Alignment.TRAILING)
        );

        password.setHint("Password");
        password.setPrefixIcon(new javax.swing.ImageIcon(Objects.requireNonNull(getClass().getResource("/images/car-key-5803.png"))));

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(pwdPanel);
        pwdPanel.setOpaque(false);
        pwdPanel.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
                jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel6Layout.createSequentialGroup()
                                .addComponent(imgKey, javax.swing.GroupLayout.PREFERRED_SIZE, 0, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(password))
        );
        jPanel6Layout.setVerticalGroup(
                jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(imgKey, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 0, Short.MAX_VALUE)
                        .addComponent(password, javax.swing.GroupLayout.Alignment.TRAILING)
        );

        RoundButton rnd = new RoundButton();
        rnd.setBackground((new java.awt.Color(25, 25, 112)));
        rnd.setForeground(new java.awt.Color(96, 117, 137));
        rnd.setFont(new java.awt.Font("Arial", 1, 18));
        rnd.setText("LOGIN");
        rnd.setFocusable(false);

        rnd.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                setVisible(false);
                try {
                    if(ServerPointer.getStub().isSignedUp(new Account(username.getText(),password.getText()))){
                        System.out.println("Accesso in corso....");
                        counter = 0;
                        new UIAdverseEvent(username.getText());

                    }
                    else {
                        JOptionPane.showMessageDialog(null, "Accesso rifiutato! Tentativi rimasti:" + (MAX_TIMES - counter), "Messaggio", JOptionPane.ERROR_MESSAGE);
                        new UILogin();
                        counter++;
                        if (counter > MAX_TIMES) {
                            JOptionPane.showMessageDialog(null, "Tentativi esauriti!", "Messaggio", JOptionPane.ERROR_MESSAGE);
                            System.exit(0);
                        }
                    }
                } catch (RemoteException e) {
                    throw new RuntimeException(e);
                }
            }
        });




        back.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        back.setIcon(new javax.swing.ImageIcon(Objects.requireNonNull(getClass().getResource("/images/color50ind.png")))); // NOI18N
        back.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jLabel3MousePressed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(rightPanel);
        rightPanel.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(leftPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addGap(32, 32, 32)
                                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(userPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                        .addComponent(pwdPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                        .addComponent(rnd, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                                .addGap(16, 16, 16))
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(back, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addContainerGap())))
        );
        jPanel1Layout.setVerticalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(leftPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(back, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(92, 92, 92)
                                .addComponent(userPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(41, 41, 41)
                                .addComponent(pwdPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(48, 48, 48)
                                .addComponent(rnd, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(rightPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(rightPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

    }// </editor-fold>

    /**
     * Metodo che permette di tornare alla schermata precedente mediante la freccia apposita
     * @param evt gestione dell'evento collegato al mouse
     * @author Damiano Ficara
     */
    private void jLabel3MousePressed(java.awt.event.MouseEvent evt) {
        this.dispose();
        new UICitizen();
    }

    /**
     * Label contenente immagine di presentazione della schermata di login
     */
    private JLabel intro;
    /**
     * Label per inserire l'immagine per tornare alla schermata precedente
     */
    private JLabel back;
    /**
     * Label per contere l'immagine per identificare la password
     */
    private JLabel imgKey;
    /**
     * Label per contere l'immagine per identificare il nome utente
     */
    private JLabel imgUser;
    /**
     * Panel che contiene i campi di inserimento delle informazioni dell'utente
     */
    private JPanel rightPanel;
    /**
     * Panel che contiene l'immagine di presentazione della schermata
     */
    private JPanel leftPanel;
    /**
     * Panel che contiene il campo relativo allo username
     */
    private JPanel userPanel;
    /**
     * Panel che contiene il campo relativo alla password
     */
    private JPanel pwdPanel;
    /**
     * Campo relativo alla password
     */
    private MyPwdField password;
    /**
     * Campo relativo allo username
     */
    private MyTextField username;

    /**
     * variabile che tiene conto dei tentavi per compiere il login
     */
    private static Integer counter = 0;
    /**
     * variabile che tiene conto dei tentavi massimi per compiere il login
     */
    private static  final Integer MAX_TIMES = 3;
}