package model.Prenotazione;

import java.sql.Date;

public class PrenotazioneDisponibile {
    private int oraStart, oraEnd;
    private Date date;
    private String nomeCampo;

    public PrenotazioneDisponibile(int oraStart, int oraEnd, Date date, String nomeCampo) {
        this.oraStart = oraStart;
        this.oraEnd = oraEnd;
        this.date = date;
        this.nomeCampo = nomeCampo;
    }

    public int getOraStart() {
        return oraStart;
    }

    public int getOraEnd() {
        return oraEnd;
    }

    public Date getDate() {
        return date;
    }

    public String getNomeCampo() {
        return nomeCampo;
    }

    @Override
    public String toString() {
        return "PrenotazioneDisponibile{" +
                "oraStart=" + oraStart +
                ", oraEnd=" + oraEnd +
                ", date=" + date +
                ", nomeCampo='" + nomeCampo + '\'' +
                '}';
    }
}
