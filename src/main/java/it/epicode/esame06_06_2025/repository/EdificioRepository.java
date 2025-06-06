package it.epicode.esame06_06_2025.repository;

import it.epicode.esame06_06_2025.entities.Edificio;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EdificioRepository extends JpaRepository<Edificio, Integer> {
   List<Edificio> findByCitta(String citta);
   List<Edificio> findByIndirizzo(String via);
}
