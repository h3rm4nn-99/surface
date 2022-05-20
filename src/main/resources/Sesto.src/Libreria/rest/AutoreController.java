package com.example.Libreria.rest;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.apache.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.example.Libreria.DTO.AutoreDTO;
import com.example.Libreria.Service.service.AutoreService;
import com.example.Libreria.configuration.RisultatoDTO;
import io.github.resilience4j.bulkhead.annotation.Bulkhead;
import io.github.resilience4j.bulkhead.annotation.Bulkhead.Type;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.timelimiter.annotation.TimeLimiter;
import io.micrometer.core.instrument.util.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


/**
 * Modella l'entita Autore mediante l'oggetto AutoreDTO.
 * Utilizza tre metodi get che consentono di recuperare l'Autore a
 * seconda del id, del nome e di recuperare tutti gli autori.
 * Utilizza un metodo delete per eliminare l'Autore.
 * Utilizza un metodo update per modificare alcuni campi dell'Autore.
 * Utilizza un metodo post per inserire un nuovo Autore.
 * 
 */

@RestController
public class AutoreController {

	@Autowired
	private AutoreService autoreService;

	private Logger logger = LogManager.getLogger(AutoreController.class);
	
	
	/**
	 * @param {AutoreDTO} autoreDTO da inserire
	 * @return {CompletableFuture<ResponseEntity<RisultatoDTO<AutoreDTO>>>} 
	 */
	@RequestMapping(
			value = "api/autore/add", 
			method = RequestMethod.POST, 
			produces = MediaType.APPLICATION_JSON_VALUE)
	@CircuitBreaker(
			name = "insertAutore", 
			fallbackMethod = "fallbackInsertAutore")
	@Bulkhead(
			name = "insertAutore",
			fallbackMethod = "fallbackInsertAutore", 
			type = Bulkhead.Type.THREADPOOL)
	@TimeLimiter(
			name = "insertAutore",
			fallbackMethod = "fallbackInsertAutore")
	@Transactional
	public CompletableFuture<ResponseEntity<RisultatoDTO<AutoreDTO>>> insertAutore(@RequestBody AutoreDTO autoreDTO) {
		RisultatoDTO<AutoreDTO> result = new RisultatoDTO<AutoreDTO>();	
		boolean cognome = StringUtils.isBlank(autoreDTO.getCognome());
		boolean nome = StringUtils.isBlank(autoreDTO.getNome());
		boolean libroEmpty = autoreDTO.getLibri().isEmpty();
		if(!cognome && !nome && !libroEmpty){
				 AutoreDTO resultAutoreDTO = autoreService.insertAutore(autoreDTO);
				 result.setData(resultAutoreDTO)
				 .success(HttpStatus.SC_OK)
				 .setDescrizione("Operazione avvenuta con successo");
				 logger.info("Inserimento autore avvenuta con successo.");
				 return CompletableFuture.completedFuture(ResponseEntity.status(HttpStatus.SC_OK).body(result));
			}
			result.setData(null)
			.success(HttpStatus.SC_BAD_REQUEST)
			.setDescrizione("Operazione non avvenuta con successo, inserire bene i dati");
			logger.info("Inserimento di un autore non avvenuta con successo");
			return  CompletableFuture.completedFuture(ResponseEntity.status(HttpStatus.SC_BAD_REQUEST).body(result));
	}
	/**
	 * @param {AutoreDTO} autoreDTO da inserire
	 * @param {Throwable} eccezione lanciata in caso di errore 
	 * @return {CompletableFuture<ResponseEntity<RisultatoDTO<AutoreDTO>>>} 
	 */
	public CompletableFuture<ResponseEntity<RisultatoDTO<AutoreDTO>>> fallbackInsertAutore(AutoreDTO autoreDTO, Throwable throwable){
		RisultatoDTO<AutoreDTO> result = new RisultatoDTO<AutoreDTO>();
		throwable.printStackTrace();
		result.setData(null)
		.success(HttpStatus.SC_GATEWAY_TIMEOUT)
		.setDescrizione("Tempo scaduto, non è possibile inserire un Autore");
		logger.error("Fallback: qualcosa è andato storto!");
		return  CompletableFuture.completedFuture(ResponseEntity.status(HttpStatus.SC_GATEWAY_TIMEOUT).body(result));
	}
	
	/**
	 * @return {CompletableFuture<ResponseEntity<RisultatoDTO<List<AutoreDTO>>>>} 
	 */
	@RequestMapping(
			value = "api/autore/all",
			method = RequestMethod.GET, 
			produces = MediaType.APPLICATION_JSON_VALUE)
	@CircuitBreaker(
			name = "getAllAutore", 
			fallbackMethod = "fallbackgetAllAutore")
	@Bulkhead(
			name = "getAllAutore", 
			fallbackMethod = "fallbackgetAllAutore", 
			type = Bulkhead.Type.THREADPOOL)
	@TimeLimiter(
			name = "getAllAutore", 
			fallbackMethod = "fallbackgetAllAutore")
	public CompletableFuture<ResponseEntity<RisultatoDTO<List<AutoreDTO>>>> getAllAutore(){
		RisultatoDTO<List<AutoreDTO>> result = new RisultatoDTO<List<AutoreDTO>>();
		List<AutoreDTO> autoriDTO = autoreService.getAll();
		if(autoriDTO.isEmpty()) {
			result.setData(null)
			.success(HttpStatus.SC_BAD_REQUEST)
			.setDescrizione("Operazione non avvenuta con successo");
			logger.error("Non è possibile mostrare la lista con tutti gli autori.");
			return CompletableFuture.completedFuture(ResponseEntity.status(HttpStatus.SC_BAD_REQUEST).body(result));
		}
		result.setData(autoriDTO)
		.success(HttpStatus.SC_OK)
		.setDescrizione("Operazione avvenuta con successo");
		logger.info("L'operazione che mostra tutti gli autori è avvenuta con successo.");
		return CompletableFuture.completedFuture(ResponseEntity.status(HttpStatus.SC_OK).body(result));
	}
	/**
	 * @param {Throwable} eccezione lanciata in caso di errore 
	 * @return {CompletableFuture<ResponseEntity<RisultatoDTO<List<AutoreDTO>>>>} 
	 */
	public CompletableFuture<ResponseEntity<RisultatoDTO<List<AutoreDTO>>>> fallbackgetAllAutore(Throwable throwable){
		RisultatoDTO<List<AutoreDTO>> result = new RisultatoDTO<List<AutoreDTO>>();	
		throwable.printStackTrace();
		result.setData(null)
		.success(HttpStatus.SC_GATEWAY_TIMEOUT)
		.setDescrizione("Tempo scaduto, non è possibile trovare tutti gli autori");
		logger.error("Fallback: qualcosao è anadto storto!.");
		return  CompletableFuture.completedFuture(ResponseEntity.status(HttpStatus.SC_GATEWAY_TIMEOUT).body(result));
	}
	
	/**
	 * @param {int} id Autore da cercare
	 * @return {CompletableFuture<ResponseEntity<RisultatoDTO<AutoreDTO>>>} 
	 */
	@RequestMapping(
			value = "api/autore/{id}", 
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
	@CircuitBreaker(
			name = "findAutoreById",
			fallbackMethod = "fallbackfindAutoreById")
	@Bulkhead(
			name = "findAutoreById", 
			fallbackMethod = "fallbackfindAutoreById",
			type = Type.THREADPOOL)
	@TimeLimiter(
			name = "findAutoreById",
			fallbackMethod = "fallbackfindAutoreById")
	public CompletableFuture<ResponseEntity<RisultatoDTO<AutoreDTO>>> findAutoreById(@PathVariable int id) {
		RisultatoDTO<AutoreDTO> result = new RisultatoDTO<AutoreDTO>();
		AutoreDTO resultAutoreDTO = autoreService.findAutoreById(id);
		if(resultAutoreDTO != null) {
			
				result.setData(resultAutoreDTO)
				.success(HttpStatus.SC_OK)
				.setDescrizione("Operazione avvenuta con succcesso");
				logger.info("L'operazione FindById avvenuta con successo.");
				return CompletableFuture.completedFuture(ResponseEntity.status(HttpStatus.SC_OK).body(result));
			}
			result.setData(null)
			.success(HttpStatus.SC_BAD_REQUEST)
			.setDescrizione("Operazione non avvenuta con successo, inserire bene i dati");
			logger.error("L'operazione FindById non è avvenuta con successo.");
			return CompletableFuture.completedFuture(ResponseEntity.status(HttpStatus.SC_BAD_REQUEST).body(result));
	}
	/**
	 * @param {int} id Autore da cercare
	 * @param {Throwable} eccezione lanciata in caso di errore 
	 * @return {CompletableFuture<ResponseEntity<RisultatoDTO<AutoreDTO>>>} 
	 */
	public CompletableFuture<ResponseEntity<RisultatoDTO<AutoreDTO>>> fallbackfindAutoreById(int id,Throwable throwable){
		RisultatoDTO<AutoreDTO> result = new RisultatoDTO<AutoreDTO>();	
		throwable.printStackTrace();
		result.setData(null)
		.success(HttpStatus.SC_GATEWAY_TIMEOUT)
		.setDescrizione("Tempo scaduto, non è possibile trovare l'autore");
		logger.error("Fallback: qualcosa è andato storto!.");
		return  CompletableFuture.completedFuture(ResponseEntity.status(HttpStatus.SC_GATEWAY_TIMEOUT).body(result));
	}
	
	/**
	 *@param {String} nome autore da trovare
	 * @return {CompletableFuture<ResponseEntity<RisultatoDTO<List<AutoreDTO>>>>} 
	 */
	@RequestMapping(
			value = "api/autore/nome/{name}",
			method = RequestMethod.GET, 
			produces = MediaType.APPLICATION_JSON_VALUE)
	@CircuitBreaker(
			name = "findAutoreByName", 
			fallbackMethod = "fallbackfindAutoreByName")
	@Bulkhead(
			name = "findAutoreByName", 
			fallbackMethod = "fallbackfindAutoreByName",
			type = Type.THREADPOOL)
	@TimeLimiter(
			name = "findAutoreByName",
			fallbackMethod = "fallbackfindAutoreByName")
	public CompletableFuture<ResponseEntity<RisultatoDTO<List<AutoreDTO>>>> findAutoreByNome(@PathVariable String nome) {
		RisultatoDTO<List<AutoreDTO>> result = new RisultatoDTO<List<AutoreDTO>>();
		List<AutoreDTO> resultAutoreDTO = autoreService.findAutoreByNome(nome);
		if(!resultAutoreDTO.isEmpty()) {
			
			result.setData(resultAutoreDTO)
			.success(HttpStatus.SC_OK)
			.setDescrizione("Operazione avvenuta con successo");
			logger.info("L'operazione FindByNome è avvenuta con successo.");
			return  CompletableFuture.completedFuture(ResponseEntity.status(HttpStatus.SC_OK).body(result));
		}
		result.setData(null)
		.success(HttpStatus.SC_BAD_REQUEST)
		.setDescrizione("Operazione non avvenuta con successo, inserire bene i dati");
		logger.info("L'operazione FindByNome non è avvenuta con successo.");
		return CompletableFuture.completedFuture(ResponseEntity.status(HttpStatus.SC_BAD_REQUEST).body(result));
	}
	/**
	 * @param {String} nome autore da trovare
	 * @param {Throwable} eccezione lanciata in caso di errore 
	 * @return {CompletableFuture<ResponseEntity<RisultatoDTO<List<AutoreDTO>>>>} 
	 */
	public CompletableFuture<ResponseEntity<RisultatoDTO<List<AutoreDTO>>>> fallbackfindAutoreByName(String nome ,Throwable throwable){
		RisultatoDTO<List<AutoreDTO>> result = new RisultatoDTO<List<AutoreDTO>>();	
		throwable.printStackTrace();
		result.setData(null)
		.success(HttpStatus.SC_GATEWAY_TIMEOUT)
		.setDescrizione("Tempo scaduto, non è possibile trovare l'autore con il suddetto nome");
		logger.error("Fallback: qualcosa è andato storto!.");
		return  CompletableFuture.completedFuture(ResponseEntity.status(HttpStatus.SC_GATEWAY_TIMEOUT).body(result));
	}
	
	
	/**
	 * @param {int} id Autore da modificare
	 * @param {AutoreDTO} autoreDTO con nuovi parametri
	 * @return {CompletableFuture<ResponseEntity<RisultatoDTO<AutoreDTO>>>} 
	 */
	@RequestMapping(
			value = "api/autore/{id}", 
			method = RequestMethod.PUT)
	@CircuitBreaker(
			name = "updateAutore",
			fallbackMethod = "fallbackUpdateAutore")
	@Bulkhead(
			name = "updateAutore",
			fallbackMethod = "fallbackUpdateAutore",
			type = Type.THREADPOOL)
	@TimeLimiter(
			name = "updateAutore",
			fallbackMethod = "fallbackUpdateAutore")
	public CompletableFuture<ResponseEntity<RisultatoDTO<AutoreDTO>>> updateAutore(@RequestBody AutoreDTO autoreDTO, @PathVariable int id) {
		RisultatoDTO<AutoreDTO> result = new RisultatoDTO<AutoreDTO>();
		boolean nome = StringUtils.isBlank(autoreDTO.getNome());
		boolean cognome = StringUtils.isBlank(autoreDTO.getCognome());
		boolean libroEmpty = autoreDTO.getLibri().isEmpty();
		if(!nome && !cognome && !libroEmpty) {
			AutoreDTO resultAutoreDTO =  autoreService.updateAutore(autoreDTO, id);
			if(resultAutoreDTO != null) {
				result.setData(resultAutoreDTO)
				.success(HttpStatus.SC_OK)
				.setDescrizione("Operazione avvenuta con successo");
				logger.info("L'aggiornamento dell'autore è avvenuto con successo.");
				return  CompletableFuture.completedFuture(ResponseEntity.status(HttpStatus.SC_OK).body(result));
			}
		}
		result.setData(null)
		.success(HttpStatus.SC_BAD_REQUEST)
		.setDescrizione("Operazione non avvenuta con successo, inserire bene i dati");
		logger.error("L'aggiornamento dell'autore non è avvenuta con successo.");
		return  CompletableFuture.completedFuture(ResponseEntity.status(HttpStatus.SC_BAD_REQUEST).body(result));
	}
	/**
	 * @param {int} id Autore da modificare
	 * @param {Throwable} eccezione lanciata in caso di errore 
	 * @param {AutoreDTO} autoreDTO con nuovi parametri
	 * @return {CompletableFuture<ResponseEntity<RisultatoDTO<AutoreDTO>>>} 
	 */
	public CompletableFuture<ResponseEntity<RisultatoDTO<AutoreDTO>>> fallbackUpdateAutore(AutoreDTO autoreDTO, int id,Throwable throwable){
		RisultatoDTO<AutoreDTO> result = new RisultatoDTO<AutoreDTO>();	
		throwable.printStackTrace();
		result.setData(null)
		.success(HttpStatus.SC_GATEWAY_TIMEOUT)
		.setDescrizione("Tempo scaduto, non è possibile aggiornare l'autore");
		logger.error("Fallback: qualcosa è andato storto!.");
		return  CompletableFuture.completedFuture(ResponseEntity.status(HttpStatus.SC_GATEWAY_TIMEOUT).body(result));
	}
	
	
	/**
	 * @param {int} id Autore da eliminare 
	 * @return {CompletableFuture<ResponseEntity<RisultatoDTO<String>>>} 
	 */
	@RequestMapping(
			value = "api/autore/{id}", 
			method =RequestMethod.DELETE)
	@CircuitBreaker(
			name = "deleteAutore",
			fallbackMethod = "fallbackDeleteAutore")
	@Bulkhead(
			name = "deleteAutore",
			fallbackMethod = "fallbackDeleteAutore",
			type = Type.THREADPOOL)
	@TimeLimiter(
			name = "deleteAutore",
			fallbackMethod = "fallbackDeleteAutore")
	public CompletableFuture<ResponseEntity<RisultatoDTO<String>>> deleteAutore (@PathVariable int id) {
		RisultatoDTO<String> result = new RisultatoDTO<String>();	
		autoreService.deleteAutore(id);
		
		result.setData(null)
		.success(HttpStatus.SC_OK)
		.setDescrizione("Eliminazione avvenuta con successo");
		logger.info("Eliminazione autore avvenuta con successo.");
		return  CompletableFuture.completedFuture(ResponseEntity.status(HttpStatus.SC_OK).body(result));
	}
	/**
	 * @param {int} id Autore da eliminare 
	 * @param {Throwable} eccezione lanciata in caso di errore 
	 * @return {CompletableFuture<ResponseEntity<RisultatoDTO<String>>>} 
	 */
	public CompletableFuture<ResponseEntity<RisultatoDTO<String>>> fallbackDeleteAutore(int id, Throwable throwable){
		RisultatoDTO<String> result = new RisultatoDTO<String>();
		throwable.printStackTrace();
		
		result.setData(null)
		.success(HttpStatus.SC_GATEWAY_TIMEOUT)
		.setDescrizione("Tempo scaduto, non è possibile elimanare la Casa Editrice desiderata");
		logger.error("Fallback: qualcosa è andato storto!.");
		return CompletableFuture.completedFuture(ResponseEntity.status(HttpStatus.SC_GATEWAY_TIMEOUT).body(result));
	}
	
	
}
