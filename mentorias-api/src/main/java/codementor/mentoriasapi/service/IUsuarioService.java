package codementor.mentoriasapi.service;

import codementor.mentoriasapi.model.Usuario;

import java.util.List;

public interface IUsuarioService extends ICRUD<Usuario, Integer>{

    boolean isUsuarioDuplicate(double dni);
    boolean existsOcupacionById(Integer idOcupacion);

    List<Usuario> findUsuariosBydni(double dni);

}