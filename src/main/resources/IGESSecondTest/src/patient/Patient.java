package patient;

import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.lang.reflect;
@Entity
@Table(name = "paziente")
public class Patient {
  @Id
  private String socialSecurityNumber;

  @Column
  private String username;

  @Column
  private String password;

  @Column
  private String email;

  @Column
  private String phoneNumber;

  @Column(name = "isinterno")
  private Boolean isInterno;

  @Column
  private String indirizzo;

  @Column
  private String nome;

  @Column
  private String cognome;

  public Patient() {

  }

  /**
   * Permette di ottenere un paziente in base alle sue informazioni principali.
   *
   * @param socialSecurityNumber il codice fiscale del paziente
   *
   * @param username lo username per accedere alla piattaforma
   *
   * @param password la password per accedere alla piattaforma
   *
   * @param email la mail associata al paziente
   *
   * @param phoneNumber il telefono associato al paziente
   *
   * @param isInterno true se il paziente e' interno, false altrimenti
   *
   * @param indirizzo l'indirizzo di residenza del paziente
   *
   * @param nome il nome del paziente
   *
   * @param cognome il cognome del paziente
   */
  public Patient(String socialSecurityNumber, String username, String password, String email,
                 String phoneNumber, Boolean isInterno, String indirizzo,
                 String nome, String cognome) {
    this.socialSecurityNumber = socialSecurityNumber;
    this.username = username;
    this.password = password;
    this.email = email;
    this.phoneNumber = phoneNumber;
    this.isInterno = isInterno;
    this.indirizzo = indirizzo;
    this.nome = nome;
    this.cognome = cognome;
  }

  /**
   * Permette di ottenere il codice fiscale del paziente.
   *
   * @return il codice fiscale del paziente
   */
  public String getSocialSecurityNumber() {
    return socialSecurityNumber;
  }

  /**
   * Permette di impostare il codice fiscale al paziente.
   *
   * @param socialSecurityNumber il codice fiscale da impostare
   */
  public void setSocialSecurityNumber(String socialSecurityNumber) {
    this.socialSecurityNumber = socialSecurityNumber;
  }

  /**
   * Permette di ottenere lo username associato al paziente.
   *
   * @return lo username associato
   */
  public String getUsername() {
    return username;
  }

  /**
   * Permette di impostare uno username al paziente.
   *
   * @param username lo username da impostare
   */
  public void setUsername(String username) {
    this.username = username;
  }

  /**
   * Permette di ottenere la password associata al paziente.
   *
   * @return la password del paziente
   */
  public String getPassword() {
    return password;
  }

  /**
   * Permette di associare una password al paziente.
   *
   * @param password la password da associare
   */
  public void setPassword(String password) {
    this.password = password;
  }

  /**
   * Permette di ottenere la mail associata al paziente.
   *
   * @return la mail associata al paziente
   */
  public String getEmail() {
    return email;
  }

  /**
   * Permette di associare una mail al paziente.
   *
   * @param email la mail da associare
   */
  public void setEmail(String email) {
    this.email = email;
  }

  /**
   * Permette di ottenere il numero di telefono associato al paziente.
   *
   * @return il numero di telefono associato
   */
  public String getPhoneNumber() {
    return phoneNumber;
  }

  /**
   * Permette di associare un numero di telefono al paziente.
   *
   * @param phoneNumber un numero di telefono da associare al paziente
   */
  public void setPhoneNumber(String phoneNumber) {
    this.phoneNumber = phoneNumber;
  }

  /**
   * Permette di stabilire se un paziente e' esterno oppure interno.
   *
   * @return true se il paziente e' interno, false altrimenti
   */
  public Boolean getIsInterno() {
    return isInterno;
  }

  /**
   * Permette di impostare se un paziente e'esterno oppure interno.
   *
   * @param interno true se interno, false se esterno
   */
  public void setIsInterno(Boolean interno) {
    isInterno = interno;
  }

  /**
   * Permette di ottenere l'indirizzo associato al paziente.
   *
   * @return l'indirizzo associato al paziente
   */
  public String getIndirizzo() {
    return indirizzo;
  }

  /**
   * Permette di impostare l'indirizzo al paziente.
   *
   * @param indirizzo l'indirizzo da associare al paziente
   */
  public void setIndirizzo(String indirizzo) {
    this.indirizzo = indirizzo;
  }

  /**
   * Permette di ottenere il nome di un paziente.
   *
   * @return il nome del paziente
   */
  public String getNome() {
    return nome;
  }

  /**
   * Permette di impostare un nome al paziente.
   *
   * @param nome il nome da impostare al paziente
   */
  public void setNome(String nome) {
    this.nome = nome;
  }

  /**
   * Permette di ottenere il cognome del paziente.
   *
   * @return il cognome del paziente
   */
  public String getCognome() {
    return cognome;
  }

  /**
   * Permette di impostare un cognome al paziente.
   *
   * @param cognome un cognome da impostare al paziente
   */
  public void setCognome(String cognome) {
    this.cognome = cognome;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Patient patient = (Patient) o;
    return Objects.equals(socialSecurityNumber, patient.socialSecurityNumber)
            && Objects.equals(username, patient.username)
            && Objects.equals(password, patient.password)
            && Objects.equals(email, patient.email)
            && Objects.equals(phoneNumber, patient.phoneNumber)
            && Objects.equals(isInterno, patient.isInterno)
            && Objects.equals(indirizzo, patient.indirizzo)
            && Objects.equals(nome, patient.nome)
            && Objects.equals(cognome, patient.cognome);
  }

  @Override
  public int hashCode() {
    return Objects.hash(socialSecurityNumber, username, password, email, phoneNumber,
            isInterno, indirizzo, nome, cognome);
  }

  @Override
  public String toString() {
    return "Patient{"
            + "cf='" + socialSecurityNumber + '\''
            + ", username='" + username + '\''
            + ", password='" + password + '\''
            + ", mail='" + email + '\''
            + ", telefono='" + phoneNumber + '\''
            + ", isInterno=" + isInterno
            + ", indirizzo='" + indirizzo + '\''
            + ", nome='" + nome + '\''
            + ", cognome='" + cognome + '\''
            + '}';
  }
}
