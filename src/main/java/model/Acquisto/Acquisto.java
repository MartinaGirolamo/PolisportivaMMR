package model.Acquisto;

import java.sql.Date;

public class Acquisto {
    private String utente, codiceAbb;
    private Date dataAcquisto;

    public Acquisto() {
    }

    public String getUtente() {
        return utente;
    }

    public void setUtente(String utente) {
        this.utente = utente;
    }

    public String getCodiceAbb() {
        return codiceAbb;
    }

    public void setCodiceAbb(String codiceAbb) {
        this.codiceAbb = codiceAbb;
    }

    public Date getDataAcquisto() {
        return dataAcquisto;
    }

    public void setDataAcquisto(Date dataAcquisto) {
        this.dataAcquisto = dataAcquisto;
    }
}
