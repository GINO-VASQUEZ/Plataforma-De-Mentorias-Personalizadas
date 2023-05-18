package codementor.mentoriasapi.repository;

import codementor.mentoriasapi.model.Ocupacion;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface IOcupacionRepository extends IGenericRepository<Ocupacion, Integer> {

    boolean existsByNameOrDescription(String name, String description);
    boolean existsByNameAndDescription(String name, String description);
    boolean existsByIdOcupacion(Integer idOcupacion);

    @Query("FROM Ocupacion c WHERE c.name = ?1 AND c.description LIKE %?2%")
    List<Ocupacion> getByNameAndDescription(String name, String description);
}
