package model.Acquisto;

import model.ConPool;
import model.Utente.Utente;

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

    public boolean acquistoGiaPresente(Acquisto acquisto){
        Acquisto acquistoRitorno = new Acquisto();
        try(Connection conn = ConPool.getConnection()){

            PreparedStatement ps= conn.prepareStatement("SELECT * FROM acquisto WHERE utente = '"+acquisto.getUtente()+"' AND codiceAbb='"+acquisto.getCodiceAbb()+"' AND dataAcquisto='"+acquisto.getDataAcquisto()+"';");
            ResultSet set = ps.executeQuery();
            while(set.next()){
                acquistoRitorno.setDataAcquisto(set.getDate("dataAcquisto"));
                acquistoRitorno.setUtente(set.getString("utente"));
                acquistoRitorno.setCodiceAbb(set.getInt("codiceAbb"));
            }

            if(acquistoRitorno.getUtente()!=null) {System.out.println("Acquisto già presente in db"); return true;}
            else return false;


        }catch(SQLException e){
            System.out.println("non presente in Database");
            return false;
        }
    }


}
