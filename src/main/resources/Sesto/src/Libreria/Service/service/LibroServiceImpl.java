package Libreria.Service.service;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.Libreria.DAO.LibroDAO;
import com.example.Libreria.DTO.LibroDTO;
import com.example.Libreria.entity.Libro;
import com.example.Libreria.utils.utilLibro;


/**
 * Modella l'entità Autore attraverso l'oggetto AutoreDTO 
 * mediante i seguenti metodi implementati.
 * Utilizza quattro metodi get che cercano il Libro in base al titolo,
 * cercano tutti i Libro, cercano il Libro in base alla data di uscita,
 * cercano il Libro in base all'ID
 * Utilizza un metodo delete per eliminare un Libro.
 * Utilizza un metodo post per inserire una nuovo Libro.
 * Utilizza un metodo put per modificare alcuni campi del Libro.
 */


@Service
public class LibroServiceImpl implements LibroService {
	
	@Autowired
	private LibroDAO libroDAO;
	@Autowired
	private utilLibro utilLibro;
	
	
	/**
	 * @param {LibroDTO} libroDTO da inserire
	 * @return {LibroDTO} libroDTO inserito
	 */
	@Transactional
	public LibroDTO insertLibro (LibroDTO libroDTO) {
		Libro libro = utilLibro.libroDTOtoLibro(libroDTO);
		libroDAO.save(libro);
		LibroDTO libroDTO2 = new LibroDTO();
		libroDTO2.setDataUscita(libro.getDataUscita());
		libroDTO2.setTitolo(libro.getTitolo());
		libroDTO2.setTrama(libro.getTrama());
		libroDTO2.setCasaEditrice(utilLibro.getCasaEditriceByLibro(libro.getCasaEditrice()));
		libroDTO2.setLinked_locazione(utilLibro.getLocazioneByLibro(libro.getLinked_locazione()));
		return libroDTO2;
	}
	
	/**  
	 * @return {List<LibroDTO>}
	 */
	public List<LibroDTO> getAllLibro(){
		List<LibroDTO> libriDTO = utilLibro.allLibriDTO(libroDAO.findAll());
		return libriDTO;
	}
	
	/**
	 * @param {LibroDTO} libroDTO con i nuovi parametri
	 * @param {int} idLibro da modificare
	 * @return {LibroDTO}  libroDTO modificato con i nuovi parametri
	 */
	//MODIFICA SOLO TITOLO,TRAME E DATA-USCITA DEL LIBRO
	public LibroDTO updateLibro(LibroDTO libroDTO, int id) {
		Optional<Libro> foundLibro = libroDAO.findById(id);
		LibroDTO libroDTO2 = new LibroDTO();
		if(foundLibro.isPresent()) {
			Libro libro = foundLibro.get();
			libro.setTitolo(libroDTO.getTitolo());
			libro.setTrama(libroDTO.getTrama());
			libro.setDataUscita(libroDTO.getDataUscita());
			libroDTO2.setDataUscita(foundLibro.get().getDataUscita());
			libroDTO2.setTitolo(foundLibro.get().getTitolo());
			libroDTO2.setTrama(foundLibro.get().getTrama());
			libroDAO.save(foundLibro.get());
			return libroDTO2;  
		}
		return null;
	}
	
	/**
	 * @param {String} titolo libro da cercare
	 * @return {LibroDTO} libro trocato con quel titolo specifico
	 */
	//TITOLO LIBRO UNIVOCO
	public LibroDTO findLibroByTitolo(String titolo) {
		Optional<Libro> libroOptional = libroDAO.findByTitolo(titolo);
		if(libroOptional.isPresent()) {
			LibroDTO resultLibroDTO = utilLibro.getLibroDTOFromLibro(libroOptional.get());
			return resultLibroDTO;
		}
		return null;
	}
	
	/**
	 * @param {int} idLibro da cercare 
	 * @return {LibroDTO} libroDTO trovato
	 */
	public LibroDTO findLibroById(int id) {
		Optional<Libro> libroOptional = libroDAO.findById(id);
		if(libroOptional.isPresent()) {
			LibroDTO libroDTO = utilLibro.getLibroDTOFromLibro(libroOptional.get());
			return libroDTO;
		}
		return null;
	}
	
	/**
	 * @param {int} idLibro da eliminare  
	 * @return void
	 */
	public void deleteLibro (int id) {
			Libro libro = libroDAO.findById(id).get();
			libroDAO.delete(libro);

	}
	
	/**
	 * @param {Date} data di pubblicazione
	 * @return {List<LibroDTO>} libri pbblicata dopo la data specificata
	 */
	public List<LibroDTO> getLibroAfterSpecificDate(Date dataUscita){	
		Optional<List<Libro>> libriOptional = libroDAO.findBydataUscitaGreaterThan(dataUscita);
		if(libriOptional.isPresent()) {
			List<LibroDTO> libriDTO = utilLibro.allLibriDTO(libriOptional.get());
			return libriDTO;
		}
		 return null;
	}
	
}
