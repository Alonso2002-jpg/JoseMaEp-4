package pe.isil.EPermanente4.model.factura;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import pe.isil.EPermanente4.model.eventos.Evento;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "facturas")
public class Factura {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull(message = "El usuario no puede ser nulo")
    private Long idUsuario;
    @ManyToOne
    @JoinColumn(name = "id_evento")
    private Evento evento;
    private Double total;
    private LocalDateTime fechaCreacion;

    @PrePersist
    public void prePersist() {
        fechaCreacion = LocalDateTime.now();
    }
}
