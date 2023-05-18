package codementor.mentoriasapi.controller;

import codementor.mentoriasapi.dto.UsuarioDTO;
import codementor.mentoriasapi.model.Usuario;
import codementor.mentoriasapi.service.IUsuarioService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/usuarios")
@RequiredArgsConstructor
public class UsuarioController {

    private final IUsuarioService service;

    @Qualifier("usuarioMapper")
    private final ModelMapper mapper;

    @PostMapping
    public ResponseEntity<UsuarioDTO> create(@Valid @RequestBody UsuarioDTO dto) throws Exception {
        Usuario obj = service.save(convertToEntity(dto));
        return new ResponseEntity<>(convertToDto(obj), HttpStatus.CREATED);
    }


    @PutMapping("/{id}")
    public ResponseEntity<UsuarioDTO> update(@Valid @PathVariable("id") Integer idUsuario, @RequestBody UsuarioDTO dto) throws Exception {
        dto.setIdUsuario(idUsuario);
        Usuario obj = service.update(convertToEntity(dto), idUsuario);
        return new ResponseEntity<>(convertToDto(obj), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<UsuarioDTO>>  readAll() throws Exception{
        List<UsuarioDTO> list = service.readAll().stream().map(this::convertToDto).collect(Collectors.toList());
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UsuarioDTO> readById(@PathVariable("id") Integer id) throws Exception{
        Usuario obj = service.readById(id);
        return new ResponseEntity<>(convertToDto(obj), HttpStatus.OK);
    }

    @GetMapping("/dni/{dni}")
    public ResponseEntity<List<UsuarioDTO>> findByIsbn(@PathVariable("dni") double dni) {
        List<Usuario> usuarios = service.findUsuariosBydni(dni);
        List<UsuarioDTO> dtos = usuarios.stream().map(this::convertToDto).collect(Collectors.toList());
        return new ResponseEntity<>(dtos, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Integer id) throws Exception {
        service.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    private UsuarioDTO convertToDto(Usuario obj){
        return mapper.map(obj, UsuarioDTO.class);
    }

    private Usuario convertToEntity(UsuarioDTO dto){
        return mapper.map(dto, Usuario.class);
    }
}
