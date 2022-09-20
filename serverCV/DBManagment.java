package serverCV;

import java.sql.*;

/**
 * La classe DBManagment permette di creare la connessione al server Postgres, il database e le tabelle
 *
 * @author Luca Perfetti
 */

public class DBManagment {
    /**
     * Nome del database
     */
    String nameDB = "vaxcenter";

    /**
     * Rappresenta l'istanza corrente del databse
     */
    static DBManagment instanceDB = null;

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
     * Metodo che ritorna l'istanza del database
     */
    public static DBManagment getDB(){
        if(instanceDB == null){
            instanceDB = new DBManagment();
        }
        return instanceDB;
    }

    /**
     * Metodo che crea la connessine al server Postgres tramite il driver JDBC
     */
    public Connection connect(String hostDB, int portDB; String userDB, String passwordDB){
        this.hostDB = hostDB;
        this.portDB = portDB;
        this.userDB = userDB;
        this.passwordDB = passwordDB;

        try{
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection("jdbc:postgresql://"+ hostDB + ":" + portDB + "/" + nameDB, userDB, passwordDB);

            if(connection!=null){
                System.out.println("Connection established");
                createTable();
            }else{
                System.out.println("Connection failed");
            }
        }catch (Exception e){
            createDB();
        }
        return connection;
    }

    /**
     * Metodo che permette di creare il database con le relative tabelle nel caso in cui quest'ultimo non esiste già
     */
    public void createDB(){
        PreparedStatement preparedstmt;

        try{
            connection = DriverManager.getConnection("jdbc:postgresql://" + hostDB + ":" + portDB + "/", userDB, passwordDB);
            String query = "create database " + nameDB;
            preparedstmt = connection.prepareStatement(query);
            preparedstmt.execute();
            preparedstmt.close();
            connection.close();
            System.out.println("Database created");
            createTable();
        }catch(Exception e){
            System.out.println(e);
        }
    }

    /**
     * Metodo che permette di creare le tabelle nel database nel caso in cui esse non esistono già
     */
    public void createTable(){
        PreparedStatement preparedstmt;

        try{
            connection = DriverManager.getConnection("jdbc:postgresql://" + hostDB + ":" + portDB + "/" + nameDB, userDB, passwordDB);

            String query = "create table if not exists Vaccinati("
                    + "id Numeric PRIMARY KEY,"
                    + "nomeCentro VARCHAR(30),"
                    + "nome VARCHAR(30),"
                    + "cognome VARCHAR(30),"
                    + "codiceFiscale CHAR(16),"
                    + "dataVaccino DATE,"
                    + "vaxTipo VARCHAR(30)"
                    + "isReg BOOLEAN);"

                    + "create table if not exists CentroVaccinale("
                    + "nomeCentro VARCHAR(30) PRIMARY KEY,"
                    + "qualificatore VARCHAR(20),"
                    + "nomeVia CHAR(2),"
                    + "numeroCivico VARCHAR(30),"
                    + "comune VARCHAR(30),"
                    + "sigla CHAR(2),"
                    + "cap VARCHAR(5),"
                    + "tipologia VARCHAR(20)"
                    + "numeroSegnalazioni Numeric,"
                    + "avgSeverita Numeric);"

                    + "create table if not exists Cittadini_Registrati("
                    + "id Numeric PRIMARY KEY,"
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
            connection.close();
            System.out.println("Table created");
        }catch(Exception e){
            System.out.println(e);
        }
    }
}