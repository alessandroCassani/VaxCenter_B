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
    final String nameDB = "vaxcenter";

    /**
     * Rappresenta l'istanza corrente del databse
     */
    static DBManagement instanceDB = null;

    /**
     * Indirizzo di rete del dbms
     */
    String hostDB = "localhost";

    /**
     * Porta del server Postgres
     */
    int portDB = 1099;

    /**
     * Username dell'utente per accedere al server Postgres
     */
    String userDB;

    /**
     * Password dell'utente per accedere al server Postgres
     */
    String passwordDB;

    /**
     * Oggetto per gestire la connessione
     */
    Connection connection = null;

    /**
     * Url per la connessione al server Postgres
     */
    String url = "jdbc:postgresql://"+ hostDB + ":" + portDB + "/";


    /**
     * Metodo che ritorna l'istanza del database
     *
     * @author Luca Perfetti
     */
    public static DBManagement getDB(){
        if(instanceDB == null){
            instanceDB = new DBManagement();
        }
        return instanceDB;
    }

    /**
     * Metodo che crea la connessione al server Postgres tramite il driver JDBC
     *
     * @param hostDB host del database
     * @param portDB porta del database
     * @param userDB username per accedere al server di Postgres
     * @param passwordDB password per accedere al server di Postgres
     * @return true o false, in base all'esito dell'operazione
     *
     * @author Luca Perfetti
     */
    public boolean connect(String hostDB, int portDB, String userDB, String passwordDB){
        this.hostDB = hostDB;
        this.portDB = portDB;
        this.userDB = userDB;
        this.passwordDB = passwordDB;

        try{
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection(url + nameDB, userDB, passwordDB);

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
    public boolean createDB(){
        PreparedStatement preparedstmt;

        try{
            connection = DriverManager.getConnection(url, userDB, passwordDB);
            String query = "create database " + nameDB;
            preparedstmt = connection.prepareStatement(query);
            preparedstmt.execute();
            preparedstmt.close();
            connection.close();
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
    public boolean createTable(){
        PreparedStatement preparedstmt;

        try{
            connection = DriverManager.getConnection(url + nameDB, userDB, passwordDB);

            String query = "create table if not exists Vaccinati("
                    + "id Numeric PRIMARY KEY,"
                    + "nome VARCHAR(30),"
                    + "cognome VARCHAR(30),"
                    + "CodiceFiscale CHAR(16),"
                    + "dataVaccino DATE,"
                    + "vaxTipo VARCHAR(30));"

                    + "create table if not exists CentroVaccinale("
                    + "nomeCentro VARCHAR(30) PRIMARY KEY,"
                    + "qualificatore VARCHAR(20),"
                    + "nomeVia CHAR(2),"
                    + "numeroCivico VARCHAR(30),"
                    + "comune VARCHAR(30),"
                    + "sigla CHAR(2),"
                    + "cap VARCHAR(5),"
                    + "tipologia VARCHAR(20));"

                    + "create table if not exists Cittadini_Registrati("
                    + "id VARCHAR(16) PRIMARY KEY,"
                    + "nome VARCHAR(30),"
                    + "cognome VARCHAR(30),"
                    + "CodiceFiscale CHAR(16),"
                    + "email VARCHAR(30),"
                    + "username VARCHAR(30),"
                    + "password VARCHAR(30));"

                    + "create table if not exists Eventi_Avversi("
                    + "id Numeric PRIMARY KEY,"
                    + "mal_di_testa INTEGER,"
                    + "febbre INTEGER,"
                    + "dolori_muscolari INTEGER,"
                    + "linfoadenompatia INTEGER,"
                    + "crisi_ipertensiva INTEGER);"

                    + "create table if not exists Severita("
                    + "id Numeric PRIMARY KEY,"
                    + "mal_di_testa INTEGER,"
                    + "febbre INTEGER,"
                    + "dolori_muscolari INTEGER,"
                    + "linfoadenompatia INTEGER,"
                    + "crisi_ipertensiva INTEGER,"
                    + "note VARCHAR(256));";

            preparedstmt = connection.prepareStatement(query);
            preparedstmt.execute();
            preparedstmt.close();
            connection.close();
            return true;
        }catch(Exception e){
            System.out.println(e);
        }
        return false;
    }
}