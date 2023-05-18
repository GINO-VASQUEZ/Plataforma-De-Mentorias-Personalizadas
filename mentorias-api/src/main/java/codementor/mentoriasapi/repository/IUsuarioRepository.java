package codementor.mentoriasapi.repository;

import codementor.mentoriasapi.model.Usuario;

import java.util.List;

public interface IUsuarioRepository extends IGenericRepository<Usuario, Integer> {
    boolean existsByDni(double dni);

    //método de consulta para obtener los datos de un libro por isbn
    List<Usuario> findBydni(double dni);
}
