package storage.Campo;

import storage.ConPool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CampoDAO implements CampoDAOInterface {

    public CampoDAO() {}

    public Campo selectCampoByNome(String nome) {
        Campo c = new Campo();
        try (Connection conn = ConPool.getConnection()) {
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM Campo WHERE nome ='" + nome + "';");
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

}
