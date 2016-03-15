package db;
import java.sql.*;
import java.util.ArrayList;
import java.util.Scanner;

public class DBHandler {

    private static Connection conn;
    public DBHandler(){
        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost/treningbase?autoReconnect=true&useSSL=false","root","example");
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public static ResultSet getTables(DatabaseMetaData dbmd) throws SQLException{
        return dbmd.getTables(null, null, null, null);
    }
    public static ResultSet getColums(String tableName, DatabaseMetaData dbmd) throws SQLException{
        return dbmd.getColumns(null, null, tableName, null);
    }
    public static void leggTilProgram(Connection conn,String navn) throws SQLException{
        PreparedStatement ps = conn.prepareStatement("INSERT INTO program(navn) VALUES(?)");
        ps.setString(1, navn);
        ps.executeUpdate();
    }
    public static void leggTilGruppe(Connection conn,String muskelgruppe, String tilhører) throws SQLException{
        if((tilhører != null)&& !checkIfInDB(conn,"gruppe","muskelgruppe",tilhører)){
            PreparedStatement ps = conn.prepareStatement("INSERT INTO gruppe(muskelgruppe,Gruppe_GruppeID) VALUES(?,?)");
            ps.setString(1, tilhører);
            ps.setNull(2, java.sql.Types.INTEGER);//ikke ferdig
            ps.executeUpdate();
        }
        if(!checkIfInDB(conn,"gruppe","muskelgruppe",muskelgruppe)){
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("Select GruppeID FROM gruppe WHERE muskelgruppe="+'"'+tilhører+'"');
            int tilhørergruppe;
            if(rs.next()){
                tilhørergruppe = rs.getInt("GruppeID");
                PreparedStatement ps = conn.prepareStatement("INSERT INTO gruppe(muskelgruppe, Gruppe_GruppeID) VALUES(?,?)");
                ps.setString(1, muskelgruppe);
                ps.setInt(2, tilhørergruppe);
                ps.executeUpdate();
            }
            else{
                PreparedStatement ps = conn.prepareStatement("INSERT INTO gruppe(muskelgruppe, Gruppe_GruppeID) VALUES(?,?)");
                ps.setString(1, muskelgruppe);
                ps.setNull(2, java.sql.Types.INTEGER);
                ps.executeUpdate();
            }
        }
        else{
            System.out.println("finnes i databasen");
        }
    }

    public static ArrayList<String> getProgramNames() throws SQLException {
        PreparedStatement ps = conn.prepareStatement("select Navn from Program");
        ArrayList<String> programNames = new ArrayList<>();
        ResultSet res = ps.executeQuery();

        while(res.next()){
            programNames.add(res.getString("Navn"));
        }
        return programNames;
    }

    public static ArrayList<String> getExerciseNames() throws SQLException {
        PreparedStatement ps = conn.prepareStatement("select Navn from Øvelse");
        ArrayList<String> exerciseNames = new ArrayList<>();
        ResultSet res = ps.executeQuery();

        while(res.next()){
            exerciseNames.add(res.getString("Navn"));
        }
        return exerciseNames;
    }

    public static ArrayList<String> getMuscleGroupNames() throws SQLException {
        PreparedStatement ps = conn.prepareStatement("select MuskelGruppe from Gruppe");
        ArrayList<String> muscleGroupNames = new ArrayList<>();
        ResultSet res = ps.executeQuery();

        while(res.next()){
            muscleGroupNames.add(res.getString("MuskelGruppe"));
        }
        return muscleGroupNames;
    }


    public static ResultSet getAllRowsFromTable(Connection conn,String table) throws SQLException{
        Statement stmt = conn.createStatement();
        return stmt.executeQuery("SELECT * FROM "+table);

    }
    public static boolean checkIfInDB(Connection conn,String table, String column, String tuplevalue) throws SQLException{
        PreparedStatement ps = conn.prepareStatement("SELECT * FROM "+table+" WHERE "+ column+"=?");
        ps.setString(1, tuplevalue);
        ResultSet rs = ps.executeQuery();
        if (rs.next()){
            return true;
        }
        else return false;
    }
    public static void main(String[] args) {
        DBHandler dbh = new DBHandler();
        ArrayList<String> list = null;
        try {
            list = getProgramNames();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        for (String s: list) {
            System.out.println(s);
        }
    }
}