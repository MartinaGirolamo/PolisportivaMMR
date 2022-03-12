package application;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import storage.Noleggio.Noleggio;
import storage.Noleggio.NoleggioDAO;
import storage.Prenotazione.Prenotazione;
import storage.Prenotazione.PrenotazioneDAO;
import storage.Utente.Utente;
import storage.Utente.UtenteDAO;

import javax.servlet.ServletException;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

public class ServletEliminaPrenotazioneTEST {

    @Test
    public void eliminazionePrenotazioneSuccessoTest() throws IOException, SQLException, ServletException {
        UtenteDAO uDao = Mockito.mock(UtenteDAO.class);
        Utente utente = new Utente();

        utente.setNome("mario");
        utente.setCognome("rossi");
        utente.setEmail("mariorossi@gmail.com");
        utente.setPsword("Password123*");
        utente.setDateN("12-10-1998");

        PrenotazioneDAO pDao = Mockito.mock(PrenotazioneDAO.class);
        Prenotazione prenotazione = new Prenotazione();

        prenotazione.setCodice(10);
        prenotazione.setDateP(Date.valueOf("2022-10-10"));
        prenotazione.setEmail("mariorossi@gmail.com");
        prenotazione.setOraStart(9);
        prenotazione.setNomeCampo("Calcio");
        prenotazione.setOraEnd(1);

        NoleggioDAO nDao = Mockito.mock(NoleggioDAO.class);
        ArrayList<Noleggio> noleggio = new ArrayList<>();
        Noleggio n = new Noleggio();

        n.setCodicePren(10);
        n.setCodiceAttr(1);
        n.setQta(1);
        noleggio.add(n);

        MockHttpServletRequest request = new MockHttpServletRequest();
        MockHttpServletResponse response = new MockHttpServletResponse();
        HttpSession session = request.getSession();

        session.setAttribute("user", utente);
        request.setParameter("nome", "mario");
        request.setParameter("cognome", "rossi");
        request.setParameter("password", "Password123*");
        request.setParameter("email", "mariorossi@gmail.com");
        request.setParameter("dateN", "12-10-1998");

        request.setParameter("codice", "10"); //codice pren

        Mockito.when(nDao.selectNoleggioByPrenotazione(10)).thenReturn(noleggio);
        Mockito.when(nDao.deleteNoleggio(10, 1)).thenReturn(true);
        Mockito.when(pDao.selectPrenotazioneByCodice(10)).thenReturn(prenotazione);

        ServletEliminaPrenotazione controller = new ServletEliminaPrenotazione(pDao, nDao);

        request.addHeader("referer", "header");
        controller.eliminaPrenotazione(request, response);
        assertEquals("PRENOTAZIONE ELIMINATA CON SUCCESSO!", response.getContentAsString().contains("PRENOTAZIONE ELIMINATA CON SUCCESSO!"));

    }

    @Test
    public void eliminazionePrenotazioneErroreNoleggioTest() throws IOException, SQLException, ServletException {
        UtenteDAO uDao = Mockito.mock(UtenteDAO.class);
        Utente utente = new Utente();

        utente.setNome("mario");
        utente.setCognome("rossi");
        utente.setEmail("mariorossi@gmail.com");
        utente.setPsword("Password123*");
        utente.setDateN("12-10-1998");

        PrenotazioneDAO pDao = Mockito.mock(PrenotazioneDAO.class);
        Prenotazione prenotazione = new Prenotazione();

        prenotazione.setCodice(10);
        prenotazione.setDateP(Date.valueOf("2022-10-10"));
        prenotazione.setEmail("mariorossi@gmail.com");
        prenotazione.setOraStart(9);
        prenotazione.setNomeCampo("Calcio");
        prenotazione.setOraEnd(1);

        NoleggioDAO nDao = new NoleggioDAO();
        ArrayList<Noleggio> noleggio = new ArrayList<>();
        Noleggio n = new Noleggio();

        n.setCodicePren(10);
        n.setCodiceAttr(1);
        n.setQta(1);
        noleggio.add(n);

        MockHttpServletRequest request = new MockHttpServletRequest();
        MockHttpServletResponse response = new MockHttpServletResponse();
        HttpSession session = request.getSession();

        session.setAttribute("user", utente);
        request.setParameter("nome", "mario");
        request.setParameter("cognome", "rossi");
        request.setParameter("password", "Password123*");
        request.setParameter("email", "mariorossi@gmail.com");
        request.setParameter("dateN", "12-10-1998");

        request.setParameter("codice", "10"); //codice pren

        Mockito.when(nDao.selectNoleggioByPrenotazione(10)).thenReturn(noleggio);
        Mockito.when(nDao.deleteNoleggio(10, 99)).thenReturn(true);
        Mockito.when(pDao.selectPrenotazioneByCodice(10)).thenReturn(prenotazione);

        ServletEliminaPrenotazione controller = new ServletEliminaPrenotazione(pDao, nDao);

        request.addHeader("referer", "header");
        controller.eliminaPrenotazione(request, response);
        assertEquals("Errore 500", response.getContentAsString().contains("Errore 500"));

    }
    @Test
    public void eliminazionePrenotazioneErrorePrenotazioneTest() throws IOException, SQLException, ServletException {
        UtenteDAO uDao = Mockito.mock(UtenteDAO.class);
        Utente utente = new Utente();

        utente.setNome("mario");
        utente.setCognome("rossi");
        utente.setEmail("mariorossi@gmail.com");
        utente.setPsword("Password123*");
        utente.setDateN("12-10-1998");

        PrenotazioneDAO pDao = Mockito.mock(PrenotazioneDAO.class);
        Prenotazione prenotazione = new Prenotazione();

        prenotazione.setCodice(10);
        prenotazione.setDateP(Date.valueOf("2022-10-10"));
        prenotazione.setEmail("mariorossi@gmail.com");
        prenotazione.setOraStart(9);
        prenotazione.setNomeCampo("Calcio");
        prenotazione.setOraEnd(1);

        NoleggioDAO nDao = Mockito.mock(NoleggioDAO.class);
        ArrayList<Noleggio> noleggio = new ArrayList<>();
        Noleggio n = new Noleggio();

        n.setCodicePren(10);
        n.setCodiceAttr(1);
        n.setQta(1);
        noleggio.add(n);

        MockHttpServletRequest request = new MockHttpServletRequest();
        MockHttpServletResponse response = new MockHttpServletResponse();
        HttpSession session = request.getSession();

        session.setAttribute("user", utente);
        request.setParameter("nome", "mario");
        request.setParameter("cognome", "rossi");
        request.setParameter("password", "Password123*");
        request.setParameter("email", "mariorossi@gmail.com");
        request.setParameter("dateN", "12-10-1998");

        request.setParameter("codice", "10"); //codice pren

        Mockito.when(nDao.selectNoleggioByPrenotazione(99)).thenReturn(noleggio);
        Mockito.when(nDao.deleteNoleggio(99, 1)).thenReturn(true);
        Mockito.when(pDao.selectPrenotazioneByCodice(99)).thenReturn(prenotazione);

        ServletEliminaPrenotazione controller = new ServletEliminaPrenotazione(pDao, nDao);

        request.addHeader("referer", "header");
        controller.eliminaPrenotazione(request, response);
        assertEquals("Errore 500", response.getContentAsString().contains("Errore 500"));

    }
}