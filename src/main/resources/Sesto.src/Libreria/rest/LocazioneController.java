package com.example.Libreria.rest;

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
import com.example.Libreria.DTO.LocazioneDTO;
import com.example.Libreria.Service.service.LocazioneService;
import com.example.Libreria.configuration.RisultatoDTO;
import com.example.Libreria.utils.utilLocazione;
import io.github.resilience4j.bulkhead.annotation.Bulkhead;
import io.github.resilience4j.bulkhead.annotation.Bulkhead.Type;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.timelimiter.annotation.TimeLimiter;
import io.micrometer.core.instrument.util.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;



/**
 * Modella l'entità Locazione mediante l'oggetto LocazioneDTO.
 * Utilizza un metodo get per cercare tutte le Locazione.
 * Utilizza un metodo delete per eliminare una Locazione.
 * Utilizza un metodo post per inserire una nuova Locazione.
 * Utilizza un metodo put per modificare alcuni campi della Locazione.
 */
@RestController
public class LocazioneController {

	@Autowired
	private LocazioneService locazioneService;
	@Autowired
	private utilLocazione utilLocazione;
	
	private Logger logger = LogManager.getLogger(LocazioneController.class);
	
	/**
	 * @return {CompletableFuture<ResponseEntity<RisultatoDTO<List<LocazioneDTO>>>>} 
	 */
	@RequestMapping(
			value= "api/locazione/all",
			method = RequestMethod.GET, 
			produces = MediaType.APPLICATION_JSON_VALUE
			)
	@CircuitBreaker(
			name="getAllService",
			fallbackMethod = "fallBackLocazioneAll"
			)
	@Bulkhead(
			name="getAllService", 
			fallbackMethod = "fallBackLocazioneAll",
			type = Bulkhead.Type.THREADPOOL
			)
	@TimeLimiter(
			name="getAllService",
			fallbackMethod = "fallBackLocazioneAll"
			)
	public CompletableFuture<ResponseEntity<RisultatoDTO<List<LocazioneDTO>>>> findAllLocazione(){
		RisultatoDTO<List<LocazioneDTO>> result = new RisultatoDTO<List<LocazioneDTO>>();
		List<LocazioneDTO> resultLitLocazione = locazioneService.getAllLocazione();
		if(resultLitLocazione != null ) {
			
			result.setData(resultLitLocazione)
			.success(HttpStatus.SC_OK)
			.setDescrizione("Operazione avvnuta con successo");
			logger.info("Operazione che ritorna tutte le locazioni avvenuta con successo.");
			return CompletableFuture.completedFuture(ResponseEntity.status(HttpStatus.SC_OK).body(result));
		}
		result.setData(null)
		.success(HttpStatus.SC_BAD_REQUEST)
		.setDescrizione("Operazione non avvenuta con successo, inserire correttamente i dati");
		logger.error("Operazione che ritorna tutte le locazioni non avvenuta con successo.");
		return CompletableFuture.completedFuture(ResponseEntity.status(HttpStatus.SC_BAD_REQUEST).body(result));
	}
	
	/**
	 * @param {Throwable} eccezione lanciata in caso di errore 
	 * @return {CompletableFuture<ResponseEntity<RisultatoDTO<List<LocazioneDTO>>>>} 
	 */
	public CompletableFuture<ResponseEntity<RisultatoDTO<List<LocazioneDTO>>>> fallBackLocazioneAll(Throwable throwable){
		RisultatoDTO<List<LocazioneDTO>> result = new RisultatoDTO<List<LocazioneDTO>>();
		result.setData(null)
		.success(HttpStatus.SC_GATEWAY_TIMEOUT)
		.setDescrizione("Tempo scaduto, non è possibile visualizzare la lista delle Locazioni");
		logger.error("Fallback:qualcosa è andato storto!.");
		return  CompletableFuture.completedFuture(ResponseEntity.status(HttpStatus.SC_GATEWAY_TIMEOUT).body(result));
	}

	
	
	/**
	 * @param {LocazioneDTO} LocazioneDTO da inserire
	 * @return {CompletableFuture<ResponseEntity<RisultatoDTO<LocazioneDTO>>>} 
	 */
	@RequestMapping(
			value= "api/locazione/add",
			method = RequestMethod.POST, 
			produces = MediaType.APPLICATION_JSON_VALUE
			)
	@CircuitBreaker(
			name = "insertLocazione"
			, fallbackMethod = "fallbackLocazioneInsert"
			)
	@Bulkhead(
			name = "insertLocazione",
			fallbackMethod = "fallbackLocazioneInsert",
			type =Bulkhead.Type.THREADPOOL
			)
	@TimeLimiter(
			name = "insertLocazione"
			, fallbackMethod = "fallbackLocazioneInsert"
			)
	@Transactional
	public CompletableFuture<ResponseEntity<RisultatoDTO<LocazioneDTO>>> insertLocazione (@RequestBody LocazioneDTO locazioneDTO) {
		RisultatoDTO<LocazioneDTO> result = new RisultatoDTO<LocazioneDTO>();
		if(!StringUtils.isBlank(locazioneDTO.getScompartimento())   && !utilLocazione.libroIsEmpty(locazioneDTO.getLibro())) {
			LocazioneDTO resultLocazioneDTO = locazioneService.insertLocazione(locazioneDTO);
			
			result.setData(resultLocazioneDTO)
			.success(HttpStatus.SC_OK)
			.setDescrizione("Operazione avvenuta con successo");
			logger.info("Inserimento locazione avvenuto con successo.");
			return  CompletableFuture.completedFuture(ResponseEntity.status(HttpStatus.SC_OK).body(result));
		}
		result.setData(null)
		.success(HttpStatus.SC_BAD_REQUEST)
		.setDescrizione("Operazione non avvenuta con successo, inserire correttamente i dati");
		logger.error("Inserimento locazione non avvenuto con successo.");
		return CompletableFuture.completedFuture(ResponseEntity.status(HttpStatus.SC_BAD_REQUEST).body(result));
	}
	
	/**
	 * @param {LocazioneDTO} LocazioneDTO da inserire
	 * @param {Throwable} eccezione lanciata in caso di errore 
	 * @return {CompletableFuture<ResponseEntity<RisultatoDTO<LocazioneDTO>>>} 
	 */
	public CompletableFuture<ResponseEntity<RisultatoDTO<LocazioneDTO>>> fallbackLocazioneInsert(LocazioneDTO l, Throwable throwable){
		RisultatoDTO<LocazioneDTO> result = new RisultatoDTO<LocazioneDTO>();
		throwable.printStackTrace();
		
		result.setData(null).
		success(HttpStatus.SC_GATEWAY_TIMEOUT)
		.setDescrizione("Tempo scaduto, non è possibile inserire una locazione");
		logger.error("Fallback:qualcosa è andato storto!.");
		return CompletableFuture.completedFuture(ResponseEntity.status(HttpStatus.SC_GATEWAY_TIMEOUT).body(result));
	}
	
	
	
	/**
	 * @param {int} idLocazione da modificare
	 * @param {LocazioneDTO} locazioneDTO con i nuovi parametri
	 * @param {Throwable} eccezione lanciata in caso di errore 
	 * @return {CompletableFuture<ResponseEntity<RisultatoDTO<LocazioneDTO>>>} 
	 */
	@RequestMapping(
			value="api/locazione/update/{id}",
			method = RequestMethod.PUT,
			produces = MediaType.APPLICATION_JSON_VALUE
			)
	@CircuitBreaker(
			name = "updateLocazione",
			fallbackMethod = "fallbackLocazioneUpdate"
			)
	@Bulkhead(
			name = "updateLocazione",
			fallbackMethod = "fallbackLocazioneUpdate",
			type = Type.THREADPOOL
			)
	@TimeLimiter(
			name = "updateLocazione",
			fallbackMethod = "fallbackLocazioneUpdate"
			)
	public CompletableFuture<ResponseEntity<RisultatoDTO<LocazioneDTO>>> updateLocazione(@RequestBody LocazioneDTO locazioneDTO, @PathVariable int id) {
		RisultatoDTO<LocazioneDTO> result = new RisultatoDTO<LocazioneDTO>();
		if(!StringUtils.isBlank(locazioneDTO.getScompartimento())  && !utilLocazione.libroIsEmpty(locazioneDTO.getLibro())) {
			LocazioneDTO resultLocazioneDAO = locazioneService.updateLocazione(locazioneDTO, id);
			if(resultLocazioneDAO != null) {
				
				result.setData(resultLocazioneDAO)
				.success(HttpStatus.SC_OK)
				.setDescrizione("Operazione avvenuta con successo");
				logger.info("Aggiornamneto locazione avvenuto con successo.");
				return CompletableFuture.completedFuture(ResponseEntity.status(HttpStatus.SC_OK).body(result));
			}
		}
		result.setData(null)
		.success(HttpStatus.SC_BAD_REQUEST)
		.setDescrizione("Operazione non avvenuta con successo, inserire correttamente i dati");
		logger.error("Aggiornamneto locazione non avvenuto con successo.");
		return  CompletableFuture.completedFuture(ResponseEntity.status(HttpStatus.SC_BAD_REQUEST).body(result));
	}
	
	/**
	 * @param {int} idLocazione da modificare
	 * @param {LocazioneDTO} locazioneDTO con i nuovi parametri
	 * @param {Throwable} eccezione lanciata in caso di errore 
	 * @return {CompletableFuture<ResponseEntity<RisultatoDTO<LocazioneDTO>>>} 
	 */
	public CompletableFuture<ResponseEntity<RisultatoDTO<LocazioneDTO>>> fallbackLocazioneUpdate(LocazioneDTO l, int id ,Throwable throwable){
		RisultatoDTO<LocazioneDTO> result = new RisultatoDTO<LocazioneDTO>();
		throwable.printStackTrace();
		
		result.setData(null)
		.success(HttpStatus.SC_GATEWAY_TIMEOUT)
		.setDescrizione("Tempo scaduto, non è possibile aggiornare una locazione");
		logger.error("Fallback:qualcosa è andato storto!.");
		return CompletableFuture.completedFuture(ResponseEntity.status(HttpStatus.SC_GATEWAY_TIMEOUT).body(result));
	}
	
	
	
	/**
	 * @param {int} idLocazione da eliminare  
	 * @return { CompletableFuture<ResponseEntity<RisultatoDTO<String>>>} 
	 */
	@RequestMapping(
			value="api/locazione/delete/{id}",
			method = RequestMethod.DELETE,
			produces = MediaType.APPLICATION_JSON_VALUE
			)
	@CircuitBreaker(
			name = "deleteLocazione",
			fallbackMethod = "fallbackLocazioneDelete"
			)
	@Bulkhead(
			name = "deleteLocazione",
			fallbackMethod = "fallbackLocazioneDelete",
			type = Type.THREADPOOL
			)
	@TimeLimiter(
			name = "deleteLocazione",
			fallbackMethod = "fallbackLocazioneDelete"
			)
	public CompletableFuture<ResponseEntity<RisultatoDTO<String>>> deleteLocazione (@PathVariable int id) {
		RisultatoDTO<String> result = new RisultatoDTO<String>();	
		locazioneService.deleteLocazione(id);
		
		result.setData(null)
		.success(HttpStatus.SC_GATEWAY_TIMEOUT)
		.setDescrizione("Eliminazion avvenuta con successo");
		logger.info("Eliminazione locazione avvenuta con successo.");
		return CompletableFuture.completedFuture(ResponseEntity.status(HttpStatus.SC_OK).body(result));
	}
	
	/**
	 * @param {int} idLocazione da eliminare 
	 * @param {Throwable} eccezione lanciata in caso di errore 
	 * @return {CompletableFuture<ResponseEntity<RisultatoDTO<String>>>} 
	 */
	public CompletableFuture<ResponseEntity<RisultatoDTO<String>>> fallbackLocazioneDelete (int id, Throwable throwable){
		RisultatoDTO<String> result = new RisultatoDTO<String>();	
		throwable.printStackTrace();
		
		result.setData(null)
		.success(HttpStatus.SC_GATEWAY_TIMEOUT)
		.setDescrizione("Tempo scaduto, non è possibile eliminare la Locazione indicata");
		logger.error("Fallback:qualcosa è andato storto!.");
		return CompletableFuture.completedFuture(ResponseEntity.status(HttpStatus.SC_GATEWAY_TIMEOUT).body(result));
	}
	
	
}
