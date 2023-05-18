package codementor.mentoriasapi.service.impl;

import codementor.mentoriasapi.model.Ocupacion;
import codementor.mentoriasapi.repository.IGenericRepository;
import codementor.mentoriasapi.repository.IOcupacionRepository;
import codementor.mentoriasapi.exception.DataAlreadyExistsException;
import codementor.mentoriasapi.exception.ModelNotFoundException;
import codementor.mentoriasapi.model.Ocupacion;
import codementor.mentoriasapi.service.IOcupacionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor

public class OcupacionServiceImpl extends CRUDImpl<Ocupacion, Integer> implements IOcupacionService {

    private final IOcupacionRepository repo;


    @Override
    protected IGenericRepository<Ocupacion, Integer> getRepo() {
        return repo;
    }

    @Override
    public Ocupacion save(Ocupacion ocupacion) throws Exception {
        String name = ocupacion.getName();
        String description = ocupacion.getDescription();

        if (isOcupacionDuplicate(name, description)) {
            throw new DataAlreadyExistsException("La Ocupacion ya existe con el nombre y/o descripcion que has ingresado.");
        }

        return super.save(ocupacion);
    }

    @Override
    public Ocupacion update(Ocupacion Ocupacion, Integer id) throws Exception {

        String name = Ocupacion.getName();
        String description = Ocupacion.getDescription();

        getRepo().findById(id).orElseThrow( () -> new ModelNotFoundException("La ocupacion con id " + id + " no existe."));

        if (isOcupacionDuplicateUpdate(name, description)) {
            throw new DataAlreadyExistsException("La ocupacion ya existe con el nombre y/o descripción que has ingresado.");
        }

        return super.update(Ocupacion,id);
    }


    public boolean isOcupacionDuplicate(String name, String description) {
        return repo.existsByNameOrDescription(name, description);
    }

    public boolean isOcupacionDuplicateUpdate(String name, String description) {
        return repo.existsByNameAndDescription(name, description);
    }


}
