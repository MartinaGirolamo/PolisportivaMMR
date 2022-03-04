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
import java.lang.reflect.Array;
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
        if(listaParametri==null){
            RequestDispatcher requestDispatcher= req.getRequestDispatcher("interface/errorNoleggio.jsp");
            requestDispatcher.forward(req, resp);
        }
        for(int i = 0; i< listaParametri.length;i++){
            System.out.println(listaParametri[i]);}
        ArrayList<Noleggio> listNoleggi = new ArrayList<>();
        for (int i = 0; i < listaParametri.length; i++) {
            Attrezzatura a = ad.getAttrezzaturaFromNome(listaParametri[i]);
            String qtaString = req.getParameter(listaParametri[i]);
            int qta = 0;
            if (qtaString != "") {
                qta = Integer.parseInt(qtaString);
            }
            if(qta==0) {
                RequestDispatcher requestDispatcher = req.getRequestDispatcher("interface/errorNoleggio.jsp");
                requestDispatcher.forward(req, resp);

            }
            else {
                Noleggio n = new Noleggio();
                n.setCodiceAttr(a.getCodice());
                n.setCodicePren(prenotazione.getCodice());
                n.setQta(qta);
                listNoleggi.add(n);
                System.out.println("codiceAttrezzatura: " + a.getCodice() + " codicePrenotazione: " + prenotazione.getCodice() + " qta: " + qta);
            }
            }
        boolean effettuato=true;
        for (int i = 0; i<listNoleggi.size() && effettuato;i++) {
            Noleggio n = listNoleggi.get(i);
            if(noleggioDAO.insertNoleggio(n)){
                effettuato=true;
            }
            else {
                effettuato=false;
            }
        }

        if(effettuato){
            RequestDispatcher requestDispatcher = req.getRequestDispatcher("interface/NoleggioEffettuato.jsp");
            requestDispatcher.forward(req, resp);
        }
        else {
            RequestDispatcher requestDispatcher= req.getRequestDispatcher("interface/Error500.jsp");
            requestDispatcher.forward(req, resp);
        }






    }
}
