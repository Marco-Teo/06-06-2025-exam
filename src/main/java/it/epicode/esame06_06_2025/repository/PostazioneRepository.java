package it.epicode.esame06_06_2025.repository;

import it.epicode.esame06_06_2025.entities.Postazione;
import it.epicode.esame06_06_2025.enumeration.TipoPostazione;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostazioneRepository extends JpaRepository<Postazione, Integer> {
    List<Postazione> findByTipoPostazione(TipoPostazione tipoPostazione);
    List<Postazione> findByNumeroMassimo(int numeroMassimo);
    List<Postazione> findByEdificio_Citta(String citta);
}
