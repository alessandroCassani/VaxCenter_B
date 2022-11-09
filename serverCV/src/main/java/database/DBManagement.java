package database;

import database.UILoginToServer;
import org.postgresql.util.PSQLException;

import java.io.*;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Paths;
import java.sql.*;

/**
 * La classe DBManagement permette di creare la connessione al server Postgres, il database e le tabelle
 *
 * @author Luca Perfetti
 * @author Alessandro Cassani
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
            connect(UILoginToServer.getHostTextField(),
                    UILoginToServer.getPortTextField(),
                    UILoginToServer.getUserTextField(),
                    UILoginToServer.getPswTextField()
            );
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
            if(connection==null) {
                createTable();
                insertDataSet();
                return true;
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
            insertDataSet();
            return true;
        }catch(Exception e){
            System.out.println(e);
        }
        return false;
    }

    /**
     * Metodo che permette di creare le tabelle nel database nel caso in cui esse non esistono già
     * @return true o false, in base all'esito dell'operazione
     *
     * @author Luca Perfetti
     */
    private static boolean createTable(){
        PreparedStatement preparedstmt;

        try{
            connection = DriverManager.getConnection(url + nameDB, userDB, passwordDB);

            String query = "create table if not exists vaccinati("
                    + "id VARCHAR(16) PRIMARY KEY,"
                    + "nome_centro_vaccinale VARCHAR(50),"
                    + "nome VARCHAR(30),"
                    + "cognome VARCHAR(30),"
                    + "codice_fiscale VARCHAR(16),"
                    + "data_vaccino VARCHAR(40),"
                    + "tipo_vaccino VARCHAR(30));"

                    + "create table if not exists centri_vaccinali("
                    + "nome_centro_vaccinale VARCHAR(50) PRIMARY KEY,"
                    + "qualificatore VARCHAR(7),"
                    + "nome_via VARCHAR(30),"
                    + "civico VARCHAR(6),"
                    + "provincia CHAR(2),"
                    + "comune VARCHAR(30),"
                    + "cap INTEGER,"
                    + "tipologia VARCHAR(20));"

                    + "create table if not exists cittadini("
                    + "id VARCHAR(16) PRIMARY KEY,"
                    + "nome VARCHAR(30),"
                    + "cognome VARCHAR(30),"
                    + "codice_fiscale VARCHAR(16),"
                    + "email VARCHAR(30),"
                    + "username VARCHAR(30),"
                    + "password VARCHAR(30),"
                    + "nome_centro_vaccinale VARCHAR(30) REFERENCES centri_vaccinali);"

                    + "create table if not exists eventi_avversi("
                    + "username VARCHAR(30) PRIMARY KEY,"
                    + "mal_di_testa INTEGER,"
                    + "febbre INTEGER,"
                    + "tachicardia INTEGER,"
                    + "dolori_muscolari INTEGER,"
                    + "linfoadenopatia INTEGER,"
                    + "crisi_ipertensiva INTEGER,"
                    + "note VARCHAR(256));"

                    +"create table if not exists dataset_comuni("
                    +"comune VARCHAR(40) PRIMARY KEY,"
                    +"provincia VARCHAR(2),"
                    +"cap INTEGER,"
                    +"regione VARCHAR(21));";

            preparedstmt = connection.prepareStatement(query);
            preparedstmt.execute();
            preparedstmt.close();
            return true;
        }catch(Exception e){
            e.printStackTrace();}
        return false;}


    /**
     * il metodo permette di inserire il dataSet di comuni province cap e regione italiane
     *
     * @author Alessandro Cassani
     */
    private static void insertDataSet() {
        try {
            PreparedStatement ps;
            URL resource = DBManagement.class.getResource("/dataset/dataset_comuni");
            File dataset = null;
            String ds = "";
            assert resource != null;
            dataset = Paths.get(resource.toURI()).toFile();
            assert dataset != null;
            BufferedReader br = new BufferedReader(new FileReader(dataset));
            StringBuilder sb = new StringBuilder();
            String line = br.readLine();
            int i = 0;

            while (line != null) {
                sb.append(line);
                sb.append("\n");
                line = br.readLine();
                i++;
            }
            ds = sb.toString();
            getDB().connection.prepareStatement(ds).executeUpdate();
        }catch(Exception e){e.printStackTrace();}
    }
}