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

import static org.junit.Assert.assertEquals;

public class ServletCambioPasswordTEST {

    @Test
    public void cambioPasswordSuccesso() throws ServletException, IOException {
        UtenteDAO dao = Mockito.mock(UtenteDAO .class);
        Utente utente = new Utente();

        utente.setNome("mario");
        utente.setCognome("rossi");
        utente.setEmail("mariorossi@gmail.com");
        utente.setPsword("Password123*");
        utente.setDateN("12-10-1998");


        MockHttpServletRequest request = new MockHttpServletRequest();
        MockHttpServletResponse response = new MockHttpServletResponse();
        HttpSession session = request.getSession();
        request.addHeader("referer", "header");
        session.setAttribute("user",utente);
        request.setParameter("nome", "mario");
        request.setParameter("cognome", "rossi");
        request.setParameter("passwordAttuale", "Password123*");
        request.setParameter("email", "mariorossi@gmail.com");
        request.setParameter("dateN","12-10-1998");
        request.setParameter("nuovaPassword","Password456!");
        request.setParameter("verificanuovaPassword","Password456!");

        Mockito.when(dao.updateUtente(utente)).thenReturn(true);
        ServletCambiaPassword controller = new ServletCambiaPassword(dao);

        controller.cambioPassword(request, response);
        assertEquals("CAMBIO PASSWORD EFFETTUATO", response.getContentAsString().contains("CAMBIO PASSWORD EFFETTUATO"));

    }
    @Test
    public void cambioPasswordInsuccessoUno() throws ServletException, IOException { //password diverse
        UtenteDAO dao = Mockito.mock(UtenteDAO .class);
        Utente utente = new Utente();

        utente.setNome("mario");
        utente.setCognome("rossi");
        utente.setEmail("mariorossi@gmail.com");
        utente.setPsword("Password123*");
        utente.setDateN("12-10-1998");


        MockHttpServletRequest request = new MockHttpServletRequest();
        MockHttpServletResponse response = new MockHttpServletResponse();
        HttpSession session = request.getSession();
        request.addHeader("referer", "header");
        session.setAttribute("user",utente);
        request.setParameter("nome", "mario");
        request.setParameter("cognome", "rossi");
        request.setParameter("passwordAttuale", "Password123*");
        request.setParameter("email", "mariorossi@gmail.com");
        request.setParameter("dateN","12-10-1998");
        request.setParameter("nuovaPassword","Password456!");
        request.setParameter("verificanuovaPassword","Password789!");

        Mockito.when(dao.updateUtente(utente)).thenReturn(true);
        ServletCambiaPassword controller = new ServletCambiaPassword(dao);

        controller.cambioPassword(request, response);
        assertEquals("CAMBIO NON PASSWORD EFFETTUATO", response.getContentAsString().contains("CAMBIO PASSWORD NON EFFETTUATO"));

    }
    @Test
    public void cambioPasswordInsuccessoDue() throws ServletException, IOException { //password non rispetta i canoni
        UtenteDAO dao = Mockito.mock(UtenteDAO .class);
        Utente utente = new Utente();

        utente.setNome("mario");
        utente.setCognome("rossi");
        utente.setEmail("mariorossi@gmail.com");
        utente.setPsword("Password123*");
        utente.setDateN("12-10-1998");


        MockHttpServletRequest request = new MockHttpServletRequest();
        MockHttpServletResponse response = new MockHttpServletResponse();
        HttpSession session = request.getSession();
        request.addHeader("referer", "header");
        session.setAttribute("user",utente);
        request.setParameter("nome", "mario");
        request.setParameter("cognome", "rossi");
        request.setParameter("passwordAttuale", "Password123*");
        request.setParameter("email", "mariorossi@gmail.com");
        request.setParameter("dateN","12-10-1998");
        request.setParameter("nuovaPassword","password");
        request.setParameter("verificanuovaPassword","password"); //password non rispetta i canoni

        Mockito.when(dao.updateUtente(utente)).thenReturn(true);
        ServletCambiaPassword controller = new ServletCambiaPassword(dao);

        controller.cambioPassword(request, response);
        assertEquals("PASSWORD NON VALIDA", response.getContentAsString().contains("PASSWORD NON VALIDA"));

    }
}
