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
import java.sql.SQLException;

@WebServlet(name = "ServletRegister", value = "/ServletRegister")
public class ServletRegister extends HttpServlet {
    private UtenteDAO utenteDao;
    public ServletRegister(){
        this.utenteDao=new UtenteDAO();
    }

    public ServletRegister(UtenteDAO utenteDao){
        this.utenteDao = utenteDao;
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            registrazioneUtente(request, response);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);

    }

    private boolean checkPassword(String password){
        if(password.matches("((?=.*\\d)(?=.*[a-z])(?=.*[A-Z]).{8,20})")){
            return true;
        }
        return false;
    }



    public void registrazioneUtente(HttpServletRequest request,HttpServletResponse response) throws IOException, SQLException, ServletException {
        Utente user = (Utente) request.getSession().getAttribute("user");
        String nome = request.getParameter("nome");
        String cognome = request.getParameter("cognome");
        String email = request.getParameter("email");
        String psw = request.getParameter("psw");
        String dateN = request.getParameter("dateN");


        Utente utente= new Utente();
        if(user==null||user.getEmail()==null || !user.isIs_Admin()){

            utente.setNome(nome);
            utente.setCognome(cognome);
            utente.setEmail(email);
            utente.setPsword(psw);
            utente.setDateN(dateN);
            utente.setIs_Admin(false);
            System.out.println(utente.toString());
        }
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
        while(!checkPassword(psw)){
            RequestDispatcher requestDispatcher= request.getRequestDispatcher("interface/ErrorePasswordRegister.jsp");
            requestDispatcher.forward(request,response);
        }

        if(utenteDAO.controllaEmail(utente)){
            address="interface/EmailGiaInUso.jsp";
            System.out.println("email già presente in database");
            //request.getSession().setAttribute("error", true);
        //per fare il test
        }else{
            if(utenteDAO.insertUtente(utente)){
                System.out.println("Inserimento effettuato");
                if(utente.isIs_Admin()){
                    address="index.jsp";
                }
                else{
                    address="interface/RegistrazioneEffettuata.jsp";
                }
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
}
