package application;

import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.mock.web.MockHttpSession;
import storage.Utente.Utente;
import storage.Utente.UtenteDAO;

import javax.servlet.ServletException;
import java.io.IOException;
import java.sql.SQLException;

import static org.junit.Assert.*;

public class ServletLoginTEST {


    @Test
    public void successoLogin() throws SQLException, IOException, ServletException {
        MockHttpServletRequest request = new MockHttpServletRequest();
        request.addHeader("referer", "header");
        UtenteDAO dao = Mockito.mock(UtenteDAO.class);
        request.setParameter("nome", "rosa");
        request.setParameter("cognome", "bianchi");
        request.setParameter("email", "rosabianchi@gmail.com");
        request.setParameter("psw", "password1");
        request.setParameter("dateN", "12-10-1998");

        Utente utente=new Utente();
        utente.setNome("rosa");
        utente.setCognome("bianchi");
        utente.setEmail("rosabianchi@gmail.com");
        utente.setPsword("password1");
        utente.setDateN("12-10-1998");
        utente.setIs_Admin(false);
        MockHttpServletResponse response = new MockHttpServletResponse();

        Mockito.when(dao.selectUtenteByEmailPassword("rosabianchi@gmail.com", "password1")).thenReturn(utente);

        ServletLogin controller = new ServletLogin(dao);

        controller.loginUtente(request, response);
        assertFalse("Email o password SBAGLIATA", response.getContentAsString().contains("Email o password SBAGLIATA"));

    }

    @Test
    public void emailSbagliataLogin() throws SQLException, IOException, ServletException {
        MockHttpServletRequest request = new MockHttpServletRequest();
        request.addHeader("referer", "header");
        UtenteDAO dao = Mockito.mock(UtenteDAO.class);
        request.setParameter("nome", "rosa");
        request.setParameter("cognome", "bianchi");
        request.setParameter("email", "emailSbagliata@gmail.com");
        request.setParameter("psw", "password1");
        request.setParameter("dateN", "12-10-1998");

        Utente utente=new Utente();
        utente.setNome(null);
        utente.setCognome(null);
        utente.setEmail(null);
        utente.setPsword(null);
        utente.setDateN(null);
        utente.setIs_Admin(false);
        MockHttpServletResponse response = new MockHttpServletResponse();


        Mockito.when(dao.selectUtenteByEmailPassword("emailSbagliata@gmail.com", "password1")).thenReturn(utente);

        ServletLogin controller = new ServletLogin(dao);

        controller.loginUtente(request, response);
        assertTrue("Email o password SBAGLIATA non rilevata", (Boolean) request.getAttribute("errore"));

    }

    @Test
    public void passwordSbagliataLogin() throws SQLException, IOException, ServletException {
        MockHttpServletRequest request = new MockHttpServletRequest();
        request.addHeader("referer", "header");
        UtenteDAO dao = Mockito.mock(UtenteDAO.class);
        request.setParameter("nome", "rosa");
        request.setParameter("cognome", "bianchi");
        request.setParameter("email", "rosabianchi@gmail.com");
        request.setParameter("psw", "passwordSbagliata");
        request.setParameter("dateN", "12-10-1998");

        Utente utente=new Utente();
        utente.setNome(null);
        utente.setCognome(null);
        utente.setEmail(null);
        utente.setPsword(null);
        utente.setDateN(null);
        utente.setIs_Admin(false);
        MockHttpServletResponse response = new MockHttpServletResponse();

        Mockito.when(dao.selectUtenteByEmailPassword("rosabianchi@gmail.com", "passwordSbagliata")).thenReturn(utente);

        ServletLogin controller = new ServletLogin(dao);
        controller.loginUtente(request, response);
        assertTrue("Email o password SBAGLIATA", (Boolean) request.getAttribute("errore"));

    }
}