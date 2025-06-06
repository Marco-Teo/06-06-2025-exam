package it.epicode.esame06_06_2025.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;


@Entity
@Data
@NoArgsConstructor
@ToString(exclude = "postazioni")
public class Edificio {

    @Id
    @GeneratedValue
    private int id;

    private String indirizzo;

    private String citta;

    @OneToMany(mappedBy = "edificio")
    private List<Postazione> postazioni;
}
