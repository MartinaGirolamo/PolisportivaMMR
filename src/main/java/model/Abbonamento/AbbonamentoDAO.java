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
            abbonamento.setCodice(set.getInt("codice"));
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


    public boolean deleteAbbonamento(int codice){
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

    public boolean insertAbbonamento(Abbonamento abbonamento){
        try(Connection conn=ConPool.getConnection()) {
            PreparedStatement ps= conn.prepareStatement("INSERT INTO abbonamento ( mesi, tariffa, tipologia ) VALUES (?,?,?);");
            ps.setInt(1,abbonamento.getMesi());
            ps.setFloat(2,abbonamento.getTariffa());
            ps.setString(3, abbonamento.getTipologia());
            int ritorno=ps.executeUpdate();
            if (ritorno==2) return false;
            else return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public Abbonamento selectAbbonamentoByTipologiaAndMesi(String tipologia, int mesi){
        Abbonamento a = new Abbonamento();
        try(Connection conn = ConPool.getConnection()){
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM Abbonamento WHERE tipologia='"+tipologia+"' AND mesi= '"+mesi+"';");
            ResultSet set = ps.executeQuery();
            while (set.next()){
                a.setCodice(set.getInt("codice"));
                a.setTipologia(set.getString("tipologia"));
                a.setTariffa(set.getFloat("tariffa"));
                a.setMesi(set.getInt("mesi"));

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return a;
    }




}
