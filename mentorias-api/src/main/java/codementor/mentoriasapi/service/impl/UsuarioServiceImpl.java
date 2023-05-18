package codementor.mentoriasapi.service.impl;

import codementor.mentoriasapi.exception.DataAlreadyExistsException;
import codementor.mentoriasapi.exception.ModelNotFoundException;
import codementor.mentoriasapi.model.Ocupacion;
import codementor.mentoriasapi.model.Usuario;
import codementor.mentoriasapi.repository.IGenericRepository;
import codementor.mentoriasapi.repository.IOcupacionRepository;
import codementor.mentoriasapi.repository.IUsuarioRepository;
import codementor.mentoriasapi.service.IUsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor

public class UsuarioServiceImpl extends CRUDImpl<Usuario, Integer> implements IUsuarioService {

    //@Autowired
    private final IUsuarioRepository repo;
    private final IOcupacionRepository repositoryOcupacion;


    @Override
    protected IGenericRepository<Usuario, Integer> getRepo() {
        return repo;
    }

    @Override
    public Usuario save(Usuario usuario) throws Exception {
        Integer idOcupacion = usuario.getOcupacion().getIdOcupacion();

        // Verificar si existe una Ocupacion con el idOcupacion proporcionado
        boolean ocupacionExists = this.existsOcupacionById(idOcupacion);

        if (ocupacionExists) {
            // La categoría existe, puedes continuar guardando el libro
            if (isUsuarioDuplicate(usuario.getDni()) ) {
                throw new DataAlreadyExistsException("El libro con Dni " + usuario.getDni() + " ya existe.");
            }
            return super.save(usuario);
        } else {
            // La categoría no existe, lanzar una excepción o realizar alguna acción adicional
            throw new ModelNotFoundException("La Usuario con id " + idOcupacion + " no existe.");
        }
    }

    @Override
    public Usuario update(Usuario usuario, Integer idUsuario) throws Exception {
        Integer idOcupacion = usuario.getOcupacion().getIdOcupacion();

        // Verificar si existe una Ocupacion con el idOcupacion proporcionado
        boolean OcupacionExists = this.existsOcupacionById(idOcupacion);

        // Verificar si existe un Usuario con el idBook proporcionado
        getRepo().findById(idUsuario).orElseThrow( () -> new ModelNotFoundException("El Usuario con id " + idUsuario + " no existe."));

        if (OcupacionExists) {
            // LaOcupacion existe, puedes continuar guardando el Usuario
            return super.update(usuario, idUsuario);
        } else {
            // La Ocupacion no existe, lanzar una excepción o realizar alguna acción adicional
            throw new ModelNotFoundException("La categoría con id " + idOcupacion + " no existe.");
        }
    }

    @Override
    public List<Usuario> findUsuariosBydni(double dni) {
        return repo.findBydni(dni);
    }

    public boolean isUsuarioDuplicate(double dni) {
        return repo.existsByDni(dni);
    }

    public boolean existsOcupacionById(Integer idOcupacion) {
        return repositoryOcupacion.existsByIdOcupacion(idOcupacion);
    }
}
