package model.Prenotazione;

import model.ConPool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

/* Il formato della data prenotazione deve essere: 'AAAA-MM-GG'*/

public class PrenotazioneDAO {

    public PrenotazioneDAO(){}

    public ArrayList<Prenotazione> selectPrenotazioniByDataAndCampo (Date dataPrenotazione, String nomeCampo){
        System.out.println("data: "+dataPrenotazione+" nomeCampo: "+nomeCampo);
        ArrayList<Prenotazione> list = new ArrayList<>();
        try(Connection conn= ConPool.getConnection()){
            PreparedStatement ps= conn.prepareStatement("SELECT * FROM prenotazione WHERE dateP= '"+dataPrenotazione+"' AND campo='"+nomeCampo+"';");
            ResultSet set = ps.executeQuery();
            while(set.next()){
               Prenotazione prenotazione = new Prenotazione();
               prenotazione.setDateP(set.getDate("dateP"));
               prenotazione.setOraStart(set.getInt("oraStart"));
               prenotazione.setOraEnd(set.getInt("oraEnd"));
               prenotazione.setCodice(set.getString("codice"));
               prenotazione.setNomeCampo(set.getString("campo"));
               prenotazione.setEmail(set.getString("utente"));

                list.add(prenotazione);
                prenotazione.toString();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public ArrayList<Prenotazione> selectPrenotazioneByUtente (String email){
        ArrayList<Prenotazione> list = new ArrayList<>();
        try(Connection conn= ConPool.getConnection()){
            PreparedStatement ps= conn.prepareStatement("SELECT * FROM prenotazione WHERE utente= '"+email+"';");
            ResultSet set = ps.executeQuery();
            while(set.next()){
                Prenotazione prenotazione = new Prenotazione();
                prenotazione.setDateP(set.getDate("dateP"));
                prenotazione.setOraStart(set.getInt("oraStart"));
                prenotazione.setOraEnd(set.getInt("oraEnd"));
                prenotazione.setCodice(set.getString("codice"));
                prenotazione.setNomeCampo(set.getString("campo"));
                prenotazione.setEmail(set.getString("utente"));

                list.add(prenotazione);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public boolean insertPrenotazione(Prenotazione prenotazione){
        try(Connection conn=ConPool.getConnection()) {
            PreparedStatement ps= conn.prepareStatement("INSERT INTO prenotazione ( codice, oraStart, oraEnd, dateP, utente, campo ) VALUES (?,?,?,?,?,?);");
            ps.setString(1,prenotazione.getCodice());
            ps.setInt(2,prenotazione.getOraStart());
            ps.setInt(3,prenotazione.getOraEnd());
            ps.setDate(4,prenotazione.getDateP());
            ps.setString(5,prenotazione.getEmail());
            ps.setString(6,prenotazione.getNomeCampo());

            int ritorno=ps.executeUpdate();
            if (ritorno==2) return false;
            else return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean deletePrenotazione( int codice) {
        try(Connection conn = ConPool.getConnection()){
            PreparedStatement ps = conn.prepareStatement("DELETE FROM prenotazione WHERE codice='"+codice+"'");
            int ritorno=ps.executeUpdate();
            if (ritorno==2) return false;
            else return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public int sizeNumeroPrenotazioni(){
        ArrayList<Prenotazione> list = new ArrayList<>();
        try(Connection conn= ConPool.getConnection()){
            PreparedStatement ps= conn.prepareStatement("SELECT * FROM prenotazione;");
            ResultSet set = ps.executeQuery();
            while(set.next()){
                Prenotazione prenotazione = new Prenotazione();
                prenotazione.setDateP(set.getDate("dateP"));
                prenotazione.setOraStart(set.getInt("oraStart"));
                prenotazione.setOraEnd(set.getInt("oraEnd"));
                prenotazione.setCodice(set.getString("codice"));
                prenotazione.setNomeCampo(set.getString("campo"));
                prenotazione.setEmail(set.getString("utente"));

                list.add(prenotazione);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list.size();
    }


    }

