package application;

import storage.Attrezzatura.Attrezzatura;
import storage.Attrezzatura.AttrezzaturaDAO;
import storage.Noleggio.Noleggio;
import storage.Noleggio.NoleggioDAO;
import storage.Prenotazione.Prenotazione;
import storage.Utente.Utente;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet(name = "ServletNoleggia", value = "/ServletNoleggia")
public class ServletNoleggia extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Prenotazione prenotazione = (Prenotazione) req.getSession().getAttribute("prenotazioneEffettuata");
        AttrezzaturaDAO ad = new AttrezzaturaDAO();
        ArrayList<Attrezzatura> list = ad.selectAttrezzaturaByTipologia(prenotazione.getNomeCampo());
        Utente user=(Utente) req.getSession().getAttribute("user");
        String tipologia = list.get(0).getTipologia();
        String[] listaParametri =  req.getParameterValues(tipologia);
        NoleggioDAO noleggioDAO = new NoleggioDAO();

        for (int i = 0; i < listaParametri.length; i++) {
            Attrezzatura a = ad.getAttrezzaturaFromNome(listaParametri[i]);
            String qtaString= req.getParameter(listaParametri[i]);
            int qta = 0;
            if(qtaString!=null) {
                qta = Integer.parseInt(qtaString);
            }
            Noleggio n = new Noleggio();
            n.setCodiceAttr(a.getCodice());
            n.setCodicePren(prenotazione.getCodice());
            n.setQta(qta);
            System.out.println("codiceAttrezzatura: "+a.getCodice()+" codicePrenotazione: "+prenotazione.getCodice()+" qta: "+qta);
            if(noleggioDAO.insertNoleggio(n)){
                RequestDispatcher requestDispatcher = req.getRequestDispatcher("interface/NoleggioEffettuato.jsp");
                requestDispatcher.forward(req, resp);
            }
            else {

                RequestDispatcher requestDispatcher= req.getRequestDispatcher("interface/Error500.jsp");
                requestDispatcher.forward(req, resp);
            }
        }






    }
}
