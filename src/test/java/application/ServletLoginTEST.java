package application;

import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import storage.Utente.UtenteDAO;

import javax.servlet.ServletException;
import java.io.IOException;
import java.sql.SQLException;

import static org.junit.Assert.assertEquals;

public class ServletLoginTEST {
    UtenteDAO dao = Mockito.mock(UtenteDAO.class);

    @Test
    public void successoLogin() throws SQLException, IOException, ServletException {
        MockHttpServletRequest request = new MockHttpServletRequest();
        request.addHeader("referer", "header");

        request.setParameter("nome", "rosa");
        request.setParameter("cognome", "bianchi");
        request.setParameter("email", "rosabianchi@gmail.com");
        request.setParameter("psw", "password1");
        request.setParameter("dateN", "12-10-1998");

        MockHttpServletResponse response = new MockHttpServletResponse();

        Mockito.when(dao.selectUtenteByEmailPassword("rosabianchi@gmail.com", "password1")).thenReturn(null);

        ServletLogin controller = new ServletLogin(dao);

        controller.loginUtente(request, response);
        assertEquals("Email o password SBAGLIATA", response.getContentAsString().contains("Email o password SBAGLIATA"));

    }

    @Test
    public void emailSbagliataLogin() throws SQLException, IOException, ServletException {
        MockHttpServletRequest request = new MockHttpServletRequest();
        request.addHeader("referer", "header");

        request.setParameter("nome", "rosa");
        request.setParameter("cognome", "bianchi");
        request.setParameter("email", "emailSbagliata@gmail.com");
        request.setParameter("psw", "password1");
        request.setParameter("dateN", "12-10-1998");

        MockHttpServletResponse response = new MockHttpServletResponse();

        Mockito.when(dao.selectUtenteByEmailPassword("emailSbagliata@gmail.com", "password1")).thenReturn(null);

        ServletLogin controller = new ServletLogin(dao);

        controller.loginUtente(request, response);
        assertEquals("Email o password SBAGLIATA", response.getContentAsString().contains("Email o password SBAGLIATA"));

    }

    @Test
    public void passwordSbagliataLogin() throws SQLException, IOException, ServletException {
        MockHttpServletRequest request = new MockHttpServletRequest();
        request.addHeader("referer", "header");

        request.setParameter("nome", "rosa");
        request.setParameter("cognome", "bianchi");
        request.setParameter("email", "rosabianchi@gmail.com");
        request.setParameter("psw", "passwordSbagliata");
        request.setParameter("dateN", "12-10-1998");

        MockHttpServletResponse response = new MockHttpServletResponse();

        Mockito.when(dao.selectUtenteByEmailPassword("rosabianchi@gmail.com", "passwordSbagliata")).thenReturn(null);

        ServletLogin controller = new ServletLogin(dao);
        controller.loginUtente(request, response);
        assertEquals("Email o password SBAGLIATA", response.getContentAsString().contains("Email o password SBAGLIATA"));

    }
}