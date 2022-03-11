package storage.Attrezzatura;

import storage.ConPool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class AttrezzaturaDAO {

    public AttrezzaturaDAO(){}

    public ArrayList<Attrezzatura> selectAttrezzaturaByTipologia(String tipologia) {
        ArrayList<Attrezzatura> list = new ArrayList<>();
        try(Connection conn= ConPool.getConnection()){
            PreparedStatement ps= conn.prepareStatement("SELECT * FROM Attrezzatura WHERE tipologia ='"+tipologia+"';");
            ResultSet set = ps.executeQuery();
            while(set.next()){
                Attrezzatura attrezzatura = new Attrezzatura();
                attrezzatura.setCodice(set.getInt("codice"));
                attrezzatura.setNome(set.getString("nome"));
                attrezzatura.setQta(set.getInt("qta"));
                attrezzatura.setTariffa(set.getFloat("tariffa"));
                attrezzatura.setPath(set.getString("path"));
                attrezzatura.setTipologia(set.getString("tipologia"));
                list.add(attrezzatura);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public Attrezzatura getAttrezzaturaFromNome(String nome){
        Attrezzatura attrezzatura = new Attrezzatura();
        try(Connection conn= ConPool.getConnection()){
            PreparedStatement ps= conn.prepareStatement("SELECT * FROM Attrezzatura WHERE nome ='"+nome+"';");
            ResultSet set = ps.executeQuery();

            while(set.next()){

                attrezzatura.setCodice(set.getInt("codice"));
                attrezzatura.setNome(set.getString("nome"));
                attrezzatura.setQta(set.getInt("qta"));
                attrezzatura.setTariffa(set.getFloat("tariffa"));
                attrezzatura.setPath(set.getString("path"));
                attrezzatura.setTipologia(set.getString("tipologia"));

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
       return attrezzatura;
    }

    public Attrezzatura getAttrezzaturaByCodice(int codice){
        Attrezzatura attrezzatura = new Attrezzatura();
        try(Connection conn= ConPool.getConnection()){
            PreparedStatement ps= conn.prepareStatement("SELECT * FROM Attrezzatura WHERE codice ="+codice+";");
            ResultSet set = ps.executeQuery();

            while(set.next()){

                attrezzatura.setCodice(set.getInt("codice"));
                attrezzatura.setNome(set.getString("nome"));
                attrezzatura.setQta(set.getInt("qta"));
                attrezzatura.setTariffa(set.getFloat("tariffa"));
                attrezzatura.setPath(set.getString("path"));
                attrezzatura.setTipologia(set.getString("tipologia"));

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return attrezzatura;
    }
}

