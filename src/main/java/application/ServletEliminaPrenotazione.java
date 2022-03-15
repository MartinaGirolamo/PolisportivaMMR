package application;

import storage.Attrezzatura.AttrezzaturaDAO;
import storage.Noleggio.Noleggio;
import storage.Noleggio.NoleggioDAO;
import storage.Prenotazione.Prenotazione;
import storage.Prenotazione.PrenotazioneDAO;
import storage.Utente.Utente;
import storage.Utente.UtenteDAO;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet(name = "ServletEliminaPrenotazione", value = "/ServletEliminaPrenotazione")
public class ServletEliminaPrenotazione extends HttpServlet {
    private PrenotazioneDAO prenotazioneDAO;
    private NoleggioDAO noleggioDAO;

    public ServletEliminaPrenotazione(){
        this.noleggioDAO=new NoleggioDAO();
        this.prenotazioneDAO=new PrenotazioneDAO();
    }
    public ServletEliminaPrenotazione(PrenotazioneDAO prenotazioneDAO,NoleggioDAO noleggioDAO){
        this.prenotazioneDAO = prenotazioneDAO;
        this.noleggioDAO = noleggioDAO;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
       eliminaPrenotazione(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);


    }


    public void eliminaPrenotazione(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String context = req.getContextPath();
        Utente utente = (Utente)req.getSession().getAttribute("user");
        String codStr=req.getParameter("codice");
        int codice=-1;

        if(codStr!=null)
            codice=Integer.parseInt(codStr);

        NoleggioDAO nd = new NoleggioDAO();
        ArrayList<Noleggio> list = nd.selectNoleggioByPrenotazione(codice);
        for (Noleggio n: list) {
            if(!(nd.deleteNoleggio(n.getCodicePren(),n.getCodiceAttr()))) {
                System.out.println("Errore in delete noleggio");
                RequestDispatcher requestDispatcher = req.getRequestDispatcher("interface/Error500.jsp");
                requestDispatcher.forward(req, resp);
            }
        }

        PrenotazioneDAO pd = new PrenotazioneDAO();
        Prenotazione prenotazione = pd.selectPrenotazioneByCodice(codice);

        if (prenotazione != null || prenotazione.getCodice() > 0) {
            if (pd.deletePrenotazione(codice)) {
                RequestDispatcher requestDispatcher = req.getRequestDispatcher("interface/PrenotazioneEliminata.jsp");
                requestDispatcher.forward(req, resp);
            } else {
                System.out.println("Errore in delete prenotazione");
                RequestDispatcher requestDispatcher = req.getRequestDispatcher("interface/Error500.jsp");
                requestDispatcher.forward(req, resp);
            }
        }
    }
}
