package storage.NoleggioTest;

import org.junit.jupiter.api.Test;
import storage.ConPool;
import storage.Noleggio.Noleggio;
import storage.Noleggio.NoleggioDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import static org.springframework.test.util.AssertionErrors.assertTrue;

public class NoleggioDAOTest {
    private NoleggioDAO dao = new NoleggioDAO();
    private String stringa1 = "INSERT INTO Campo (nome,descrizione,tariffa,numGiocatori) value ('BeachVolley','Descrizione',20,12)";
    private String stringa2 = "INSERT INTO Utente ( email,pword,  nome, cognome, is_Admin, dateN ) VALUE ('utente1@utente.it','Password1','utente','tizio',false,'1996-12-22');";
    private String stringa3 = "INSERT INTO Prenotazione(codice, oraStart,oraEnd,dateP,utente,campo, tariffaTotale ) VALUE(50,10,12,'2022-02-12','utente1@utente.it','BeachVolley',30);";
    private String stringa4 = "INSERT INTO Prenotazione(codice, oraStart,oraEnd,dateP,utente,campo, tariffaTotale ) VALUE(51,10,12,'2022-02-12','utente1@utente.it','BeachVolley',30);";
    private String stringa5 = "INSERT INTO Attrezzatura (codice,nome,qta,tariffa,path,tipologia) value (9,'Attrezzatura1',12,2,'path','BeachVolley');";
    private String stringa6= "INSERT INTO Noleggio(codicePren, codiceAttr,qta) VALUE (50,9,5)";
    private String stringa7= "INSERT INTO Noleggio(codicePren, codiceAttr,qta) VALUE (51,9,10)";


    @Test
    public void selectAllNoleggiTest(){
        ArrayList<Noleggio> list,listRitorno;
        list=new ArrayList<>();
        try {
            Connection connection = ConPool.getConnection();
            Statement statement = connection.createStatement();
            statement.execute("DELETE FROM Noleggio WHERE codicePren=50 AND codiceAttr=9;");
            statement.execute("DELETE FROM Noleggio WHERE codicePren=51 AND codiceAttr=9;");
            statement.execute("DELETE FROM Prenotazione WHERE codice=50");
            statement.execute("DELETE FROM Prenotazione WHERE codice=51");
            statement.execute("DELETE FROM Attrezzatura WHERE codice=9");
            statement.execute("DELETE FROM Campo WHERE nome='BeachVolley'");
            statement.execute("DELETE FROM Utente WHERE email='utente1@utente.it'");
            statement.execute(stringa1);
            statement.execute(stringa2);
            statement.execute(stringa3);
            statement.execute(stringa4);
            statement.execute(stringa5);
            statement.execute(stringa6);
            statement.execute(stringa7);

        } catch (SQLException e) {
            e.printStackTrace();
        }

        Noleggio n1 = new Noleggio();
       n1.setCodicePren(50);
       n1.setCodiceAttr(9);
       n1.setQta(5);
       list.add(n1);
        Noleggio n2 = new Noleggio();
        n2.setCodicePren(51);
        n2.setCodiceAttr(9);
        n2.setQta(10);
        list.add(n2);

        listRitorno=dao.selectAllNoleggio();

        try {
            Connection connection = ConPool.getConnection();
            Statement statement = connection.createStatement();
            statement.execute("DELETE FROM Noleggio WHERE codicePren=50 AND codiceAttr=9;");
            statement.execute("DELETE FROM Noleggio WHERE codicePren=51 AND codiceAttr=9;");
            statement.execute("DELETE FROM Prenotazione WHERE codice=50");
            statement.execute("DELETE FROM Prenotazione WHERE codice=51");
            statement.execute("DELETE FROM Attrezzatura WHERE codice=9");
            statement.execute("DELETE FROM Campo WHERE nome='BeachVolley'");
            statement.execute("DELETE FROM Utente WHERE email='utente1@utente.it'");

        } catch (SQLException e) {
            e.printStackTrace();
        }


        boolean x= false;
        if(listRitorno!=null){
            boolean test2= listRitorno.equals(list);
            assertTrue("La lista di noleggi non è restituita correttamente", test2);
        }
    }

    @Test
    public void selectNoleggioByPrenotazione(){
        ArrayList<Noleggio> list,listRitorno;
        list=new ArrayList<>();
        try {
            Connection connection = ConPool.getConnection();
            Statement statement = connection.createStatement();
            statement.execute("DELETE FROM Noleggio WHERE codicePren=50 AND codiceAttr=9;");
            statement.execute("DELETE FROM Prenotazione WHERE codice=50");
            statement.execute("DELETE FROM Attrezzatura WHERE codice=9");
            statement.execute("DELETE FROM Campo WHERE nome='BeachVolley'");
            statement.execute("DELETE FROM Utente WHERE email='utente1@utente.it'");
            statement.execute(stringa1);
            statement.execute(stringa2);
            statement.execute(stringa3);
            statement.execute(stringa5);
            statement.execute(stringa6);

        } catch (SQLException e) {
            e.printStackTrace();
        }

        Noleggio n1 = new Noleggio();
        n1.setCodicePren(50);
        n1.setCodiceAttr(9);
        n1.setQta(5);
        list.add(n1);
        listRitorno=dao.selectNoleggioByPrenotazione(50);

        try {
            Connection connection = ConPool.getConnection();
            Statement statement = connection.createStatement();
            statement.execute("DELETE FROM Noleggio WHERE codicePren=50 AND codiceAttr=9;");
            statement.execute("DELETE FROM Prenotazione WHERE codice=50");
            statement.execute("DELETE FROM Attrezzatura WHERE codice=9");
            statement.execute("DELETE FROM Campo WHERE nome='BeachVolley'");
            statement.execute("DELETE FROM Utente WHERE email='utente1@utente.it'");

        } catch (SQLException e) {
            e.printStackTrace();
        }


        boolean x= false;
        if(listRitorno!=null){
            boolean test2= listRitorno.equals(list);
            assertTrue("La lista di noleggi non è restituita correttamente", test2);
        }
    }

    @Test
    public void deletePrenotazioneTest(){
        try {
            Connection connection = ConPool.getConnection();
            Statement statement = connection.createStatement();
            statement.execute("DELETE FROM Noleggio WHERE codicePren=50 AND codiceAttr=9;");
            statement.execute("DELETE FROM Prenotazione WHERE codice=50");
            statement.execute("DELETE FROM Attrezzatura WHERE codice=9");
            statement.execute("DELETE FROM Campo WHERE nome='BeachVolley'");
            statement.execute("DELETE FROM Utente WHERE email='utente1@utente.it'");
            statement.execute(stringa1);
            statement.execute(stringa2);
            statement.execute(stringa3);
            statement.execute(stringa5);
            statement.execute(stringa6);

        } catch (SQLException e) {
            e.printStackTrace();
        }

        dao.deleteNoleggio(50,9);
        Noleggio n1 = new Noleggio();
        n1.setCodicePren(50);
        n1.setCodiceAttr(9);
        n1.setQta(5);
        ArrayList<Noleggio> list = dao.selectNoleggioByPrenotazione(50);

        boolean x= false;

        if(list==null){
            boolean test2= true;
            assertTrue("La cancellazione di noleggio non è eseguita correttamente", test2);}

    }

    @Test
    public void insertNoleggioTest(){
        ArrayList<Noleggio> list,listRitorno;
        list=new ArrayList<>();
        try {
            Connection connection = ConPool.getConnection();
            Statement statement = connection.createStatement();
            statement.execute("DELETE FROM Noleggio WHERE codicePren=50 AND codiceAttr=9;");
            statement.execute("DELETE FROM Prenotazione WHERE codice=50");
            statement.execute("DELETE FROM Attrezzatura WHERE codice=9");
            statement.execute("DELETE FROM Campo WHERE nome='BeachVolley'");
            statement.execute("DELETE FROM Utente WHERE email='utente1@utente.it'");
            statement.execute(stringa1);
            statement.execute(stringa2);
            statement.execute(stringa3);
            statement.execute(stringa5);
            statement.execute(stringa6);

        } catch (SQLException e) {
            e.printStackTrace();
        }

        Noleggio n1 = new Noleggio();
        n1.setCodicePren(50);
        n1.setCodiceAttr(9);
        n1.setQta(5);
        list.add(n1);
        dao.insertNoleggio(n1);
        listRitorno=dao.selectAllNoleggio();

        try {
            Connection connection = ConPool.getConnection();
            Statement statement = connection.createStatement();
            statement.execute("DELETE FROM Noleggio WHERE codicePren=50 AND codiceAttr=9;");
            statement.execute("DELETE FROM Prenotazione WHERE codice=50");
            statement.execute("DELETE FROM Attrezzatura WHERE codice=9");
            statement.execute("DELETE FROM Campo WHERE nome='BeachVolley'");
            statement.execute("DELETE FROM Utente WHERE email='utente1@utente.it'");

        } catch (SQLException e) {
            e.printStackTrace();
        }


        boolean x= false;
        if(listRitorno!=null && listRitorno.contains(n1)){
            boolean test2= true;
            assertTrue("La lista di noleggi non è restituita correttamente", test2);
        }
    }

}

