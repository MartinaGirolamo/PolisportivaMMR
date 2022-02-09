package model.Acquisto;

import java.sql.Date;

public class Acquisto {
    private String utente;
    private int codiceAbb;
    private Date dataAcquisto;

    public Acquisto() {
    }

    public String getUtente() {
        return utente;
    }

    public void setUtente(String utente) {
        this.utente = utente;
    }

    public int getCodiceAbb() {
        return codiceAbb;
    }

    public void setCodiceAbb(int codiceAbb) {
        this.codiceAbb = codiceAbb;
    }

    public Date getDataAcquisto() {
        return dataAcquisto;
    }

    public void setDataAcquisto(Date dataAcquisto) {
        this.dataAcquisto = dataAcquisto;
    }

    @Override
    public String toString() {
        return "Acquisto{" +
                "utente='" + utente + '\'' +
                ", codiceAbb=" + codiceAbb +
                ", dataAcquisto=" + dataAcquisto +
                '}';
    }
}
