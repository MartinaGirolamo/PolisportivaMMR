package application;

import storage.Abbonamento.Abbonamento;
import storage.Abbonamento.AbbonamentoDAO;
import storage.Acquisto.AcquistoDAO;
import storage.Attrezzatura.Attrezzatura;
import storage.Attrezzatura.AttrezzaturaDAO;
import storage.Utente.Utente;
import storage.Utente.UtenteDAO;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "ServletEliminaAcquisto", value = "/ServletEliminaAcquisto")
public class ServletEliminaAcquisto extends HttpServlet {
    private AbbonamentoDAO abbonamentoDAO;
    public ServletEliminaAcquisto(){
        this.abbonamentoDAO = new AbbonamentoDAO();
    }

    public ServletEliminaAcquisto(AbbonamentoDAO abbonamentoDAO){
        this.abbonamentoDAO = abbonamentoDAO;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
       eliminaAcquisto(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }

    public void eliminaAcquisto(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Utente user = (Utente) req.getSession().getAttribute("user");
        String email = user.getEmail();
        String tipologia = req.getParameter("tipologia");
        AcquistoDAO aq = new AcquistoDAO();
        AbbonamentoDAO abbonamentoDAO = new AbbonamentoDAO();
        Abbonamento ab = abbonamentoDAO.selectAbbonamentoByTipologia(tipologia);
        if(aq.deleteAcquisto(ab.getCodice(),email)){
            RequestDispatcher requestDispatcher = req.getRequestDispatcher("interface/acquistoEliminato.jsp");
            requestDispatcher.forward(req, resp);
        }
        else{
            RequestDispatcher requestDispatcher = req.getRequestDispatcher("interface/Error500.jsp");
            requestDispatcher.forward(req, resp);
        }
    }


}
