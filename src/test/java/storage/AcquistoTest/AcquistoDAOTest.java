package storage.AcquistoTest;

import org.junit.jupiter.api.Test;
import storage.Acquisto.Acquisto;
import storage.Acquisto.AcquistoDAO;
import storage.ConPool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

import static org.springframework.test.util.AssertionErrors.assertTrue;

public class AcquistoDAOTest {
    private AcquistoDAO dao = new AcquistoDAO();
    private String stringa1 = "INSERT INTO Abbonamento (codice,tariffa,tipologia) value (50,30,'BeachVolley');";
    private String stringa2 = "INSERT INTO Utente ( email,pword,  nome, cognome, is_Admin, dateN ) VALUES ('utente1@utente.it','Password1','utente','tizio',false,'1996-12-22');";
    private String stringa3 = "INSERT INTO Acquisto (utente, codiceAbb, dataAcquisto, nMesi) value ('utente1@utente.it',50,'2021-12-12',1);";
    private String stringa4 = "INSERT INTO Acquisto (utente, codiceAbb, dataAcquisto, nMesi) value ('utente1@utente.it',50,'2022-03-21',2);";

    @Test
    public void selectAllAcquistiTest(){
        ArrayList<Acquisto> list,listRitorno;
        list=new ArrayList<>();
        try {
            Connection connection = ConPool.getConnection();
            PreparedStatement ps3 = connection.prepareStatement("DELETE FROM Acquisto WHERE codiceAbb=50 AND utente='utente1@utente.it' AND dataAcquisto='2021-12-12';"); ps3.executeUpdate();
            PreparedStatement ps4 = connection.prepareStatement("DELETE FROM Acquisto WHERE codiceAbb=50 AND utente='utente1@utente.it' AND dataAcquisto='2022-03-21';"); ps4.executeUpdate();
            PreparedStatement ps1 = connection.prepareStatement("DELETE FROM Abbonamento WHERE codice=50;"); ps1.executeUpdate();
            PreparedStatement ps2 = connection.prepareStatement("DELETE FROM Utente WHERE email='utente1@utente.it';"); ps2.executeUpdate();

            PreparedStatement preparedStatement1= connection.prepareStatement(stringa1);
            PreparedStatement preparedStatement2= connection.prepareStatement(stringa2);
            PreparedStatement preparedStatement3= connection.prepareStatement(stringa3);
            PreparedStatement preparedStatement4= connection.prepareStatement(stringa4);

        } catch (SQLException e) {
            e.printStackTrace();
        }

        java.sql.Date data1 = java.sql.Date.valueOf("2021-12-12");
        java.sql.Date data2 = java.sql.Date.valueOf("2022-03-21");

        Acquisto a1 = new Acquisto("utente1@utente.it",50,1,data1); list.add(a1);
        Acquisto a2 = new Acquisto("utente1@utente.it",50,2,data2);list.add(a2);
        listRitorno=dao.selectAllAcquisti();

        if(!list.isEmpty()&& !listRitorno.isEmpty()){
        boolean test2 = list.equals(listRitorno);
        assertTrue("La lista di acquisti non è restituita correttamente", test2);}

        try {
            Connection connection = ConPool.getConnection();
            PreparedStatement ps3 = connection.prepareStatement("DELETE FROM Acquisto WHERE codiceAbb=50 AND utente='utente1@utente.it' AND dataAcquisto='2021-12-12';"); ps3.executeUpdate();
            PreparedStatement ps4 = connection.prepareStatement("DELETE FROM Acquisto WHERE codiceAbb=50 AND utente='utente1@utente.it' AND dataAcquisto='2022-03-21';"); ps4.executeUpdate();
            PreparedStatement ps1 = connection.prepareStatement("DELETE FROM Abbonamento WHERE codice=50;"); ps1.executeUpdate();
            PreparedStatement ps2 = connection.prepareStatement("DELETE FROM Utente WHERE email='utente1@utente.it';"); ps2.executeUpdate();

        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    @Test
    public void selectAcquistoByUtente(){
        ArrayList<Acquisto> list,listRitorno;
        list=new ArrayList<>();
        try {
            Connection connection = ConPool.getConnection();
            PreparedStatement ps3 = connection.prepareStatement("DELETE FROM Acquisto WHERE codiceAbb=50 AND utente='utente1@utente.it' AND dataAcquisto='2021-12-12';"); ps3.executeUpdate();
            PreparedStatement ps4 = connection.prepareStatement("DELETE FROM Acquisto WHERE codiceAbb=50 AND utente='utente1@utente.it' AND dataAcquisto='2022-03-21';"); ps4.executeUpdate();
            PreparedStatement ps1 = connection.prepareStatement("DELETE FROM Abbonamento WHERE codice=50;"); ps1.executeUpdate();
            PreparedStatement ps2 = connection.prepareStatement("DELETE FROM Utente WHERE email='utente1@utente.it';"); ps2.executeUpdate();

            PreparedStatement preparedStatement1= connection.prepareStatement(stringa1);
            PreparedStatement preparedStatement2= connection.prepareStatement(stringa2);
            PreparedStatement preparedStatement3= connection.prepareStatement(stringa3);
            PreparedStatement preparedStatement4= connection.prepareStatement(stringa4);

        } catch (SQLException e) {
            e.printStackTrace();
        }

        java.sql.Date data1 = java.sql.Date.valueOf("2021-12-12");
        java.sql.Date data2 = java.sql.Date.valueOf("2022-03-21");

        Acquisto a1 = new Acquisto("utente1@utente.it",50,1,data1); list.add(a1);
        Acquisto a2 = new Acquisto("utente1@utente.it",50,2,data2);list.add(a2);
        listRitorno=dao.selectAcquistoByUtente("utente1@utente.it");

        if(!list.isEmpty()&& !listRitorno.isEmpty()){
            boolean test2 = list.equals(listRitorno);
            assertTrue("La lista di acquisti non è restituita correttamente", test2);}

        try {
            Connection connection = ConPool.getConnection();
            PreparedStatement ps3 = connection.prepareStatement("DELETE FROM Acquisto WHERE codiceAbb=50 AND utente='utente1@utente.it' AND dataAcquisto='2021-12-12';"); ps3.executeUpdate();
            PreparedStatement ps4 = connection.prepareStatement("DELETE FROM Acquisto WHERE codiceAbb=50 AND utente='utente1@utente.it' AND dataAcquisto='2022-03-21';"); ps4.executeUpdate();
            PreparedStatement ps1 = connection.prepareStatement("DELETE FROM Abbonamento WHERE codice=50;"); ps1.executeUpdate();
            PreparedStatement ps2 = connection.prepareStatement("DELETE FROM Utente WHERE email='utente1@utente.it';"); ps2.executeUpdate();

        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    @Test
    public void insertAcquistoTest(){
        ArrayList<Acquisto> listRitorno;
        try {
            Connection connection = ConPool.getConnection();
            PreparedStatement ps3 = connection.prepareStatement("DELETE FROM Acquisto WHERE codiceAbb=50 AND utente='utente1@utente.it' AND dataAcquisto='2021-12-12';"); ps3.executeUpdate();
            PreparedStatement ps1 = connection.prepareStatement("DELETE FROM Abbonamento WHERE codice=50;"); ps1.executeUpdate();
            PreparedStatement ps2 = connection.prepareStatement("DELETE FROM Utente WHERE email='utente1@utente.it';"); ps2.executeUpdate();

            PreparedStatement preparedStatement1= connection.prepareStatement(stringa1);
            PreparedStatement preparedStatement2= connection.prepareStatement(stringa2);
            PreparedStatement preparedStatement3= connection.prepareStatement(stringa3);

        } catch (SQLException e) {
            e.printStackTrace();
        }

        java.sql.Date data1 = java.sql.Date.valueOf("2021-12-12");


        Acquisto a1 = new Acquisto("utente1@utente.it",50,1,data1);
        listRitorno=dao.selectAcquistoByUtente("utente1@utente.it");

        if(!listRitorno.isEmpty()){
            boolean test2 = listRitorno.contains(a1);
            assertTrue("L'inserimento non è stato eseguito correttamente", test2);}

        try {
            Connection connection = ConPool.getConnection();
            PreparedStatement ps3 = connection.prepareStatement("DELETE FROM Acquisto WHERE codiceAbb=50 AND utente='utente1@utente.it' AND dataAcquisto='2021-12-12';"); ps3.executeUpdate();
            PreparedStatement ps1 = connection.prepareStatement("DELETE FROM Abbonamento WHERE codice=50;"); ps1.executeUpdate();
            PreparedStatement ps2 = connection.prepareStatement("DELETE FROM Utente WHERE email='utente1@utente.it';"); ps2.executeUpdate();

        }catch (SQLException e){
            e.printStackTrace();
        }
    }


    @Test
    public void deleteAcquistoTest(){
        ArrayList<Acquisto> listRitorno;
        try {
            Connection connection = ConPool.getConnection();
            PreparedStatement ps3 = connection.prepareStatement("DELETE FROM Acquisto WHERE codiceAbb=50 AND utente='utente1@utente.it' AND dataAcquisto='2021-12-12';"); ps3.executeUpdate();
            PreparedStatement ps1 = connection.prepareStatement("DELETE FROM Abbonamento WHERE codice=50;"); ps1.executeUpdate();
            PreparedStatement ps2 = connection.prepareStatement("DELETE FROM Utente WHERE email='utente1@utente.it';"); ps2.executeUpdate();

            PreparedStatement preparedStatement1= connection.prepareStatement(stringa1);
            PreparedStatement preparedStatement2= connection.prepareStatement(stringa2);
            PreparedStatement preparedStatement3= connection.prepareStatement(stringa3);

            PreparedStatement ps4 = connection.prepareStatement("DELETE FROM Acquisto WHERE codiceAbb=50 AND utente='utente1@utente.it' AND dataAcquisto='2021-12-12';"); ps4.executeUpdate();
            PreparedStatement ps5 = connection.prepareStatement("DELETE FROM Abbonamento WHERE codice=50;"); ps5.executeUpdate();
            PreparedStatement ps6 = connection.prepareStatement("DELETE FROM Utente WHERE email='utente1@utente.it';"); ps6.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        java.sql.Date data1 = java.sql.Date.valueOf("2021-12-12");


        Acquisto a1 = new Acquisto("utente1@utente.it",50,1,data1);
        dao.deleteAcquisto(50,"utente1@utente.it");
        listRitorno= dao.selectAcquistoByUtente("utente1@utente.it");

        if(!listRitorno.isEmpty()){
            boolean test2 = listRitorno.contains(a1);
            assertTrue("L'inserimento non è stato eseguito correttamente", test2);}


    }




}
