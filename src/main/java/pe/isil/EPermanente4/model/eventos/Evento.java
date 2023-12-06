package pe.isil.EPermanente4.model.eventos;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "evento")
public class Evento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank(message = "El nombre no puede estar vacío")
    private String nombre;
    @NotBlank(message = "La fecha no puede estar vacía")
    private String fecha;
    @NotBlank(message = "La hora no puede estar vacía")
    private String hora;
    @NotNull(message = "La capacidad no puede ser nula")
    private Integer capacidad;
    @NotNull(message = "La duracion no puede ser nula")
    private Double duracion;
    @Min(value = 0, message = "El precio no puede ser negativo")
    @NotNull(message = "El precio no puede ser nulo")
    private Double precio;

    private LocalDateTime fechaCreacion;
    private LocalDateTime fechaActualizacion;

    @PrePersist
    void prePersist() {
        this.fechaCreacion = LocalDateTime.now();
        this.fechaActualizacion = LocalDateTime.now();
    }

    @PreUpdate
    void preUpdate() {
        this.fechaActualizacion = LocalDateTime.now();
    }
}
