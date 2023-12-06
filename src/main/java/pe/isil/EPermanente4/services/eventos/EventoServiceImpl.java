package pe.isil.EPermanente4.services.eventos;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import pe.isil.EPermanente4.exceptions.EventoNotFound;
import pe.isil.EPermanente4.model.eventos.Evento;
import pe.isil.EPermanente4.repository.eventos.EventoRepository;

import java.util.List;

@Service
public class EventoServiceImpl implements EventoService {
    private final EventoRepository eventoRepository;

    @Autowired
    public EventoServiceImpl(EventoRepository eventoRepository) {
        this.eventoRepository = eventoRepository;
    }

    @Override
    public Page<Evento> findAllPage(Pageable pageable) {
        return eventoRepository.findAll(pageable);
    }

    @Override
    public List<Evento> findAll() {
        return eventoRepository.findAll();
    }

    @Override
    public Evento findById(Long id) {
        return eventoRepository.findById(id).orElseThrow(() -> new EventoNotFound("El evento no existe"));
    }

    @Override
    public Evento save(Evento evento) {
        return eventoRepository.save(evento);
    }

    @Override
    public Evento update(Long id,Evento evento) {
        findById(id);
        evento.setId(id);
        return eventoRepository.save(evento);
    }

    @Override
    public void deleteById(Long id) {
        findById(id);
        eventoRepository.deleteById(id);
    }
}
