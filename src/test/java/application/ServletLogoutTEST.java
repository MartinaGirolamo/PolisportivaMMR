package application;

import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.mock.web.MockHttpSession;
import storage.Utente.Utente;

import javax.servlet.ServletException;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ServletLogoutTEST {
    @Test
    public void logoutSuccessoTest() throws IOException, ServletException {
        Utente utente = new Utente();
        utente.setNome("rosa");
        utente.setCognome("bianchi");
        utente.setEmail("rosabianchi@gmail.com");
        utente.setPsword("e.coppola37@studenti.unisa.it");
        utente.setDateN("12-10-1998");

        MockHttpServletRequest request = new MockHttpServletRequest();
        MockHttpServletResponse response = new MockHttpServletResponse();

        MockHttpSession session = (MockHttpSession) request.getSession();

        session.setAttribute("utente", utente);

        ServletLogout controller = new ServletLogout();

        request.addHeader("referer", "header");
        controller.logout(request, response);
        session = (MockHttpSession) request.getSession();
        assertEquals(true,session.getAttribute("uscito"));
    }
}
