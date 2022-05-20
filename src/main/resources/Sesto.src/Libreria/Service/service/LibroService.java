package com.example.Libreria.Service.service;

import java.sql.Date;
import java.util.List;
import com.example.Libreria.DTO.LibroDTO;


/**
 * Modella l'oggetto LibroDTO attraverso i seguenti metodi.
 */
public interface LibroService {

	public LibroDTO insertLibro (LibroDTO libroDTO);
	public List<LibroDTO> getAllLibro();
	public LibroDTO updateLibro(LibroDTO libro, int id);
	public LibroDTO findLibroByTitolo(String titolo);
	public LibroDTO findLibroById(int id);
	public void deleteLibro (int id);
	public List<LibroDTO> getLibroAfterSpecificDate(Date dataUscita);
}
