package storage.Attrezzatura;

import java.util.Objects;

public class Attrezzatura {
    private String nome, path, tipologia;
    private int qta, codice;
    private float tariffa;

    public Attrezzatura() {}

    public int getCodice() {
        return codice;
    }

    public void setCodice(int codice) {
        this.codice = codice;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getQta() {
        return qta;
    }

    public void setQta(int qta) {
        this.qta = qta;
    }

    public float getTariffa() {
        return tariffa;
    }

    public void setTariffa(float tariffa) {
        this.tariffa = tariffa;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getTipologia() {
        return tipologia;
    }

    public void setTipologia(String tipologia) {
        this.tipologia = tipologia;
    }

    @Override
    public String toString() {
        return "Attrezzatura{" +
                "nome='" + nome + '\'' +
                ", path='" + path + '\'' +
                ", tipologia='" + tipologia + '\'' +
                ", qta=" + qta +
                ", codice=" + codice +
                ", tariffa=" + tariffa +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Attrezzatura that = (Attrezzatura) o;
        return qta == that.qta && codice == that.codice && Float.compare(that.tariffa, tariffa) == 0 && Objects.equals(nome, that.nome) && Objects.equals(path, that.path) && Objects.equals(tipologia, that.tipologia);
    }

}
