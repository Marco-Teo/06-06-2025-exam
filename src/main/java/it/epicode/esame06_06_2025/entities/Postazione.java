package it.epicode.esame06_06_2025.entities;

import it.epicode.esame06_06_2025.enumeration.TipoPostazione;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Postazione {

    @Id
    @GeneratedValue
    private int id;

    private String descrizione;

    @Enumerated(EnumType.STRING)
    private TipoPostazione tipoPostazione;

    private int numeroMassimo;

    @ManyToOne
    @JoinColumn(name = "edificio_id")
    private Edificio edificio;
}
