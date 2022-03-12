package storage.Abbonamento;

import java.util.Objects;

public class Abbonamento {
    private String tipologia;
    private int codice;
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

    public float getTariffa() {
        return tariffa;
    }

    public void setTariffa(float tariffa) {
        this.tariffa = tariffa;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Abbonamento that = (Abbonamento) o;
        return codice == that.codice && Float.compare(that.tariffa, tariffa) == 0 && Objects.equals(tipologia, that.tipologia);
    }

}
