package application;

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

@WebServlet(name = "ServletPrenota", value = "/ServletPrenota")
public class ServletPrenota extends HttpServlet {
    public ServletPrenota(){

    }
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        prenota(req, resp);
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        prenota(req, resp);
    }


    public void prenota(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Utente user=(Utente) req.getSession().getAttribute("user");
        ArrayList<PrenotazioneDisponibile> prenotazioniDisponibili = (ArrayList<PrenotazioneDisponibile>) req.getSession().getAttribute("listaPrenotazioniDisponibili");
        PrenotazioneDAO prenotazioneDAO = new PrenotazioneDAO();
        ArrayList<Prenotazione> listPrenotazioniPresenti = prenotazioneDAO.selectAllPrenotazioni();//
        int max =0;
        for (Prenotazione p: listPrenotazioniPresenti) {
            if(p.getCodice()>max)
                max=p.getCodice();
        }
        if(prenotazioniDisponibili==null || prenotazioniDisponibili.isEmpty()){
            RequestDispatcher requestDispatcher= req.getRequestDispatcher("interface/Error500.jsp");
            requestDispatcher.forward(req, resp);
        }
        int indiceArrayScelto = Integer.parseInt(req.getParameter("indiceArrayScelto"));
        PrenotazioneDisponibile prenotazioneScelta = prenotazioniDisponibili.get(indiceArrayScelto);
        System.out.println(indiceArrayScelto);
        if(user == null || user.getEmail()==null){
            RequestDispatcher requestDispatcher= req.getRequestDispatcher("interface/UtenteNonLoggato.jsp");
            requestDispatcher.forward(req, resp);
        }
        else if(indiceArrayScelto<0 || prenotazioneScelta==null || prenotazioneScelta.getDate()==null){
            RequestDispatcher requestDispatcher= req.getRequestDispatcher("interface/Error500.jsp");
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
            prenotazione.setCodice(max+1);
            if(pd.insertPrenotazione(prenotazione)) {
                System.out.println("Prenotazione effettuata: "+prenotazione );
                HttpSession session = req.getSession(true);
                session.setAttribute("prenotazioneEffettuata", prenotazione);
                RequestDispatcher requestDispatcher = req.getRequestDispatcher("interface/PrenotazioneEffettuata.jsp");
                requestDispatcher.forward(req, resp);

            }
            else{
                System.out.println("Prenotazione NON effettuata: " );
                RequestDispatcher requestDispatcher= req.getRequestDispatcher("interface/Error500.jsp");
                requestDispatcher.forward(req, resp);
            }

        }

    }
}
