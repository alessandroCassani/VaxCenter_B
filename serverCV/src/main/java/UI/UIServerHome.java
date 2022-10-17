 package UI;

 import UI.graphics.RoundButton;

 import javax.swing.*;
 import javax.swing.border.Border;
 import javax.swing.border.LineBorder;
 import java.awt.*;
 import java.awt.event.ActionEvent;
 import java.awt.event.ActionListener;
 import java.awt.event.WindowAdapter;
 import java.awt.event.WindowEvent;
 import java.util.Objects;

 /**
  * classe che rappresenta l'interfaccia grafica che permette di gestire l'attivazione e lo spegnimento del server PostGre
  *
  *  @author Alessandro Cassani
  *  @author Paolo Bruscagin
  */
 public class UIServerHome extends JFrame implements ActionListener {

     /**
      * Panel per inserire l'immagine d'interfaccia
      */
     JPanel immagine = new JPanel();

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
      *campo di testo in cui viene visuaizzato lo stato del server (on/off)
      */
    JLabel status = new JLabel();

     /**
      * Bottone per tornare nell'interfaccia UILoginToServer
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

        immagine.setBounds(70, 120, 300, 300);
        immagine.setBackground(new Color(181, 226, 232));
        JLabel  lblPic = new JLabel();
        lblPic.setIcon(new ImageIcon(Objects.requireNonNull(getClass().getResource("/images/serverdb_ridv1.png"))));
        immagine.add(lblPic);


        textField.setFont(new Font("Arial",Font.ITALIC,30));
        textField.setBounds(100,60,300,30);
        textField.setBorder(bordo);

        status.setFont(new Font("Arial",Font.ITALIC,20));
        status.setBounds(420,400,200,30);
        status.setBorder(bordo);

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


        add(startBtn);
        add(stopBtn);
        add(textField);
        add(status);
        add(backToLoginToServer);
        add(immagine);


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

                int resp = JOptionPane.showConfirmDialog(null, "Sei sicuro di uscire? Non comporterà variazioni allo stato del Server ...",
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

        if(e.getSource() == startBtn){
            // caricamento oggetto server nel registry

             status.setText("server running...");
        } else if(e.getSource() == stopBtn){
            //eliminazione oggetto server dal registry

            status.setText("server offline!");
        } else if (e.getSource() == backToLoginToServer) {
            this.dispose();
            new UILoginToServer();

        }
    }
}
