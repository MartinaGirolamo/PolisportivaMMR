package application;

import storage.Prenotazione.Prenotazione;
import storage.Prenotazione.PrenotazioneDAO;
import storage.Utente.Utente;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "ServletEliminaPrenotazione", value = "/ServletEliminaPrenotazione")
public class ServletEliminaPrenotazione extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Utente utente = (Utente)req.getSession().getAttribute("user");
        String codStr=req.getParameter("codice");
        int codice=-1;

        if(codStr!=null)
            codice=Integer.parseInt(codStr);

        PrenotazioneDAO pd = new PrenotazioneDAO();
        Prenotazione prenotazione = pd.selectPrenotazioneByCodice(codice);

        if(prenotazione!=null || prenotazione.getCodice()>0) {
           if(pd.deletePrenotazione(codice)){
               RequestDispatcher requestDispatcher= req.getRequestDispatcher("interface/visualizzaPrenotazioniDisponibili.jsp");
               requestDispatcher.forward(req, resp);
           }
           else{
               RequestDispatcher requestDispatcher= req.getRequestDispatcher("interface/Error500.jsp");
               requestDispatcher.forward(req, resp);
           }


        }

    }
}
