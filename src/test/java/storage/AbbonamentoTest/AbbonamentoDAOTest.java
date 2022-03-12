package storage.AbbonamentoTest;

import org.junit.jupiter.api.Test;
import storage.Abbonamento.Abbonamento;
import storage.Abbonamento.AbbonamentoDAO;
import storage.ConPool;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import static org.springframework.test.util.AssertionErrors.assertTrue;

public class AbbonamentoDAOTest {

    private AbbonamentoDAO dao = new AbbonamentoDAO();

    private String stringa= "INSERT INTO Abbonamento (tariffa,tipologia) value (30,'BeachVolley');";

    @Test
    public void selectAbbonamentoByTipologia(){
        Abbonamento abbonamento = new Abbonamento();
        Abbonamento abbonamentoRitorno;
        try {
            Connection connection = ConPool.getConnection();
            PreparedStatement ps1 = connection.prepareStatement("DELETE FROM Abbonamento WHERE tipologia='BeachVolley';");
            ps1.executeUpdate();
            PreparedStatement ps2 = connection.prepareStatement(stringa);
            ps2.executeUpdate();


        }catch (SQLException e){
            e.printStackTrace();
        }

        abbonamento.setTipologia("BeachVolley");
        abbonamento.setTariffa(30);
        abbonamento.setCodice(5);

        abbonamentoRitorno=dao.selectAbbonamentoByTipologia("BeachVolley");
        boolean test2 = abbonamento.equals(abbonamentoRitorno);

        assertTrue("L'abbonamento non è restituito correttamente", test2);

        try {
            Connection connection = ConPool.getConnection();
            PreparedStatement ps1 = connection.prepareStatement("DELETE FROM Abbonamento WHERE tipologia='BeachVolley';");
            ps1.execute();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    @Test
    public void selectAbbonamentoByCodice(){
        Abbonamento abbonamento = new Abbonamento();
        Abbonamento abbonamentoRitorno;
        try {
            Connection connection = ConPool.getConnection();
            PreparedStatement ps1 = connection.prepareStatement("DELETE FROM Abbonamento WHERE tipologia='BeachVolley';");
            ps1.executeUpdate();
            PreparedStatement ps2 = connection.prepareStatement(stringa);
            ps2.executeUpdate();


        }catch (SQLException e){
            e.printStackTrace();
        }

        abbonamento=dao.selectAbbonamentoByTipologia("BeachVolley");

        abbonamentoRitorno=dao.selectAbbonamentoByCodice(abbonamento.getCodice());
        boolean test2 = abbonamento.equals(abbonamentoRitorno);

        assertTrue("L'abbonamento non è restituito correttamente", test2);

        try {
            Connection connection = ConPool.getConnection();
            PreparedStatement ps1 = connection.prepareStatement("DELETE FROM Abbonamento WHERE tipologia='BeachVolley';");
            ps1.execute();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

}
