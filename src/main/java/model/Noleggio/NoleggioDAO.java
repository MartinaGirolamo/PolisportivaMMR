package model.Noleggio;

import model.ConPool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class NoleggioDAO {
    public NoleggioDAO(){}

    public ArrayList<Noleggio> selectAllNoleggio(){
        ArrayList<Noleggio> list = new ArrayList<>();
        try(Connection conn = ConPool.getConnection()){
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM noleggio");
            ResultSet set = ps.executeQuery();
            while (set.next()){
            Noleggio noleggio = new Noleggio();
            noleggio.setCodiceAttr(set.getInt("codiceAttr"));
            noleggio.setCodicePren(set.getInt("codicePren"));
            noleggio.setQta(set.getInt("qta"));
            list.add(noleggio);
            }
            return list;
        }catch (SQLException e){
            e.printStackTrace();
        }
        return list;
    }

    public boolean insertNoleggio(Noleggio noleggio){
            try (Connection conn = ConPool.getConnection()){
                PreparedStatement ps = conn.prepareStatement("INSERT INTO noleggio (codiceAttr, codicePren, qta) VALUES (?,?,?)");
                ps.setInt(1, noleggio.getCodiceAttr());
                ps.setInt(2, noleggio.getCodicePren());
                ps.setInt(3,noleggio.getQta());
                int ritorno=ps.executeUpdate();
                if (ritorno==2) return false;
                else return true;
            }catch (SQLException e){
                e.printStackTrace();
            }
            return  false;
    }

    public Noleggio selectNoleggioByPrenotazione(String codicePrenotazione){
        try(Connection conn = ConPool.getConnection()){
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM noleggio WHERE codicePren='"+codicePrenotazione+"';");
            ResultSet set = ps.executeQuery();
                Noleggio noleggio = new Noleggio();
                noleggio.setCodiceAttr(set.getInt("codiceAttr"));
                noleggio.setCodicePren(set.getInt("codicePren"));
                noleggio.setQta(set.getInt("qta"));
                return noleggio;

        }catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }

}
