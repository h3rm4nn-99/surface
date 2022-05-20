package Libreria.utils;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import com.example.Libreria.DAO.AutoreDAO;
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

/**
 * @author AF
 */
/**
 * Supporta il modellamento dell'entità CasaEditrice.
 */


@Configuration
public class utilCasaEditrice {

	@Autowired
	private LibroDAO libroDAO;
	@Autowired
	private LocazioneDAO locazioneDAO;
	@Autowired
	private AutoreDAO autoreDAO;

	
		//SEZIONE SUPPORTO ADD
		
		/**
		 * @param {CasaEditriceDTO} 
		 * @return {List<Libro>} 
		 */
		@Transactional
		public List<Libro> getLibriFromCasaEditriceDTO(CasaEditriceDTO casaEditriceDTO){
			List<LibroDTO> libriDTO = casaEditriceDTO.getLibri();
			List<Libro> libri = new ArrayList<Libro>();
			for(LibroDTO libroDTO: libriDTO) {
				Libro libro = new Libro();
				libro.setTitolo(libroDTO.getTitolo());
				libro.setDataUscita(libroDTO.getDataUscita());
				libro.setTrama(libroDTO.getTrama());
				libro.setLinked_locazione(getLocazioneFromLibro(libroDTO));
				libro.setLinkedAutori(getAutoriFromLibro(libroDTO.getLinkedAutori()));
				libroDAO.save(libro);
				libri.add(libro);
			}
			return libri;
		}
	
		/**
		 * @param {LibroDTO}  
		 * @return {Locazione} locazione associata al libro  
		 */
		@Transactional
		public Locazione getLocazioneFromLibro(LibroDTO libroDTO) {
			LocazioneDTO locazioneDTO = libroDTO.getLinked_locazione();
			Locazione locazione = new Locazione();
			locazione.setPosizione(locazioneDTO.getPosizione());
			locazione.setScompartimento(locazioneDTO.getScompartimento());
			locazioneDAO.save(locazione);
			return locazione;
		}
	
		/**
		 * @param {List<AutoreDTO>}  
		 * @return {List<Autore>} lista autori associati al libro 
		 */
		@Transactional
		public List<Autore> getAutoriFromLibro(List<AutoreDTO> autoriDTO){
			List<Autore> autori = new ArrayList<Autore>();
			autoriDTO.forEach(autoreDTO-> {
				Autore autore = new Autore();
				autore.setCognome(autoreDTO.getCognome());
				autore.setNome(autoreDTO.getNome());
				autoreDAO.save(autore);
				autori.add(autore);
			});
			return autori;
		}

		
		
		//SEZIONE SUPPORTO GET-ALL
		
		/**
		 * @param {List<CasaEditrice>}  
		 * @return {List<CasaEditriceDTO>}
		 */
		public List<CasaEditriceDTO> getAllCasaEditrice(List<CasaEditrice> caseEditrici) {
			List<CasaEditriceDTO> caseEditriciDTO = new ArrayList<CasaEditriceDTO>();
			for(CasaEditrice caseEditrice : caseEditrici) {
				CasaEditriceDTO casaEditriceDTO = new CasaEditriceDTO();
				casaEditriceDTO.setNome(caseEditrice.getNome());
				casaEditriceDTO.setLibri(getAllLibriFromCasaEditrice(caseEditrice));
				caseEditriciDTO.add(casaEditriceDTO);
			}
			return caseEditriciDTO;
		}
		
		/**
		 * @param {CasaEditrice}  
		 * @return {List<LibriDTO>} lista di libri associati alla casa editrice
		 */
		public List<LibroDTO> getAllLibriFromCasaEditrice(CasaEditrice casaEditrice){
			List<LibroDTO> libriDTO = new  ArrayList<LibroDTO>();
			List<Libro> libri = casaEditrice.getLibri();
			for(Libro libro : libri) {
				LibroDTO libroDTO = new LibroDTO();
				libroDTO.setDataUscita(libro.getDataUscita());
				libroDTO.setTitolo(libro.getTitolo());
				libroDTO.setTrama(libro.getTrama());
				if(libro.getLinked_locazione() != null) {
					libroDTO.setLinked_locazione(getLocazioneDTO(libro));
				}
				libriDTO.add(libroDTO);
			}
			return libriDTO;
		}
		
		/**
		 * @param {Libro}  
		 * @return {LocazioneDTO} locazione associata al libro
		 */
		public LocazioneDTO getLocazioneDTO(Libro libro) {
			Locazione locazione = libro.getLinked_locazione();
			LocazioneDTO locazioneDTO = new LocazioneDTO();
			if(locazione != null) {
				locazioneDTO.setPosizione(locazione.getPosizione());
				locazioneDTO.setScompartimento(locazione.getScompartimento());
				return locazioneDTO;
			}
			return null;
		}
	

}
