package application;

import storage.Campo.Campo;
import storage.Campo.CampoDAO;
import storage.Prenotazione.Prenotazione;
import storage.Prenotazione.PrenotazioneDAO;
import storage.Prenotazione.PrenotazioneDisponibile;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;

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
        String stringOraStart, stringNumOre;
        int oraStart=0, numOre=0;
        ArrayList<Prenotazione> arrayPrenotazioniPresenti = null;
        CampoDAO campoDAO = new CampoDAO();
        Campo campo1 = campoDAO.selectCampoByNome(campo);
        System.out.println(campo1);

        java.sql.Date data = java.sql.Date.valueOf(datetimeString);
        stringOraStart =req.getParameter("oraStartScelta");
        stringNumOre= req.getParameter("numOreScelte");

        switch (stringOraStart){
            case "09:00":{ oraStart=9; break;}
            case "10:00":{ oraStart=10; break;}
            case "11:00":{ oraStart=11; break;}
            case "12:00":{ oraStart=12; break;}
            case "13:00":{ oraStart=13; break;}
            case "14:00":{ oraStart=14; break;}
            case "15:00":{ oraStart=15; break;}
            case "16:00":{ oraStart=16; break;}
            case "17:00":{ oraStart=17; break;}
            case "18:00":{ oraStart=18; break;}
            case "19:00":{ oraStart=19; break;}
            case "20:00":{ oraStart=20; break;}
            default:oraStart=0;

        }
        if(stringNumOre!=null ){;
            numOre = Integer.parseInt(stringNumOre);

        }
        float tariffaTotale=campo1.getTariffa()*numOre;

        System.out.println("campo = "+campo+" data= "+data+" oraStart= "+oraStart+" numOre= "+numOre+"");
        PrenotazioneDAO prenotazioneDAO = new PrenotazioneDAO();
        if(data!=null && campo!=null){
            arrayPrenotazioniPresenti =  prenotazioneDAO.selectPrenotazioniByDataAndCampo(data,campo);}
        /*STAMPA DI PROVA PRENOTAZIONI PRESENTI*/
        if(arrayPrenotazioniPresenti.size()!=0){
            System.out.println("PRENOTAZIONI GIA PRESENTI IN DB");
           for (int i=0; i<arrayPrenotazioniPresenti.size();i++ ){
               System.out.println(arrayPrenotazioniPresenti.get(i).toString());
           }
        }
        else{
        System.out.println("arrayPrenotazioniPresenti null");}

        ArrayList<PrenotazioneDisponibile> prenotazioniDisponibili = new ArrayList<>();
        if(arrayPrenotazioniPresenti.size()==0){
            for(int i = oraStart; i<=20; i++){
                PrenotazioneDisponibile pd;
                if((i+numOre)<=22){
                    pd= new PrenotazioneDisponibile(i,i+numOre,data,campo, tariffaTotale);
                    System.out.println(pd);
                    prenotazioniDisponibili.add(pd);
                }
            }
        }
        else if(arrayPrenotazioniPresenti.size()==1){
            for(int i = oraStart; i<=20; i++){
                PrenotazioneDisponibile pd;
                if(i>=arrayPrenotazioniPresenti.get(0).getOraEnd()|| i+numOre<arrayPrenotazioniPresenti.get(0).getOraStart()){
                    pd= new PrenotazioneDisponibile(i,i+numOre,data,campo,tariffaTotale);
                    prenotazioniDisponibili.add(pd);
                }
            }
        }

        else{
            for(int i=oraStart; i<23;i++){
                for(int j=0; j<arrayPrenotazioniPresenti.size()-1;j++){
                    if(i+numOre<=arrayPrenotazioniPresenti.get(j).getOraStart()){
                        if(j==0){
                            PrenotazioneDisponibile pd = new PrenotazioneDisponibile(i,i+numOre,data,campo,tariffaTotale);
                            prenotazioniDisponibili.add(pd);
                            break;
                        }
                        if(i>=arrayPrenotazioniPresenti.get(j-1).getOraEnd()){
                            PrenotazioneDisponibile pd = new PrenotazioneDisponibile(i,i+numOre,data,campo,tariffaTotale);
                            prenotazioniDisponibili.add(pd);
                            break;
                        }
                    }
                    else if(i>=arrayPrenotazioniPresenti.get(j).getOraEnd() && i+numOre<=arrayPrenotazioniPresenti.get(j+1).getOraStart()){
                        PrenotazioneDisponibile pd = new PrenotazioneDisponibile(i,i+numOre,data,campo,tariffaTotale);
                        prenotazioniDisponibili.add(pd);
                        break;
                    }

                }

            }
        }

        /*STAMPA DI PROVA*/
        if(prenotazioniDisponibili.size()!=0) {
            System.out.println("PRENOTAZIONI DISPONIBILI");
            for (int i = 0; i < prenotazioniDisponibili.size(); i++) {
                System.out.println(prenotazioniDisponibili.get(i).toString());
            }
        }
        else{
            System.out.println("prenotazioniDisponibili = null");
        }


        HttpSession session = req.getSession(true);
        session.setAttribute("listaPrenotazioniDisponibili", prenotazioniDisponibili);
        RequestDispatcher requestDispatcher= req.getRequestDispatcher("interface/visualizzaPrenotazioniDisponibili.jsp");
        requestDispatcher.forward(req, resp);
    }
}
