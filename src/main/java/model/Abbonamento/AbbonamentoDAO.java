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
            list.add(abbonamento);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }


    public boolean deleteAbbonamento(int codice){
        try(Connection conn = ConPool.getConnection()){
            PreparedStatement ps = conn.prepareStatement("DELETE FROM Abbonamento WHERE codice='"+codice+"'");
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
            PreparedStatement ps= conn.prepareStatement("INSERT INTO Abbonamento ( tariffa, tipologia ) VALUES (?,?);");
            ps.setFloat(1,abbonamento.getTariffa());
            ps.setString(2, abbonamento.getTipologia());
            int ritorno=ps.executeUpdate();
            if (ritorno==2) return false;
            else return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public Abbonamento selectAbbonamentoByTipologia(String tipologia){
        Abbonamento a = new Abbonamento();
        try(Connection conn = ConPool.getConnection()){
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM Abbonamento WHERE tipologia='"+tipologia+"';");
            ResultSet set = ps.executeQuery();
            while (set.next()){
                a.setCodice(set.getInt("codice"));
                a.setTipologia(set.getString("tipologia"));
                a.setTariffa(set.getFloat("tariffa"));

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return a;
    }

    public Abbonamento selectAbbonamentoByCodice(int codice){
        Abbonamento abbonamento = new Abbonamento();
        try(Connection conn = ConPool.getConnection()){
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM Abbonamento WHERE codice="+codice+";");
            ResultSet set = ps.executeQuery();
            while (set.next()){

                abbonamento.setCodice(set.getInt("codice"));
                abbonamento.setTipologia(set.getString("tipologia"));
                abbonamento.setTariffa(set.getFloat("tariffa"));


            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return abbonamento;
    }




}
