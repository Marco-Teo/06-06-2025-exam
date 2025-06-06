package it.epicode.esame06_06_2025;

import it.epicode.esame06_06_2025.entities.Edificio;
import it.epicode.esame06_06_2025.entities.Postazione;
import it.epicode.esame06_06_2025.enumeration.TipoPostazione;
import it.epicode.esame06_06_2025.repository.EdificioRepository;
import it.epicode.esame06_06_2025.repository.PostazioneRepository;
import it.epicode.esame06_06_2025.repository.PrenotazioniRepository;
import it.epicode.esame06_06_2025.repository.UtenteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class Runner implements CommandLineRunner {

    @Autowired
    private EdificioRepository edificioRepository;

    @Autowired
    private PostazioneRepository postazioneRepository;

    @Autowired
    private PrenotazioniRepository prenotazioniRepository;

    @Autowired
    private UtenteRepository utenteRepository;

    @Override
    public void run(String... args) throws Exception {
        if (edificioRepository.count() == 0 && postazioneRepository.count() == 0) {
            Edificio edificio1 = new Edificio();
            edificio1.setCitta("Roma");
            edificio1.setIndirizzo("Via Roma 1");
            edificioRepository.save(edificio1);

            Edificio edificio2 = new Edificio();
            edificio2.setCitta("Milano");
            edificio2.setIndirizzo("Via Milano 1");
            edificioRepository.save(edificio2);

            Edificio edificio3 = new Edificio();
            edificio3.setCitta("Siena");
            edificio3.setIndirizzo("Via Siena 1");
            edificioRepository.save(edificio3);

            Postazione p1 = new Postazione();
            p1.setDescrizione("Open space in Via Roma con 20 postazioni");
            p1.setNumeroMassimo(20);
            p1.setTipoPostazione(TipoPostazione.OPENSPACE);
            p1.setEdificio(edificio1);
            postazioneRepository.save(p1);

            Postazione p2 = new Postazione();
            p2.setDescrizione("Sala riunioni a Roma con 10 posti");
            p2.setNumeroMassimo(10);
            p2.setTipoPostazione(TipoPostazione.SALA_RIUNIONI);
            p2.setEdificio(edificio1);
            postazioneRepository.save(p2);

            Postazione p3 = new Postazione();
            p3.setDescrizione("Postazione privata a Milano");
            p3.setNumeroMassimo(5);
            p3.setTipoPostazione(TipoPostazione.PRIVATO);
            p3.setEdificio(edificio2);
            postazioneRepository.save(p3);

            Postazione p6 = new Postazione();
            p6.setDescrizione("Sala riunioni a Milano");
            p6.setNumeroMassimo(20);
            p6.setTipoPostazione(TipoPostazione.SALA_RIUNIONI);
            p6.setEdificio(edificio2);
            postazioneRepository.save(p6);

            Postazione p4 = new Postazione();
            p4.setDescrizione("Postazione privata a Siena");
            p4.setNumeroMassimo(5);
            p4.setTipoPostazione(TipoPostazione.PRIVATO);
            p4.setEdificio(edificio3);
            postazioneRepository.save(p4);

            Postazione p5 = new Postazione();
            p5.setDescrizione("Compleanno Marco il 3/06/2025 a Siena");
            p5.setNumeroMassimo(200);
            p5.setTipoPostazione(TipoPostazione.OPENSPACE);
            p5.setEdificio(edificio3);
            postazioneRepository.save(p5);

            System.out.println("✅ Dati iniziali salvati nel database!");
        } else {
            System.out.println("ℹ️ Dati già presenti nel database, nessun salvataggio eseguito.");
        }
    }
}
