package application;

import storage.Utente.Utente;
import storage.Utente.UtenteDAO;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "ServletLogin", value = "/ServletLogin")
public class ServletLogin extends HttpServlet {
    private UtenteDAO utenteDao;

    public ServletLogin(){
        this.utenteDao = new UtenteDAO();
    }
    public ServletLogin(UtenteDAO utenteDao){
        this.utenteDao = utenteDao;
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        loginUtente(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
     doGet(request,response);
    }

    public void loginUtente(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email= request.getParameter("email");
        String password= request.getParameter("psw");
        System.out.println(email +" "+ password);

        Utente utente= utenteDao.selectUtenteByEmailPassword(email,password);
        //System.out.println("utente : "+utente.toString());
        String address=null;
        if(utente==null || utente.getEmail()== null || utente.getPsword()== null){
            address="interface/ErroreLogin.jsp";
            request.setAttribute("errore",true);
            HttpSession session = request.getSession(false);
            RequestDispatcher requestDispatcher= request.getRequestDispatcher(address);

        }
        else
        {
            address="index.jsp";
            HttpSession session = request.getSession(true);
            session.setAttribute("user", utente);

            RequestDispatcher requestDispatcher= request.getRequestDispatcher(address);

            requestDispatcher.forward(request, response);
        }


    }

    }
