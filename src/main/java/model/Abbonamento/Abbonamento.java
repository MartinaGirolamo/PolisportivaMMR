package model.Abbonamento;

public class Abbonamento {
    private String tipologia;
    private int mesi, codice;
    private float tariffa;

    public Abbonamento(){}

    public int getCodice() {
        return codice;
    }

    public void setCodice(int codice) {
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
