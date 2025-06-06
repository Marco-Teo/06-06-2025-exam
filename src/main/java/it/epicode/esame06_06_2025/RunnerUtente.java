package it.epicode.esame06_06_2025;

import it.epicode.esame06_06_2025.entities.Postazione;
import it.epicode.esame06_06_2025.entities.Prenotazione;
import it.epicode.esame06_06_2025.entities.Utente;
import it.epicode.esame06_06_2025.repository.EdificioRepository;
import it.epicode.esame06_06_2025.repository.PostazioneRepository;
import it.epicode.esame06_06_2025.repository.PrenotazioniRepository;
import it.epicode.esame06_06_2025.repository.UtenteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

@Component
public class RunnerUtente implements org.springframework.boot.CommandLineRunner {

    @Autowired
    private UtenteRepository utenteRepository;
    @Autowired
    private PostazioneRepository postazioneRepository;
    @Autowired
    private EdificioRepository edificioRepository;
    @Autowired
    private PrenotazioniRepository prenotazioniRepository;

    private Scanner scanner = new Scanner(System.in);
    private Utente currentUser = null;

    @Override
    public void run(String... args) throws Exception {
        System.out.println("=== Benvenuto nel sistema di prenotazioni ===");

        while (true) {
            if (currentUser == null) {
                showLoginMenu();
            } else {
                showMainMenu();
            }
        }
    }

    private void showLoginMenu() {
        System.out.println("\nScegli un'opzione:");
        System.out.println("1. Registrati");
        System.out.println("2. Login");
        System.out.println("0. Esci");

        String scelta = scanner.nextLine();

        switch (scelta) {
            case "1":
                registraUtente();
                break;
            case "2":
                loginUtente();
                break;
            case "0":
                System.out.println("Arrivederci!");
                System.exit(0);
                break;
            default:
                System.out.println("Scelta non valida, riprova.");
        }
    }

    private void registraUtente() {
        try {
            System.out.print("Username: ");
            String username = scanner.nextLine().trim();

            if (username.isEmpty()) {
                System.out.println("Username non può essere vuoto.");
                return;
            }

            if (utenteRepository.findByUserName(username).isPresent()) {
                System.out.println("❌ Username già esistente.");
                return;
            }

            System.out.print("Nome: ");
            String nome = scanner.nextLine().trim();
            System.out.print("Cognome: ");
            String cognome = scanner.nextLine().trim();

            System.out.print("Email: ");
            String email = scanner.nextLine().trim();

            if (utenteRepository.findByEmail(email).isPresent()) {
                System.out.println("❌ Email già in uso.");
                return;
            }

            Utente nuovoUtente = new Utente();
            nuovoUtente.setUserName(username);
            nuovoUtente.setNome(nome);
            nuovoUtente.setCognome(cognome);
            nuovoUtente.setEmail(email);

            utenteRepository.save(nuovoUtente);
            currentUser = nuovoUtente;
            System.out.println("✅ Registrazione completata. Sei loggato come " + username);
        } catch (Exception e) {
            System.out.println("Errore durante la registrazione: " + e.getMessage());
        }
    }

    private void loginUtente() {
        try {
            System.out.print("Username: ");
            String username = scanner.nextLine().trim();
            System.out.print("Email: ");
            String email = scanner.nextLine().trim();

            Optional<Utente> user = utenteRepository.findByUserNameAndEmail(username, email);
            if (user.isPresent()) {
                currentUser = user.get();
                System.out.println("✅ Login effettuato con successo. Benvenuto " + currentUser.getNome());
            } else {
                System.out.println("❌ Credenziali non valide.");
            }
        } catch (Exception e) {
            System.out.println("Errore durante il login: " + e.getMessage());
        }
    }

    private void showMainMenu() {
        System.out.println("\nCosa vuoi fare?");
        System.out.println("1. Visualizza tutte le postazioni");
        System.out.println("2. Visualizza tutti gli edifici");
        System.out.println("3. Cerca location per citta");
        System.out.println("4. Prenota una postazione per oggi");
        System.out.println("5. Visualizza le tue prenotazioni");
        System.out.println("6. Logout");
        System.out.println("0. Esci");

        String scelta = scanner.nextLine();

        switch (scelta) {
            case "1":
                mostraPostazioni();
                break;
            case "2":
                mostraEdifici();
                break;
            case "3":
                System.out.println("Inserisci la città:");
                String citta = scanner.nextLine();
                mostraPostazionePerCitta(citta);
                break;
            case "4":
                prenotaPostazione();
                break;
            case "5":
                mostraPrenotazioniUtente();
                break;
            case "6":
                logout();
                break;
            case "0":
                System.out.println("Arrivederci!");
                System.exit(0);
                break;
            default:
                System.out.println("Scelta non valida, riprova.");
        }
    }

    private void mostraPostazioni() {
        List<Postazione> postazioni = postazioneRepository.findAll();
        if (postazioni.isEmpty()) {
            System.out.println("Nessuna postazione trovata.");
        } else {
            postazioni.forEach(System.out::println);
        }
    }

    private void mostraEdifici() {
        edificioRepository.findAll().forEach(System.out::println);
    }

    private void mostraPostazionePerCitta(String citta) {
        List<Postazione> postazioni = postazioneRepository.findByEdificio_Citta(citta);
        if (postazioni.isEmpty()) {
            System.out.println("Nessuna postazione trovata per la città: " + citta);
        } else {
            postazioni.forEach(System.out::println);
        }
    }

    private void prenotaPostazione() {
        try {
            System.out.print("Inserisci ID della postazione da prenotare: ");
            Integer idPostazione = Integer.parseInt(scanner.nextLine());
            Optional<Postazione> postazioneOpt = postazioneRepository.findById(idPostazione);
            if (postazioneOpt.isEmpty()) {
                System.out.println("❌ Postazione non trovata.");
                return;
            }

            LocalDate oggi = LocalDate.now();

            boolean hasBookingToday = prenotazioniRepository.existsByUtenteAndDataPrenotazione(currentUser, oggi);
            if (hasBookingToday) {
                System.out.println("❌ Hai già una prenotazione per oggi, non puoi prenotare più di una postazione nello stesso giorno.");
                return;
            }

            Prenotazione prenotazione = new Prenotazione();
            prenotazione.setUtente(currentUser);
            prenotazione.setPostazione(postazioneOpt.get());
            prenotazione.setDataPrenotazione(oggi);

            prenotazioniRepository.save(prenotazione);
            System.out.println("✅ Prenotazione effettuata con successo!");
        } catch (NumberFormatException e) {
            System.out.println("Inserisci un ID valido.");
        } catch (Exception e) {
            System.out.println("Errore durante la prenotazione: " + e.getMessage());
        }
    }

    private void mostraPrenotazioniUtente() {
        List<Prenotazione> prenotazioni = prenotazioniRepository.findByUtente(currentUser);
        if (prenotazioni.isEmpty()) {
            System.out.println("Non hai prenotazioni attive.");
        } else {
            prenotazioni.forEach(System.out::println);
        }
    }

    private void logout() {
        currentUser = null;
        System.out.println("Sei stato disconnesso.");
    }
}
