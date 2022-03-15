package storage.Abbonamento;

import storage.ConPool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class AbbonamentoDAO implements AbbonamentoDAOInterface {

    public AbbonamentoDAO(){}


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
