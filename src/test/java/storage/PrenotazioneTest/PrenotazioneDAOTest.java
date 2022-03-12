package storage.PrenotazioneTest;

import org.junit.jupiter.api.Test;
import storage.ConPool;
import storage.Prenotazione.Prenotazione;
import storage.Prenotazione.PrenotazioneDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

import static org.springframework.test.util.AssertionErrors.assertTrue;

public class PrenotazioneDAOTest {

    PrenotazioneDAO dao = new PrenotazioneDAO();
    private String stringa1 = "INSERT INTO Campo (nome,descrizione,tariffa,numGiocatori) value ('BeachVolley','Descrizione',20,12)";
    private String stringa2 = "INSERT INTO Utente ( email,pword,  nome, cognome, is_Admin, dateN ) VALUE ('utente1@utente.it','Password1','utente','tizio',false,'1996-12-22');";
    private String stringa3 = "INSERT INTO Prenotazione(codice, oraStart,oraEnd,dateP,utente,campo, tariffaTotale ) VALUE(50,10,12,'2022-02-12','utente1@utente.it','BeachVolley',30),";
    private String stringa4 = "INSERT INTO Prenotazione(codice, oraStart,oraEnd,dateP,utente,campo, tariffaTotale ) VALUE(51,16,18,'2022-02-12','utente1@utente.it','BeachVolley',30),";

    @Test
    public void selectPrenotazioniByDataAndCampo(){
        ArrayList<Prenotazione> list,listRitorno;
        list=new ArrayList<>();

        try {
            Connection connection = ConPool.getConnection();
            PreparedStatement ps3 = connection.prepareStatement("DELETE FROM Prenotazione WHERE codice=50;"); ps3.executeUpdate();
            PreparedStatement ps4 = connection.prepareStatement("DELETE FROM Acquisto WHERE codice=51;"); ps4.executeUpdate();
            PreparedStatement ps1 = connection.prepareStatement("DELETE FROM Campo WHERE nome='BeachVolley';"); ps1.executeUpdate();
            PreparedStatement ps2 = connection.prepareStatement("DELETE FROM Utente WHERE email='utente1@utente.it';"); ps2.executeUpdate();

            PreparedStatement preparedStatement1= connection.prepareStatement(stringa1);
            PreparedStatement preparedStatement2= connection.prepareStatement(stringa2);
            PreparedStatement preparedStatement3= connection.prepareStatement(stringa3);
            PreparedStatement preparedStatement4= connection.prepareStatement(stringa4);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        java.sql.Date data = java.sql.Date.valueOf("2022-02-12");
        Prenotazione p1 = new Prenotazione(10,12,50,30,data,"utente1@utente.it","BeachVolley"); list.add(p1);
        Prenotazione p2 = new Prenotazione(16,18,50,30,data,"utente1@utente.it","BeachVolley"); list.add(p1);

        listRitorno = dao.selectPrenotazioniByDataAndCampo(data,"BeachVolley");
        if(!list.isEmpty()){
            boolean test2 = list.equals(listRitorno);
            assertTrue("La lista di prenotazioni non è restituita correttamente", test2);
        }

        try {
            Connection connection = ConPool.getConnection();
            PreparedStatement ps3 = connection.prepareStatement("DELETE FROM Prenotazione WHERE codice=50;"); ps3.executeUpdate();
            PreparedStatement ps4 = connection.prepareStatement("DELETE FROM Acquisto WHERE codice=51;"); ps4.executeUpdate();
            PreparedStatement ps1 = connection.prepareStatement("DELETE FROM Campo WHERE nome='BeachVolley';"); ps1.executeUpdate();
            PreparedStatement ps2 = connection.prepareStatement("DELETE FROM Utente WHERE email='utente1@utente.it';"); ps2.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

}
