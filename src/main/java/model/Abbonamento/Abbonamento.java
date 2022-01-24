package model.Abbonamento;

public class Abbonamento {
    private String codice,tipologia;
    private int mesi;
    private float tariffa;

    public Abbonamento(){}

    public String getCodice() {
        return codice;
    }

    public void setCodice(String codice) {
        this.codice = codice;
    }

    public String getTipologia() {
        return tipologia;
    }

    public void setTipologia(String tipologia) {
        this.tipologia = tipologia;
    }

    public int getMesi() {
        return mesi;
    }

    public void setMesi(int mesi) {
        this.mesi = mesi;
    }

    public float getTariffa() {
        return tariffa;
    }

    public void setTariffa(float tariffa) {
        this.tariffa = tariffa;
    }
}
