package pe.isil.EPermanente4.controllers.facturas;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pe.isil.EPermanente4.model.factura.Factura;
import pe.isil.EPermanente4.model.factura.ModeloFactura;
import pe.isil.EPermanente4.model.users.User;
import pe.isil.EPermanente4.services.facturas.FacturaService;

@Controller
@RequestMapping("/facturas")
@PreAuthorize("hasRole('USER')")
public class FacturaController {

    private final FacturaService facturaService;

    @Autowired
    public FacturaController(FacturaService facturaService) {
        this.facturaService = facturaService;
    }

    @GetMapping("/{id}")
    public String index(Model model, @PathVariable Long id) {
        model.addAttribute("factura", new ModeloFactura());
        model.addAttribute("idEvento", id);
        return "factura";
    }

    @PostMapping
    public String save(ModeloFactura modeloFactura, Authentication authentication) {
        modeloFactura.setIdUsuario(((User) authentication.getPrincipal()).getId());
        Factura factura = facturaService.validarTotal(modeloFactura);
        facturaService.save(factura);
        return "redirect:/eventos";
    }
}
