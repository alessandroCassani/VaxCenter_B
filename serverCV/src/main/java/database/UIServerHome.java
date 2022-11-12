package database;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.ExportException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Objects;

/**
 * classe che rappresenta l'interfaccia grafica che permette di gestire l'attivazione e lo spegnimento del server PostGre
 *
 *  @author Alessandro Cassani
 *  @author Paolo Bruscagin
 */
public class UIServerHome extends JFrame implements ActionListener {

    /**
     * Porta sulla quale si ascolterà il server
     */
    private static int PORT = 1099;
    /**
     *  Oggetto che implementa l'interfaccia <code>database.ServerInterface</code> e si occupa delle operazioni dei client
     */
    static ServerImpl server;

    /**
     * Oggetto <code>Registry</code> che mette a disposizione metodi per le operazioni relative ai riferimenti degli oggetti remoti
     */
    static Registry registry;

    /**
     * Nome del servizio caricato sul registry
     */
    static final String SERVICE_NAME = "VaxCenter";
    /**
     * Tentativi di utilizzo massimi del server
     */
    static boolean check = false;
    /**
     * Variabile booleana che tiene traccia dello stato corrente del server
     */

    JPanel immagine = new JPanel();
    /**
     * Panel per inserire l'immagine del server running
     */
    JPanel sr = new JPanel();
    /**
     * Panel per inserire l'immagine del server offline
     */
    JPanel so = new JPanel();

    /**
     * bottone di attivazione del server
     */
    RoundButton startBtn = new RoundButton("START");

    /**
     * campo di testo per UI per informare l'utente sullo scopo dell'interfaccia grafica
     */
    JLabel textField = new JLabel("Gestisci il Server:");

    /**
     * bottone di spegnimento del server
     */
    RoundButton stopBtn = new RoundButton("STOP");

    /**
     * bottone d'inserimento del dataset
     */
    RoundButton insertDS = new RoundButton("DATASET");


    /**
     *campo di testo in cui viene visuaizzato lo stato del server (on/off)
     */
    JLabel status = new JLabel();

    /**
     * Bottone per tornare nell'interfaccia database.UILoginToServer
     */

    JButton backToLoginToServer;

    /**
     * costruttore che contiene le informazioni per il caricamento dei componenti d'interfaccia grafica
     *
     * @author Alessandro Cassani
     */
    public UIServerHome(){
        Border bordo = new LineBorder(new Color(0x808080, true), 2, true);
        Border bordobtnInd = new LineBorder(new Color(181, 226, 232), 2, true);

        //immagine programma

        immagine.setBounds(70, 160, 300, 300);
        immagine.setBackground(new Color(181, 226, 232));
        JLabel  lblPic = new JLabel();
        lblPic.setIcon(new ImageIcon(Objects.requireNonNull(getClass().getResource("/images/serverdb_ridv1.png"))));
        immagine.add(lblPic);


        textField.setFont(new Font("Arial",Font.ITALIC,30));
        textField.setBounds(100,80,300,30);
        textField.setBorder(bordo);

        status.setFont(new Font("Arial",Font.ITALIC,20));
        status.setText("Server Offline ...");
        status.setBounds(500,300,200,30);
        status.setBorder(bordo);

        sr.setBounds(650, 280, 150, 75);
        sr.setBackground(new Color(181, 226, 232));
        JLabel  lblSR = new JLabel();
        lblSR.setIcon(new ImageIcon(Objects.requireNonNull(getClass().getResource("/images/srunning_v1.gif"))));
        sr.add(lblSR);

        so.setBounds(650, 280, 150, 75);
        so.setBackground(new Color(181, 226, 232));
        JLabel  lblSO = new JLabel();
        lblSO.setIcon(new ImageIcon(Objects.requireNonNull(getClass().getResource("/images/X_v2.png"))));
        so.add(lblSO);

        startBtn.setBorder(bordo);
        startBtn.setBounds(435,150,200,75);
        startBtn.setBackground(new Color(0xFF0EB94E, true));
        startBtn.setFont(new Font("Georgia", Font.BOLD, 20));
        startBtn.setFocusable(false);
        startBtn.addActionListener(this);

        stopBtn.setBorder(bordo);
        stopBtn.setBounds(700,150,200,75);
        stopBtn.setBackground(new Color(0xCC1E1E));
        stopBtn.setFont(new Font("Georgia", Font.BOLD, 20));
        stopBtn.setFocusable(false);
        stopBtn.addActionListener(this);

        insertDS.setBorder(bordo);
        insertDS.setBounds(565,400,200,75);
        insertDS.setBackground(new Color(0xFF12DECD, true));
        insertDS.setFont(new Font("Georgia", Font.BOLD, 20));
        insertDS.setFocusable(false);
        insertDS.addActionListener(this);


        ImageIcon ind = new ImageIcon(Objects.requireNonNull(getClass().getResource("/images/color50ind.png")));
        backToLoginToServer = new JButton(ind);
        backToLoginToServer.setBounds(10, 10, 55 , 55);
        backToLoginToServer.setFont(new Font("Georgia", Font.BOLD, 17));
        backToLoginToServer.setBackground(new Color(181, 226, 232));
        backToLoginToServer.setForeground(Color.WHITE);
        backToLoginToServer.setBorder(bordobtnInd);
        backToLoginToServer.setFocusable(false);
        backToLoginToServer.addActionListener(this);
        backToLoginToServer.setFocusable(false);
        backToLoginToServer.setOpaque(true);
        backToLoginToServer.setContentAreaFilled(false);



        add(startBtn);
        add(stopBtn);
        add(textField);
        add(status);
        add(backToLoginToServer);
        add(immagine);
        add(sr).setVisible(false);
        add(so);
        add(insertDS);


        //Icona avvio del programma
        ImageIcon logo = new ImageIcon(Objects.requireNonNull(getClass().getResource("/images/serverdb.png")));
        setIconImage(logo.getImage());

        setTitle("Server Home");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize (1000, 600);
        setLocationRelativeTo(null);
        setResizable(false);
        setForeground(Color.WHITE);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        getContentPane().setBackground(new Color(181, 226, 232));
        setForeground(Color.WHITE);
        setVisible(true);

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
     * metodo che permette di gestire gli eventi associati ai listener dei componenti di UI attivati dall'utente
     * @param e the event to be processed
     *
     * @author Alessandro Cassani
     * @author Paolo Bruscagin
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == startBtn) {
            startServer();
            status.setText("Server is Running ...");
            sr.setVisible(true);
            so.setVisible(false);
        } else if(e.getSource() == stopBtn){
            stopServer();
            status.setText("Server Offline ...");
            so.setVisible(true);
            sr.setVisible(false);
        } else if (e.getSource() == backToLoginToServer) {
            this.dispose();
            new UILoginToServer();

        }

        if (e.getSource() == insertDS){
            //Popup inserimento dataset
            UIManager.put("OptionPane.yesButtonText", "Si");
            UIManager.put("OptionPane.noButtonText", "No");
                    int resp = JOptionPane.showConfirmDialog(null, "Sei sicuro di inserire il DataSet?",
                            "Inserimento DataSet", JOptionPane.YES_NO_OPTION);

                    if (resp == JOptionPane.YES_OPTION) {
                        JOptionPane.showMessageDialog(null, "DataSet inserito con successo!", "Messaggio",JOptionPane.INFORMATION_MESSAGE);

                    } else {
                        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
                    }
                }


    }

    /**
     * Il metodo permette di istanziare un nuovo oggetto registry e definire un nuovo oggetto remoto
     * @return true/false a seguito del corretto funzionamento
     * @author Damiano Ficara
     * @author Luca Perfetti
     */
    public static boolean startServer(){
        try{
            server = new ServerImpl();

            try {
                registry = LocateRegistry.createRegistry(PORT);
            }catch (ExportException e){
                registry = LocateRegistry.getRegistry(PORT);
            }

            registry.rebind(SERVICE_NAME, server);
            check = true;
        }catch(RemoteException e){
            e.printStackTrace();
            return false;
        }

        return true;
    }

    /**
     * Il metodo permette di rimuove un oggetto registry e compiere unbind
     * @return true/false a seguito del corretto funzionamento
     * @author Damiano Ficara
     * @author Luca Perfetti
     */
    public static boolean stopServer(){
        if(check){
            try {
                registry.unbind(SERVICE_NAME);
                UnicastRemoteObject.unexportObject(server, true);
                check = false;
                return true;
            } catch (RemoteException | NotBoundException e) {
                e.printStackTrace();
            }
            return false;
        }else{
            return true;
        }
    }
}

