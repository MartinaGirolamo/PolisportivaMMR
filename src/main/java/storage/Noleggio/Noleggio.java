package storage.Noleggio;

import java.util.Objects;

public class Noleggio {
    private int codicePren, codiceAttr;
    private int qta;

    public Noleggio() {
    }

    public int getCodicePren() {
        return codicePren;
    }

    public void setCodicePren(int codicePren) {
        this.codicePren = codicePren;
    }

    public int getCodiceAttr() {
        return codiceAttr;
    }

    public void setCodiceAttr(int codiceAttr) {
        this.codiceAttr = codiceAttr;
    }

    public int getQta() {
        return qta;
    }

    public void setQta(int qta) {
        this.qta = qta;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Noleggio noleggio = (Noleggio) o;
        return codicePren == noleggio.codicePren && codiceAttr == noleggio.codiceAttr && qta == noleggio.qta;
    }

}
