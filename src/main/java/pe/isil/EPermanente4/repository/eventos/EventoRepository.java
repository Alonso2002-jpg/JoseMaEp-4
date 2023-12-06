package pe.isil.EPermanente4.repository.eventos;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pe.isil.EPermanente4.model.eventos.Evento;

import java.util.List;
@Repository
public interface EventoRepository extends JpaRepository<Evento, Long> {
}
