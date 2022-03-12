package storage.Campo;

import java.util.Objects;

public class Campo {

    private String nome,descrizione;
    private int numGiocatori;
    private float tariffa;

    public Campo(){}

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    public float getTariffa() {
        return tariffa;
    }

    public void setTariffa(float tariffa) {
        this.tariffa = tariffa;
    }

    public int getNumGiocatori() {
        return numGiocatori;
    }

    public void setNumGiocatori(int numGiocatori) {
        this.numGiocatori = numGiocatori;
    }

    @Override
    public String toString() {
        return "Campo{" +
                "nome='" + nome + '\'' +
                ", descrizione='" + descrizione + '\'' +
                ", numGiocatori=" + numGiocatori +
                ", tariffa=" + tariffa +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Campo campo = (Campo) o;
        return numGiocatori == campo.numGiocatori && Float.compare(campo.tariffa, tariffa) == 0 && Objects.equals(nome, campo.nome) && Objects.equals(descrizione, campo.descrizione);
    }

}
