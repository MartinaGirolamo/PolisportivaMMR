package storage.Prenotazione;


import java.sql.Date;
import java.util.Comparator;
import java.util.Objects;

public class Prenotazione {
    private int oraStart, oraEnd, codice;
    float tariffaTotale;
    private Date dateP;
    private String email, nomeCampo;

    public Prenotazione(){}

    public Prenotazione(int oraStart, int oraEnd, int codice, float tariffaTotale, Date dateP, String email, String nomeCampo) {
        this.oraStart = oraStart;
        this.oraEnd = oraEnd;
        this.codice = codice;
        this.tariffaTotale = tariffaTotale;
        this.dateP = dateP;
        this.email = email;
        this.nomeCampo = nomeCampo;
    }

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

    public float getTariffaTotale() {
        return tariffaTotale;
    }

    public void setTariffaTotale(float tariffaTotale) {
        this.tariffaTotale = tariffaTotale;
    }

    @Override
    public String toString() {
        return "Prenotazione{" +
                "oraStart=" + oraStart +
                ", oraEnd=" + oraEnd +
                ", codice=" + codice +
                ", tariffaTotale=" + tariffaTotale +
                ", dateP=" + dateP +
                ", email='" + email + '\'' +
                ", nomeCampo='" + nomeCampo + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Prenotazione that = (Prenotazione) o;
        return oraStart == that.oraStart && oraEnd == that.oraEnd && codice == that.codice && Float.compare(that.tariffaTotale, tariffaTotale) == 0 && Objects.equals(dateP, that.dateP) && Objects.equals(email, that.email) && Objects.equals(nomeCampo, that.nomeCampo);
    }


}
