package application;

import junit.framework.Assert;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import storage.Prenotazione.Prenotazione;
import storage.Prenotazione.PrenotazioneDAO;
import storage.Prenotazione.PrenotazioneDisponibile;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertTrue;


public class ServletMostraPrenotazioneTEST {

    @Test
    public void successoTest() throws ServletException, IOException {
        MockHttpServletRequest request = new MockHttpServletRequest();
        request.addHeader("referer","header");

        request.setParameter("campoScelto","Calcio");
        request.setParameter("dataScelta","2022-02-21");
        request.setParameter("oraStartScelta","12:00");
        request.setParameter("numOreScelte","2");

        MockHttpServletResponse response = new MockHttpServletResponse();

        ArrayList<Prenotazione> arrayList = new ArrayList<>();
        java.sql.Date data = java.sql.Date.valueOf("2022-02-21");
        Prenotazione p1 = new Prenotazione(10,11,1,20,data,"email@email.it","Calcio"); arrayList.add(p1);
        Prenotazione p2 = new Prenotazione(13,14,2,20,data,"email@email.it","Calcio");arrayList.add(p2);
        Prenotazione p3 = new Prenotazione(18,20,3,40,data,"email@email.it","Calcio");arrayList.add(p3);

        PrenotazioneDAO pd = Mockito.mock(PrenotazioneDAO.class);
        Mockito.when(pd.selectPrenotazioniByDataAndCampo(data,"Calcio")).thenReturn(arrayList); // quando vado a chiamare il metodo selectPrenotazioniByDataAndCampo con i parametri deve restituire arrayList
        ServletMostraPrenotazione servletMostraPrenotazione = new ServletMostraPrenotazione(pd);
        servletMostraPrenotazione.mostraPrenotazione(request,response);
        ArrayList<PrenotazioneDisponibile> list = new ArrayList<>();
        PrenotazioneDisponibile pDisp1 = new PrenotazioneDisponibile(11,13,data,"Calcio",40); list.add(pDisp1);
        PrenotazioneDisponibile pDisp2 = new PrenotazioneDisponibile(14,16,data,"Calcio",40); list.add(pDisp2);
        PrenotazioneDisponibile pDisp3 = new PrenotazioneDisponibile(15,17,data,"Calcio",40); list.add(pDisp3);
        PrenotazioneDisponibile pDisp4 = new PrenotazioneDisponibile(16,18,data,"Calcio",40); list.add(pDisp4);
        PrenotazioneDisponibile pDisp5 = new PrenotazioneDisponibile(20,22,data,"Calcio",40); list.add(pDisp5);

        ArrayList<PrenotazioneDisponibile> listaReturn = (ArrayList<PrenotazioneDisponibile>) request.getAttribute("listaPrenotazioniDisponibili");
        if(listaReturn!=null){
        assertTrue(listaReturn.equals(arrayList),"Le prenotazioni disponibili sono incorrette");}

    }
}
