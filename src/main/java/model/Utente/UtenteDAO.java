package model.Utente;

import model.ConPool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class UtenteDAO {

    public UtenteDAO(){}

    public ArrayList<Utente> selectAllUtenti(){
        ArrayList<Utente> list = new ArrayList<>();
        try(Connection conn= ConPool.getConnection()){
            PreparedStatement ps= conn.prepareStatement("SELECT * FROM Utente;");
            ResultSet set = ps.executeQuery();
            while(set.next()){
                Utente utente = new Utente();
                utente.setIs_Admin(set.getBoolean("is_admin"));//is_admin in parentesi è quello del database (NOME)
               utente.setEmail(set.getString("email"));
               utente.setPsword(set.getString("pword"));
               utente.setNome(set.getString("nome"));
               utente.setCognome(set.getString("cognome"));
               utente.setDateN(set.getString("dateN"));
                list.add(utente);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public Utente selectUtenteByEmailPassword(String email,String password){
        Utente utenteRitorno = new Utente();
        try(Connection conn= ConPool.getConnection()){
            PreparedStatement ps= conn.prepareStatement("SELECT * FROM Utente WHERE email = '"+email+"' AND pword = '"+password+"'");
            //ps.setString(1, email);
            ResultSet set = ps.executeQuery();
            while(set.next()){
            utenteRitorno.setEmail(set.getString("email"));
            System.out.println(utenteRitorno.getEmail());
                utenteRitorno.setIs_Admin(set.getBoolean("is_admin"));//IfAdmin in parentesi è quello del database (NOME)
                utenteRitorno.setPsword(set.getString("pword"));
                utenteRitorno.setNome(set.getString("nome"));
                utenteRitorno.setCognome(set.getString("cognome"));
                utenteRitorno.setDateN(set.getString("dateN"));
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return utenteRitorno;
    }

    public boolean insertUtente(Utente utente){
        try(Connection conn=ConPool.getConnection()) {
            PreparedStatement ps= conn.prepareStatement("INSERT INTO utente ( email,pword,  nome, cognome, is_Admin, dateN ) VALUES (?,?,?,?,?,?);");
            ps.setString(1, utente.getEmail());
            ps.setString(2,utente.getPsword());
            ps.setString(3,utente.getNome());
            ps.setString(4, utente.getCognome());
            ps.setBoolean(5, utente.isIs_Admin());
            ps.setString(6,utente.getDateN());


            int ritorno=ps.executeUpdate();
            if (ritorno==2) return false;
            else return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean deleteUtente(String email) {
        try(Connection conn = ConPool.getConnection()){
            PreparedStatement ps = conn.prepareStatement("DELETE FROM utente WHERE email='"+email+"'");
           // ps.setString(1,email);
            int ritorno=ps.executeUpdate();
            if (ritorno==2) return false;
            else return true;
        } catch (SQLException e) {
        e.printStackTrace();
    }
        return false;
    }


    public boolean updateUtente(Utente utente){
        try(Connection conn=ConPool.getConnection()) {
            PreparedStatement ps= conn.prepareStatement("UPDATE utente SET pword='"+utente.getPsword()+"'WHERE email='"+utente.getEmail()+"';");
            System.out.println(utente);
            int ritorno=ps.executeUpdate();
            if (ritorno==2) return false;
            else return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean checkPassword(Utente u, String vecchiaPassword){
        Utente utenteRitorno =new Utente();
        try(Connection conn=ConPool.getConnection()) {
            PreparedStatement ps= conn.prepareStatement("SELECT * FROM UTENTE where email='"+u.getEmail()+"' and pword='"+vecchiaPassword+"'");
            ResultSet set = ps.executeQuery();
            while(set.next()){
                utenteRitorno.setEmail(set.getString("email"));
                System.out.println(utenteRitorno.getEmail());
                utenteRitorno.setIs_Admin(set.getBoolean("is_admin"));//IfAdmin in parentesi è quello del database (NOME)
                utenteRitorno.setPsword(set.getString("pword"));
                utenteRitorno.setNome(set.getString("nome"));
                utenteRitorno.setCognome(set.getString("cognome"));
                utenteRitorno.setDateN(set.getString("dateN"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        if(utenteRitorno.getPsword()!=null)return true;
        else return false;
    }



    public boolean controllaEmail(Utente utente){

        Utente utenteRitorno = new Utente();
        try(Connection conn = ConPool.getConnection()){

            PreparedStatement ps= conn.prepareStatement("SELECT * FROM utente WHERE email = '"+utente.getEmail()+"'");
            //ps.setString(1, email);
            ResultSet set = ps.executeQuery();
            while(set.next()){
                utenteRitorno.setEmail(set.getString("email"));
                System.out.println(utenteRitorno.getEmail());
                utenteRitorno.setIs_Admin(set.getBoolean("is_admin"));//IfAdmin in parentesi è quello del database (NOME)
                utenteRitorno.setPsword(set.getString("pword"));
                utenteRitorno.setNome(set.getString("nome"));
                utenteRitorno.setCognome(set.getString("cognome"));
                utenteRitorno.setDateN(set.getString("dateN"));
            }

            if(utenteRitorno.getEmail()!=null) {System.out.println("Utente ritorno del DAO:"+utenteRitorno.toString()); return true;}
            else return false;


        }catch(SQLException e){
            System.out.println("non presente in Database");
            return false;
        }


    }


}
