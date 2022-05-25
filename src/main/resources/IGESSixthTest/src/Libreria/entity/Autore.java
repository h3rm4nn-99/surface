package Libreria.entity;

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
import javax.persistence.Table;

/*
 * Modella l'entità Autore composta de nome, cognome e ha una relazione
 * @ManyToMany con l'entità Libro.
 */
@Entity
@Table (name = "Autore")
public class Autore {
	
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	@Column(name = "id_autore")
	private int idAutore;
	@Column(name = "nome_autore")
	public String nome;
	@Column(name ="cognome_autore")
	public String cognome;
	
	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinTable(
			name = "linked_autore",
			joinColumns = @JoinColumn(name = "id_autore"),
			inverseJoinColumns = @JoinColumn(name = "id_libro")
	)
	List<Libro> libri = new ArrayList<Libro>();

	/**
	 * @return {int} idAutore
	 */
	public int getId_autore() {
		return idAutore;
	}

	/**
	 * @return void
	 * @param {int} idAutore
	 */
	public void setIdAutore(int idAutore) {
		this.idAutore = idAutore;
	}
	
	/**
	 * @return {String} nome Autore
	 */
	public String getNome() {
		return nome;
	}

	/**
	 * @return void
	 * @param {String} nome Autore
	 */
	public void setNome(String nome) {
		this.nome = nome;
	}

	/**
	 * @return {String} cognome Autore
	 */
	public String getCognome() {
		return cognome;
	}
	
	/**
	 * @return void
	 * @param {String} cognome Autore
	 */
	public void setCognome(String cognome) {
		this.cognome = cognome;
	}

	/**
	 * @return {List<Libro>} lista di libri associati ad Autore
	 */
	public List<Libro> getLibri() {
		return libri;
	}

	/**
	 * @return void
	 * @param {List<Libro>} lista di libri da associare a libro
	 */
	public void setLibri(List<Libro> libri) {
		this.libri = libri;
	}
	
	

}
