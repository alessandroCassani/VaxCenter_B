import java.sql.*;

/**
 * La classe DBManagement permette di creare la connessione al server Postgres, il database e le tabelle
 *
 * @author Luca Perfetti
 */

public class DBManagement {
    /**
     * Nome del database
     */
    static String nameDB = "vaxcenter";

    /**
     * Rappresenta l'istanza corrente del databse
     */
    static DBManagement instanceDB = null;

    /**
     * Indirizzo di rete del dbms
     */
    static String hostDB = "localhost";

    /**
     * Porta del server Postgres
     */
    static int portDB = 5432;

    /**
     * Username dell'utente per accedere al server Postgres
     */
     static String userDB;

    /**
     * Password dell'utente per accedere al server Postgres
     */
    static String passwordDB;

    /**
     * Oggetto per gestire la connessione
     */
    public static Connection connection = null;

    /**
     * Url per la connessione al server Postgres
     */
    static String url = "jdbc:postgresql://"+ hostDB + ":" + portDB + "/";


    /**
     * Metodo che ritorna l'istanza del database
     *
     * @author Luca Perfetti
     */
    public static DBManagement getDB(){
        if(instanceDB == null){
            instanceDB = new DBManagement();
            connect("localhost",5432,"postgres","postgres");
        }
        return instanceDB;
    }

    /**
     * Metodo che crea la connessione al server Postgres tramite il driver JDBC
     *
     * @param host host del database
     * @param port porta del database
     * @param user username per accedere al server di Postgres
     * @param password password per accedere al server di Postgres
     * @return true o false, in base all'esito dell'operazione
     *
     * @author Luca Perfetti
     */
    public static boolean connect(String host, int port, String user, String password){
        hostDB = host;
        portDB = port;
        userDB = user;
        passwordDB = password;

        try{
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection(url + nameDB, userDB, passwordDB);
            //System.out.println(connection.isValid(10));
            if(connection!=null){
                createTable();
                return true;
            }else{
                System.out.println("Connection failed");
            }
        }catch (Exception e){
            createDB();
        }
        return false;
    }

    /**
     * Metodo che permette di creare il database con le relative tabelle nel caso in cui quest'ultimo non esiste già
     *
     * @return true o false, in base all'esito dell'operazione
     *
     * @author Luca Perfetti
     */
    private static boolean createDB(){
        PreparedStatement preparedstmt;

        try{
            connection = DriverManager.getConnection(url, userDB, passwordDB);
            String query = "create database " + nameDB;
            preparedstmt = connection.prepareStatement(query);
            preparedstmt.execute();
            preparedstmt.close();
            createTable();
            return true;
        }catch(Exception e){
            System.out.println(e);
        }
        return false;
    }

    /**
     * Metodo che permette di creare le tabelle nel database nel caso in cui esse non esistono già
     *
     * @return true o false, in base all'esito dell'operazione
     *
     * @author Luca Perfetti
     */
    private static boolean createTable(){
        PreparedStatement preparedstmt;

        try{
            connection = DriverManager.getConnection(url + nameDB, userDB, passwordDB);

            String query = "create table if not exists Vaccinati("
                    + "id VARCHAR(16) PRIMARY KEY,"
                    + "nomeCentro VARCHAR(30),"
                    + "nome VARCHAR(30),"
                    + "cognome VARCHAR(30),"
                    + "codiceFiscale CHAR(16),"
                    + "dataVaccino VARCHAR(20),"
                    + "vaxTipo VARCHAR(30));"

                    + "create table if not exists CentroVaccinale("
                    + "nomeCentro VARCHAR(30) PRIMARY KEY,"
                    + "indirizzo VARCHAR(60),"
                    + "tipologia VARCHAR(20));"

                    + "create table if not exists Cittadini_Registrati("
                    + "id VARCHAR(16) PRIMARY KEY,"
                    + "email VARCHAR(30),"
                    + "username VARCHAR(30),"
                    + "password VARCHAR(30));"

                    + "create table if not exists Eventi_Avversi("
                    + "id Numeric PRIMARY KEY,"
                    + "nomeEventoAvverso VARCHAR(30),"
                    + "severita Numeric,"
                    + "note VARCHAR(256));";

            preparedstmt = connection.prepareStatement(query);
            preparedstmt.execute();
            preparedstmt.close();
            return true;
        }catch(Exception e){
                            e.printStackTrace();}
        return false;
    }
}