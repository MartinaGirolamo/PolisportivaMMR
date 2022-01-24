package model.Campo;

public class Campo {

    private String nome,descrizione;
    private int tariffa, numGiocatori;

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

    public int getTariffa() {
        return tariffa;
    }

    public void setTariffa(int tariffa) {
        this.tariffa = tariffa;
    }

    public int getNumGiocatori() {
        return numGiocatori;
    }

    public void setNumGiocatori(int numGiocatori) {
        this.numGiocatori = numGiocatori;
    }
}
