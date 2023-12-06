package pe.isil.EPermanente4.services.users;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import pe.isil.EPermanente4.model.eventos.Evento;
import pe.isil.EPermanente4.model.users.User;

import java.util.List;

public interface UserService {
    List<User> findAll();
    List<Evento> findEventosById(Long id);
    User findById(Long id);
    User findByEmail(String email);
    User findByEmailIgnoreCase(String email);
    User save(User user);
    User update(Long id,User user);
    void deleteById(Long id);
}
