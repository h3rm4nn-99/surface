package Libreria.Service.service;

import java.util.List;
import com.example.Libreria.DTO.CasaEditriceDTO;


/**
 * Modella l'oggetto CasaEditriceDTO attraverso i seguenti metodi.
 */
public interface CasaEditriceService {

	public CasaEditriceDTO insertCasaEditrice (CasaEditriceDTO casaEditrice);
	public List<CasaEditriceDTO> getAllCasaEditrice();
	public CasaEditriceDTO updateCasaEditrice(CasaEditriceDTO casaEditrice, int id);
	public List<CasaEditriceDTO> findCasaEditriceByNome(String nome);
	public void deleteCasaEitice (int id);
}
