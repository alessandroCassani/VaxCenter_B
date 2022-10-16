 package UI;

 import javax.swing.*;
 import javax.swing.border.Border;
 import javax.swing.border.LineBorder;
 import java.awt.*;
 import java.awt.event.ActionEvent;
 import java.awt.event.ActionListener;
 import java.util.Objects;

 /**
  * classe che rappresenta l'interfaccia grafica che permette di gestire l'attivazione e lo spegnimento del server PostGre
  *
  *  @author Alessandro Cassani
  *  @author Paolo Bruscagin
  */
 public class UIServerHome extends JFrame implements ActionListener {

     /**
      * bottone di attivazione del server
      */
    JButton startBtn = new JButton("START");

     /**
      * campo di testo per UI per informare l'utente sullo scopo dell'interfaccia grafica
      */
    JLabel textField = new JLabel("manage the server!");

     /**
      * bottone di spegnimento del server
      */
    JButton stopBtn = new JButton("STOP");

     /**
      * container relativo all'interfaccia grafica
      */
    Container container = getContentPane();

     /**
      *campo di testo in cui viene visuaizzato lo stato del server (on/off)
      */
    JLabel status = new JLabel();

     /**
      * costruttore che contiene le informazioni per il caricamento dei componenti di interfaccia grafica
      *
      * @author Alessandro Cassani
      */
    public UIServerHome(){
        Border bordo = new LineBorder(new Color(0x808080, true), 2, true);

        container.setLayout(null);

        textField.setFont(new Font("Arial",Font.ITALIC,50));
        textField.setBounds(260,80,600,100);
        textField.setBorder(bordo);

        status.setFont(new Font("Arial",Font.ITALIC,20));
        status.setBounds(420,400,200,30);
        status.setBorder(bordo);

        startBtn.setBorder(bordo);
        startBtn.setBounds(225,250,130,60);
        startBtn.setBackground(Color.GREEN);
        startBtn.setOpaque(true);
        startBtn.addActionListener(this);

        stopBtn.setBorder(bordo);
        stopBtn.setBounds(635,250,130,60);
        stopBtn.setBackground(Color.red);
        stopBtn.setOpaque(true);
        stopBtn.addActionListener(this);


        container.add(startBtn);
        container.add(stopBtn);
        container.add(textField);
        container.add(status);


        //Icona avvio del programma
        ImageIcon logo = new ImageIcon(Objects.requireNonNull(getClass().getResource("/images/logoServer.png")));
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
        }

        if(e.getSource() == stopBtn){
            //eliminazione oggetto server dal registry

            status.setText("server offline!");
        }
    }
}
