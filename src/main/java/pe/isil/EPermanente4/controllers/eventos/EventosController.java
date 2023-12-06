package pe.isil.EPermanente4.controllers.eventos;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pe.isil.EPermanente4.services.eventos.EventoService;

@Controller
@PreAuthorize("hasRole('USER')")
@RequestMapping("/eventos")
public class EventosController {
    private final EventoService eventoService;

    @Autowired
    public EventosController(EventoService eventoService) {
        this.eventoService = eventoService;
    }

    @GetMapping
    public String index(Model model) {
        model.addAttribute("eventos", eventoService.findAll());
        return "eventos";
    }
}
