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

    public ResultSet getTables(DatabaseMetaData dbmd){
        try {
            return dbmd.getTables(null, null, null, null);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public ResultSet getColums(String tableName, DatabaseMetaData dbmd){
        try {
            return dbmd.getColumns(null, null, tableName, null);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }

    }
    public void leggTilProgram(Connection conn,String navn){
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement("INSERT INTO program(navn) VALUES(?)");
            ps.setString(1, navn);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
    public void leggTilGruppe(Connection conn,String muskelgruppe, String tilhører){
        try {
            if ((tilhører != null) && !checkIfInDB(conn, "gruppe", "muskelgruppe", tilhører)) {
                PreparedStatement ps = conn.prepareStatement("INSERT INTO gruppe(muskelgruppe,Gruppe_GruppeID) VALUES(?,?)");
                ps.setString(1, tilhører);
                ps.setNull(2, java.sql.Types.INTEGER);//ikke ferdig
                ps.executeUpdate();
            }
            if (!checkIfInDB(conn, "gruppe", "muskelgruppe", muskelgruppe)) {
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

    public ArrayList<String> getProgramNames() throws SQLException {
        PreparedStatement ps = conn.prepareStatement("select Navn from Program");
        ArrayList<String> programNames = new ArrayList<>();
        ResultSet res = ps.executeQuery();

        while(res.next()){
            programNames.add(res.getString("Navn"));
        }
        return programNames;
    }

    public ArrayList<String> getExerciseNames() throws SQLException {
        PreparedStatement ps = conn.prepareStatement("select Navn from Øvelse");
        ArrayList<String> exerciseNames = new ArrayList<>();
        ResultSet res = ps.executeQuery();

        while(res.next()){
            exerciseNames.add(res.getString("Navn"));
        }
        return exerciseNames;
    }

    public ArrayList<String> getMuscleGroupNames() throws SQLException {
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

    public boolean checkIfInDB(Connection conn,String table, String column, String tuplevalue){
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement("SELECT * FROM "+table+" WHERE "+ column+"=?");
            ps.setString(1, tuplevalue);
            ResultSet rs = ps.executeQuery();
            // Return if we got results
            return rs.next();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

}