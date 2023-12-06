package pe.isil.EPermanente4.services.users;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import pe.isil.EPermanente4.exceptions.UserNotFound;
import pe.isil.EPermanente4.model.eventos.Evento;
import pe.isil.EPermanente4.model.factura.Factura;
import pe.isil.EPermanente4.model.users.User;
import pe.isil.EPermanente4.repository.facturas.FacturaRepository;
import pe.isil.EPermanente4.repository.users.UserRepository;

import java.util.List;
@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final FacturaRepository facturaRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, FacturaRepository facturaRepository) {
        this.userRepository = userRepository;
        this.facturaRepository = facturaRepository;
    }
    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public List<Evento> findEventosById(Long id) {
        return facturaRepository.findByIdUsuario(id).stream().map(Factura::getEvento).toList();
    }


    @Override
    public User findById(Long id) {
        return userRepository.findById(id).orElseThrow(()-> new UserNotFound("Usuario no encontrado"));
    }

    @Override
    public User findByEmail(String email) {
        return userRepository.findByEmailIgnoreCase(email).orElseThrow(()-> new UserNotFound("Usuario no encontrado"));
    }

    @Override
    public User findByEmailIgnoreCase(String email) {
        return null;
    }

    @Override
    public User save(User user) {
        return userRepository.save(user);
    }

    @Override
    public User update(Long id, User user) {
        findById(id);
        user.setId(id);
        return userRepository.save(user);
    }

    @Override
    public void deleteById(Long id) {
        findById(id);
        userRepository.deleteById(id);
    }
}
