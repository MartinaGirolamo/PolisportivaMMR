package storage.UtenteTest;
/*
@author Girolamo Martina*/
import org.junit.jupiter.api.Test;
import storage.ConPool;
import storage.Utente.Utente;
import storage.Utente.UtenteDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

import static org.springframework.test.util.AssertionErrors.assertTrue;


public class UtenteDAOTest {
    private UtenteDAO utenteDAO = new UtenteDAO();

    private String stringa1 = "INSERT INTO Utente ( email,pword,  nome, cognome, is_Admin, dateN ) VALUES ('utente1@utente.it','Password1','utente','tizio',false,'1996-12-22');";
    private String stringa2 = "INSERT INTO Utente ( email,pword,  nome, cognome, is_Admin, dateN ) VALUES ('utente2@utente.it','Password2','utente2','tizio',false,'1996-12-22');";



    @Test
    public void selectAllUtentiTest() throws SQLException {
        ArrayList<Utente> listaUtenti = new ArrayList<Utente>();
        ArrayList<Utente> listaUtentiTest;
        Utente utente1, utente2;
        utente1 = new Utente();
        utente2 = new Utente();
        try {
            Connection connection = ConPool.getConnection();
            PreparedStatement ps1 = connection.prepareStatement("DELETE FROM Utente WHERE email='utente1@utente.it';");
            ps1.executeUpdate();
            PreparedStatement ps2 = connection.prepareStatement("DELETE FROM Utente WHERE email='utente2@utente.it';");
            ps2.executeUpdate();
            PreparedStatement ps3 = connection.prepareStatement(stringa1);
            ps3.executeUpdate();
            PreparedStatement ps4 = connection.prepareStatement(stringa2);
            ps4.executeUpdate();

        }catch (SQLException e){
            e.printStackTrace();
        }

        utente1.setEmail("utente1@utente.it");
        utente1.setCognome("tizio");
        utente1.setNome("utente");
        utente1.setDateN("1996-12-22");
        utente1.setIs_Admin(false);
        utente1.setPsword("Password1");

        utente2.setEmail("utente2@utente.it");
        utente2.setCognome("tizio");
        utente2.setNome("utente2");
        utente2.setDateN("1996-12-22");
        utente2.setIs_Admin(false);
        utente2.setPsword("Password2");
        listaUtenti.add(utente1);
        listaUtenti.add(utente2);

        listaUtentiTest=utenteDAO.selectAllUtenti();
        boolean test2 = true;
        for(Utente x: listaUtenti)
        {

            test2 = false;

            if(listaUtentiTest.contains(x))
            {
                test2 = true;
            }
            else
            {
                break;
            }

        }

        assertTrue("La lista di utenti non è restituita correttamente", test2);

        try {
            Connection connection = ConPool.getConnection();
            PreparedStatement ps1 = connection.prepareStatement("DELETE FROM Utente WHERE email='utente1@utente.it';");
            ps1.execute();
            PreparedStatement ps2 = connection.prepareStatement("DELETE FROM Utente WHERE email='utente2@utente.it';");
            ps2.execute();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    @Test
    public void selectUtenteByEmailPasswordTest() throws SQLException {

        Utente utente1, utenteTest;
        utente1 = new Utente();
        utenteTest = new Utente();
        try {
            Connection connection = ConPool.getConnection();
            PreparedStatement ps1 = connection.prepareStatement("DELETE FROM Utente WHERE email='utente1@utente.it';");
            ps1.executeUpdate();
            PreparedStatement ps2 = connection.prepareStatement(stringa1);
            ps2.executeUpdate();


        }catch (SQLException e){
            e.printStackTrace();
        }

        utente1.setEmail("utente1@utente.it");
        utente1.setCognome("tizio");
        utente1.setNome("utente");
        utente1.setDateN("1996-12-22");
        utente1.setIs_Admin(false);
        utente1.setPsword("Password1");



        utenteTest=utenteDAO.selectUtenteByEmailPassword("utente1@utente.it","Password1");
        boolean test2 = utenteTest.equals(utente1);

        assertTrue("L'utente non è restituito correttamente", test2);

        try {
            Connection connection = ConPool.getConnection();
            PreparedStatement ps1 = connection.prepareStatement("DELETE FROM Utente WHERE email='utente1@utente.it';");
            ps1.execute();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    @Test
    public void insertUtenteTest(){
        Utente utente1, utenteTest;
        utente1 = new Utente();
        utenteTest = new Utente();
        try {
            Connection connection = ConPool.getConnection();
            PreparedStatement ps1 = connection.prepareStatement("DELETE FROM Utente WHERE email='utente1@utente.it';");
            ps1.executeUpdate();
            PreparedStatement ps2 = connection.prepareStatement(stringa1);
            ps2.executeUpdate();


        }catch (SQLException e){
            e.printStackTrace();
        }

        utente1.setEmail("utente1@utente.it");
        utente1.setCognome("tizio");
        utente1.setNome("utente");
        utente1.setDateN("1996-12-22");
        utente1.setIs_Admin(false);
        utente1.setPsword("Password1");



        utenteTest=utenteDAO.selectUtenteByEmailPassword("utente1@utente.it","Password1");
        boolean test2 = utenteTest.equals(utente1);

        assertTrue("L'utente non è stato inserito  correttamente", test2);

        try {
            Connection connection = ConPool.getConnection();
            PreparedStatement ps1 = connection.prepareStatement("DELETE FROM Utente WHERE email='utente1@utente.it';");
            ps1.execute();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    @Test
    public void deleteUtenteTest() throws SQLException {
        Utente utente;
        try {
            Connection connection = ConPool.getConnection();
            PreparedStatement ps2 = connection.prepareStatement(stringa1);
            ps2.executeUpdate();
            PreparedStatement ps1 = connection.prepareStatement("DELETE FROM Utente WHERE email='utente1@utente.it';");
            ps1.executeUpdate();


        }catch (SQLException e){
            e.printStackTrace();
        }

        boolean test2;
        utente = utenteDAO.selectUtenteByEmailPassword("utente1@utente.it","Password1");
        if(utente == null || utente.getEmail()==null){
            test2= true;
        }
        else {test2=false;}

        assertTrue("L'utente non è stato eliminato  correttamente", test2);

    }

    @Test
    public void updateUtenteTest(){
        Utente utente1, utenteTest;
        try {
            Connection connection = ConPool.getConnection();
            PreparedStatement ps2 = connection.prepareStatement(stringa1);
            ps2.executeUpdate();
            PreparedStatement ps1 = connection.prepareStatement("UPDATE Utente SET pword='NuovaPassword1'WHERE email='utente1@utente.it';");
            ps1.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }

        utente1=new Utente();
        utente1.setEmail("utente1@utente.it");
        utente1.setCognome("tizio");
        utente1.setNome("utente");
        utente1.setDateN("1996-12-22");
        utente1.setIs_Admin(false);
        utente1.setPsword("NuovaPassword1");

        boolean test2;

        utenteTest = utenteDAO.selectUtenteByEmailPassword("utente1@utente.it","NuovaPassword1");
        if(utente1.equals(utenteTest)){
            test2= true;
        }
        else {test2=false;}

        assertTrue("L'utente non è stato modificato correttamente", test2);
        try {
            Connection connection = ConPool.getConnection();
            PreparedStatement ps1 = connection.prepareStatement("DELETE FROM Utente WHERE email='utente1@utente.it';");
            ps1.execute();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    @Test
   public void controllaEmailTest(){
       {

           Utente utente1 = new Utente();
           try {
               Connection connection = ConPool.getConnection();
               PreparedStatement ps1 = connection.prepareStatement("DELETE FROM Utente WHERE email='utente1@utente.it';");
               ps1.executeUpdate();
               PreparedStatement ps2 = connection.prepareStatement(stringa1);
               ps2.executeUpdate();


           }catch (SQLException e){
               e.printStackTrace();
           }

           utente1.setEmail("utente1@utente.it");
           utente1.setCognome("tizio");
           utente1.setNome("utente");
           utente1.setDateN("1996-12-22");
           utente1.setIs_Admin(false);
           utente1.setPsword("Password1");


            boolean test2=false;
           if(utenteDAO.controllaEmail(utente1)){
               test2=true;
           }
           else test2=false;
           assertTrue("L'utente non è restituito correttamente", test2);

           try {
               Connection connection = ConPool.getConnection();
               PreparedStatement ps1 = connection.prepareStatement("DELETE FROM Utente WHERE email='utente1@utente.it';");
               ps1.execute();
           }catch (SQLException e){
               e.printStackTrace();
           }

       }

   }
   @Test
   public void isAdminTest(){

       try {
           Connection connection = ConPool.getConnection();
           PreparedStatement ps1 = connection.prepareStatement("DELETE FROM Utente WHERE email='utenteAdmin@utente.it';");
           ps1.executeUpdate();
           PreparedStatement ps2 = connection.prepareStatement("INSERT INTO Utente ( email,pword,  nome, cognome, is_Admin, dateN ) VALUES ('utenteAdmin@utente.it','Password1','utente','tizio',true,'1996-12-22');");
           ps2.executeUpdate();


       }catch (SQLException e){
           e.printStackTrace();
       }

       boolean test2 = utenteDAO.isAdmin("utenteAdmin@utente.it");

       assertTrue("Il controllo non è eseguito correttamente", test2);

       try {
           Connection connection = ConPool.getConnection();
           PreparedStatement ps1 = connection.prepareStatement("DELETE FROM Utente WHERE email='utenteAdmin@utente.it';");
           ps1.execute();
       }catch (SQLException e){
           e.printStackTrace();
       }
    }






}
