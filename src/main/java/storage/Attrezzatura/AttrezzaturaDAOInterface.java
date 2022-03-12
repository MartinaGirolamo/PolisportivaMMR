package storage.Attrezzatura;

import storage.ConPool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public interface AttrezzaturaDAOInterface {


    public ArrayList<Attrezzatura> selectAttrezzaturaByTipologia(String tipologia);

    public Attrezzatura getAttrezzaturaFromNome(String nome);

    public Attrezzatura getAttrezzaturaByCodice(int codice);
}

