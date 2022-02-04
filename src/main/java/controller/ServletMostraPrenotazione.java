package controller;

import model.Prenotazione.Prenotazione;
import model.Prenotazione.PrenotazioneDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

@WebServlet (name = "ServletMostraPrenotazione", value = "/ServletMostraPrenotazione")
public class ServletMostraPrenotazione extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String campo= req.getParameter("campoScelto");
        String datetimeString= req.getParameter("dataScelta");
        Date result=null;
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        if(datetimeString!=null) {
            try {
                result = formatter.parse(datetimeString);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        String stringOraStart, stringNumOre;
        int oraStart=0, numOre=0;
        stringOraStart =req.getParameter("oraStartScelta");
        stringNumOre= req.getParameter("numOreScelte");
        if(stringNumOre!=null && stringOraStart!=null) {
            oraStart = Integer.parseInt(stringOraStart);
            numOre = Integer.parseInt(stringNumOre);
        }

        System.out.println("campo = "+campo+" data= "+result+" oraStart= "+oraStart+" numOre= "+numOre);
        PrenotazioneDAO prenotazioneDAO = new PrenotazioneDAO();
        if(result!=null && campo!=null){
        ArrayList<Prenotazione> arrayPrenotazioniPresenti =  prenotazioneDAO.selectPrenotazioniByDataAndCampo(result,campo);}


    }
}
