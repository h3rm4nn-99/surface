package com.example.Libreria.DAO;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.Libreria.entity.CasaEditrice;


@Repository
public interface CasaEditriceDAO extends JpaRepository<CasaEditrice, Integer> {

	public Optional<List<CasaEditrice>> findByNome(String titolo);
}
