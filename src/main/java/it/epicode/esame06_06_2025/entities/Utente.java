package it.epicode.esame06_06_2025.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
public class Utente {

    @Id
    @GeneratedValue
    private int id;


    @Column(unique = true)
    private String userName;

    private String nome;

    private String cognome;

    @Column(unique = true)
    private String email;

    @OneToMany(mappedBy = "utente")
    private List<Prenotazione> prenotazioni;
}
