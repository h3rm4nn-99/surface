package com.example.Libreria.DTO;

import java.io.Serializable;
import java.util.Date;
import java.util.ArrayList;
import java.util.List;



public class LibroDTO implements Serializable{


	private static final long serialVersionUID = 1L;
	private int idLibro;
	public String titolo;
	public String trama;
	public Date dataUscita;
	List<AutoreDTO> linkedAutori = new ArrayList<AutoreDTO>();
	private LocazioneDTO linked_locazione;
	private CasaEditriceDTO casaEditrice;

	public LibroDTO(int idLibro, String titolo, String trama, Date dataUscita, List<AutoreDTO> linkedAutori,
			LocazioneDTO linked_locazione, CasaEditriceDTO casaEditrice) {
		super();
		this.idLibro = idLibro;
		this.titolo = titolo;
		this.trama = trama;
		this.dataUscita = dataUscita;
		this.linkedAutori = linkedAutori;
		this.linked_locazione = linked_locazione;
		this.casaEditrice = casaEditrice;
	}
	
	public LibroDTO() {
		
	}

	
	/**
	 * @return {int} idLibroDTO
	 */
	public int getIdLibro() {
		return idLibro;
	}

	/**
	 * @param {int} idLibroDTO da associare
	 * @return void
	 */
	public void setId_libro(int idLibro) {
		this.idLibro = idLibro;
	}
	
	
	/**
	 * @return {String} titolo del libroDTO
	 */
	public String getTitolo() {
		return titolo;
	}

	/**
	 * @param {String} titolo da associare al libroDTO
	 * @return void
	 */
	public void setTitolo(String titolo) {
		this.titolo = titolo;
	}

	/**
	 * @return {String} trama del libroDTO
	 */
	public String getTrama() {
		return trama;
	}

	
	/**
	 * @return void
	 * @param {String} trama da associare al libroDTO
	 */
	public void setTrama(String trama) {
		this.trama = trama;
	}

	/**
	 * @return {Date} data pubblicazione libroDTO
	 */
	public Date getDataUscita() {
		return dataUscita;
	}

	/**
	 * @param {Date} data di pubblicazione da associare al libroDTO
	 * @return void
	 */
	public void setDataUscita(Date dataUscita) {
		this.dataUscita = dataUscita;
	}

	/**
	 * @return {List<AutoreDTO>} lista di autoriDTO associati al libroDTO
	 */
	public List<AutoreDTO> getLinkedAutori() {
		return linkedAutori;
	}

	/**
	 * @return void
	 * @param {List<AutoreDTO>} lista di autoriDTO da associare al libroDTO
	 */
	public void setLinkedAutori(List<AutoreDTO> linkedAutori) {
		this.linkedAutori = linkedAutori;
	}

	/**
	 * @return {LocazioneDTO} locazioneDTO associata al libroDTO
	 */
	public LocazioneDTO getLinked_locazione() {
		return linked_locazione;
	}

	/**
	 * @param {LocazioneDTO} locazioneDTO da associare al libroDTO
	 * @return void
	 */
	public void setLinked_locazione(LocazioneDTO linked_locazione) {
		this.linked_locazione = linked_locazione;
	}
	
	/**
	 * @return {CasaEditriceDTO} casaEditriceDTO associata al libroDTO
	 */
	public CasaEditriceDTO getCasaEditrice() {
		return casaEditrice;
	}

	
	/**
	 * @param {CasaEditriceDTO} casaEditriceDTO da associare al libroDTO
	 * @return void
	 */
	public void setCasaEditrice(CasaEditriceDTO casaEditrice) {
		this.casaEditrice = casaEditrice;
	}
	
	
	@Override
	public String toString() {
		return "LibroDTO [idLibro=" + idLibro + ", titolo=" + titolo + ", trama=" + trama + ", dataUscita=" + dataUscita
				+ ", linkedAutori=" + linkedAutori + ", linked_locazione=" + linked_locazione + ", casaEditrice="
				+ casaEditrice + "]";
	}

	/**
	 * 
	 */
}
