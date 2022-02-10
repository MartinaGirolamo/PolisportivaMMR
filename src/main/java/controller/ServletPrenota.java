package controller;

import model.Prenotazione.Prenotazione;
import model.Prenotazione.PrenotazioneDAO;
import model.Prenotazione.PrenotazioneDisponibile;
import model.Utente.Utente;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;

@WebServlet(name = "ServletPrenota", value = "/ServletPrenota")
public class ServletPrenota extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Utente user=(Utente) req.getSession().getAttribute("user");
        ArrayList<PrenotazioneDisponibile> prenotazioniDisponibili = (ArrayList<PrenotazioneDisponibile>) req.getSession().getAttribute("listaPrenotazioniDisponibili");
        int indiceArrayScelto = Integer.parseInt(req.getParameter("indiceArrayScelto"));
        PrenotazioneDisponibile prenotazioneScelta = prenotazioniDisponibili.get(indiceArrayScelto);

        if(user == null || user.getEmail()==null){
            RequestDispatcher requestDispatcher= req.getRequestDispatcher("view/UtenteNonLoggato.jsp");
            requestDispatcher.forward(req, resp);
        }
        else{
            PrenotazioneDAO pd = new PrenotazioneDAO();
            Prenotazione prenotazione = new Prenotazione();

            prenotazione.setEmail(user.getEmail());
            prenotazione.setNomeCampo(prenotazioneScelta.getNomeCampo());
            prenotazione.setOraStart(prenotazioneScelta.getOraStart());
            prenotazione.setOraEnd(prenotazioneScelta.getOraEnd());
            prenotazione.setDateP(prenotazioneScelta.getDate());
            prenotazione.setTariffaTotale(prenotazioneScelta.getTariffaTotale());
            prenotazione.setCodice(pd.sizeNumeroPrenotazioni()+1);
            if(pd.insertPrenotazione(prenotazione)) {
                System.out.println("Prenotazione effettuata: "+prenotazione );
                HttpSession session = req.getSession(true);
                session.setAttribute("prenotazioneEffettuata", prenotazione);
                RequestDispatcher requestDispatcher = req.getRequestDispatcher("view/PrenotazioneEffettuata.jsp");
                requestDispatcher.forward(req, resp);

            }
            else{
                System.out.println("Prenotazione NON effettuata: " );
                RequestDispatcher requestDispatcher= req.getRequestDispatcher("view/Error500.jsp");
                requestDispatcher.forward(req, resp);
            }

        }

    }
}
