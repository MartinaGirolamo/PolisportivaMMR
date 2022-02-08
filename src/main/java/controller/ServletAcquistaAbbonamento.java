package controller;


import model.Abbonamento.Abbonamento;
import model.Abbonamento.AbbonamentoDAO;
import model.Acquisto.Acquisto;
import model.Acquisto.AcquistoDAO;
import model.Utente.Utente;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;


@WebServlet(name = "ServletAcquistaAbbonamento", value = "/ServletAcquistaAbbonamento")
public class ServletAcquistaAbbonamento extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Utente user=(Utente) req.getSession().getAttribute("user");
        String nMesiString = (String) req.getParameter("nMesi");
        String tipologia= (String) req.getParameter("abb");
        System.out.println("tipologia= "+tipologia);
        int nMesi=0;
        if(nMesiString!=null){
           nMesi = Integer.parseInt(nMesiString);
        }
        System.out.println("nMesi= "+nMesi);
        AbbonamentoDAO ad = new AbbonamentoDAO();
        Abbonamento abbonamento = ad.selectAbbonamentoByTipologiaAndMesi(tipologia,nMesi);
        System.out.println(""+abbonamento);
        if(user == null || user.getEmail()==null){
            RequestDispatcher requestDispatcher= req.getRequestDispatcher("view/UtenteNonLoggato.jsp");
            requestDispatcher.forward(req, resp);
        }

        else if(tipologia!=null && nMesi!=0){
            AcquistoDAO acquistoDAO = new AcquistoDAO();
            Acquisto acquisto = new Acquisto();
            LocalDate todaysDate = LocalDate.now();
            java.sql.Date data = java.sql.Date.valueOf(todaysDate);
            acquisto.setDataAcquisto(data);
            acquisto.setUtente(user.getEmail());
            acquisto.setCodiceAbb(abbonamento.getCodice());
            if(acquistoDAO.acquistoGiaPresente(acquisto)){
                RequestDispatcher requestDispatcher = req.getRequestDispatcher("view/AcquistoNonPossibile.jsp");
                requestDispatcher.forward(req, resp);
            }
            if (acquistoDAO.insertAcquisto(acquisto)) {
                System.out.println("Acquisto effettuato: " + acquisto);
                RequestDispatcher requestDispatcher = req.getRequestDispatcher("view/AcquistoEffettuato.jsp");
                requestDispatcher.forward(req, resp);
            } else {
                System.out.println("Acquisto NON effettuato: ");
                RequestDispatcher requestDispatcher = req.getRequestDispatcher("view/Error500.jsp");
                requestDispatcher.forward(req, resp);
            }
        }
        else{
            System.out.println("tipologia e nMesi null");
            RequestDispatcher requestDispatcher = req.getRequestDispatcher("view/Error500.jsp");
            requestDispatcher.forward(req, resp);
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
