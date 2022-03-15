package storage.Noleggio;

import storage.ConPool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public interface NoleggioDAOInterface {


    ArrayList<Noleggio> selectAllNoleggio();

    public boolean insertNoleggio(Noleggio noleggio);


    public ArrayList<Noleggio> selectNoleggioByPrenotazione(int codicePrenotazione);

    public boolean deleteNoleggio(int codicePrenotazione, int codiceAttrezzatura);

}
