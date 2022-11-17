package UI;

import database.ServerInterface;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.font.TextAttribute;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Map;
import java.util.Objects;

/**
 * Classe che permette la comunicazione con il server remoto
 *
 * @author Luca Perfetti
 */
public class ServerPointer extends JFrame implements ActionListener {
    /**
     * Porta sulla quale si ascolterà il server
     */
    private static int PORT = 1099;
    /**
     * Nome del servizio caricato sul registry
     */
    static final String SERVICE_NAME = "VaxCenter";
    /**
     * Oggetto che permette la comunicazione tra client e server
     */
    static Registry registry;

    /**
     * Oggetto che permette l'invocazione dei metodi remoti sul server
     */
    static ServerInterface stub;

    /**
     * Label Esci che permette di uscire dal programma
     */

    JLabel esci = new JLabel("Esci");

    public ServerPointer(){

        //Label cliccabile che ti permette di uscire dal programma

        esci.setFont(new Font("Georgia", Font.ITALIC, 18));
        Font font = esci.getFont();
        Map attributes = font.getAttributes();
        attributes.put(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON);
        esci.setFont(font.deriveFont(attributes));
        esci.setForeground(new Color(0,49,83));
        esci.setBounds(60,30,100,20);
        esci.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                System.exit(0);

            }
        });

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

        //Icona avvio del programma
        ImageIcon logo = new ImageIcon(Objects.requireNonNull(getClass().getResource("/images/logo.png")));
        setIconImage(logo.getImage());

        add(esci);


        setTitle("VaxCenter");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize (500, 500);
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
     * Metodo che per mette di avviare il programma
     * @param args
     *
     * @author Luca Perfetti
     */
    // Punto di avvio del client
    public static void main(String[] args)
    {
        new ServerPointer();
        //ServerPointer.connectToRMI();


    }

    /**
     * Metodo che permette di ottenere un riferimento al registro sull'host corrente
     * @return l'oggetto registry
     *
     * @author Luca Perfetti
     */
    public static Registry getRegistry(){
        return registry;
    }

    /**
     * Metodo che permette di aggiornare l'oggetto registry
     * @param registry l'oggetto registry
     *
     * @author Luca Perfetti
     */
    public static void setRegistry(Registry registry){
        ServerPointer.registry = registry;
    }

    /**
     * Metodo che ritorna l'oggetto stub
     * @return l'oggetto stub
     *
     * @author Luca Perfetti
     */
    public static ServerInterface getStub(){
        return stub;
    }

    /**
     * Metodo che permette di aggiornare l'oggetto stub
     * @param stub l'oggetto stub
     *
     * @author Luca Perfetti
     */
    public static void setStub(ServerInterface stub){
        ServerPointer.stub = stub;
    }


    /**
     * Metodo che permette di inizializzare l'oggetto necessario alla comunicazione RMI
     *
     * @author Luca Perfetti
     * @author Damiano Ficara
     */
    public static void connectToRMI()
    {
        try {
            ServerPointer.setRegistry(LocateRegistry.getRegistry("localhost",PORT));
            ServerPointer.setStub((ServerInterface) ServerPointer.getRegistry().lookup(SERVICE_NAME));
            System.out.println("Connessione avvenuta correttamente");
        }catch (RemoteException e) {
            JOptionPane.showMessageDialog(null, " SERVER OFFLINE !", "Messaggio",JOptionPane.ERROR_MESSAGE);
            System.exit(0);
            System.out.println("Impossibile connettersi al server remoto");
        } catch(NotBoundException e) {
            JOptionPane.showMessageDialog(null, " SERVIZIO NON TROVATO !", "Messaggio",JOptionPane.ERROR_MESSAGE);
            System.exit(0);
            System.out.println("Impossibile connettersi al server remoto");
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}