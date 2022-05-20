package com.example.Libreria.Service.service;


import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.Libreria.DAO.LocazioneDAO;
import com.example.Libreria.DTO.LocazioneDTO;
import com.example.Libreria.entity.Locazione;
import com.example.Libreria.utils.utilLocazione;


/**
 * Modella l'entità Autore utilizzando l'oggetto AutoreDTO 
 * mediante i seguenti metodi implementati.
 * Utilizza un metodo get per cercare tutte le Locazione.
 * Utilizza un metodo delete per eliminare una Locazione.
 * Utilizza un metodo post per inserire una nuova Locazione.
 * Utilizza un metodo put per modificare alcuni campi della Locazione.
 */


@Service
public class LocazioniServiceImpl implements LocazioneService {

	@Autowired
	private LocazioneDAO locazioneDAO;
	@Autowired
	private utilLocazione utilLocazione;
	
	/** 
	 * @return {List<LocazioneDTO>}
	 */
	public List<LocazioneDTO> getAllLocazione(){
		List<Locazione> locazioni =  locazioneDAO.findAll();
		if(!locazioni.isEmpty()) {
			return utilLocazione.getAllLoczioniDTOFromLocazioni(locazioni);
		}
		return null;
	}
	
	/**
	 * @param {LocazioneDTO} locazioneDTO da inserire
	 * @return {LocazioneDTO} locazioneDTO inserita
	 */
	//IL JSON RITRNATO MOSTRA SOLO LA POSIZIONE E LO SCOMPARTIMENTO
	public LocazioneDTO insertLocazione (LocazioneDTO locazioneDTO) {
		Locazione locazione = utilLocazione.locazioneDTOtoLocazione(locazioneDTO);
		locazioneDAO.save(locazione);
		LocazioneDTO locazioneDTO2 = new LocazioneDTO();
		locazioneDTO2.setPosizione(locazione.getPosizione());
		locazioneDTO2.setScompartimento(locazione.getScompartimento());
		return locazioneDTO2;
	}
	
	/**
	 * @param {int} idLocazione da modificare
	 * @param {LocazioneDTO} locazioneDTO con i nuovi parametri
	 * @return {LoazioneDTO} locazioneDTO modificata con i nuovi parametri
	 */
	public LocazioneDTO updateLocazione(LocazioneDTO locazioneDTO, int id) {
		Optional<Locazione> foundLocazione = locazioneDAO.findById(id);
		if(foundLocazione.isPresent()) {
			Locazione locazione = foundLocazione.get();
			locazione.setPosizione(locazioneDTO.getPosizione());
			locazione.setScompartimento(locazioneDTO.getScompartimento());
			locazioneDAO.save(locazione);
			
			LocazioneDTO locazioneDTO2 = new LocazioneDTO();
			locazioneDTO2.setPosizione(foundLocazione.get().getPosizione());
			locazioneDTO2.setScompartimento(foundLocazione.get().getScompartimento());
			return locazioneDTO;  
		}
		return null;
	}
	
	/**
	 * @param {int} idLocazione da eliminare  
	 * @return void
	 */
	public void deleteLocazione (int id) {
		if(locazioneDAO.existsById(id)) {
			Locazione locazione = locazioneDAO.findById(id).get();
			locazioneDAO.delete(locazione);
		}
	}
	
}
