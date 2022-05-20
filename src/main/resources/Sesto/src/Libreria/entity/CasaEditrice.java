package Libreria.entity;

import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;


/**
 * Modella l'entità CasaEditrice composta da un nome e una relazione
 * OneToMany con l'entita Libro.
 */
@Entity
@Table(name ="CasaEditrice")
public class CasaEditrice {
	
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	@Column(name = "id_casa_editice")
	private int idCasaEditrice;

	@Column(name="nome_casa_editrice")
	public String nome;

	
	@OneToMany(mappedBy = "casaEditrice", fetch = FetchType.EAGER, cascade = CascadeType.MERGE, orphanRemoval = true)
	List<Libro> libri;
	
	/**
	 * @return {int} idCasaEditrice
	 */
	public int getIdCasaEditrice() {
		return idCasaEditrice;
	}

	/**
	 * @return void
	 * @param {int} idCasaEditrice
	 */
	public void setIdCasaEditrice(int idCasaEditrice) {
		this.idCasaEditrice = idCasaEditrice;
	}

	/**
	 * @return {String} nomeCasaEditrice
	 */
	public String getNome() {
		return nome;
	}

	/**
	 * @return void
	 * @param {String} nomeCasaEditrice
	 */
	public void setNome(String nome) {
		this.nome = nome;
	}

	/**
	 * @return {List<Libro>} lista di libri associati alla casaEditrice
	 */
	public List<Libro> getLibri() {
		return libri;
	}

	/**
	 * @return void
	 * @param {List<Libro>} lista di libri da associare alla casaEditrice
	 */
	public void setLibri(List<Libro> libri) {
		this.libri = libri;
	}

	
	
}
