package com.example.Libreria.DTO;

import java.io.Serializable;
import java.util.List;



public class CasaEditriceDTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int idCasaEditrice;
	public String nome;
	List<LibroDTO> libri;
	
	public CasaEditriceDTO() {
		
	}
	
	public CasaEditriceDTO(int idCasaEditrice, String nome, List<LibroDTO> libri) {
		super();
		this.idCasaEditrice = idCasaEditrice;
		this.nome = nome;
		this.libri = libri;
	}

	
	/**
	 * @return {int} idCasaEditriceDTO
	 */
	public int getIdCasaEditrice() {
		return idCasaEditrice;
	}

	/**
	 * @return void
	 * @param {int} idCasaEditriceDTO
	 */
	public void setIdCasaEditrice(int idCasaEditrice) {
		this.idCasaEditrice = idCasaEditrice;
	}

	
	/**
	 * @return {String} nomeCasaEditriceDTO
	 */
	public String getNome() {
		return nome;
	}

	
	/**
	 * @return void
	 * @param {String} nomeCasaEditriceDTO
	 */
	public void setNome(String nome) {
		this.nome = nome;
	}

	/**
	 * @return {List<LibroDTO>} lista di libriDTO associati alla casaEditriceDTO
	 */
	public List<LibroDTO> getLibri() {
		return libri;
	}

	/**
	 * @return void
	 * @param {List<LibroDTO>} lista di libriDTO da associare alla casaEditriceDTO
	 */
	public void setLibri(List<LibroDTO> libri) {
		this.libri = libri;
	}

}
