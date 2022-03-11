package storage.Prenotazione;

import java.sql.Date;
import java.util.Objects;

public class PrenotazioneDisponibile {
    private int oraStart, oraEnd;
    private float tariffaTotale;
    private Date date;
    private String nomeCampo;

    public PrenotazioneDisponibile(int oraStart, int oraEnd, Date date, String nomeCampo, float tariffaTotale) {
        this.oraStart = oraStart;
        this.oraEnd = oraEnd;
        this.date = date;
        this.nomeCampo = nomeCampo;
        this.tariffaTotale=tariffaTotale;
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

    public float getTariffaTotale() {
        return tariffaTotale;
    }

    public void setTariffaTotale(float tariffaTotale) {
        this.tariffaTotale = tariffaTotale;
    }

    @Override
    public String toString() {
        return "PrenotazioneDisponibile{" +
                "oraStart=" + oraStart +
                ", oraEnd=" + oraEnd +
                ", tariffaTotale=" + tariffaTotale +
                ", date=" + date +
                ", nomeCampo='" + nomeCampo + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        PrenotazioneDisponibile that = (PrenotazioneDisponibile) o;
        return oraStart == that.oraStart && oraEnd == that.oraEnd && Float.compare(that.tariffaTotale, tariffaTotale) == 0 && Objects.equals(date, that.date) && Objects.equals(nomeCampo, that.nomeCampo);
    }


}
