package model.Acquisto;

import model.ConPool;
import model.Utente.Utente;

import java.sql.*;
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
                acquisto.setnMesi(set.getInt("nMesi"));
                list.add(acquisto);
            }

        }catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public boolean insertAcquisto (Acquisto acquisto){
        try(Connection conn=ConPool.getConnection()) {
            PreparedStatement ps= conn.prepareStatement("INSERT INTO acquisto ( codiceAbb,utente, dataAcquisto,nMesi ) VALUES (?,?,?,?);");
            ps.setInt(1,acquisto.getCodiceAbb());
            ps.setString(2, acquisto.getUtente());
            ps.setDate(3,acquisto.getDataAcquisto());
            ps.setInt(4,acquisto.getnMesi());

            int ritorno=ps.executeUpdate();
            if (ritorno==2) return false;
            else return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean acquistoGiaPresente(Acquisto a){
        ArrayList<Acquisto> list = new ArrayList<>();
        try(Connection conn = ConPool.getConnection()){

            PreparedStatement ps= conn.prepareStatement("SELECT * FROM acquisto WHERE dataAcquisto BETWEEN '"+a.getDataAcquisto()+"' AND adddate('"+a.getDataAcquisto()+"',INTERVAL "+a.getnMesi()+" MONTH) AND utente='"+a.getUtente()+"' AND codiceAbb="+a.getCodiceAbb()+";");
            ResultSet set = ps.executeQuery();
            while(set.next()){
                Acquisto acquistoRitorno = new Acquisto();
                acquistoRitorno.setDataAcquisto(set.getDate("dataAcquisto"));
                acquistoRitorno.setUtente(set.getString("utente"));
                acquistoRitorno.setCodiceAbb(set.getInt("codiceAbb"));
                acquistoRitorno.setnMesi(set.getInt("nMesi"));
                list.add(acquistoRitorno);
            }

        }catch(SQLException e){
            System.out.println("non presente in Database");
            return false;
        }
        if(list.size()==0) return false;
        else return true;
    }




}
