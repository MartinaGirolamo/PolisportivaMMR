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
                attrezzatura.setCodice(set.getString("codice"));
                attrezzatura.setNome(set.getString("nome"));
                attrezzatura.setQta(set.getInt("qta"));
                attrezzatura.setTariffa(set.getFloat("tariffa"));
                list.add(attrezzatura);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public boolean insertAttrezzature(Attrezzatura attrezzatura){
        try(Connection conn=ConPool.getConnection()) {
            PreparedStatement ps= conn.prepareStatement("INSERT INTO attrezzatura ( codice, nome, qta,tariffa) VALUES (?,?,?,?);");
            ps.setString(1,attrezzatura.getCodice());
            ps.setString(2,attrezzatura.getNome());
            ps.setInt(3,attrezzatura.getQta());
            ps.setFloat(4,attrezzatura.getTariffa());

            int ritorno=ps.executeUpdate();
            if (ritorno==2) return false;
            else return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean deleteAttrezzatura(String codice){
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

    public boolean updateQuantitaAttrezzatura(int newQta, String codiceAttrezzatura){
        try(Connection conn=ConPool.getConnection()) {
            PreparedStatement ps= conn.prepareStatement("UPDATE attrezzatura SET  ( qta=?) WHERE  (codice=?);");

            ps.setInt(1,newQta);
            ps.setString(2,codiceAttrezzatura);

            int ritorno=ps.executeUpdate();
            if (ritorno==2) return false;
            else return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

}

