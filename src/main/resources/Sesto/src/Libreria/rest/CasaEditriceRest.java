package Libreria.rest;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import javax.transaction.Transactional;
import org.apache.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.example.Libreria.DTO.CasaEditriceDTO;
import Libreria.Service.service.CasaEditriceService;
import Libreria.configuration.RisultatoDTO;
import io.github.resilience4j.bulkhead.annotation.Bulkhead;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.timelimiter.annotation.TimeLimiter;
import io.micrometer.core.instrument.util.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;



/**
 * Modella l'entità CasaEditrice mediante l'oggetto CasaEditriceDTO.
 * Utilizza due metodi get che cercano le CasaEditrice in base al nome 
 * e cercano tutte le CasaEditrice.
 * Utilizza un metodo delete per eliminare una CasaEditrice.
 * Utilizza un metodo post per inserire una nuova CasaEditrice.
 * Utilizza un metodo put per modificare alcuni campi della CasaEditrice.
 */

@RestController
public class CasaEditriceRest {
	
	@Autowired
	private CasaEditriceService caseEditriciService;
	
	private Logger logger = LogManager.getLogger(CasaEditriceRest.class);
	
	/**
	 * @param {CasaEditriceDTO} CasaEditriceDTO da inserire
	 * @return {CompletableFuture<ResponseEntity<RisultatoDTO<CasaEditriceDTO>>>} 
	 */
	@RequestMapping(
			value="api/casaeditrice/add",
			method = RequestMethod.POST,
			produces = MediaType.APPLICATION_JSON_VALUE
			)
	@CircuitBreaker(
			name = "insertCasaEditrice",
			fallbackMethod = "fallbackInsertCasaEditrice"
			)
	@Bulkhead(
			name = "insertCasaEditrice", 
			fallbackMethod = "fallbackInsertCasaEditrice",
			type = Bulkhead.Type.THREADPOOL
			)
	@TimeLimiter(
			name = "insertCasaEditrice",
			fallbackMethod = "fallbackInsertCasaEditrice"
			)
	@Transactional
	public CompletableFuture<ResponseEntity<RisultatoDTO<CasaEditriceDTO>>> insertCasaEditrice (@RequestBody CasaEditriceDTO casaEditriceDTO) {
		RisultatoDTO<CasaEditriceDTO> result = new RisultatoDTO<CasaEditriceDTO>();
		boolean nome = StringUtils.isBlank(casaEditriceDTO.getNome());
		boolean libri = casaEditriceDTO.getLibri().isEmpty();
		if(!nome && !libri) {
			CasaEditriceDTO resultCasaEditrice = caseEditriciService.insertCasaEditrice(casaEditriceDTO);
			
			result.setData(resultCasaEditrice)
			.success(HttpStatus.SC_OK)
			.setDescrizione("Operazione avvenuta con successo");
			logger.info("L'inserimento di una Casa Editrice è avvenuta con successo.");
			return CompletableFuture.completedFuture(ResponseEntity.status(HttpStatus.SC_OK).body(result));
		}
		result.setData(null)
		.success(HttpStatus.SC_BAD_REQUEST)
		.setDescrizione("Operazione non avvenuta con successo");
		logger.error("L'insermento di una Casa Editrice non è avvenuta con successo.");
		return  CompletableFuture.completedFuture(ResponseEntity.status(HttpStatus.SC_BAD_REQUEST).body(result));
	}
	/**
	 * @param {CasaEditriceDTO} CasaEditriceDTO da inserire
	 * @param {Throwable} eccezione lanciata in caso di errore 
	 * @return {CompletableFuture<ResponseEntity<RisultatoDTO<CasaEditriceDTO>>>} 
	 */
	public  CompletableFuture<ResponseEntity<RisultatoDTO<CasaEditriceDTO>>> fallbackInsertCasaEditrice (CasaEditriceDTO casaEditriceDTO, Throwable throwable){
		RisultatoDTO<CasaEditriceDTO> result = new RisultatoDTO<CasaEditriceDTO>();
		throwable.printStackTrace();
		result.setData(null)
		.success(HttpStatus.SC_GATEWAY_TIMEOUT)
		.setDescrizione("Tempo scaduto, non è possibile inserire una Casa Editrice");
		logger.error("Fallback: qualcosa è andato storto!.");
		return  CompletableFuture.completedFuture(ResponseEntity.status(HttpStatus.SC_GATEWAY_TIMEOUT).body(result));
	}
	
	
	
	
	
	
	
	/**
	 * @return {CompletableFuture<ResponseEntity<RisultatoDTO<List<CasaEditriceDTO>>>>} 
	 */
	@RequestMapping(
			value ="api/casaeditrice/all", 
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE
			)
	@CircuitBreaker(
			name = "getAllCasaEditrice",
			fallbackMethod = "fallbackgetAllCasaEditrice"
			)
	@Bulkhead(
			name = "getAllCasaEditrice",
			fallbackMethod = "fallbackgetAllCasaEditrice",
			type = Bulkhead.Type.THREADPOOL
			)
	@TimeLimiter(
			name = "getAllinsertCasaEditrice", 
			fallbackMethod = "fallbackgetAllCasaEditrice"
			)
	public CompletableFuture<ResponseEntity<RisultatoDTO<List<CasaEditriceDTO>>>>  getAllCasaEditrice(){
		RisultatoDTO<List<CasaEditriceDTO>> result = new RisultatoDTO<List<CasaEditriceDTO>>();
		List<CasaEditriceDTO> resultCase =  caseEditriciService.getAllCasaEditrice();
		if(resultCase.isEmpty()) {
			
			result.setData(null)
			.success(HttpStatus.SC_BAD_REQUEST)
			.setDescrizione("Operazione non avvenuta con successo");
			logger.error("L'operazione per ottenere tutte le Case Editrici non è avvenuta con successo.");
			return  CompletableFuture.completedFuture(ResponseEntity.status(HttpStatus.SC_BAD_REQUEST).body(result));
		}
		result.setData(resultCase)
		.success(HttpStatus.SC_OK)
		.setDescrizione("Operazione avvenuta con successo");
		logger.info("L'operazione per ottenere tutte le Case Editrici è avvenuta con successo.");
		return CompletableFuture.completedFuture(ResponseEntity.status(HttpStatus.SC_OK).body(result));
	}
	
	/**
	 * @param {Throwable} eccezione lanciata in caso di errore 
	 * @return {CompletableFuture<ResponseEntity<RisultatoDTO<List<CasaEditriceDTO>>>>} 
	 */
	public CompletableFuture<ResponseEntity<RisultatoDTO<List<CasaEditriceDTO>>>> fallbackgetAllCasaEditrice(Throwable throwable){
		RisultatoDTO<List<CasaEditriceDTO>> result = new RisultatoDTO<List<CasaEditriceDTO>>();
		throwable.printStackTrace();
		result.setData(null)
		.success(HttpStatus.SC_GATEWAY_TIMEOUT)
		.setDescrizione("Tempo scaduto, non è possibile visualizzare tutte le Case Editrici");
		logger.error("Fallback: qualcosa è andato storto!.");
		return  CompletableFuture.completedFuture(ResponseEntity.status(HttpStatus.SC_GATEWAY_TIMEOUT).body(result));
	}
	
	
	
	
	
	
	/**
	 * @param {CasaEditriceDTO} CasaEditirce con nuovi parametri
	 * @param {int} id CasaEditrice da modificare
	 * @return {CompletableFuture<ResponseEntity<RisultatoDTO<CasaEditriceDTO>>>} 
	 */
	@RequestMapping(
			value="api/casaeditrice/update/{id}",
			method = RequestMethod.PUT, 
			produces = MediaType.APPLICATION_JSON_VALUE
			)
	@CircuitBreaker(
			name = "updateCasaEditrice", 
			fallbackMethod = "fallbackUpdateCasaEditrice"
			)
	@Bulkhead(
			name = "updateCasaEditrice", 
			fallbackMethod = "fallbackUpdateCasaEditrice", 
			type = Bulkhead.Type.THREADPOOL
			)
	@TimeLimiter(
			name = "updateCasaEditrice",
			fallbackMethod = "fallbackUpdateCasaEditrice"
			)
	public CompletableFuture<ResponseEntity<RisultatoDTO<CasaEditriceDTO>>> updateCasaEditrice(@RequestBody CasaEditriceDTO casaEditriceDTO ,@PathVariable int id) {
		RisultatoDTO<CasaEditriceDTO> result = new RisultatoDTO<CasaEditriceDTO>();
		boolean nome = StringUtils.isBlank(casaEditriceDTO.getNome());
		boolean libri = casaEditriceDTO.getLibri().isEmpty();
		if(!nome && !libri) {
			CasaEditriceDTO resultCasaEditrice = caseEditriciService.updateCasaEditrice(casaEditriceDTO,id);
			if(resultCasaEditrice != null) {
				
				result.setData(resultCasaEditrice)
				.success(HttpStatus.SC_OK)
				.setDescrizione("Operazione avvenuta con successo");
				logger.info("L'aggironamento della Casa Editrice è avvenuto con successo.");
				return CompletableFuture.completedFuture(ResponseEntity.status(HttpStatus.SC_OK).body(result));
			}
		}
		result.setData(null)
		.success(HttpStatus.SC_BAD_REQUEST)
		.setDescrizione("Operazione non avvenuta con successo");
		logger.error("L'aggironamento della Casa Editrice non è avvenuto con successo.");
		return CompletableFuture.completedFuture(ResponseEntity.status(HttpStatus.SC_BAD_REQUEST).body(result));
	}
	/**
	 * @param {CasaEditriceDTO} CasaEditirce con nuovi parametri
	 * @param {int} id CasaEditrice da modificare
	 * @param {Throwable} eccezione lanciata in caso di errore 
	 * @return {CompletableFuture<ResponseEntity<RisultatoDTO<CasaEditriceDTO>>>} 
	 */
	public CompletableFuture<ResponseEntity<RisultatoDTO<CasaEditriceDTO>>> fallbackUpdateCasaEditrice (CasaEditriceDTO casaEditriceDTO, int id,Throwable throwable){
		RisultatoDTO<CasaEditriceDTO> result = new RisultatoDTO<CasaEditriceDTO>();
		throwable.printStackTrace();
		
		result.setData(null)
		.success(HttpStatus.SC_GATEWAY_TIMEOUT)
		.setDescrizione("Tempo scaduto, non è possibile aggiornare una Casa Editrice");
		logger.error("Fallback: qualcosa è andato storto!.");
		return  CompletableFuture.completedFuture(ResponseEntity.status(HttpStatus.SC_GATEWAY_TIMEOUT).body(result));
	}
	
	
	
	
	
	/**
	 * @param {String} nome CasaEditrice da cercare
	 * @return {CompletableFuture<ResponseEntity<RisultatoDTO<List<CasaEditriceDTO>>>>} 
	 */
	@RequestMapping(
			value = "api/casaeditrice/find/{nome}",
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
	@CircuitBreaker(
			name = "findCasaEditriceByNome",
			fallbackMethod = "fallbackFindCasaEditriceByNome"
			)
	@Bulkhead(
			name = "findCasaEditriceByNome",
			fallbackMethod = "fallbackFindCasaEditriceByNome",
			type = Bulkhead.Type.THREADPOOL
			)
	@TimeLimiter(
			name = "findCasaEditriceByNome",
			fallbackMethod = "fallbackFindCasaEditriceByNome"
			)
	public CompletableFuture<ResponseEntity<RisultatoDTO<List<CasaEditriceDTO>>>> findCasaEditriceByNome(@PathVariable String nome) {
		RisultatoDTO<List<CasaEditriceDTO>> result = new  RisultatoDTO<List<CasaEditriceDTO>>();
		List<CasaEditriceDTO> resultCasaEditriceDTO = caseEditriciService.findCasaEditriceByNome(nome);
		if(!StringUtils.isBlank(nome) && !resultCasaEditriceDTO.isEmpty()) {
			result.setData(resultCasaEditriceDTO)
			.success(HttpStatus.SC_OK)
			.setDescrizione("Operazione avvenuta con successo");
			logger.info("L'operazione FindByNome avvenuta con successo.");
			return CompletableFuture.completedFuture(ResponseEntity.status(HttpStatus.SC_OK).body(result));
		}
		result.setData(null)
		.success(HttpStatus.SC_BAD_REQUEST)
		.setDescrizione("Operazione non avvenuta con successo, nome CasaEditrice non esistente");
		logger.error("Loperazione FindByNome non è avvenuta con successo.");
		return CompletableFuture.completedFuture(ResponseEntity.status(HttpStatus.SC_BAD_REQUEST).body(result));	
	}
	/**
	 * @param {String} nome CasaEditrice da cercare
	 * @param {Throwable} eccezione lanciata in caso di errore 
	 * @return {CompletableFuture<ResponseEntity<RisultatoDTO<List<CasaEditriceDTO>>>>} 
	 */
	public CompletableFuture<ResponseEntity<RisultatoDTO<List<CasaEditriceDTO>>>> fallbackFindCasaEditriceByNome(String nome, Throwable throwable){
		RisultatoDTO<List<CasaEditriceDTO>> result = new RisultatoDTO<List<CasaEditriceDTO>>();
		throwable.printStackTrace();
		
		result.setData(null)
		.success(HttpStatus.SC_GATEWAY_TIMEOUT)
		.setDescrizione("Tempo scaduto, non è possibile visualizzare tutte le Case Editrici con il nome selezionato");
		logger.error("Fallback:qualcosa è andato storto!.");
		return  CompletableFuture.completedFuture(ResponseEntity.status(HttpStatus.SC_GATEWAY_TIMEOUT).body(result));
	}
	
	
	
	
	
	/**
	 * @param {int} id CasaEditrice da eliminare 
	 * @return {CompletableFuture<ResponseEntity<RisultatoDTO<String>>>} 
	 */
	@RequestMapping(
			value ="api/casaeditrice/delete/{id}",
			method = RequestMethod.DELETE,
			produces = MediaType.APPLICATION_JSON_VALUE
			)
	@CircuitBreaker(
			name = "deleteCasaEditrice",
			fallbackMethod = "fallbackDeleteCasaEditrice"
			)
	@Bulkhead(
			name = "deleteCasaEditrice",
			fallbackMethod = "fallbackDeleteCasaEditrice"
			, type = Bulkhead.Type.THREADPOOL
			)
	@TimeLimiter(
			name = "deleteCasaEditrice",
			fallbackMethod = "fallbackDeleteCasaEditrice"
			)
	public CompletableFuture<ResponseEntity<RisultatoDTO<String>>> deleteCasaEitrice (@PathVariable int id) {
		RisultatoDTO<String> result = new RisultatoDTO<String>();
		caseEditriciService.deleteCasaEitice(id);
		
		result.setData(null)
		.success(HttpStatus.SC_OK)
		.setDescrizione("Eliminazione avvenuta con successo");
		logger.info("Eliminazione Casa Editrice avvenuta con successo.");
		return CompletableFuture.completedFuture(ResponseEntity.status(HttpStatus.SC_OK).body(result));
	}
	/**
	 * @param {int} id CasaEditrice da eliminare 
	 * @param {Throwable} eccezione lanciata in caso di errore 
	 * @return {CompletableFuture<ResponseEntity<RisultatoDTO<String>>>} 
	 */
	public CompletableFuture<ResponseEntity<RisultatoDTO<String>>> fallbackDeleteCasaEditrice(int id, Throwable throwable){
		RisultatoDTO<String> result = new RisultatoDTO<String>();
		throwable.printStackTrace();
		
		result.setData(null)
		.success(HttpStatus.SC_GATEWAY_TIMEOUT)
		.setDescrizione("Tempo scaduto, non è possibile elimanare la Casa Editrice desiderata");
		logger.error("Fallback: qualcosa è andato storto!.");
		return CompletableFuture.completedFuture(ResponseEntity.status(HttpStatus.SC_OK).body(result));
	}
	

}
