package application;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import storage.Abbonamento.Abbonamento;
import storage.Abbonamento.AbbonamentoDAO;
import storage.Utente.Utente;
import storage.Utente.UtenteDAO;

import javax.servlet.ServletException;
import javax.servlet.http.HttpSession;

import java.io.IOException;

import static org.junit.Assert.assertEquals;

public class ServletEliminaAcquistoTEST {


    @Test
    public void eliminaAcquistoSuccessoTest() throws ServletException, IOException {
        UtenteDAO uDao= Mockito .mock(UtenteDAO .class);
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

        AbbonamentoDAO aDao= Mockito .mock(AbbonamentoDAO .class);
        Abbonamento abbonamento=new Abbonamento();

        abbonamento.setCodice(1);
        abbonamento.setTariffa(30);
        abbonamento.setTipologia("calcio");

        request.setParameter("tipologia","calcio");

        Mockito.when(aDao.selectAbbonamentoByTipologia("calcio")).thenReturn(abbonamento);


        ServletEliminaAcquisto controller = new ServletEliminaAcquisto(aDao);

        controller.eliminaAcquisto(request, response);
        assertEquals("ACQUISTO ELIMINATO CON SUCCESSO!", response.getContentAsString().contains("ACQUISTO ELIMINATO CON SUCCESSO!"));

    }
}
