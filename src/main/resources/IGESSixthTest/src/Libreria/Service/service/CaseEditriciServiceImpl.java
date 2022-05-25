package Libreria.Service.service;


import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.Libreria.DAO.CasaEditriceDAO;
import com.example.Libreria.DTO.CasaEditriceDTO;
import com.example.Libreria.entity.CasaEditrice;
import com.example.Libreria.utils.utilCasaEditrice;


/**
 * Modella l'entita CasaEditrice utilizzando l'oggetto CasaEditriceDTO 
 * meditnate i seguenti metodi implementati.
 * Utilizza due metodi get che cercano le CasaEditrice in base al nome 
 * e cercano tutte le CasaEditrice.
 * Utilizza un metodo delete per eliminare una CasaEditrice.
 * Utilizza un metodo post per inserire una nuova CasaEditrice.
 * Utilizza un metodo put per modificare alcuni campi della CasaEditrice.
 */


@Service
public class CaseEditriciServiceImpl implements CasaEditriceService {

	@Autowired
	private CasaEditriceDAO casaEditriceDAO;
	@Autowired
	private utilCasaEditrice utilCasaEditrice;
	

	/**
	 * @param {CasaEditriceDTO} CasaEditriceDTO da inserire
	 * @return {CasaEditriceDTO} CasaEditriceDTO inserita
	 */
	public CasaEditriceDTO insertCasaEditrice (CasaEditriceDTO casaEditriceDTO) {
		CasaEditrice casaEditrice = new CasaEditrice();
		casaEditrice.setNome(casaEditriceDTO.getNome());
		casaEditrice.setLibri(utilCasaEditrice.getLibriFromCasaEditriceDTO(casaEditriceDTO));
		casaEditriceDAO.save(casaEditrice);
		CasaEditriceDTO casaEditriceDTO2 = new CasaEditriceDTO();
		casaEditriceDTO2.setNome(casaEditrice.getNome());
		return casaEditriceDTO2;
	}
	
	/** 
	 * @return {List<CasaEditiceDTO>} 
	 */
	public List<CasaEditriceDTO> getAllCasaEditrice(){
		List<CasaEditrice> caseEditrici = casaEditriceDAO.findAll();
		return utilCasaEditrice.getAllCasaEditrice(caseEditrici);
	}
	
	/**
	 * @param {int} idCasaEditrice da modificare
	 * @param {CasaEditriceDTO} CasaEditriceDTO con i nuovi parametri 
	 * @return {CasaEditriceDTO} CasaditriceDTO con parametri modificati
	 */
	public CasaEditriceDTO updateCasaEditrice(CasaEditriceDTO casaEditriceDTO, int id) {
		Optional<CasaEditrice> foundCasaEditriceOptional = casaEditriceDAO.findById(id);
		CasaEditriceDTO casaEditriceDTO2 = new CasaEditriceDTO();
		if(foundCasaEditriceOptional.isPresent()) {
			CasaEditrice casaEditrice = foundCasaEditriceOptional.get();
			casaEditrice.setNome(casaEditriceDTO.getNome());
			casaEditriceDAO.save(casaEditrice);
			return casaEditriceDTO2;  
		}
		return null;
	}
	
	/**
	 * @param {String} nome della CasaEditrice da cercare
	 * @return {List<CasaEditriceDTO>}
	 */
	public List<CasaEditriceDTO> findCasaEditriceByNome(String nome) {
		Optional<List<CasaEditrice>> casaEditriceOptional = casaEditriceDAO.findByNome(nome);
		if(casaEditriceOptional.isPresent()) {
			List<CasaEditrice> casaEditrice = casaEditriceOptional.get();
			return utilCasaEditrice.getAllCasaEditrice(casaEditrice);
		}
		return null;
	}
	
	/**
	 * @param {int} idCasaEditrice da eliminare  
	 * @return void
	 */
	public void deleteCasaEitice (int id) {
			CasaEditrice casaEditrice = casaEditriceDAO.findById(id).get();
			casaEditriceDAO.delete(casaEditrice);
	}

}
