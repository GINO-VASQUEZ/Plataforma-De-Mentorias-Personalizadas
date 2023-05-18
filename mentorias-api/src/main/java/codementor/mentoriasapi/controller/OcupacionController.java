package codementor.mentoriasapi.controller;

import codementor.mentoriasapi.dto.OcupacionDTO;
import codementor.mentoriasapi.model.Ocupacion;

import codementor.mentoriasapi.service.IOcupacionService;
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
@RequestMapping("/ocupaciones")
@RequiredArgsConstructor
public class OcupacionController {


    private final IOcupacionService service;

    @Qualifier("ocupacionMapper")
    private final ModelMapper mapper;


    @PostMapping
    public ResponseEntity<OcupacionDTO> create(@Valid @RequestBody OcupacionDTO dto) throws Exception {
        Ocupacion obj = service.save(convertToEntity(dto));
        return new ResponseEntity<>(convertToDto(obj), HttpStatus.CREATED);
    }


    @PutMapping("/{id}")
    public ResponseEntity<OcupacionDTO> update(@Valid @PathVariable("id") Integer id, @RequestBody OcupacionDTO dto) throws Exception {
        dto.setIdOcupacion(id);
        Ocupacion obj = service.update(convertToEntity(dto), id);
        return new ResponseEntity<>(convertToDto(obj), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<OcupacionDTO>>  readAll() throws Exception{
        List<OcupacionDTO> list = service.readAll().stream().map(this::convertToDto).collect(Collectors.toList());
        return new ResponseEntity<>(list, HttpStatus.OK);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Integer id) throws Exception {
        service.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


    /////////////////////convert mapper//////////////////

    private OcupacionDTO convertToDto(Ocupacion obj){
        return mapper.map(obj, OcupacionDTO.class);
    }

    private Ocupacion convertToEntity(OcupacionDTO dto){
        return mapper.map(dto, Ocupacion.class);
    }

}
