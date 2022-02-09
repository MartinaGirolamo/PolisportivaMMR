package model.Attrezzatura;

import model.ConPool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class AttrezzaturaDAO {

    public AttrezzaturaDAO(){}


    public ArrayList<Attrezzatura> selectAllAttrezzatura(){
        ArrayList<Attrezzatura> list = new ArrayList<>();
        try(Connection conn= ConPool.getConnection()){
            PreparedStatement ps= conn.prepareStatement("SELECT * FROM attrezzatura;");
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

    public boolean insertAttrezzature(Attrezzatura attrezzatura){
        try(Connection conn=ConPool.getConnection()) {
            PreparedStatement ps= conn.prepareStatement("INSERT INTO attrezzatura ( nome, qta,tariffa,path,tipologia) VALUES (?,?,?,?,?);");
            ps.setString(1,attrezzatura.getNome());
            ps.setInt(2,attrezzatura.getQta());
            ps.setFloat(3,attrezzatura.getTariffa());
            ps.setString(4, attrezzatura.getPath());
            ps.setString(5, attrezzatura.getTipologia());

            int ritorno=ps.executeUpdate();
            if (ritorno==2) return false;
            else return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean deleteAttrezzatura(int codice){
        try(Connection conn = ConPool.getConnection()){
            PreparedStatement ps = conn.prepareStatement("DELETE FROM attrezzatura WHERE codice='"+codice+"'");
            int ritorno=ps.executeUpdate();
            if (ritorno==2) return false;
            else return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean updateQuantitaAttrezzatura(int newQta, int codiceAttrezzatura){
        try(Connection conn=ConPool.getConnection()) {
            PreparedStatement ps= conn.prepareStatement("UPDATE attrezzatura SET  ( qta=?) WHERE  (codice=?);");

            ps.setInt(1,newQta);
            ps.setInt(2,codiceAttrezzatura);

            int ritorno=ps.executeUpdate();
            if (ritorno==2) return false;
            else return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public ArrayList<Attrezzatura> selectAttrezzaturaByTipologia(String tipologia) {
        ArrayList<Attrezzatura> list = new ArrayList<>();
        try(Connection conn= ConPool.getConnection()){
            PreparedStatement ps= conn.prepareStatement("SELECT * FROM attrezzatura WHERE tipologia ='"+tipologia+"';");
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
}

