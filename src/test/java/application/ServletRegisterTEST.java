package application;


import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import storage.Utente.Utente;
import storage.Utente.UtenteDAO;
import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;
import javax.servlet.ServletException;
import javax.sql.rowset.serial.SerialException;
import javax.swing.text.Document;
import java.io.IOException;
import java.sql.SQLException;

public class ServletRegisterTEST {
    UtenteDAO dao = Mockito.mock(UtenteDAO.class);

    @Test
    public void successoTest() throws SQLException, IOException, ServletException {
        MockHttpServletRequest request = new MockHttpServletRequest();
        request.addHeader("referer","header");

        request.setParameter("nome","Mario");
        request.setParameter("cognome","Rossi");
        request.setParameter("email","mariorossi@gmail.com");
        request.setParameter("psw","Password123*");
        request.setParameter("dateN","2000-01-01");

        MockHttpServletResponse response = new MockHttpServletResponse();

        Utente utente = new Utente();
        utente.setNome("Mario");
        utente.setCognome("Rossi");
        utente.setEmail("mariorossi@gmail.com");
        utente.setPsword("Password123*");
        utente.setDateN("2000-01-01");
        utente.setIs_Admin(false);

        Mockito.when(dao.insertUtente(utente)).thenReturn(true);

        ServletRegister controller = new ServletRegister(dao);

        controller.registrazioneUtente(request,response);
        utente.setPsword("");
        Utente utenteSession = (Utente) request.getSession().getAttribute("utente");

        if(utente!=null && utenteSession!=null){
        assertTrue("L'utente non è registrato correttamente", utente.equals(utenteSession));}
    }

    @Test
    public void registrazioneEmailPresenteTest() throws IOException, SQLException, ServletException {
        MockHttpServletRequest request = new MockHttpServletRequest();
        request.addHeader("referer","header");

        request.setParameter("nome","Mario");
        request.setParameter("cognome","Rossi");
        request.setParameter("email","emailerrata");
        request.setParameter("psw","Password123*");
        request.setParameter("dateN","2000-01-01");

        MockHttpServletResponse response = new MockHttpServletResponse();

        Utente utente = new Utente();
        utente.setNome("Mario");
        utente.setCognome("Rossi");
        utente.setEmail("emailerrata");
        utente.setPsword("Password123*");
        utente.setDateN("2000-01-01");
        utente.setIs_Admin(false);

        Mockito.when(dao.insertUtente(utente)).thenReturn(true);

        ServletRegister controller = new ServletRegister(dao);

        controller.registrazioneUtente(request,response);

        assertEquals("Email già utilizzata", response.getContentAsString().contains("Email già utilizzata") );
    }
}
