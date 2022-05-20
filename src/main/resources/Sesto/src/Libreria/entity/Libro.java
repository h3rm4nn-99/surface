package Libreria.entity;

import java.util.Date;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;



/**
 * Modella l'entità Libro che è composta da nome, titolo,trama, ha una relazione
 * @ManyToMany con l'entità Autore, una relazione @OneToOne con l'entità
 * Locazione e una relazione @ManyToOne con l'entità CasaEditirce.
 */
@Entity
@Table( name = "Libro")
public class Libro {

	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	@Column(name = "id_libro")
	private int idLibro;
	
	@Column(name ="titolo_libro")
	public String titolo;
	@Column(name="trama_libro")
	public String trama;
	@Column(name="data_uscita_libro")
	public Date dataUscita;
	
	
	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinTable(
			name = "linked_autore",
			joinColumns = @JoinColumn(name = "id_libro"),
			inverseJoinColumns = @JoinColumn(name = "id_autore")
	)
	List<Autore> linkedAutori = new ArrayList<Autore>();
	
	@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "id_locazione", referencedColumnName = "id_locazione")
	private Locazione linked_locazione;
	
	
	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "id_casa_editrice")
	private CasaEditrice casaEditrice;
	
	public Libro() {
		
	}
	
	/**
	 * @return {int} idLibro
	 */
	public int getIdLibro() {
		return idLibro;
	}

	/**
	 * @param {int} idLibro da associare
	 * @return void
	 */
	public void setIdLibro(int idLibro) {
		this.idLibro = idLibro;
	}

	/**
	 * @return {String} titolo del libro
	 */
	public String getTitolo() {
		return titolo;
	}

	/**
	 * @param {String} titolo da associare al libro
	 * @return void
	 */
	public void setTitolo(String titolo) {
		this.titolo = titolo;
	}

	/**
	 * @return {String} trama del libro
	 */
	public String getTrama() {
		return trama;
	}

	/**
	 * @return void
	 * @param {String} trama da associare al libro
	 */
	public void setTrama(String trama) {
		this.trama = trama;
	}
	
	/**
	 * @return {Date} data pubblicazione libro
	 */
	public Date getDataUscita() {
		return dataUscita;
	}

	/**
	 * @param {Date} data di pubblicazione da associare al libro
	 * @return void
	 */
	public void setDataUscita(Date dataUscita) {
		this.dataUscita = dataUscita;
	}

	/**
	 * @return {List<Autore>} lista di autori associati al libro
	 */
	public List<Autore> getLinkedAutori() {
		return linkedAutori;
	}

	/**
	 * @return void
	 * @param {List<Autore>} lista di autori da associare al libro
	 */
	public void setLinkedAutori(List<Autore> linkedAutori) {
		this.linkedAutori = linkedAutori;
	}

	/**
	 * @return {Locazione} locazione associata al libro
	 */
	public Locazione getLinked_locazione() {
		return linked_locazione;
	}

	/**
	 * @param {Locazione} locazione da associare al libro
	 * @return void
	 */
	public void setLinked_locazione(Locazione linked_locazione) {
		this.linked_locazione = linked_locazione;
	}

	/**
	 * @return {CasaEditrice} casaEditriceDTO associata al libro
	 */
	public CasaEditrice getCasaEditrice() {
		return casaEditrice;
	}
	
	/**
	 * @param {CasaEditrice} casaEditrice da associare al libro
	 * @return void
	 */
	public void setCasaEditrice(CasaEditrice casaEditrice) {
		this.casaEditrice = casaEditrice;
	}
	
	
}
