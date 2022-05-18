package com.example.Libreria.DAO;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.Libreria.entity.Libro;




@Repository
public interface LibroDAO extends JpaRepository<Libro, Integer> {

	public Optional<Libro> findByTitolo(String titolo);
	
	public Optional<List<Libro>> findBydataUscitaGreaterThan(Date dataUscita);
}
