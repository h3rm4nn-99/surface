package healthworker;

import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "agente_sanitario")
public class HealthWorker {
  @Id
  private String social_security_number;
  @Column
  private String username;
  @Column
  private String password;
  @Column
  private String mail;
  @Column
  private String phone_number;
  @Column
  private String ruolo;
  @Column
  private String cognome;
  @Column
  private String nome;
  @Column
  private String indirizzo;
  @Column
  private int idstruttura;

  public HealthWorker(String social_security_number, String username, String password,
                      String mail, String phone_number, String ruolo, String cognome,
                      String nome, String indirizzo, int idstruttura) {
    this.social_security_number = social_security_number;
    this.username = username;
    this.password = password;
    this.mail = mail;
    this.phone_number = phone_number;
    this.ruolo = ruolo;
    this.cognome = cognome;
    this.nome = nome;
    this.indirizzo = indirizzo;
    this.idstruttura = idstruttura;
  }

  public String getSocial_security_number() {
    return social_security_number;
  }

  public void setSocial_security_number(String social_security_number) {
    this.social_security_number = social_security_number;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getMail() {
    return mail;
  }

  public void setMail(String mail) {
    this.mail = mail;
  }

  public String getPhone_number() {
    return phone_number;
  }

  public void setPhone_number(String phone_number) {
    this.phone_number = phone_number;
  }

  public String getRuolo() {
    return ruolo;
  }

  public void setRuolo(String ruolo) {
    this.ruolo = ruolo;
  }

  public String getCognome() {
    return cognome;
  }

  public void setCognome(String cognome) {
    this.cognome = cognome;
  }

  public String getNome() {
    return nome;
  }

  public void setNome(String nome) {
    this.nome = nome;
  }

  public String getIndirizzo() {
    return indirizzo;
  }

  public void setIndirizzo(String indirizzo) {
    this.indirizzo = indirizzo;
  }

  public int getIdstruttura() {
    return idstruttura;
  }

  public void setIdstruttura(int idstruttura) {
    this.idstruttura = idstruttura;
  }

  public HealthWorker() {}

  @Override
  public String toString() {
    return "HealthWorker [cf=" 
      + social_security_number + ", cognome=" + cognome
      + ", idstruttura=" + idstruttura + ", indirizzo="
      + indirizzo + ", mail=" 
      + mail + ", nome=" + nome + ", password=" + password 
      + ", ruolo=" + ruolo
      + ", telefono=" + phone_number + ", username=" + username + "]";
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    HealthWorker that = (HealthWorker) o;
    return idstruttura == that.idstruttura
            && Objects.equals(social_security_number, that.social_security_number)
            && Objects.equals(username, that.username)
            && Objects.equals(password, that.password)
            && Objects.equals(mail, that.mail)
            && Objects.equals(phone_number, that.phone_number)
            && Objects.equals(ruolo, that.ruolo)
            && Objects.equals(cognome, that.cognome)
            && Objects.equals(nome, that.nome)
            && Objects.equals(indirizzo, that.indirizzo);
  }

  @Override
  public int hashCode() {
    return Objects.hash(social_security_number, username, password, mail, phone_number,
            ruolo, cognome, nome, indirizzo, idstruttura);
  }
}
