package it.epicode.esame06_06_2025.repository;


import it.epicode.esame06_06_2025.entities.Utente;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.List;
import java.util.Optional;

public interface UtenteRepository extends JpaRepository<Utente,Integer> {
    Optional<Utente> findByUserName(String userName);
    Optional<Utente> findByEmail(String userName);
    Optional<Utente> findByUserNameAndEmail(String userName, String mail);
    List<Utente> findByNome(String nome);
    List<Utente> findByCognome(String cognome);

}
