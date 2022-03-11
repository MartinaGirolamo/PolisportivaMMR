package storage.Utente;

import java.util.Objects;

public class Utente {
    private String email,  psword, nome, cognome, dateN ;
    private boolean is_Admin;

    public Utente (){}

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPsword() {
        return psword;
    }

    public void setPsword(String psword) {
        this.psword = psword;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCognome() {
        return cognome;
    }

    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    public String getDateN() {
        return dateN;
    }

    public void setDateN(String dateN) {
        this.dateN = dateN;
    }

    public boolean isIs_Admin() {
        return is_Admin;
    }

    public void setIs_Admin(boolean is_Admin) {
        this.is_Admin = is_Admin;
    }

    @Override
    public String toString() {
        return "Utente{" +
                "email='" + email + '\'' +
                ", nome='" + nome + '\'' +
                ", cognome='" + cognome + '\'' +
                ", dateN='" + dateN + '\'' +
                ", is_Admin=" + is_Admin +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        Utente utente = (Utente) o;
        return is_Admin == utente.is_Admin && Objects.equals(email, utente.email) && Objects.equals(psword, utente.psword) && Objects.equals(nome, utente.nome) && Objects.equals(cognome, utente.cognome) && Objects.equals(dateN, utente.dateN);
    }


}
