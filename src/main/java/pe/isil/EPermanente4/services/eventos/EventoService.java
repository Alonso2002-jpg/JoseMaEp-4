package pe.isil.EPermanente4.services.eventos;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import pe.isil.EPermanente4.model.eventos.Evento;

import java.util.List;

public interface EventoService {
    Page<Evento> findAllPage(Pageable pageable);
    List<Evento> findAll();
    Evento findById(Long id);
    Evento save(Evento evento);
    Evento update(Long id,Evento evento);
    void deleteById(Long id);
}
