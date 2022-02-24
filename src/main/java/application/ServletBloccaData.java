package application;

import storage.Campo.Campo;
import storage.Campo.CampoDAO;
import storage.Noleggio.Noleggio;
import storage.Noleggio.NoleggioDAO;
import storage.Prenotazione.Prenotazione;
import storage.Prenotazione.PrenotazioneDAO;
import storage.Prenotazione.PrenotazioneDisponibile;
import storage.Utente.Utente;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

@WebServlet (name = "ServletBloccaData", value = "/ServletBloccaData")
public class ServletBloccaData extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Utente user = (Utente) req.getSession().getAttribute("user");
        PrenotazioneDAO prenotazioneDAO = new PrenotazioneDAO();
        ArrayList<Prenotazione> listPrenotazioniPresenti = prenotazioneDAO.selectAllPrenotazioni();
        int max =0;
        for (Prenotazione p: listPrenotazioniPresenti) {
            if(p.getCodice()>=max)
                max=p.getCodice();

        }
        System.out.println(max);

        if(!user.isIs_Admin()){
            RequestDispatcher requestDispatcher= req.getRequestDispatcher("interface/Error500.jsp");
            requestDispatcher.forward(req, resp);
        }
        String datetimeString= req.getParameter("dataScelta");
        java.sql.Date data = java.sql.Date.valueOf(datetimeString);
        int oraStart=9, oraEnd=22;
        PrenotazioneDAO pd = new PrenotazioneDAO();


        if(user!=null && user.isIs_Admin()){
            boolean controllo = true;
            for(int i = 0; i<3 && controllo;i++) {
                Prenotazione prenotazione = new Prenotazione();
                String campo="";
                if(i==0){
                     campo = "pallavolo";
                }
                if(i==1){
                    campo = "tennis";
                }
                if(i==2){
                    campo = "calcio";
                }
                ArrayList<Prenotazione> listaPrenotazioniPresenti = pd.selectPrenotazioniByDataAndCampo(data,campo);
                if(!listaPrenotazioniPresenti.isEmpty()){
                    for (Prenotazione p: listaPrenotazioniPresenti) {
                        NoleggioDAO nd = new NoleggioDAO();
                        ArrayList<Noleggio> list = nd.selectNoleggioByPrenotazione(p.getCodice());
                        for (Noleggio n: list) {
                            if(!(nd.deleteNoleggio(n.getCodicePren(),n.getCodiceAttr()))) {
                                System.out.println("Errore in delete noleggio");
                                RequestDispatcher requestDispatcher = req.getRequestDispatcher("interface/Error500.jsp");
                                requestDispatcher.forward(req, resp);
                            }
                        }

                        pd.deletePrenotazione(p.getCodice());
                    }
                }

                prenotazione.setEmail(user.getEmail());
                prenotazione.setNomeCampo(campo);
                prenotazione.setOraStart(oraStart);
                prenotazione.setOraEnd(oraEnd);
                prenotazione.setDateP(data);
                prenotazione.setTariffaTotale(0);
                prenotazione.setCodice(max+1);
                if (pd.insertPrenotazione(prenotazione)) {
                    System.out.println("Data: " + data + " bloccata!");
                    max+=1;

                } else {
                    controllo=false;
                }
            }

            if(!controllo){
                RequestDispatcher requestDispatcher= req.getRequestDispatcher("interface/Error500.jsp");
                requestDispatcher.forward(req, resp);
            }
            else {
                RequestDispatcher requestDispatcher= req.getRequestDispatcher("interface/bloccaDataEffettuato.jsp");
                requestDispatcher.forward(req, resp);
            }

        }






    }


}
