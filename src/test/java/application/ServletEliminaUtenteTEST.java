package application;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import storage.Utente.Utente;
import storage.Utente.UtenteDAO;

import javax.servlet.ServletException;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class ServletEliminaUtenteTEST {
    @Test
    public void eliminazioneUtenteTest() throws IOException, SQLException, ServletException {
        UtenteDAO dao = Mockito .mock(UtenteDAO .class);
        Utente  utente = new Utente();

        utente.setNome("mario");
        utente.setCognome("rossi");
        utente.setEmail("mariorossi@gmail.com");
        utente.setPsword("Password123*");
        utente.setDateN("12-10-1998");


        MockHttpServletRequest  request = new MockHttpServletRequest();
        MockHttpServletResponse  response = new MockHttpServletResponse();
        HttpSession  session = request.getSession();

        session.setAttribute("user",utente);
        request.setParameter("nome", "mario");
        request.setParameter("cognome", "rossi");
        request.setParameter("password", "Password123*");
        request.setParameter("email", "mariorossi@gmail.com");
        request.setParameter("dateN","12-10-1998");

        Mockito.when(dao.selectUtenteByEmailPassword("mariorossi@gmail.com","Password123*")).thenReturn(utente);
        Mockito.when(dao.deleteUtente("mariorossi@gmail.com")).thenReturn(true);

        ServletEliminaUtente controller = new ServletEliminaUtente(dao);

        request.addHeader("referer", "header");
        controller.eliminaUtente(request, response);
        assertTrue("UTENTE ELIMINATO", response.getContentAsString().contains("UTENTE ELIMINATO"));

    }

}
