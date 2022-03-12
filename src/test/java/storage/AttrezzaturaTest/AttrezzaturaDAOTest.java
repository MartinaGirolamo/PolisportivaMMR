package storage.AttrezzaturaTest;

import org.junit.jupiter.api.Test;
import org.w3c.dom.Attr;
import storage.Attrezzatura.Attrezzatura;
import storage.Attrezzatura.AttrezzaturaDAO;
import storage.ConPool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

import static org.springframework.test.util.AssertionErrors.assertTrue;

public class AttrezzaturaDAOTest {
    private AttrezzaturaDAO dao = new AttrezzaturaDAO();
    String stringa = "INSERT INTO Campo (nome,descrizione,tariffa,numGiocatori) value ('BeachVolley','Descrizione',20,12) ";
    String stringa1 = "INSERT INTO Attrezzatura (codice,nome,qta,tariffa,path,tipologia) value (9,'Attrezzatura1',12,2,'path','BeachVolley');";
    String stringa2 = "INSERT INTO Attrezzatura (codice,nome,qta,tariffa,path,tipologia) value (10,'Attrezzatura2',12,2,'path','BeachVolley');";


    @Test
    public void selectAttrezzaturaByTipologiaTest(){
        ArrayList<Attrezzatura> list = new ArrayList<>();
        ArrayList<Attrezzatura> listRitorno;
        try {
            Connection connection = ConPool.getConnection();
            PreparedStatement ps = connection.prepareStatement("DELETE FROM Campo WHERE nome='BeachVolley'");
            ps.executeUpdate();
            PreparedStatement statement = connection.prepareStatement(stringa);
            statement.executeUpdate();
            PreparedStatement ps1 = connection.prepareStatement("DELETE FROM Attrezzatura WHERE nome='Attrezzatura1';");
            ps1.executeUpdate();
            PreparedStatement ps2 = connection.prepareStatement("DELETE FROM Attrezzatura WHERE nome='Attrezzatura2';");
            ps2.executeUpdate();
            PreparedStatement ps3 = connection.prepareStatement(stringa1);
            ps3.executeUpdate();
            PreparedStatement ps4 = connection.prepareStatement(stringa2);
            ps4.executeUpdate();

        }catch (SQLException e){
            e.printStackTrace();
        }

        for(int i=1;i<3;i++){
            int c=8;
            Attrezzatura a = new Attrezzatura();
            a.setTipologia("BeachVolley");
            a.setCodice(c+i);
            a.setTariffa(2);
            a.setQta(12);
            a.setPath("path");
            a.setNome("Attrezzatura"+i);
            list.add(a);
        }

        listRitorno= dao.selectAttrezzaturaByTipologia("BeachVolley");

        boolean test2 = listRitorno.equals(list);

        assertTrue("La lista di attrezzatura non è restituita correttamente", test2);

        try {
            Connection connection = ConPool.getConnection();
            PreparedStatement ps1 = connection.prepareStatement("DELETE FROM Attrezzatura WHERE nome='Attrezzatura1';");
            ps1.executeUpdate();
            PreparedStatement ps2 = connection.prepareStatement("DELETE FROM Attrezzatura WHERE nome='Attrezzatura2';");
            ps2.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    @Test
    public void getAttrezzaturaFromNomeTest(){
        try {
            Connection connection = ConPool.getConnection();
            PreparedStatement ps = connection.prepareStatement("DELETE FROM Campo WHERE nome='BeachVolley'");
            ps.executeUpdate();
            PreparedStatement statement = connection.prepareStatement(stringa);
            statement.executeUpdate();
            PreparedStatement ps1 = connection.prepareStatement("DELETE FROM Attrezzatura WHERE nome='Attrezzatura1';");
            ps1.executeUpdate();
            PreparedStatement ps2 = connection.prepareStatement(stringa1);
            ps2.executeUpdate();

        }catch (SQLException e){
            e.printStackTrace();
        }

        Attrezzatura a = new Attrezzatura();
        a.setTipologia("BeachVolley");
        a.setCodice(9);
        a.setTariffa(2);
        a.setQta(12);
        a.setPath("path");
        a.setNome("Attrezzatura1");

        Attrezzatura aRitorno = dao.getAttrezzaturaFromNome("Attrezzatura1");

        boolean test2 = aRitorno.equals(a);
        assertTrue("L'attrezzatura non è restituita correttamente", test2);

        try {
            Connection connection = ConPool.getConnection();
            PreparedStatement ps1 = connection.prepareStatement("DELETE FROM Attrezzatura WHERE nome='Attrezzatura1';");
            ps1.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }

    }

    @Test
    public void getAttrezzaturaByCodiceTest(){
        try {
            Connection connection = ConPool.getConnection();
            PreparedStatement ps = connection.prepareStatement("DELETE FROM Campo WHERE nome='BeachVolley'");
            ps.executeUpdate();
            PreparedStatement statement = connection.prepareStatement(stringa);
            statement.executeUpdate();
            PreparedStatement ps1 = connection.prepareStatement("DELETE FROM Attrezzatura WHERE nome='Attrezzatura1';");
            ps1.executeUpdate();
            PreparedStatement ps2 = connection.prepareStatement(stringa1);
            ps2.executeUpdate();

        }catch (SQLException e){
            e.printStackTrace();
        }

        Attrezzatura a = new Attrezzatura();
        a.setTipologia("BeachVolley");
        a.setCodice(9);
        a.setTariffa(2);
        a.setQta(12);
        a.setPath("path");
        a.setNome("Attrezzatura1");

        Attrezzatura aRitorno = dao.getAttrezzaturaByCodice(9);

        boolean test2 = aRitorno.equals(a);
        assertTrue("L'attrezzatura non è restituita correttamente", test2);

        try {
            Connection connection = ConPool.getConnection();
            PreparedStatement ps1 = connection.prepareStatement("DELETE FROM Attrezzatura WHERE nome='Attrezzatura1';");
            ps1.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }

    }
}
