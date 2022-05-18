package com.example.Libreria.Service.service;

import java.util.List;

import com.example.Libreria.DTO.AutoreDTO;


/**
 * Modella l'oggetto AutoreDTO attraverso i seguenti metodi.
 */
public interface AutoreService {
 	
		public AutoreDTO insertAutore(AutoreDTO autoreDTO);
		public List<AutoreDTO> getAll();
		public AutoreDTO findAutoreById(int id);
		public List<AutoreDTO> findAutoreByNome(String nome);
		public AutoreDTO updateAutore(AutoreDTO autoreDTO, int id);
		public void deleteAutore (int id);
		
}
