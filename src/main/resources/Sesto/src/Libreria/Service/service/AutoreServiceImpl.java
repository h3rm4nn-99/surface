package Libreria.Service.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.Libreria.DAO.AutoreDAO;
import com.example.Libreria.DTO.AutoreDTO;
import com.example.Libreria.entity.Autore;
import com.example.Libreria.utils.utilAutore;


/**
 * Modella l'entità Autore utilizzando l'oggetto AutoreDTO
 * mediante i seguenti metodi implementati.
 * Utilizza tre metodi get che consentono di recuperare l'Autore a
 * seconda del id, del nome e di recuperare tutti gli autori.
 * Utilizza un metodo delete per eliminare l'Autore.
 * Utilizza un metodo update per modificare alcuni campi dell'Autore.
 * Utilizza un metodo post per inserire un nuovo Autore.
 */


@Service
public class AutoreServiceImpl implements AutoreService{
	
	@Autowired
	private AutoreDAO autoreDAO;
	@Autowired
	utilAutore mapperAutoreDTO;
	

	/**
	 * @param {AutoreDTO} autoreDTO da inserire 
	 * @return {AutoreTO} autoreDTO inserito
	 */
	@Transactional
	public AutoreDTO insertAutore(AutoreDTO autoreDTO) {
		Autore autore = new Autore();
		autore.setCognome(autoreDTO.getCognome());
		autore.setNome(autoreDTO.getNome());
		autore.setLibri(mapperAutoreDTO.getLibri(autoreDTO));
		autoreDAO.save(autore);
		return autoreDTO;
	}
	
	/** 
	 * @return {List<AutoreDTO>}
	 */
	public List<AutoreDTO> getAll(){
		List<Autore> autori = autoreDAO.findAll();
		List<AutoreDTO> autoriDTO = new ArrayList<AutoreDTO>();
		for(Autore autore : autori) {
				AutoreDTO autoreDTO = new AutoreDTO();
				autoreDTO.setCognome(autore.getCognome());
				autoreDTO.setNome(autore.getNome());
				autoreDTO.setLibri(mapperAutoreDTO.getLibriAutoreDTO(autore));
				autoriDTO.add(autoreDTO);
		}
		return autoriDTO;
	}
	
	/**
	 * @param {int} idAutore da cercare
	 * @return {AutoreDTO} autore associato a quel id
	 */
	public AutoreDTO findAutoreById(int id) {
		Optional<Autore> autoreOptional = autoreDAO.findById(id);
		if(autoreOptional.isPresent()) {
			Autore autore = autoreOptional.get();
			AutoreDTO autoreDTO = new AutoreDTO();
			autoreDTO.setCognome(autore.getCognome());
			autoreDTO.setNome(autore.getNome());
			autoreDTO.setLibri(mapperAutoreDTO.getLibriAutoreDTO(autore));
			return autoreDTO;
		}
		return null;
	}
	
	/**
	 * @param {String} nome autore da cercare
	 * @return {List<AutoreDTO>} lista di autori associati a nome
	 */
	public List<AutoreDTO> findAutoreByNome(String nome) {
		Optional<List<Autore>> autoriOptional = autoreDAO.findByNome(nome);
		List<AutoreDTO> autoriDTO = new ArrayList<AutoreDTO>();
		if(autoriOptional.isPresent()) {
			List<Autore> autori = autoriOptional.get();
			for(Autore autore : autori) {
				AutoreDTO autoreDTO = new AutoreDTO();
				autoreDTO.setCognome(autore.getCognome());
				autoreDTO.setNome(autore.getNome());
				autoreDTO.setLibri(mapperAutoreDTO.getLibriAutoreDTO(autore));
				autoriDTO.add(autoreDTO);
			}
		}
		if(!autoriDTO.isEmpty()) {
			return autoriDTO;
		}
		return null;
	}

	/**
	 * @param {int} idAutore da modificare
	 * @param {AutoreDTO} autoreDTO con i nuovi parametri
	 * @return {AutoreDTO} autoreDTO con le modifiche effettuate
	 */
	//MODIFICA SOLO IL NOME E IL COGNOME DELL'AUTORE
	public AutoreDTO updateAutore(AutoreDTO autoreDTO, int id) {
		Optional<Autore> autoreOptional = autoreDAO.findById(id);
		AutoreDTO autoreDTO2 = new AutoreDTO();
		if(autoreOptional.isPresent()) {
			Autore foundAutore = autoreOptional.get();
			foundAutore.setCognome(autoreDTO.getCognome());
			foundAutore.setNome(autoreDTO.getNome());
			
			autoreDTO2.setCognome(foundAutore.getCognome());
			autoreDTO2.setNome(foundAutore.getNome());
			autoreDTO2.setLibri(mapperAutoreDTO.getLibriAutoreDTO(foundAutore));
			autoreDAO.save(foundAutore);
			return autoreDTO2;
		}
		return null;
	}
	
	/**
	 * @param {int} idAutore da eliminare  
	 * @return void
	 */
	public void deleteAutore (int id) {
			Autore autore = autoreDAO.findById(id).get();
			autoreDAO.delete(autore);
	}
}
