package com.example.Libreria.DTO;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;



public class AutoreDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -205237454982908283L;
	private int idAutore;
	public String nome;
	public String cognome;
	List<LibroDTO> libri = new ArrayList<LibroDTO>();

	public AutoreDTO(int id_autore, String nome, String cognome, List<LibroDTO> libri) {
		super();
		this.idAutore = id_autore;
		this.nome = nome;
		this.cognome = cognome;
		this.libri = libri;
	}

	public AutoreDTO() {
		
	}
	
	/**
	 * @return {int} idAutoreDTO
	 */
	public int getIdAutore() {
		return idAutore;
	}

	/**
	 * @return void
	 * @param {int} idAutoreDTO
	 */
	public void setIdAutore(int idAutore) {
		this.idAutore = idAutore;
	}
	
	/**
	 * @return {String} nome AutoreDTO
	 */
	public String getNome() {
		return nome;
	}

	/**
	 * @return void
	 * @param {String} nome AutoreDTO
	 */
	public void setNome(String nome) {
		this.nome = nome;
	}

	/**
	 * @return {String} cognome AutoreDTO
	 */
	public String getCognome() {
		return cognome;
	}

	/**
	 * @return void
	 * @param {String} cognome AutoreDTO
	 */
	public void setCognome(String cognome) {
		this.cognome = cognome;
	}

	/**
	 * @return {List<LibroDTO>} lista di libriDTO associati ad AutoreDTO
	 */
	public List<LibroDTO> getLibri() {
		return libri;
	}

	
	/**
	 * @return void
	 * @param {List<LibroDTO>} lista di libriDTO da associare a libroDTO
	 */
	public void setLibri(List<LibroDTO> libri) {
		this.libri = libri;
	}
}
