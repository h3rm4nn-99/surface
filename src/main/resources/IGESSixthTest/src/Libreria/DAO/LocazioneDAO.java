package Libreria.DAO;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import Libreria.entity.Locazione;



@Repository
public interface LocazioneDAO extends JpaRepository<Locazione, Integer> {

}
