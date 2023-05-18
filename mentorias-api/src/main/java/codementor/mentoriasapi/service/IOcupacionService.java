package codementor.mentoriasapi.service;


import codementor.mentoriasapi.model.Ocupacion;

public interface IOcupacionService extends ICRUD<Ocupacion, Integer>{
    public boolean isOcupacionDuplicate(String name, String description);
}
