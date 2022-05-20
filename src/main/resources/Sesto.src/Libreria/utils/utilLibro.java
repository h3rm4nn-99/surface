package com.example.Libreria.utils;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import com.example.Libreria.DAO.AutoreDAO;
import com.example.Libreria.DAO.CasaEditriceDAO;
import com.example.Libreria.DAO.LocazioneDAO;
import com.example.Libreria.DTO.AutoreDTO;
import com.example.Libreria.DTO.CasaEditriceDTO;
import com.example.Libreria.DTO.LibroDTO;
import com.example.Libreria.DTO.LocazioneDTO;
import com.example.Libreria.entity.Autore;
import com.example.Libreria.entity.CasaEditrice;
import com.example.Libreria.entity.Libro;
import com.example.Libreria.entity.Locazione;

/**
 * @author AF
 */
/**
 * Supporta il modellamento dell'entità Libro.
 */

@Configuration
public class utilLibro {

	@Autowired
	private CasaEditriceDAO casaEditriceDAO;
	@Autowired
	private LocazioneDAO locazioneDAO;
	@Autowired
	private AutoreDAO autoreDAO;

	//SEZIONE SUPPORTO ADD
	
	/**
	 * @param {CasaEditriceDTO} casaEditriceDTO associata al libroDTO
	 * @return {CasaEditrice} 
	 */
	@Transactional
	public CasaEditrice getCasaEditriceByLibroDTO (CasaEditriceDTO casaEditriceDTO) {
		CasaEditrice casaEditrice = new CasaEditrice();
		casaEditrice.setNome(casaEditriceDTO.getNome());
		casaEditriceDAO.save(casaEditrice);
		return casaEditrice;
	}
	
	/**
	 * @param {LocazioneDTO} associata al libroDTO
	 * @return {Locazione} 
	 */
	@Transactional
	public Locazione getLocazioneByLibroDTO(LocazioneDTO locazioneDTO) {
		Locazione locazione = new Locazione();
		locazione.setPosizione(locazioneDTO.getPosizione());
		locazione.setScompartimento(locazioneDTO.getScompartimento());
		locazioneDAO.save(locazione);
		return locazione;
	}
	
	/**
	 * @param {Lis<AutoreDTO>} lista di autoriDTO associata al libroDTO
	 * @return {List<Autore>} 
	 */
	@Transactional
	public List<Autore> getAutoriFromLibroDTO(List<AutoreDTO> autoriDTO){
		List<Autore> autori = new ArrayList<Autore>();
		for(AutoreDTO autoreDTO : autoriDTO) {
			Autore autore = new Autore();
			autore.setCognome(autoreDTO.getCognome());
			autore.setNome(autoreDTO.getNome());
			autori.add(autore);
			autoreDAO.save(autore);
		}
		return autori;
	}
	
	/**
	 * @param {LibroDTO} 
	 * @return {Libro} 
	 */
	@Transactional
	public Libro libroDTOtoLibro(LibroDTO libroDTO) {
		Libro libroreturn = new Libro();
		libroreturn.setDataUscita(libroDTO.getDataUscita());
		libroreturn.setTitolo(libroDTO.getTitolo());
		libroreturn.setTrama(libroDTO.getTrama());
		if(libroDTO.getCasaEditrice() != null) {
			libroreturn.setCasaEditrice(getCasaEditriceByLibroDTO(libroDTO.getCasaEditrice()));
		}
		if(libroDTO.getLinked_locazione() != null) {
			libroreturn.setLinked_locazione(getLocazioneByLibroDTO(libroDTO.getLinked_locazione()));
		}
		if(libroDTO.getLinkedAutori() != null) {
			libroreturn.setLinkedAutori(getAutoriFromLibroDTO(libroDTO.getLinkedAutori()));
		}
		return libroreturn;
	}
	
	//SEZIONE SUPPORTO GET
	
	/**
	 * @param {CasaEditrice} lisa di casaEditrice associata al libro
	 * @return {CasaEditriceDTO} 
	 */
	public CasaEditriceDTO getCasaEditriceByLibro (CasaEditrice casaEditrice) {
		CasaEditriceDTO casaEditriceDTO = new CasaEditriceDTO();
		if(casaEditrice != null) {
			casaEditriceDTO.setNome(casaEditrice.getNome());
			return casaEditriceDTO;
		}
		return null;
	}
	
	/**
	 * @param {Locazione} locazione associata al libro
	 * @return {LocazioneDTO} 
	 */
	public LocazioneDTO getLocazioneByLibro(Locazione locazione) {
		LocazioneDTO locazioneDTO = new LocazioneDTO();
		if(locazione!= null) {
			locazioneDTO.setPosizione(locazione.getPosizione());
			locazioneDTO.setScompartimento(locazione.getScompartimento());
			return locazioneDTO;
		}
		return null;
	}
	
	/**
	 * @param {List<Autore} lista di autori associata al libro
	 * @return {List<AutoreDTO>} 
	 */
	public List<AutoreDTO> getAutoriByLibro(List<Autore> autori){
		List<AutoreDTO> autoriDTO = new ArrayList<AutoreDTO>();
		for(Autore autore: autori) {
			AutoreDTO autoreDTO = new AutoreDTO();
			autoreDTO.setCognome(autore.getCognome());
			autoreDTO.setNome(autore.getNome());
			autoriDTO.add(autoreDTO);
		}
		return autoriDTO;
	}
	
	/**
	 * @param {List<Libro>} 
	 * @return {List<LibroDTO>} 
	 */
	public List<LibroDTO> allLibriDTO(List<Libro> libri){
		List<LibroDTO> libriDTO = new ArrayList<LibroDTO>();
		for(Libro libro : libri) {
			LibroDTO libroDTO = new LibroDTO();
			libroDTO.setDataUscita(libro.getDataUscita());
			libroDTO.setTitolo(libro.getTitolo());
			libroDTO.setTrama(libro.getTrama());
			libroDTO.setCasaEditrice(getCasaEditriceByLibro(libro.getCasaEditrice()));
			libroDTO.setLinked_locazione(getLocazioneByLibro(libro.getLinked_locazione()));
			libroDTO.setLinkedAutori(getAutoriByLibro(libro.getLinkedAutori()));
			libriDTO.add(libroDTO);
		}
		return libriDTO;
	}
	
	/**
	 * @param {Libro} 
	 * @return {LibroDTO} 
	 */
	public LibroDTO getLibroDTOFromLibro(Libro libro) {
		LibroDTO libroDTO = new LibroDTO();
		libroDTO.setDataUscita(libro.getDataUscita());
		libroDTO.setTitolo(libro.getTitolo());
		libroDTO.setTrama(libro.getTrama());
		libroDTO.setCasaEditrice(getCasaEditriceByLibro(libro.getCasaEditrice()));
		libroDTO.setLinkedAutori(getAutoriByLibro(libro.getLinkedAutori()));
		libroDTO.setLinked_locazione(getLocazioneByLibro(libro.getLinked_locazione()));
		return libroDTO;
	}
}
