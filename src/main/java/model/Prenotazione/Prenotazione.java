package model.Prenotazione;

public class Prenotazione {
    private int codice, oraStart, oraEnd, dateP;
    private String email, nome;

    public Prenotazione(){}

    public int getCodice() {
        return codice;
    }

    public void setCodice(int codice) {
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

    public int getDateP() {
        return dateP;
    }

    public void setDateP(int dateP) {
        this.dateP = dateP;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
