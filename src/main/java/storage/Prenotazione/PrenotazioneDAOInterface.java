package storage.Prenotazione;

import storage.ConPool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;



public interface PrenotazioneDAOInterface {



    public ArrayList<Prenotazione> selectPrenotazioniByDataAndCampo(Date dataPrenotazione, String nomeCampo);

    public ArrayList<Prenotazione> selectAllPrenotazioni ();

    public ArrayList<Prenotazione> selectPrenotazioneByUtente (String email);

    public boolean insertPrenotazione(Prenotazione prenotazione);

    public boolean deletePrenotazione( int codice);

    public Prenotazione selectPrenotazioneByCodice(int codice);

    public ArrayList<Prenotazione> selectPrenotazioniCampo( String nomeCampo);

    }

