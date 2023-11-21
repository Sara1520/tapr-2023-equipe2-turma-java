package br.edu.univille.microservturma.service;

import java.util.List;
import br.edu.univille.microservturma.entity.Materia;

public interface MateriaService {
    public List<Materia> getAll();
    public Materia getById(String id);
    public Materia saveNew(Materia materia);
    public Materia update(String id, Materia materia);
    public Materia update(Materia materia);
    public Materia delete(String id);
}