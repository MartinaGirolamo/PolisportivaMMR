package model.Campo;

import model.ConPool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CampoDAO {

    public CampoDAO() {
    }

    public ArrayList<Campo> selectALlCampi() {
        ArrayList<Campo> list = new ArrayList<>();
        try (Connection conn = ConPool.getConnection()) {
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM Campo ");
            ResultSet set = ps.executeQuery();
            while (set.next()) {
                Campo campo = new Campo();
                campo.setDescrizione(set.getString("descrizione"));
                campo.setNome(set.getString("nome"));
                campo.setTariffa(set.getFloat("tariffa"));
                campo.setNumGiocatori(set.getInt("numGiocatori"));
                list.add(campo);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public Campo selectCampoByNome(String nome) {
        Campo c = new Campo();
        try (Connection conn = ConPool.getConnection()) {
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM campo WHERE nome ='" + nome + "';");
            ResultSet set = ps.executeQuery();
            while(set.next()){
            c.setNome(set.getString("nome"));
            c.setTariffa(set.getFloat("tariffa"));
            c.setNumGiocatori(set.getInt("numGiocatori"));
            c.setDescrizione(set.getString("descrizione"));
            System.out.println("Stampa in CAMPODAO= "+c.getNome()+" "+c.getTariffa());
            }
        } catch (SQLException e) {
            e.printStackTrace();

        }
        return c;
    }

    public boolean insertCampo(Campo campo){
        try (Connection conn = ConPool.getConnection()) {
            PreparedStatement ps = conn.prepareStatement("INSERT into campo (nome, descrizione, tariffa, numGiocatori) VALUES (?,?,?,?)");

            ps.setString(1,campo.getNome());
            ps.setString(2,campo.getDescrizione());
            ps.setFloat(3,campo.getTariffa());
            ps.setInt(4,campo.getNumGiocatori());

            int ritorno=ps.executeUpdate();
            if (ritorno==2) return false;
            else return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
