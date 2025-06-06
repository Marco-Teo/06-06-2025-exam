package it.epicode.esame06_06_2025.repository;

import it.epicode.esame06_06_2025.entities.Postazione;
import it.epicode.esame06_06_2025.entities.Prenotazione;
import it.epicode.esame06_06_2025.entities.Utente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface PrenotazioniRepository extends JpaRepository<Prenotazione, Integer> {
    List<Prenotazione> findByDataPrenotazione(LocalDate dataPrenotazione);
    List<Prenotazione> findByUtente(Utente utente);
    List<Prenotazione> findByPostazione(Postazione postazione);
    boolean existsByPostazioneAndDataPrenotazione(Postazione postazione, LocalDate dataPrenotazione);
    boolean existsByUtenteAndPostazioneAndDataPrenotazione(Utente utente, Postazione postazione, LocalDate dataPrenotazione);
}
