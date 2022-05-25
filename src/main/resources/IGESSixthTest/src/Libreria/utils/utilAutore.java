package Libreria.utils;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import com.example.Libreria.DAO.CasaEditriceDAO;
import com.example.Libreria.DAO.LibroDAO;
import com.example.Libreria.DAO.LocazioneDAO;
import com.example.Libreria.DTO.AutoreDTO;
import com.example.Libreria.DTO.CasaEditriceDTO;
import com.example.Libreria.DTO.LibroDTO;
import com.example.Libreria.DTO.LocazioneDTO;
import Libreria.entity.Autore;
import Libreria.entity.CasaEditrice;
import Libreria.entity.Libro;
import Libreria.entity.Locazione;

import io.micrometer.core.instrument.util.StringUtils;

/**
 * @author AF
 */
/**
 * Supporta il modellamento dell'entità Autore.
 */

@Configuration
public class utilAutore {
	
	@Autowired
	private LibroDAO libroDAO;
	
	@Autowired
	private CasaEditriceDAO casaEditriceDAO;
	
	@Autowired
	private LocazioneDAO locazineDAO;
	

	//SEZIONE SUPPORTO ADD
	
	/**
	 * @param {LibroDTO}  
	 * @return {CasaEditrice} casaEditrice associata al libro
	 */
	@Transactional
	public CasaEditrice getCasaEditrice(LibroDTO libroDTO) {
		CasaEditrice casaEditrice = new CasaEditrice();
		CasaEditriceDTO casaEditriceDTO = libroDTO.getCasaEditrice();
		casaEditrice.setNome(casaEditriceDTO.getNome());
		this.casaEditriceDAO.save(casaEditrice);
		return casaEditrice;
	}
	
	/**
	 * @param {LibroDTO}  
	 * @return {Locazione} locazione associata al libro
	 */
	@Transactional
	public Locazione getLocazione(LibroDTO libroDTO) {
		Locazione locazione = new Locazione();
		LocazioneDTO locazioneDTO = libroDTO.getLinked_locazione();
		locazione.setPosizione(locazioneDTO.getPosizione());
		locazione.setScompartimento(locazioneDTO.getScompartimento());
		locazineDAO.save(locazione);
		return locazione;
	}
	

	/**
	 * @param {AutoreDTO}  
	 * @return {List<Libro>} lista di libri associati ad autore
	 */
	@Transactional
	public List<Libro> getLibri(AutoreDTO autoreDTO){
		List<Libro> libri = new ArrayList<Libro>();
		for(LibroDTO libroDTO : autoreDTO.getLibri()) {
			if(!StringUtils.isBlank(libroDTO.getTitolo()) && !StringUtils.isBlank(libroDTO.getTrama()) && libroDTO.getDataUscita() != null && libroDTO.getCasaEditrice() != null && libroDTO.getLinked_locazione() != null) {
				Libro libro = new Libro();
				libro.setTitolo(libroDTO.getTitolo());
				libro.setTrama(libroDTO.getTrama());
				libro.setDataUscita(libroDTO.getDataUscita());
				libro.setLinked_locazione(getLocazione(libroDTO));
				libro.setCasaEditrice(getCasaEditrice(libroDTO));
				libri.add(libro);
				libroDAO.save(libro);
			}
		}
		if(libri.isEmpty()) {
			return null;
		}
		return libri;
	}
	
		
		//SEZIONE SUPPORTO GET-ALL
	
	
		/**
		 * @param {Autore}  
		 * @return {List<LibroDTO>}
		 */
		public List<LibroDTO> getLibriAutoreDTO(Autore autore){
			List<Libro> libri = autore.getLibri();
			List<LibroDTO> libriDTO = new ArrayList<LibroDTO>();
			for(Libro libro : libri) {
				LibroDTO libroDTO = new LibroDTO();
				libroDTO.setDataUscita(libro.getDataUscita());
				libroDTO.setTitolo(libro.getTitolo());
				libroDTO.setTrama(libro.getTrama());
				libroDTO.setLinked_locazione(getLocazioneDTO(libro));
				libroDTO.setCasaEditrice(getCasaEditriceDTO(libro));
				libriDTO.add(libroDTO);
			}
			return libriDTO;
		}
		
		/**
		 * @param {Libro} libro associato ad autore
		 * @return {CasaEditriceDTO} casaEditriceDTO associata a libro
		 */
		public CasaEditriceDTO getCasaEditriceDTO(Libro libro) {
			CasaEditrice casaEditrice = libro.getCasaEditrice();
			CasaEditriceDTO casaEditriceDTO = new CasaEditriceDTO();
			if(casaEditrice != null) {
				casaEditriceDTO.setNome(casaEditrice.getNome());
			}
			return casaEditriceDTO;
		}
		
		/**
		 * @param {Libro}  libro associato ad autore
		 * @return {LocazioneDTO} locazione associata a libro
		 */
		public LocazioneDTO getLocazioneDTO(Libro libro) {
			Locazione locazione = libro.getLinked_locazione();
			LocazioneDTO locazioneDTO = new LocazioneDTO();
			locazioneDTO.setPosizione(locazione.getPosizione());
			locazioneDTO.setScompartimento(locazione.getScompartimento());
			return locazioneDTO;
		}
	
	
}
