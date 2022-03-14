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

import javax.servlet.http.HttpSession;
import java.sql.Date;
import java.util.ArrayList;

public class ServletNoleggiaTEST { //da completare

    @Test
    public void successoNoleggio(){
        UtenteDAO uDao= Mockito.mock(UtenteDAO .class);
        Utente utente=new Utente();
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
        request.setParameter("password", "Password123*");
        request.setParameter("email", "mariorossi@gmail.com");
        request.setParameter("dateN","12-10-1998");

        PrenotazioneDAO pDao = Mockito.mock(PrenotazioneDAO.class);
        Prenotazione prenotazione = new Prenotazione();

        prenotazione.setCodice(100);
        prenotazione.setDateP(Date.valueOf("2022-10-12"));
        prenotazione.setEmail("mariorossi@gmail.com");
        prenotazione.setOraStart(9);
        prenotazione.setNomeCampo("Calcio");
        prenotazione.setOraEnd(1);
        session.setAttribute("prenotazioneEffettuata",prenotazione);

        NoleggioDAO nDao = Mockito.mock(NoleggioDAO.class);
        ArrayList<Noleggio> noleggio = new ArrayList<>();
        Noleggio n = new Noleggio();

        n.setCodicePren(10);
        n.setCodiceAttr(1);
        n.setQta(1);
        noleggio.add(n);
    }
}
