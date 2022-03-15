package storage.Abbonamento;

import storage.ConPool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public interface AbbonamentoDAOInterface {


    public Abbonamento selectAbbonamentoByTipologia(String tipologia);

    public Abbonamento selectAbbonamentoByCodice(int codice);

}
