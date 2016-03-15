package db;
import com.mysql.jdbc.SQLError;
import javafx.application.Application;

import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.IllegalFormatCodePointException;
import java.util.Scanner;

public class DBHandler {

    private static Connection conn;
    public DBHandler(){
        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost/treningbase?autoReconnect=true&useSSL=false", System.getenv("user"),System.getenv("password"));
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }


    public void leggTilØkt(String formål, double varighet, Date date, String notat, String programNavn){
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT ProgramID FROM Program WHERE Navn= "+'"'+programNavn+'"');
            rs.next();
            int programID  = rs.getInt("ProgramID");
            PreparedStatement ps = conn.prepareStatement("INSERT INTO økt(Formål,Varighet,Dato,Notat,ProgramID) VALUES(?,?,?,?,?)");
            float Varighet = (float) varighet;
            ps.setString(1, formål);
            ps.setFloat(2, Varighet);
            ps.setDate(3, date);
            ps.setString(4, notat);
            ps.setInt(5, programID);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
    //konverter en String til java.sql.Date
    public java.sql.Date stringToDateConverter(String string){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        java.util.Date parsed = null;
        try {
            parsed = sdf.parse(string);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        java.sql.Date data = new java.sql.Date(parsed.getTime());
        return data;
    }


    public void leggTilProgram(String navn){
        try {
            if(!checkIfInDB("program","navn",navn)){
                PreparedStatement ps = conn.prepareStatement("INSERT INTO program(navn) VALUES(?)");
                ps.setString(1, navn);
                ps.executeUpdate();
            }
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    //øvelse må finnes i database før metode under kan kjøres.
    public boolean setUpProgramØvelse(String programNavn, ArrayList<String> øvelsernavn){
        // Legger til et nytt program med sett med øvelser
        try {
            leggTilProgram(programNavn);
            PreparedStatement ps = conn.prepareStatement("SELECT ProgramID From program Where navn=?");
            ps.setString(1, programNavn);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                int programID = rs.getInt("ProgramID");
                for (String øvelsenavn : øvelsernavn) {
                    leggTilProgramØvelse(programID, øvelsenavn);
                }
            }
            return true;
        }catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }
    public void leggTilProgramØvelse(int programID, String øvelseNavn){
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement("Insert INTO programØvelse VALUES(?,?)");
            ps.setInt(1, programID);
            ps.setString(2, øvelseNavn);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void leggTilGruppe(String muskelgruppe, String tilhører){
        try {
            if ((tilhører != null) && !checkIfInDB("gruppe", "muskelgruppe", tilhører)) {
                PreparedStatement ps = conn.prepareStatement("INSERT INTO gruppe(muskelgruppe,Gruppe_GruppeID) VALUES(?,?)");
                ps.setString(1, tilhører);
                ps.setNull(2, java.sql.Types.INTEGER);//ikke ferdig
                ps.executeUpdate();
            }
            if (!checkIfInDB("gruppe", "muskelgruppe", muskelgruppe)) {
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery("Select GruppeID FROM gruppe WHERE muskelgruppe=" + '"' + tilhører + '"');
                int tilhørergruppe;
                if (rs.next()) {
                    tilhørergruppe = rs.getInt("GruppeID");
                    PreparedStatement ps = conn.prepareStatement("INSERT INTO gruppe(muskelgruppe, Gruppe_GruppeID) VALUES(?,?)");
                    ps.setString(1, muskelgruppe);
                    ps.setInt(2, tilhørergruppe);
                    ps.executeUpdate();
                } else {
                    PreparedStatement ps = conn.prepareStatement("INSERT INTO gruppe(muskelgruppe, Gruppe_GruppeID) VALUES(?,?)");
                    ps.setString(1, muskelgruppe);
                    ps.setNull(2, java.sql.Types.INTEGER);
                    ps.executeUpdate();
                }
            } else {
                System.out.println("finnes i databasen");
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public ArrayList<String> getProgramNames(){

        try{
            PreparedStatement ps = conn.prepareStatement("select Navn from Program");
            ArrayList<String> programNames = new ArrayList<>();
            ResultSet res = ps.executeQuery();

            while(res.next()){
                programNames.add(res.getString("Navn"));
            }
            return programNames;
        }catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public ArrayList<String> getExerciseNames(){
        try{
        PreparedStatement ps = conn.prepareStatement("select Navn from Øvelse");
        ArrayList<String> exerciseNames = new ArrayList<>();
        ResultSet res = ps.executeQuery();

        while(res.next()){
            exerciseNames.add(res.getString("Navn"));
        }
        return exerciseNames;
        }catch (Exception e){}
        return null;
    }

    public ArrayList<String> getSessionNames(){
        try{
            PreparedStatement ps = conn.prepareStatement("select ØktID, Dato from Økt");
            ArrayList<String> sessionNames = new ArrayList<>();
            ResultSet res = ps.executeQuery();
            while(res.next()){
                sessionNames.add(res.getInt("ØktID")+ ". "+ res.getDate("Dato").toString());
            }
            return sessionNames;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }


    //må ha gruppe_gruppeID ellers så blir det en FK Error
    public boolean leggTilØvelse(String navnØvelse, String navnGruppe){
        try {
            if (!checkIfInDB("øvelse", "navn", navnØvelse)) {
                PreparedStatement ps = conn.prepareStatement("SELECT GruppeID FROM gruppe WHERE muskelgruppe =?");
                ps.setString(1, navnGruppe);
                ResultSet rs = ps.executeQuery();
                if (rs.next()) {
                    int gruppeid = rs.getInt("GruppeID");
                    Statement stmt = conn.createStatement();
                    stmt.executeUpdate("INSERT INTO øvelse VALUES(" + '"' + navnØvelse + '"' + ", " + '"' + gruppeid + '"' + ")");
                }
                return true;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }


    public ArrayList<String> getMuscleGroupNames(){
        try{
        PreparedStatement ps = conn.prepareStatement("select MuskelGruppe from Gruppe");
        ArrayList<String> muscleGroupNames = new ArrayList<>();
        ResultSet res = ps.executeQuery();

        while(res.next()){
            muscleGroupNames.add(res.getString("MuskelGruppe"));
        }
        return muscleGroupNames;

        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }


    public static ResultSet getAllRowsFromTable(Connection conn,String table) throws SQLException{
        Statement stmt = conn.createStatement();
        return stmt.executeQuery("SELECT * FROM "+table);
    }

    public boolean checkIfInDB(String table, String column, String tuplevalue){

        try {
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM "+table+" WHERE "+ column+"=?");
            ps.setString(1, tuplevalue);
            ResultSet rs = ps.executeQuery();
            // Return if we got results
            return rs.next();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public ArrayList<String> getExercisesByProgramID(int programID){
        ArrayList<String> exerciseNames = new ArrayList<>();
        try{

            PreparedStatement ps = conn.prepareStatement("select Øvelse_Navn from programøvelse where Program_ProgramID = ?");
            ps.setInt(1, programID);
            ResultSet res = ps.executeQuery();
            while(res.next()){
                exerciseNames.add(res.getString("Øvelse_Navn"));
            }

        }catch (Exception e){
            System.err.println("Could not get Exercises by program ID!");
        }
        return exerciseNames;
    }

    //greia er at denne returnerer en arrayliste med arrayer
    public ArrayList<String[]> getØktData(int øktID){
        try {
            PreparedStatement ps = conn.prepareStatement("SELECT repetisjoner,sett,vekt,øvelse_navn FROM økt"
                    + " INNER JOIN utførelse ON økt.øktID=utførelse.økt_øktID WHERE øktID=? ");
            ps.setInt(1,øktID);
            ArrayList<String[]> listWithArrays = new ArrayList<String[]>();
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                String[] array= new String[4];
                array[0]=rs.getString("øvelse_navn");
                array[1]=Integer.toString(rs.getInt("sett"));
                array[2]=Integer.toString(rs.getInt("repetisjoner"));
                array[3]=Integer.toString(rs.getInt("vekt"));
                listWithArrays.add(array);
            }
            return listWithArrays;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public ArrayList<Integer> latestSession(){
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT ØktID , ProgramID FROM økt WHERE(ØktID=(SELECT max(ØktID) FROM økt))");
            ArrayList<Integer> liste = new ArrayList<Integer>();
            if(rs.next()){
                liste.add(rs.getInt("ØktID"));
                liste.add(rs.getInt("ProgramID"));
            }
            return liste;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }

    }
}