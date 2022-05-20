package Libreria.DAO;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import Libreria.entity.Autore;



@Repository
public interface AutoreDAO  extends JpaRepository<Autore, Integer>{
	
	public Optional<List<Autore>> findByNome(String nome);
	
}
