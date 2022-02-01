package model.Abbonamento;

import model.ConPool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class AbbonamentoDAO {

    public AbbonamentoDAO(){}

    public ArrayList<Abbonamento> selectAllAbbonamenti(){
        ArrayList<Abbonamento> list = new ArrayList<>();
        try(Connection conn = ConPool.getConnection()){
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM Abbonamento");
            ResultSet set = ps.executeQuery();
            while (set.next()){
            Abbonamento abbonamento = new Abbonamento();
            abbonamento.setCodice(set.getString("codice"));
            abbonamento.setTipologia(set.getString("tipologia"));
            abbonamento.setTariffa(set.getFloat("tariffa"));
            abbonamento.setMesi(set.getInt("mesi"));
            list.add(abbonamento);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }


    public boolean deleteAbbonamento(String codice){
        try(Connection conn = ConPool.getConnection()){
            PreparedStatement ps = conn.prepareStatement("DELETE FROM abbonamento WHERE codice='"+codice+"'");
            int ritorno=ps.executeUpdate();
            if (ritorno==2) return false;
            else return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }




}
