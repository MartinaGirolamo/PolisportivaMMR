package controller;

import model.Attrezzatura.Attrezzatura;
import model.Attrezzatura.AttrezzaturaDAO;
import model.Prenotazione.Prenotazione;
import model.Utente.Utente;

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

        for (int i = 0; i < listaParametri.length; i++) {

        }


    }
}
