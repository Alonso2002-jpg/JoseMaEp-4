package pe.isil.EPermanente4.model.users;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class RegisterUser {
    @NotBlank(message = "El nombre no puede estar vacío")
    private String nombres;
    @NotBlank(message = "Los apellidos no pueden estar vacíos")
    private String apellidos;
    @Email(message = "El email no es válido")
    @NotBlank(message = "El email no puede estar vacío")
    private String email;
    @NotBlank(message = "La contraseña no puede estar vacía")
    private String password;
    @NotBlank(message = "La comprobacion no puede estar vacía")
    private String passwordComprobacion;
}
