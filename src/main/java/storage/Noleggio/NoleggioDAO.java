package storage.Noleggio;

import storage.ConPool;

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
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM Noleggio");
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
                PreparedStatement ps = conn.prepareStatement("INSERT INTO Noleggio (codiceAttr, codicePren, qta) VALUES (?,?,?)");
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

    public ArrayList<Noleggio> selectNoleggioByPrenotazione(int codicePrenotazione){
        try(Connection conn = ConPool.getConnection()){
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM Noleggio WHERE codicePren='"+codicePrenotazione+"';");
            ResultSet set = ps.executeQuery();
                ArrayList<Noleggio> list = new ArrayList<>();
                while(set.next()) {
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
        return null;
    }

    public boolean deleteNoleggio(int codicePrenotazione, int codiceAttrezzatura){
        try(Connection conn = ConPool.getConnection()){
            PreparedStatement ps = conn.prepareStatement("DELETE FROM Noleggio WHERE codicePren='"+codicePrenotazione+"' AND codiceAttr='"+codiceAttrezzatura+"'");
            int ritorno=ps.executeUpdate();
            if (ritorno==2) return false;
            else return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

}
