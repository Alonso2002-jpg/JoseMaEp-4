package pe.isil.EPermanente4.controllers.users;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pe.isil.EPermanente4.model.users.User;
import pe.isil.EPermanente4.services.facturas.FacturaService;
import pe.isil.EPermanente4.services.users.UserService;

@Controller
@RequestMapping("/users")
@PreAuthorize("hasRole('USER')")
public class UserController {
    private final UserService userService;
    private final FacturaService facturaService;

    @Autowired
    public UserController(UserService userService, FacturaService facturaService) {
        this.userService = userService;
        this.facturaService = facturaService;
    }

    @GetMapping("/eventos")
    public String index(Model model,
                        Authentication authentication,
                        @PageableDefault(size = 5, sort = "nombre") Pageable pageable) {
        model.addAttribute("miseventos", userService.findEventosById(((User) authentication.getPrincipal()).getId()));
        return "mis-eventos";
    }
}
