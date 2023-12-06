package pe.isil.EPermanente4.services.facturas;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import pe.isil.EPermanente4.model.eventos.Evento;
import pe.isil.EPermanente4.model.factura.Factura;
import pe.isil.EPermanente4.model.factura.ModeloFactura;

import java.util.List;

public interface FacturaService {
    List<Factura> findAll();
    Factura findById(Long id);
    Factura save(Factura factura);
    Factura update(Long id,Factura factura);
    void deleteById(Long id);
    Factura validarTotal(ModeloFactura modeloFactura);
}
