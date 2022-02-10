package controller;


import model.Utente.Utente;
import model.Utente.UtenteDAO;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "ServletCambiaPassword", value = "/ServletCambiaPassword")
public class ServletCambiaPassword extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Utente user=(Utente) req.getSession().getAttribute("user");
        UtenteDAO utenteDAO = new UtenteDAO();
        String newPassword = req.getParameter("nuovaPassword");
        String verificaNuovaPassword = req.getParameter("verificaNuovaPassword");
        String passwordAttuale = req.getParameter("passwordAttuale");
        if(newPassword.equals(verificaNuovaPassword)) {
            if (utenteDAO.checkPassword(user, passwordAttuale)) {
                Utente utenteModifica = new Utente();
                utenteModifica.setCognome(user.getCognome());
                utenteModifica.setNome(user.getNome());
                utenteModifica.setDateN(user.getDateN());
                utenteModifica.setEmail(user.getEmail());
                utenteModifica.setIs_Admin(user.isIs_Admin());
                utenteModifica.setPsword(newPassword);
                if (utenteDAO.updateUtente(utenteModifica)) {
                    HttpSession session = req.getSession(true);
                    session.setAttribute("user", utenteModifica);
                    RequestDispatcher requestDispatcher = req.getRequestDispatcher("view/CambioPasswordEffettuato.jsp");
                    requestDispatcher.forward(req, resp);
                } else {
                    RequestDispatcher requestDispatcher = req.getRequestDispatcher("view/Error500.jsp");
                    requestDispatcher.forward(req, resp);
                }

            } else {
                RequestDispatcher requestDispatcher = req.getRequestDispatcher("view/CambioPasswordNonEffettuato.jsp");
                requestDispatcher.forward(req, resp);
            }
        }
        else{
            RequestDispatcher requestDispatcher = req.getRequestDispatcher("view/CambioPasswordNonEffettuato.jsp");
            requestDispatcher.forward(req, resp);
        }
    }



    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }
}
