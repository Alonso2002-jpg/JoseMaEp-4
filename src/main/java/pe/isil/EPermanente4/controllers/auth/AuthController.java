package pe.isil.EPermanente4.controllers.auth;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pe.isil.EPermanente4.exceptions.UserNotFound;
import pe.isil.EPermanente4.model.users.LoginUser;
import pe.isil.EPermanente4.model.users.RegisterUser;
import pe.isil.EPermanente4.model.users.Rol;
import pe.isil.EPermanente4.model.users.User;
import pe.isil.EPermanente4.repository.users.UserRepository;

@Controller
public class AuthController {
    private final UserRepository userRepository;
    private AuthenticationManager authenticationManager;

    public AuthController(UserRepository userRepository, AuthenticationManager authenticationManager) {
        this.userRepository = userRepository;
    }
    @GetMapping("/")
    public String index(Model model) {
        return "index";
    }
    @GetMapping("/register")
    public String register(Model model) {
        model.addAttribute("userregister", new RegisterUser());
        return "register";
    }

    @PostMapping("/register")
    public String registerPost(Model model, @Valid @ModelAttribute("userregister")RegisterUser registerUser, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("userregister", registerUser);
            return "register";
        }
        if (!registerUser.getPassword().equals(registerUser.getPasswordComprobacion())) {
            bindingResult.reject("password", "Las contraseñas no coinciden");
        }

        if (userRepository.findByEmailIgnoreCase(registerUser.getEmail()).isPresent()) {
            bindingResult.reject("email", "El email ya se encuentra registrado");
        }

        if (bindingResult.hasErrors()) {
            model.addAttribute("userregister", registerUser);
            return "register";
        }
        User user = new User();
        user.setNombres(registerUser.getNombres());
        user.setApellidos(registerUser.getApellidos());
        user.setEmail(registerUser.getEmail());
        user.setPassword(registerUser.getPassword());
        user.setRol(Rol.USER);
        userRepository.save(user);
        return "redirect:/login";
    }
    @GetMapping("/login")
    public String login(Model model) {
        model.addAttribute("userlogin", new LoginUser());
        return "login";
    }

    @PostMapping("/login")
    public String loginPost(Model model, @Valid @ModelAttribute("userlogin")LoginUser loginUser, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("userlogin", loginUser);
            return "login";
        }
        if (userRepository.findByEmailIgnoreCase(loginUser.getUsername()).isPresent()){
            User user = userRepository.findByEmailIgnoreCase(loginUser.getUsername()).get();
            if (!user.getPassword().equals(loginUser.getPassword())) {
                bindingResult.reject("password", "Contraseña incorrecta");
            }
        }
        if (bindingResult.hasErrors()) {
            model.addAttribute("userlogin", loginUser);
            return "login";
        }
        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(loginUser.getUsername(), loginUser.getPassword());

        Authentication authentication = authenticationManager.authenticate(authToken);

        SecurityContextHolder.getContext().setAuthentication(authentication);

        return "index";
    }
}
