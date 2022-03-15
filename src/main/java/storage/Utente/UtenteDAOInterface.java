package storage.Utente;

import storage.ConPool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public interface UtenteDAOInterface {


    public ArrayList<Utente> selectAllUtenti();

    public Utente selectUtenteByEmailPassword(String email,String password);

    public boolean insertUtente(Utente utente);

    public boolean deleteUtente(String email);


    public boolean updateUtente(Utente utente);

    public boolean checkPassword(Utente u, String vecchiaPassword);

    public boolean controllaEmail(Utente utente);

    public boolean isAdmin (String email);



}
