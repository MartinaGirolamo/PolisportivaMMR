package model.Prenotazione;


import java.sql.Date;

public class Prenotazione {
    private int oraStart, oraEnd;
    private Date dateP;
    private String email, nomeCampo, codice;

    public Prenotazione(){}

    public String getCodice() {
        return codice;
    }

    public void setCodice(String codice) {
        this.codice = codice;
    }

    public int getOraStart() {
        return oraStart;
    }

    public void setOraStart(int oraStart) {
        this.oraStart = oraStart;
    }

    public int getOraEnd() {
        return oraEnd;
    }

    public void setOraEnd(int oraEnd) {
        this.oraEnd = oraEnd;
    }

    public Date getDateP() {
        return dateP;
    }

    public void setDateP(Date dateP) {
        this.dateP = dateP;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNomeCampo() {
        return nomeCampo;
    }

    public void setNomeCampo(String nome) {
        this.nomeCampo = nome;
    }
}
