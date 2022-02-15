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

@WebServlet(name = "ServletRegister", value = "/ServletRegister")
public class ServletRegister extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Utente user = (Utente) request.getSession().getAttribute("user");
        String nome = request.getParameter("nome");
        String cognome = request.getParameter("cognome");
        String email = request.getParameter("email");
        String psw = request.getParameter("psw");
        String dateN = request.getParameter("dateN");

        Utente utente= new Utente();
        if(user==null){

        utente.setNome(nome);
        utente.setCognome(cognome);
        utente.setEmail(email);
        utente.setPsword(psw);
        utente.setDateN(dateN);
        utente.setIs_Admin(false);
        System.out.println(utente.toString());}
        else if(user.isIs_Admin()){
            utente.setNome(nome);
            utente.setCognome(cognome);
            utente.setEmail(email);
            utente.setPsword(psw);
            utente.setDateN(dateN);
            utente.setIs_Admin(true);
            System.out.println(utente.toString());
        }


        UtenteDAO utenteDAO = new UtenteDAO();
        String address=null;

        if(utenteDAO.controllaEmail(utente)){
            address="interface/EmailGiaInUso.jsp";
            System.out.println("email già presente in database");
        }else{
            if(utenteDAO.insertUtente(utente)){
                System.out.println("Inserimento effettuato");
                if(user.isIs_Admin()){
                    address="index.jsp";
                }
                address="interface/RegistrazioneEffettuata.jsp";
            }
            else{
                System.out.println("Inserimento NON effettuato");
                address="interface/RegistrazioneNonEffettuata.jsp";
            }
        }


        if(!utente.isIs_Admin()){
        HttpSession session = request.getSession(true);
        session.setAttribute("user", utente);}
        RequestDispatcher requestDispatcher= request.getRequestDispatcher(address);
        requestDispatcher.forward(request, response);



    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }
}
