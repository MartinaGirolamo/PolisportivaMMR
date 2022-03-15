package application;

import storage.Utente.Utente;
import storage.Utente.UtenteDAO;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "ServletEliminaAmministratore", value = "/ServletEliminaUtente")
public class ServletEliminaUtente extends HttpServlet {
    private UtenteDAO utenteDao;
    public ServletEliminaUtente(){
        this.utenteDao=new UtenteDAO();
    }

    public ServletEliminaUtente(UtenteDAO utenteDao){
        this.utenteDao = utenteDao;
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        eliminaUtente(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            doGet(request,response);
    }

    public void eliminaUtente(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Utente user = (Utente) request.getSession().getAttribute("user");
        if(user.isIs_Admin()){
            UtenteDAO utenteDAO=new UtenteDAO();
            String email = request.getParameter("email");
            if(utenteDAO.deleteUtente(email)){
                RequestDispatcher requestDispatcher= request.getRequestDispatcher("interface/UtenteEliminato.jsp");
                requestDispatcher.forward(request, response);
            }
            else {
                RequestDispatcher requestDispatcher= request.getRequestDispatcher("interface/Error500.jsp");
                requestDispatcher.forward(request, response);
            }
        }


    }
}
