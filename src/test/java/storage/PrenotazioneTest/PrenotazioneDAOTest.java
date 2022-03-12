package storage.PrenotazioneTest;

import org.junit.jupiter.api.Test;
import storage.ConPool;
import storage.Prenotazione.Prenotazione;
import storage.Prenotazione.PrenotazioneDAO;

import javax.swing.plaf.nimbus.State;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import static org.springframework.test.util.AssertionErrors.assertTrue;

public class PrenotazioneDAOTest {

    PrenotazioneDAO dao = new PrenotazioneDAO();
    private String stringa1 = "INSERT INTO Campo (nome,descrizione,tariffa,numGiocatori) value ('BeachVolley','Descrizione',20,12)";
    private String stringa2 = "INSERT INTO Utente ( email,pword,  nome, cognome, is_Admin, dateN ) VALUE ('utente1@utente.it','Password1','utente','tizio',false,'1996-12-22');";
    private String stringa3 = "INSERT INTO Prenotazione(codice, oraStart,oraEnd,dateP,utente,campo, tariffaTotale ) VALUE(50,10,12,'2022-02-12','utente1@utente.it','BeachVolley',30);";
    private String stringa4 = "INSERT INTO Prenotazione(codice, oraStart,oraEnd,dateP,utente,campo, tariffaTotale ) VALUE(51,16,18,'2022-02-12','utente1@utente.it','BeachVolley',30);";

    @Test
    public void selectPrenotazioniByDataAndCampo(){
        ArrayList<Prenotazione> list,listRitorno;
        list=new ArrayList<>();

        try {
            Connection connection = ConPool.getConnection();
            PreparedStatement ps3 = connection.prepareStatement("DELETE FROM Prenotazione WHERE codice=50;"); ps3.execute();
            PreparedStatement ps4 = connection.prepareStatement("DELETE FROM Prenotazione WHERE codice=51;"); ps4.execute();
            PreparedStatement ps1 = connection.prepareStatement("DELETE FROM Campo WHERE nome='BeachVolley';"); ps1.execute();
            PreparedStatement ps2 = connection.prepareStatement("DELETE FROM Utente WHERE email='utente1@utente.it';"); ps2.execute();
            PreparedStatement preparedStatement1= connection.prepareStatement(stringa1); preparedStatement1.execute();
            PreparedStatement preparedStatement2= connection.prepareStatement(stringa2); preparedStatement2.execute();
            PreparedStatement preparedStatement3= connection.prepareStatement(stringa3); preparedStatement3.execute();
            PreparedStatement preparedStatement4= connection.prepareStatement(stringa4); preparedStatement4.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        java.sql.Date data = java.sql.Date.valueOf("2022-02-12");
        Prenotazione p1 = new Prenotazione(10,12,50,30,data,"utente1@utente.it","BeachVolley"); list.add(p1);
        Prenotazione p2 = new Prenotazione(16,18,51,30,data,"utente1@utente.it","BeachVolley"); list.add(p2);

        listRitorno = dao.selectPrenotazioniByDataAndCampo(data,"BeachVolley");

        try {
            Connection connection = ConPool.getConnection();
            PreparedStatement ps3 = connection.prepareStatement("DELETE FROM Prenotazione WHERE codice=50;"); ps3.execute();
            PreparedStatement ps4 = connection.prepareStatement("DELETE FROM Prenotazione WHERE codice=51;"); ps4.execute();
            PreparedStatement ps1 = connection.prepareStatement("DELETE FROM Campo WHERE nome='BeachVolley';"); ps1.execute();
            PreparedStatement ps2 = connection.prepareStatement("DELETE FROM Utente WHERE email='utente1@utente.it';"); ps2.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        boolean x = false;
        if(!listRitorno.isEmpty()){
            boolean test2 = (listRitorno.equals(list));
            assertTrue("La lista di prenotazioni non è restituita correttamente", test2);
        }



    }

    @Test
    public void selectPrenotazioniCampoTest(){
        ArrayList<Prenotazione> list,listRitorno;
        list=new ArrayList<>();

        try {
            Connection connection = ConPool.getConnection();
            PreparedStatement ps3 = connection.prepareStatement("DELETE FROM Prenotazione WHERE codice=50;"); ps3.execute();
            PreparedStatement ps4 = connection.prepareStatement("DELETE FROM Prenotazione WHERE codice=51;"); ps4.execute();
            PreparedStatement ps1 = connection.prepareStatement("DELETE FROM Campo WHERE nome='BeachVolley';"); ps1.execute();
            PreparedStatement ps2 = connection.prepareStatement("DELETE FROM Utente WHERE email='utente1@utente.it';"); ps2.execute();
            PreparedStatement preparedStatement1= connection.prepareStatement(stringa1); preparedStatement1.execute();
            PreparedStatement preparedStatement2= connection.prepareStatement(stringa2); preparedStatement2.execute();
            PreparedStatement preparedStatement3= connection.prepareStatement(stringa3); preparedStatement3.execute();
            PreparedStatement preparedStatement4= connection.prepareStatement(stringa4); preparedStatement4.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        java.sql.Date data = java.sql.Date.valueOf("2022-02-12");
        Prenotazione p1 = new Prenotazione(10,12,50,30,data,"utente1@utente.it","BeachVolley"); list.add(p1);
        Prenotazione p2 = new Prenotazione(16,18,51,30,data,"utente1@utente.it","BeachVolley"); list.add(p2);

        listRitorno = dao.selectPrenotazioniCampo("BeachVolley");

        try {
            Connection connection = ConPool.getConnection();
            PreparedStatement ps3 = connection.prepareStatement("DELETE FROM Prenotazione WHERE codice=50;"); ps3.execute();
            PreparedStatement ps4 = connection.prepareStatement("DELETE FROM Prenotazione WHERE codice=51;"); ps4.execute();
            PreparedStatement ps1 = connection.prepareStatement("DELETE FROM Campo WHERE nome='BeachVolley';"); ps1.execute();
            PreparedStatement ps2 = connection.prepareStatement("DELETE FROM Utente WHERE email='utente1@utente.it';"); ps2.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        boolean x = false;
        if(!listRitorno.isEmpty()){
            boolean test2 = (listRitorno.equals(list));
            assertTrue("La lista di prenotazioni non è restituita correttamente", test2);
        }

    }

    @Test
    public void selectPrenotazioniByUtenteTest(){
        ArrayList<Prenotazione> list,listRitorno;
        list=new ArrayList<>();

        try {
            Connection connection = ConPool.getConnection();
            PreparedStatement ps3 = connection.prepareStatement("DELETE FROM Prenotazione WHERE codice=50;"); ps3.execute();
            PreparedStatement ps4 = connection.prepareStatement("DELETE FROM Prenotazione WHERE codice=51;"); ps4.execute();
            PreparedStatement ps1 = connection.prepareStatement("DELETE FROM Campo WHERE nome='BeachVolley';"); ps1.execute();
            PreparedStatement ps2 = connection.prepareStatement("DELETE FROM Utente WHERE email='utente1@utente.it';"); ps2.execute();
            PreparedStatement preparedStatement1= connection.prepareStatement(stringa1); preparedStatement1.execute();
            PreparedStatement preparedStatement2= connection.prepareStatement(stringa2); preparedStatement2.execute();
            PreparedStatement preparedStatement3= connection.prepareStatement(stringa3); preparedStatement3.execute();
            PreparedStatement preparedStatement4= connection.prepareStatement(stringa4); preparedStatement4.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        java.sql.Date data = java.sql.Date.valueOf("2022-02-12");
        Prenotazione p1 = new Prenotazione(10,12,50,30,data,"utente1@utente.it","BeachVolley"); list.add(p1);
        Prenotazione p2 = new Prenotazione(16,18,51,30,data,"utente1@utente.it","BeachVolley"); list.add(p2);

        listRitorno = dao.selectPrenotazioneByUtente("utente1@utente.it");

        try {
            Connection connection = ConPool.getConnection();
            PreparedStatement ps3 = connection.prepareStatement("DELETE FROM Prenotazione WHERE codice=50;"); ps3.execute();
            PreparedStatement ps4 = connection.prepareStatement("DELETE FROM Prenotazione WHERE codice=51;"); ps4.execute();
            PreparedStatement ps1 = connection.prepareStatement("DELETE FROM Campo WHERE nome='BeachVolley';"); ps1.execute();
            PreparedStatement ps2 = connection.prepareStatement("DELETE FROM Utente WHERE email='utente1@utente.it';"); ps2.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        boolean x = false;
        if(!listRitorno.isEmpty()){
            boolean test2 = (listRitorno.equals(list));
            assertTrue("La lista di prenotazioni non è restituita correttamente", test2);
        }
    }

    @Test
    public void selectPrenotazioneByCodiceTest(){

        try {
            Connection connection = ConPool.getConnection();
            PreparedStatement ps3 = connection.prepareStatement("DELETE FROM Prenotazione WHERE codice=50;"); ps3.execute();
            PreparedStatement ps1 = connection.prepareStatement("DELETE FROM Campo WHERE nome='BeachVolley';"); ps1.execute();
            PreparedStatement ps2 = connection.prepareStatement("DELETE FROM Utente WHERE email='utente1@utente.it';"); ps2.execute();
            PreparedStatement preparedStatement1= connection.prepareStatement(stringa1); preparedStatement1.execute();
            PreparedStatement preparedStatement2= connection.prepareStatement(stringa2); preparedStatement2.execute();
            PreparedStatement preparedStatement3= connection.prepareStatement(stringa3); preparedStatement3.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        java.sql.Date data = java.sql.Date.valueOf("2022-02-12");
        Prenotazione p1 = new Prenotazione(10,12,50,30,data,"utente1@utente.it","BeachVolley");
        Prenotazione p2 = dao.selectPrenotazioneByCodice(50);

        try {
            Connection connection = ConPool.getConnection();
            PreparedStatement ps3 = connection.prepareStatement("DELETE FROM Prenotazione WHERE codice=50;"); ps3.execute();
            PreparedStatement ps4 = connection.prepareStatement("DELETE FROM Prenotazione WHERE codice=51;"); ps4.execute();
            PreparedStatement ps1 = connection.prepareStatement("DELETE FROM Campo WHERE nome='BeachVolley';"); ps1.execute();
            PreparedStatement ps2 = connection.prepareStatement("DELETE FROM Utente WHERE email='utente1@utente.it';"); ps2.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }

            boolean test2 = p1.equals(p2);
            assertTrue("La lista di prenotazioni non è restituita correttamente", test2);

    }

    @Test
    public void insertPrenotazioneTest(){
        try {
            Connection connection = ConPool.getConnection();
            PreparedStatement ps3 = connection.prepareStatement("DELETE FROM Prenotazione WHERE codice=50;"); ps3.execute();
            PreparedStatement ps1 = connection.prepareStatement("DELETE FROM Campo WHERE nome='BeachVolley';"); ps1.execute();
            PreparedStatement ps2 = connection.prepareStatement("DELETE FROM Utente WHERE email='utente1@utente.it';"); ps2.execute();
            PreparedStatement preparedStatement1= connection.prepareStatement(stringa1); preparedStatement1.execute();
            PreparedStatement preparedStatement2= connection.prepareStatement(stringa2); preparedStatement2.execute();
            PreparedStatement preparedStatement3= connection.prepareStatement(stringa3); preparedStatement3.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        java.sql.Date data = java.sql.Date.valueOf("2022-02-12");
        Prenotazione p1 = new Prenotazione(10,12,50,30,data,"utente1@utente.it","BeachVolley");
        dao.insertPrenotazione(p1);
        Prenotazione p2 = dao.selectPrenotazioneByCodice(50);

        try {
            Connection connection = ConPool.getConnection();
            PreparedStatement ps3 = connection.prepareStatement("DELETE FROM Prenotazione WHERE codice=50;"); ps3.execute();
            PreparedStatement ps4 = connection.prepareStatement("DELETE FROM Prenotazione WHERE codice=51;"); ps4.execute();
            PreparedStatement ps1 = connection.prepareStatement("DELETE FROM Campo WHERE nome='BeachVolley';"); ps1.execute();
            PreparedStatement ps2 = connection.prepareStatement("DELETE FROM Utente WHERE email='utente1@utente.it';"); ps2.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        boolean test2 = p1.equals(p2);
        assertTrue("La prenotazione non è inserita correttamente", test2);
    }

    @Test
    public void deleteUtenteTest(){
        try {
            Connection connection = ConPool.getConnection();
            PreparedStatement ps3 = connection.prepareStatement("DELETE FROM Prenotazione WHERE codice=50;"); ps3.execute();
            PreparedStatement ps1 = connection.prepareStatement("DELETE FROM Campo WHERE nome='BeachVolley';"); ps1.execute();
            PreparedStatement ps2 = connection.prepareStatement("DELETE FROM Utente WHERE email='utente1@utente.it';"); ps2.execute();
            PreparedStatement preparedStatement1= connection.prepareStatement(stringa1); preparedStatement1.execute();
            PreparedStatement preparedStatement2= connection.prepareStatement(stringa2); preparedStatement2.execute();
            PreparedStatement preparedStatement3= connection.prepareStatement(stringa3); preparedStatement3.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        java.sql.Date data = java.sql.Date.valueOf("2022-02-12");
        Prenotazione p1 = new Prenotazione(10,12,50,30,data,"utente1@utente.it","BeachVolley");
        dao.deletePrenotazione(50);
        Prenotazione p2 = dao.selectPrenotazioneByCodice(50);


        if(p2==null||p2.getEmail()==null){
        boolean test2 = true;
        assertTrue("La prenotazione non è stata eliminata correttamente", test2);}
    }

    @Test
    public void selectAllPrenotazioniTest(){
        ArrayList<Prenotazione> list,listRitorno;
        list=new ArrayList<>();

        try {
            Connection connection = ConPool.getConnection();
            PreparedStatement ps3 = connection.prepareStatement("DELETE FROM Prenotazione WHERE codice=50;"); ps3.execute();
            PreparedStatement ps4 = connection.prepareStatement("DELETE FROM Prenotazione WHERE codice=51;"); ps4.execute();
            PreparedStatement ps1 = connection.prepareStatement("DELETE FROM Campo WHERE nome='BeachVolley';"); ps1.execute();
            PreparedStatement ps2 = connection.prepareStatement("DELETE FROM Utente WHERE email='utente1@utente.it';"); ps2.execute();
            PreparedStatement preparedStatement1= connection.prepareStatement(stringa1); preparedStatement1.execute();
            PreparedStatement preparedStatement2= connection.prepareStatement(stringa2); preparedStatement2.execute();
            PreparedStatement preparedStatement3= connection.prepareStatement(stringa3); preparedStatement3.execute();
            PreparedStatement preparedStatement4= connection.prepareStatement(stringa4); preparedStatement4.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        java.sql.Date data = java.sql.Date.valueOf("2022-02-12");
        Prenotazione p1 = new Prenotazione(10,12,50,30,data,"utente1@utente.it","BeachVolley"); list.add(p1);
        Prenotazione p2 = new Prenotazione(16,18,51,30,data,"utente1@utente.it","BeachVolley"); list.add(p2);

        listRitorno = dao.selectAllPrenotazioni();

        try {
            Connection connection = ConPool.getConnection();
            PreparedStatement ps3 = connection.prepareStatement("DELETE FROM Prenotazione WHERE codice=50;"); ps3.execute();
            PreparedStatement ps4 = connection.prepareStatement("DELETE FROM Prenotazione WHERE codice=51;"); ps4.execute();
            PreparedStatement ps1 = connection.prepareStatement("DELETE FROM Campo WHERE nome='BeachVolley';"); ps1.execute();
            PreparedStatement ps2 = connection.prepareStatement("DELETE FROM Utente WHERE email='utente1@utente.it';"); ps2.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        boolean x = false;
        if(!listRitorno.isEmpty()){
            boolean test2 = (listRitorno.equals(list));
            assertTrue("La lista di prenotazioni non è restituita correttamente", test2);
        }
    }


}
