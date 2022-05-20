package Libreria.DTO;

import java.io.Serializable;



public class LocazioneDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int idLocazione;
	public 	String scompartimento;
	public int posizione;
	public LibroDTO libro;
	
	public LocazioneDTO(int idLocazione, String scompartimento, int posizione, LibroDTO libro) {
		super();
		this.idLocazione = idLocazione;
		this.scompartimento = scompartimento;
		this.posizione = posizione;
		this.libro = libro;
	}

	public LocazioneDTO() {
		
	}
	
	
	/**
	 * @return {int} idLocazioneDTO
	 */
	public int getIdLocazione() {
		return idLocazione;
	}
	
	/**
	 * @param {int} idLocazione da associareDTO
	 * @return void
	 */
	public void setIdLocazione(int idLocazione) {
		this.idLocazione = idLocazione;
	}

	/**
	 * @return {String} scompartimento locazioneDTO
	 */
	public String getScompartimento() {
		return scompartimento;
	}

	/**
	 * @param {String} scompartimento da associare alla locazioneDTO
	 * @return void
	 */
	public void setScompartimento(String scompartimento) {
		this.scompartimento = scompartimento;
	}

	/**
	 * @return {int} posizione associata alla locazioneDTO
	 */
	public int getPosizione() {
		return posizione;
	}
	
	/**
	 * @param {int} posizione da associare alla locazioneDTO
	 * @return void
	 */
	public void setPosizione(int posizione) {
		this.posizione = posizione;
	}
	
	/**
	 * @return {LibroDTO} libroDTO associato alla locazioneDTO
	 */
	public LibroDTO getLibro() {
		return libro;
	}
	
	
	/**
	 * @param {LibroDTO} libroDTO da associare a locazioneDTO
	 * @return void
	 */
	public void setLibro(LibroDTO libro) {
		this.libro = libro;
	}
}
