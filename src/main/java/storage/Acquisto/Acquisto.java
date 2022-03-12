package storage.Acquisto;

import java.sql.Date;
import java.util.Objects;

public class Acquisto {
    private String utente;
    private int codiceAbb, nMesi;
    private Date dataAcquisto;

    public Acquisto() {
    }

    public Acquisto(String utente, int codiceAbb, int nMesi, Date dataAcquisto) {
        this.utente = utente;
        this.codiceAbb = codiceAbb;
        this.nMesi = nMesi;
        this.dataAcquisto = dataAcquisto;
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

    public int getnMesi() {
        return nMesi;
    }

    public void setnMesi(int nMesi) {
        this.nMesi = nMesi;
    }

    @Override
    public String toString() {
        return "Acquisto{" +
                "utente='" + utente + '\'' +
                ", codiceAbb=" + codiceAbb +
                ", nMesi=" + nMesi +
                ", dataAcquisto=" + dataAcquisto +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Acquisto acquisto = (Acquisto) o;
        return codiceAbb == acquisto.codiceAbb && nMesi == acquisto.nMesi && Objects.equals(utente, acquisto.utente) && Objects.equals(dataAcquisto, acquisto.dataAcquisto);
    }


}
