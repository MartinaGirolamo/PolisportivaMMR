package storage.CampoTest;

import org.junit.jupiter.api.Test;
import storage.Campo.Campo;
import storage.Campo.CampoDAO;
import storage.ConPool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import static org.springframework.test.util.AssertionErrors.assertTrue;

public class CampoDaoTest {
     private CampoDAO dao = new CampoDAO();
    private String stringa= "INSERT INTO Campo (nome,descrizione,tariffa,numGiocatori) value ('BeachVolley','Descrizione',20,12)";

    @Test
    public void selectCampoByNomeTest(){
        Campo c1,cRitorno;
        c1=new Campo();
        try{
            Connection conn = ConPool.getConnection();
            PreparedStatement ps1 = conn.prepareStatement("DELETE FROM Campo WHERE nome='BeachVolley'");
            ps1.executeUpdate();
            PreparedStatement ps2 = conn.prepareStatement(stringa);
            ps2.executeUpdate();

        }catch (SQLException e){e.printStackTrace();}

        c1.setNome("BeachVolley");
        c1.setDescrizione("Descrizione");
        c1.setTariffa(20);
        c1.setNumGiocatori(12);

        cRitorno = dao.selectCampoByNome("BeachVolley");

        boolean test2 = c1.equals(cRitorno);
        assertTrue("Il campo non è restituito correttamente", test2);

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

}
