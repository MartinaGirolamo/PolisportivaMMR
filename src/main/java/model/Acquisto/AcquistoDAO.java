package model.Acquisto;

import model.ConPool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class AcquistoDAO {

    public AcquistoDAO(){}

    public ArrayList<Acquisto> selectAcquistoByUtente(String email){
        ArrayList<Acquisto> list = new ArrayList<>();
        try(Connection conn = ConPool.getConnection()){
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM Acquisto WHERE utente='"+email+"'");
            ResultSet set = ps.executeQuery();
            while(set.next()){
                Acquisto acquisto = new Acquisto();
                acquisto.setDataAcquisto(set.getDate("dataAcquisto"));
                acquisto.setUtente(set.getString("utente"));
                acquisto.setCodiceAbb(set.getInt("codiceAbb"));
                list.add(acquisto);
            }

        }catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public boolean insertAcquisto (Acquisto acquisto){
        try(Connection conn=ConPool.getConnection()) {
            PreparedStatement ps= conn.prepareStatement("INSERT INTO acquisto ( codiceAbb,utente, dataAcquisto ) VALUES (?,?,?);");
            ps.setInt(1,acquisto.getCodiceAbb());
            ps.setString(2, acquisto.getUtente());
            ps.setDate(3,acquisto.getDataAcquisto());

            int ritorno=ps.executeUpdate();
            if (ritorno==2) return false;
            else return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

}
