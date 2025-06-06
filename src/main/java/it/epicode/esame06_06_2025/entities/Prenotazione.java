package it.epicode.esame06_06_2025.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
public class Prenotazione {

    @Id
    @GeneratedValue
    private int id;

    @ManyToOne
    @JoinColumn(name = "utente_id")
    private  Utente utente;

    @ManyToOne
    @JoinColumn(name = "postazione_id")
    private Postazione postazione;

    private LocalDate dataPrenotazione;
}
