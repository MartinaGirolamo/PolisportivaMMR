package storage.Campo;

import storage.ConPool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public interface CampoDAOInterface {

    public Campo selectCampoByNome(String nome);

}
