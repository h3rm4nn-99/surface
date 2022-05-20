package Libreria.Service.service;

import java.util.List;
import com.example.Libreria.DTO.LocazioneDTO;

/**
 * Modella l'oggetto LocazioneDTO attraverso i seguenti metodi.
 */
public interface LocazioneService {

	public List<LocazioneDTO> getAllLocazione();
	public LocazioneDTO insertLocazione (LocazioneDTO locazione);
	public LocazioneDTO updateLocazione(LocazioneDTO locazione, int id);
	public void deleteLocazione (int id);
}
