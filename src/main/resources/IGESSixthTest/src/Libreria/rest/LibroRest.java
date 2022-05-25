package Libreria.rest;

import java.sql.Date;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import javax.transaction.Transactional;
import org.apache.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.example.Libreria.DTO.LibroDTO;
import Libreria.Service.service.LibroService;
import Libreria.configuration.RisultatoDTO;
import io.github.resilience4j.bulkhead.annotation.Bulkhead;
import io.github.resilience4j.bulkhead.annotation.Bulkhead.Type;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.timelimiter.annotation.TimeLimiter;
import io.micrometer.core.instrument.util.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;




/**
 * Modella l'entità Libro mediante l'oggetto LibroDTO.
 * Utilizza quattro metodi get che cercano il Libro in base al titolo,
 * cercano tutti i Libro, cercano il Libro in base alla data di uscita,
 * cercano il Libro in base all'ID
 * Utilizza un metodo delete per eliminare un Libro.
 * Utilizza un metodo post per inserire una nuovo Libro.
 * Utilizza un metodo put per modificare alcuni campi del Libro.
 */

@RestController
public class LibroRest {
	
	@Autowired
	private LibroService libroService;
	
	private Logger logger = LogManager.getLogger(CasaEditriceRest.class);
	
	/**
	 * @param {LibroDTO} libroDTO da inserire
	 * @return {CompletableFuture<ResponseEntity<RisultatoDTO<LibroDTO>>>} 
	 */
	@RequestMapping(
			value = "api/libro/add"
			, method = RequestMethod.POST
			)
	@CircuitBreaker(
			name = "insertLibro"
			, fallbackMethod = "fallbackInsertLibro"
			)
	@Bulkhead(
			name = "insertLibro"
			, fallbackMethod = "fallbackInsertLibro"
			, type = Type.THREADPOOL
			)
	@TimeLimiter(
			name = "insertLibro"
			, fallbackMethod = "fallbackInsertLibro"
			)
	@Transactional
	public CompletableFuture<ResponseEntity<RisultatoDTO<LibroDTO>>> insertLibro (@RequestBody LibroDTO libroDTO) {
		RisultatoDTO<LibroDTO> result = new RisultatoDTO<LibroDTO>();
		boolean trama =StringUtils.isBlank(libroDTO.getTrama());
		boolean titolo = StringUtils.isBlank(libroDTO.getTitolo());
	
		if(!trama && !titolo && libroDTO.getDataUscita() != null){
			if(libroDTO.getCasaEditrice() != null && libroDTO.getLinked_locazione() != null) {
				LibroDTO resultLibroDTO = libroService.insertLibro(libroDTO);
				
				result.setData(resultLibroDTO)
				.success(HttpStatus.SC_OK)
				.setDescrizione("Operazione avvenuta con successo");
				logger.info("L'inserimento di un Libro avvenuto con successo.");
				return  CompletableFuture.completedFuture(ResponseEntity.status(HttpStatus.SC_OK).body(result));
			}
		}
		result.setData(null)
		.success(HttpStatus.SC_BAD_REQUEST)
		.setDescrizione("Operazione non avvenuta con successo, inserire correttamente i campi");
		logger.error("L'inserimento di un Libro nome è avvenuta con successo.");
		return  CompletableFuture.completedFuture(ResponseEntity.status(HttpStatus.SC_BAD_REQUEST).body(result));
	}
	/**
	 * @param {LibroDTO} libroDTO da inserire
	 * @param {Throwable} eccezione lanciata in caso di errore 
	 * @return {CompletableFuture<ResponseEntity<RisultatoDTO<LibroDTO>>>} 
	 */
	public CompletableFuture<ResponseEntity<RisultatoDTO<LibroDTO>>> fallbackInsertLibro(LibroDTO libroDTO, Throwable throwable){
		RisultatoDTO<LibroDTO> result = new RisultatoDTO<LibroDTO>();	
		
		result.setData(null)
		.success(HttpStatus.SC_GATEWAY_TIMEOUT)
		.setDescrizione("Tempo scaduto, non è possibile inserire un nuovo Libro");
		logger.error("Fallback:qualcosa è andato storto!.");
		return  CompletableFuture.completedFuture(ResponseEntity.status(HttpStatus.SC_GATEWAY_TIMEOUT).body(result));
	}
	
	
	
	
	/**
	 * @return {CompletableFuture<ResponseEntity<RisultatoDTO<List<LibroDTO>>>>} 
	 */
	@RequestMapping(
			value = "api/libro/all"
			,method = RequestMethod.GET
			)
	@CircuitBreaker(
			name = "getAllLibro",
			fallbackMethod = "fallbackGetAllLibro"
			)
	@Bulkhead(
			name = "getAllLibro", 
			fallbackMethod = "fallbackGetAllLibro",
			type = Type.THREADPOOL
			)
	@TimeLimiter(
			name = "getAllLibro",
			fallbackMethod = "fallbackGetAllLibro"
			)
	public CompletableFuture<ResponseEntity<RisultatoDTO<List<LibroDTO>>>> getAllLibro(){
		RisultatoDTO<List<LibroDTO>> result = new RisultatoDTO<List<LibroDTO>>();	
		List<LibroDTO> libri = libroService.getAllLibro();
		if(libri.isEmpty()) {
			
			result.setData(null)
			.success(HttpStatus.SC_BAD_REQUEST)
			.setDescrizione("Operazione non avvenuta con successo, non è possibile reperire tutti gli autori");
			logger.error("Operazione che ritorna tutti i libri non avvenuta con successso.");
			return CompletableFuture.completedFuture(ResponseEntity.status(HttpStatus.SC_BAD_REQUEST).body(result));
		}
		result.setData(libri)
		.success(HttpStatus.SC_OK)
		.setDescrizione("Operazione avvenuta con successo");
		logger.info("Operazione che ritorna tutti i libri avvenuta con successso.");
		return CompletableFuture.completedFuture(ResponseEntity.status(HttpStatus.SC_OK).body(result));
	}
	/**
	 * @param {Throwable} eccezione lanciata in caso di errore 
	 * @return {CompletableFuture<ResponseEntity<RisultatoDTO<List<LibroDTO>>>>} 
	 */
	public CompletableFuture<ResponseEntity<RisultatoDTO<List<LibroDTO>>>> fallbackGetAllLibro( Throwable throwable){
		RisultatoDTO<List<LibroDTO>> result = new RisultatoDTO<List<LibroDTO>>();	
		
		result.setData(null)
		.success(HttpStatus.SC_GATEWAY_TIMEOUT)
		.setDescrizione("Tempo scaduto, non è possibile trovare tutti i Libri");
		logger.error("Fallback:qualcosa è andato storto!.");
		return  CompletableFuture.completedFuture(ResponseEntity.status(HttpStatus.SC_GATEWAY_TIMEOUT).body(result));
	}
	
	
	
	
	
	
	
	
	
	/**
	 * @param {int} id libro da modificare
	 * @param {LibroDTO} libroDTO con nuovi parametri
	 * @param {Throwable} eccezione lanciata in caso di errore 
	 * @return {CompletableFuture<ResponseEntity<RisultatoDTO<LibroDTO>>>} 
	 */
	@RequestMapping(
			value = "api/libro/{id}",
			method = RequestMethod.PUT
			)
	@CircuitBreaker(
			name = "updateLibro",
			fallbackMethod = "fallbackUpdateLibro"
			)
	@Bulkhead(
			name = "updateLibro",
			fallbackMethod = "fallbackUpdateLibro",
			type = Type.THREADPOOL
			)
	@TimeLimiter(
			name = "updateLibro",
			fallbackMethod = "fallbackUpdateLibro"
			)
	public CompletableFuture<ResponseEntity<RisultatoDTO<LibroDTO>>> updateLibro(@RequestBody LibroDTO libroDTO, @PathVariable int id) {
		RisultatoDTO<LibroDTO> result = new RisultatoDTO<LibroDTO>();
		boolean  trama =StringUtils.isBlank(libroDTO.getTrama());
		boolean titolo = StringUtils.isBlank(libroDTO.getTitolo());
		if(!titolo && !trama && libroDTO.getDataUscita() != null) {
			LibroDTO resultLibroDTO =  libroService.updateLibro(libroDTO, id);
			if(resultLibroDTO != null ) {
				
				result.setData(resultLibroDTO)
				.success(HttpStatus.SC_OK)
				.setDescrizione("Operazione avvenuta con successo");
				logger.info("Aggiornamento libro avvenuto con successo.");
				return CompletableFuture.completedFuture(ResponseEntity.status(HttpStatus.SC_OK).body(result));
			}
		}
		result.setData(null)
		.success(HttpStatus.SC_BAD_REQUEST)
		.setDescrizione("Operazione non avvenuta con successo, inserire correttamente i campi");
		logger.error("Aggiornamento libro non avvenuta con successo.");
		return CompletableFuture.completedFuture(ResponseEntity.status(HttpStatus.SC_BAD_REQUEST).body(result));
	}
	/**
	 * @param {int} id libro da modificare
	 * @param {LibroDTO} libroDTO con nuovi parametri
	 * @param {Throwable} eccezione lanciata in caso di errore 
	 * @return {CompletableFuture<ResponseEntity<RisultatoDTO<LibroDTO>>>} 
	 */
	public CompletableFuture<ResponseEntity<RisultatoDTO<LibroDTO>>> fallbackUpdateLibro(LibroDTO libroDTO, int id,  Throwable throwable){
		RisultatoDTO<LibroDTO> result = new RisultatoDTO<LibroDTO>();	
		
		result.setData(null)
		.success(HttpStatus.SC_GATEWAY_TIMEOUT)
		.setDescrizione("Tempo scaduto, non è possibile aggiornare il Libro");
		logger.error("Fallback:qualcosa è andato storto!.");
		return  CompletableFuture.completedFuture(ResponseEntity.status(HttpStatus.SC_GATEWAY_TIMEOUT).body(result));
	}
	
	
	
	
	
	
	

	/**
	 * @param {int} id libro da cercare
	 * @return {CompletableFuture<ResponseEntity<RisultatoDTO<LibroDTO>>>} 
	 */
	@RequestMapping(
			value = "api/libro/id/{id}", 
			method = RequestMethod.GET
			)
	@CircuitBreaker(
			name = "findLibroById",
			fallbackMethod = "fallbackFindLibroById"
			)
	@Bulkhead(
			name = "findLibroById",
			fallbackMethod = "fallbackFindLibroById",
			type = Type.THREADPOOL
			)
	@TimeLimiter(
			name = "findLibroById",
			fallbackMethod = "fallbackFindLibroById"
			)
	public CompletableFuture<ResponseEntity<RisultatoDTO<LibroDTO>>> findLibroById(@PathVariable int id) {
		RisultatoDTO<LibroDTO> result = new RisultatoDTO<LibroDTO>();
		LibroDTO resultLibroDTO = libroService.findLibroById(id);
		if(resultLibroDTO != null) {
			
			result.setData(resultLibroDTO)
			.success(HttpStatus.SC_OK)
			.setDescrizione("Operazione avvenuta con successo");
			logger.info("Operazione FindById avvenuta con successo.");
			return CompletableFuture.completedFuture(ResponseEntity.status(HttpStatus.SC_OK).body(result));
		}
		result.setData(null)
		.success(HttpStatus.SC_BAD_REQUEST)
		.setDescrizione("Operazione non avvenuta con successo, inserire correttamenete i campi");
		logger.error("Operazione FindById non avvenuta con successo.");
		return CompletableFuture.completedFuture(ResponseEntity.status(HttpStatus.SC_BAD_REQUEST).body(result));
	}
	/**
	 * @param {int} id libro da cercare
	 * @param {Throwable} eccezione lanciata in caso di errore 
	 * @return {CompletableFuture<ResponseEntity<RisultatoDTO<LibroDTO>>>} 
	 */
	public CompletableFuture<ResponseEntity<RisultatoDTO<LibroDTO>>> fallbackFindLibroById(int id,  Throwable throwable){
		RisultatoDTO<LibroDTO> result = new RisultatoDTO<LibroDTO>();	
		
		result.setData(null)
		.success(HttpStatus.SC_GATEWAY_TIMEOUT)
		.setDescrizione("Tempo scaduto, non è possibile aggiornare il Libro");
		logger.error("Fallback:qualcosa è andato storto!.");
		return  CompletableFuture.completedFuture(ResponseEntity.status(HttpStatus.SC_GATEWAY_TIMEOUT).body(result));
	}
	
	
	
	
	
	
	/**
	 * @param {String} titolo libro da cercare
	 * @return {CompletableFuture<ResponseEntity<RisultatoDTO<LibroDTO>>>} 
	 */
	@RequestMapping(
			value = "api/libro/titolo/{titolo}",
			method = RequestMethod.GET
			)
	@CircuitBreaker(
			name = "findLibroByTitolo", 
			fallbackMethod = "fallbackFindLibroByTitolo"
			)
	@Bulkhead(
			name = "findLibroByTitolo", 
			fallbackMethod = "fallbackFindLibroByTitolo",
			type = Type.THREADPOOL
			)
	@TimeLimiter(
			name = "findLibroByTitolo", 
			fallbackMethod = "fallbackFindLibroByTitolo"
			)
	public CompletableFuture<ResponseEntity<RisultatoDTO<LibroDTO>>> findLibroByTitolo(@PathVariable String titolo) {
		RisultatoDTO<LibroDTO> result = new RisultatoDTO<LibroDTO>();
		LibroDTO resultLibroDTO =  libroService.findLibroByTitolo(titolo);
		if(resultLibroDTO != null) {
			
			result.setData(resultLibroDTO)
			.success(HttpStatus.SC_OK)
			.setDescrizione("Operazione avvenuta con successo");
			logger.info("Operazione FindByTitolo avvenuta con successo.");
			return CompletableFuture.completedFuture(ResponseEntity.status(HttpStatus.SC_OK).body(result));
		}
		result.setData(null)
		.success(HttpStatus.SC_BAD_REQUEST)
		.setDescrizione("Operazione non avvenuta con successo, inserire correttamenete i campi");
		logger.error("Operazione FindByTitolo non avvenuta con successo.");
		return CompletableFuture.completedFuture(ResponseEntity.status(HttpStatus.SC_BAD_REQUEST).body(result));
	}
	/**
	 * @param {String} titolo libro da cercare
	 * @param {Throwable} eccezione lanciata in caso di errore 
	 * @return {CompletableFuture<ResponseEntity<RisultatoDTO<LibroDTO>>>} 
	 */
	public CompletableFuture<ResponseEntity<RisultatoDTO<LibroDTO>>> fallbackFindLibroByTitolo(String titolo,  Throwable throwable){
		RisultatoDTO<LibroDTO> result = new RisultatoDTO<LibroDTO>();	
		
		result.setData(null)
		.success(HttpStatus.SC_GATEWAY_TIMEOUT)
		.setDescrizione("Tempo scaduto, non è possibile trovare un Libro con questo titolo");
		logger.error("Fallback:qualcosa è andato storto!.");
		return  CompletableFuture.completedFuture(ResponseEntity.status(HttpStatus.SC_GATEWAY_TIMEOUT).body(result));
	}
	
	
	
	
	
	/**
	 * @param {int} idLibro da eliminare 
	 * @return {CompletableFuture<ResponseEntity<RisultatoDTO<String>>>} 
	 */
	@RequestMapping(
			value = "api/libro/{id}",
			method = RequestMethod.DELETE
			)
	@CircuitBreaker(
			name = "deleteLibro",
			fallbackMethod = "fallbackDeleteLibro"
			)
	@Bulkhead(
			name = "deleteLibro",
			fallbackMethod = "fallbackDeleteLibro",
			type = Type.THREADPOOL
			)
	@TimeLimiter(
			name = "deleteLibro",
			fallbackMethod = "fallbackDeleteLibro"
			)
	public CompletableFuture<ResponseEntity<RisultatoDTO<String>>> deleteLibro (@PathVariable int id) {
		RisultatoDTO<String> result = new RisultatoDTO<String>();	
		libroService.deleteLibro(id);
		
		result.setData(null)
		.success(HttpStatus.SC_GATEWAY_TIMEOUT)
		.setDescrizione("Eliminazion avvenuta con successo");
		logger.info("Eliminazione libro avvenuta con successo.");
		return  CompletableFuture.completedFuture(ResponseEntity.status(HttpStatus.SC_OK).body(result));
	}
	
	/**
	 * @param {int} idLibro da eliminare 
	 * @param {Throwable} eccezione lanciata in caso di errore 
	 * @return {CompletableFuture<ResponseEntity<RisultatoDTO<String>>>} 
	 */
	public CompletableFuture<ResponseEntity<RisultatoDTO<String>>> fallbackDeleteLibro(int id,  Throwable throwable){
		RisultatoDTO<String> result = new RisultatoDTO<String>();	
		
		result.setData(null)
		.success(HttpStatus.SC_GATEWAY_TIMEOUT)
		.setDescrizione("Tempo scaduto, non è possibile eliminare il Libro indicato");
		logger.error("Fallback:qualcosa è andato storto!.");
		return  CompletableFuture.completedFuture(ResponseEntity.status(HttpStatus.SC_GATEWAY_TIMEOUT).body(result));
	}
	
	
	
	
	
	/**
	 * @param {Date} data antecedente alla pubblicazione di un libro
	 * @return {CompletableFuture<ResponseEntity<RisultatoDTO<List<LibroDTO>>>>} 
	 */
	@RequestMapping(
			value = "api/libro/age/{dataUscita}",
			method = RequestMethod.GET
			)
	@CircuitBreaker(
			name = "getLibroAfterSpecificDate",
			fallbackMethod = "fallbackGetLibroAfterSpecificDate"
			)
	@Bulkhead(
			name = "getLibroAfterSpecificDate",
			fallbackMethod = "fallbackGetLibroAfterSpecificDate",
			type = Type.THREADPOOL
			)
	@TimeLimiter(
			name = "getLibroAfterSpecificDate",
			fallbackMethod = "fallbackGetLibroAfterSpecificDate"
			)
	public CompletableFuture<ResponseEntity<RisultatoDTO<List<LibroDTO>>>> getLibroAfterSpecificDate(@PathVariable Date dataUscita){
		RisultatoDTO<List<LibroDTO>> result = new RisultatoDTO<List<LibroDTO>>();
		List<LibroDTO> resultLibriDTO =  libroService.getLibroAfterSpecificDate(dataUscita);
		if(!resultLibriDTO.isEmpty()) {
			result.setData(resultLibriDTO)
			.success(HttpStatus.SC_OK)
			.setDescrizione("Operazione avvenuta con successo");
			logger.info("Operazione AfterSpecificDate avvenuta con successo.");
			return CompletableFuture.completedFuture(ResponseEntity.status(HttpStatus.SC_OK).body(result));
		}
		result.setData(null)
		.success(HttpStatus.SC_BAD_REQUEST)
		.setDescrizione("Operazione non avvenuta con successo, inserire una data corretta");
		logger.error("Operazione AfterSpecificDate non avvenuta con successo.");
		return CompletableFuture.completedFuture(ResponseEntity.status(HttpStatus.SC_BAD_REQUEST).body(result));
	}
	
	/**
	 *  @param {Date} data antecedente alla pubblicazione di un libro
	 * @param {Throwable} eccezione lanciata in caso di errore 
	 * @return {CompletableFuture<ResponseEntity<RisultatoDTO<List<LibroDTO>>>>} 
	 */
	public CompletableFuture<ResponseEntity<RisultatoDTO<List<LibroDTO>>>> fallbackGetLibroAfterSpecificDate(Date date, Throwable throwable){
		RisultatoDTO<List<LibroDTO>> result = new RisultatoDTO<List<LibroDTO>>();	
		
		result.setData(null)
		.success(HttpStatus.SC_GATEWAY_TIMEOUT)
		.setDescrizione("Tempo scaduto, non è possibile eliminare il Libro indicato");
		
		logger.error("Fallback:qualcosa è andato storto!.");
		return  CompletableFuture.completedFuture(ResponseEntity.status(HttpStatus.SC_GATEWAY_TIMEOUT).body(result));
	}
	
	
	

}
