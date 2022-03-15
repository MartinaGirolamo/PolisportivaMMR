package storage.Acquisto;

import storage.ConPool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public interface AcquistoDAOInterface  {


    public ArrayList<Acquisto> selectAllAcquisti();

    public ArrayList<Acquisto> selectAcquistoByUtente(String email);

    public boolean insertAcquisto (Acquisto acquisto);

    public boolean deleteAcquisto(int codice,String utente);

    public boolean acquistoGiaPresente(Acquisto a);






}
