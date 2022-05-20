package Libreria.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import com.fasterxml.jackson.annotation.JsonIgnore;



 * Modella l'entità Locazione composta da scompartimento, locazione e ha 
 * una relazione @OneToOne con l'entità Libro.
 */

@Entity
@Table(name = "Locazione")
public class Locazione {
	
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	@Column(name =  "id_locazione")
	private int idLocazione;
	@Column(name="scompartimento_locazione")
	public 	String scompartimento;
	@Column(name="posizione_locazione")
	public int posizione;
	
	@JsonIgnore
	@OneToOne(mappedBy = "linked_locazione", fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
	public Libro libro;

	/**
	 * @return {int} idLocazione
	 */
	public int getIdLocazione() {
		return idLocazione;
	}

	/**
	 * @param {int} idLocazione da associare
	 * @return void
	 */
	public void setIdLocazione(int idLocazione) {
		this.idLocazione = idLocazione;
	}

	/**
	 * @return {String} scompartimento locazione
	 */
	public String getScompartimento() {
		return scompartimento;
	}

	/**
	 * @param {String} scompartimento da associare alla locazione
	 * @return void
	 */
	public void setScompartimento(String scompartimento) {
		this.scompartimento = scompartimento;
	}

	/**
	 * @return {int} posizione associata alla locazione
	 */
	public int getPosizione() {
		return posizione;
	}

	/**
	 * @param {int} posizione da associare alla locazione
	 * @return void
	 */
	public void setPosizione(int posizione) {
		this.posizione = posizione;
	}

	
	/**
	 * @return {Libro} libro associato alla locazione
	 */
	public Libro getLibro() {
		return libro;
	}
	
	/**
	 * @param {Libro} libro da associare a locazione
	 * @return void
	 */
	public void setLibro(Libro libro) {
		this.libro = libro;
	}

}
