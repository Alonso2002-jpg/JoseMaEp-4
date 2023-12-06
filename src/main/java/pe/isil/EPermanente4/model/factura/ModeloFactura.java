package pe.isil.EPermanente4.model.factura;

import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import pe.isil.EPermanente4.model.eventos.Evento;
@Data
public class ModeloFactura {
    private Long idUsuario;
    @NotNull(message = "El evento no puede ser nulo")
    private Long idEvento;
    private Integer totalNinos = 0;
    private Integer totalAdultos =0;
    private Integer totalAdultoMayor = 0;
    private Integer totalDeportista = 0;
}
