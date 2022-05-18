package com.example.Libreria.utils;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import com.example.Libreria.DAO.AutoreDAO;
import com.example.Libreria.DAO.CasaEditriceDAO;
import com.example.Libreria.DAO.LibroDAO;
import com.example.Libreria.DTO.AutoreDTO;
import com.example.Libreria.DTO.CasaEditriceDTO;
import com.example.Libreria.DTO.LibroDTO;
import com.example.Libreria.DTO.LocazioneDTO;
import com.example.Libreria.entity.Autore;
import com.example.Libreria.entity.CasaEditrice;
import com.example.Libreria.entity.Libro;
import com.example.Libreria.entity.Locazione;

import io.micrometer.core.instrument.util.StringUtils;

/**
 * @author AF
 */
/**
 * Supporta il modellamento dell'entità Locazione.
 */

@Configuration
public class utilLocazione {
	
	@Autowired
	private LibroDAO libroDAO;
	@Autowired
	private AutoreDAO autoreDAO;
	@Autowired
	private CasaEditriceDAO casaEditriceDAO;
	
	
	//CONTROLLER
	/**
	 * @param {LibroDTO} libroDTO associato alla locazione
	 * @return {boolean} indica se il libro è composto da vari campi 
	 */
	public boolean libroIsEmpty(LibroDTO libro) {
		if(libro.getDataUscita() != null 
				&& !StringUtils.isBlank(libro.getTitolo()) 
				&& !StringUtils.isBlank(libro.getTrama())){
			if(libro.getCasaEditrice() != null 
					&& !StringUtils.isBlank(libro.getCasaEditrice().getNome())){
				return false;
			}
		}
		return true;
	}
	
	//SEZIONE SUPPORTO GET-ALL
	
	/**
	 * @param {List<Locazione>} locazione
	 * @return {List<LocazioneDTO>}
	 */
	public List<LocazioneDTO> getAllLoczioniDTOFromLocazioni(List<Locazione> locazioni){
		List<LocazioneDTO> locazioniDTO = new ArrayList<LocazioneDTO>();
		for(Locazione locazione : locazioni) {
			LocazioneDTO locazioneDTO = new LocazioneDTO();
			locazioneDTO.setPosizione(locazione.getPosizione());
			locazioneDTO.setScompartimento(locazione.getScompartimento());
			locazioneDTO.setLibro(getLibroFromLocazione(locazione.getLibro()));
			locazioniDTO.add(locazioneDTO);
		}
		return locazioniDTO;
	}
	
	/**
	 * @param {Libro} libro associato alla locazione
	 * @return {CasaEditriceDTO} associata al libro
	 */
	public CasaEditriceDTO getCasaEditriceFromLibro(Libro libro) {
		CasaEditriceDTO resultCasaEditriceDTO = new CasaEditriceDTO();
		CasaEditrice casaEditrice = libro.getCasaEditrice();
		resultCasaEditriceDTO.setNome(casaEditrice.getNome());
		return resultCasaEditriceDTO;
	}
	
	/**
	 * @param {Libro} libro associato alla locazione
	 * @return {LibroDTO} 
	 */
	public LibroDTO getLibroFromLocazione(Libro libro) {
		LibroDTO libroDTO = new LibroDTO();
		if(libro != null) {
			libroDTO.setDataUscita(libro.getDataUscita());
			libroDTO.setTitolo(libro.getTitolo());
			libroDTO.setTrama(libro.getTrama());
			if(libro.getCasaEditrice() != null) {
				libroDTO.setCasaEditrice(getCasaEditriceFromLibro(libro));
			}
			if(getAutoriFromLibro(libro) != null ) {
				libroDTO.setLinkedAutori(getAutoriFromLibro(libro));
			}
		}	
		return libroDTO;
	}
	
	/**
	 * @param {Libro} libro associato alla locazione
	 * @return {List<AutoreDTO>} lista autori associati al libro
	 */
	public List<AutoreDTO> getAutoriFromLibro(Libro libro) {
		List<Autore> autori = libro.getLinkedAutori();
		List<AutoreDTO> autoriDTO = new ArrayList<AutoreDTO>();
		autori.forEach(autore ->{
			AutoreDTO autoreDTO = new AutoreDTO();
			autoreDTO.setCognome(autore.getCognome());
			autoreDTO.setNome(autore.getNome());
		});
		if(!autoriDTO.isEmpty()) {
			return autoriDTO;
		}
		return null;
	}
	
	//SEZIONE SUPPORTO ADD
	
	/**
	 * @param {LocazioneDTO} 
	 * @return {Locazione} 
	 */
	@Transactional
	public Locazione locazioneDTOtoLocazione(LocazioneDTO locazioneDTO) {
		Locazione locazione = new Locazione();
		locazione.setPosizione(locazioneDTO.getPosizione());
		locazione.setScompartimento(locazioneDTO.getScompartimento());
		locazione.setLibro(getLibroFromLocazioneDTO(locazioneDTO));
		return locazione;
	}
	
	/**
	 * @param {LocazioneDTO} 
	 * @return {Libro} libro assoaicato alla locazione
	 */
	@Transactional
	public Libro getLibroFromLocazioneDTO (LocazioneDTO locazioneDTO) {
		LibroDTO libroDTO = locazioneDTO.getLibro();
		Libro libro = new Libro();
		libro.setLinkedAutori(getAutoyByLibroDTO(libroDTO));
		libro.setCasaEditrice(getCasaEditriceFromLibroDTO(libroDTO));
		libro.setTitolo(libroDTO.getTitolo());
		libro.setDataUscita(libroDTO.getDataUscita());
		libro.setTrama(libroDTO.getTrama());
		libroDAO.save(libro);
		return libro;
	}
	
	/**
	 * @param {LibroDTO} 
	 * @return {CasaEditrice}  casaEditrice associata al libro
	 */
	@Transactional
	public CasaEditrice getCasaEditriceFromLibroDTO(LibroDTO libroDTO) {
		CasaEditrice casaEditrice = new CasaEditrice();
		CasaEditriceDTO casaEditriceDTO = libroDTO.getCasaEditrice();
		casaEditrice.setNome(casaEditriceDTO.getNome());
		casaEditriceDAO.save(casaEditrice);
		return casaEditrice;
	}
	
	/**
	 * @param {LibroDTO} 
	 * @return {List<Autore>} lista di autori associati al libro 
	 */
	@Transactional
	public List<Autore> getAutoyByLibroDTO(LibroDTO libroDTO) {
		List<Autore> autori = new ArrayList<Autore>();
		for(AutoreDTO autoreDTO: libroDTO.getLinkedAutori()) {
			Autore autore = new Autore();
			autore.setCognome(autoreDTO.getCognome());
			autore.setNome(autoreDTO.getNome());
			autori.add(autore);
			autoreDAO.save(autore);
		}
		if(autori.isEmpty()) {
			return null;
		}
		return autori;
	}
	
	
}
