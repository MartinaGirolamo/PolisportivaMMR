package model.Noleggio;

public class Noleggio {
    private String codicePren, codiceAttr;
    private int qta;

    public Noleggio() {
    }

    public String getCodicePren() {
        return codicePren;
    }

    public void setCodicePren(String codicePren) {
        this.codicePren = codicePren;
    }

    public String getCodiceAttr() {
        return codiceAttr;
    }

    public void setCodiceAttr(String codiceAttr) {
        this.codiceAttr = codiceAttr;
    }

    public int getQta() {
        return qta;
    }

    public void setQta(int qta) {
        this.qta = qta;
    }
}
