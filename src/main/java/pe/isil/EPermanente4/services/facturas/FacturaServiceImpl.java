package pe.isil.EPermanente4.services.facturas;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import pe.isil.EPermanente4.exceptions.FacturaNotFound;
import pe.isil.EPermanente4.model.eventos.Evento;
import pe.isil.EPermanente4.model.factura.Factura;
import pe.isil.EPermanente4.model.factura.ModeloFactura;
import pe.isil.EPermanente4.repository.eventos.EventoRepository;
import pe.isil.EPermanente4.repository.facturas.FacturaRepository;
import pe.isil.EPermanente4.services.eventos.EventoService;

import java.util.List;
@Service
public class FacturaServiceImpl implements FacturaService{
    private final FacturaRepository facturaRepository;
    private final EventoService eventoService;

    @Autowired
    public FacturaServiceImpl(FacturaRepository facturaRepository, EventoService eventoService) {
        this.facturaRepository = facturaRepository;
        this.eventoService = eventoService;
    }

    @Override
    public List<Factura> findAll() {
        return facturaRepository.findAll();
    }

    @Override
    public Factura findById(Long id) {
        return facturaRepository.findById(id).orElseThrow(()-> new FacturaNotFound("Factura no encontrada"));
    }

    @Override
    public Factura save(Factura factura) {
        return facturaRepository.save(factura);
    }

    @Override
    public Factura update(Long id, Factura factura) {
        findById(id);
        factura.setId(id);
        return facturaRepository.save(factura);
    }

    @Override
    public void deleteById(Long id) {
        findById(id);
        facturaRepository.deleteById(id);
    }
    @Override
    public Factura validarTotal(ModeloFactura modeloFactura){
        Factura factura = new Factura();
        Evento evento = eventoService.findById(modeloFactura.getIdEvento());
        Double total = 0.0;
        factura.setId(modeloFactura.getIdUsuario());
        factura.setEvento(evento);
        if (modeloFactura.getTotalDeportista() >= 2){
            total += (evento.getPrecio() * modeloFactura.getTotalNinos()) * 0.2;
        }else {
            total += (evento.getPrecio() * modeloFactura.getTotalNinos());
        }
        if (modeloFactura.getTotalAdultos() >= 3){
            total += (evento.getPrecio() * modeloFactura.getTotalAdultos()) * 0.1;
        }else {
            total += (evento.getPrecio() * modeloFactura.getTotalAdultos());
        }
        if (modeloFactura.getTotalAdultoMayor() >= 1){
            total += (evento.getPrecio() * modeloFactura.getTotalAdultoMayor()) * 0.4;
        }
        if (modeloFactura.getTotalDeportista() >= 1){
            total += 0;
        }
        factura.setTotal(total);
        return factura;
    }
}
