package br.edu.univille.microservturma.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.univille.microservturma.entity.Materia;
import br.edu.univille.microservturma.repository.MateriaRepository;
import br.edu.univille.microservturma.service.MateriaService;

@Service
public class MateriaServiceImpl 
    implements MateriaService{
    
    @Autowired
    private MateriaRepository repository;

    @Override
    public List<Materia> getAll() {
        var iterador = repository.findAll();
        List<Materia> listaMaterias = new ArrayList<>();

        iterador.forEach(listaMaterias::add);

        return listaMaterias;
    }

    @Override
    public Materia getById(String id) {
        var materia = repository.findById(id);
        if(materia.isPresent())
            return materia.get();
        return null;
    }

    @Override
    public Materia saveNew(Materia materia) {
        materia.setIdMateria(null);
        return repository.save(materia);
    }

    @Override
    public Materia update(String id, Materia materia) {
        var buscaMateriaAntigo = repository.findById(id);
        if (buscaMateriaAntigo.isPresent()){
            var materiaAntigo = buscaMateriaAntigo.get();

            //Atualizar cada atributo do objeto antigo 
           materiaAntigo.setNomeMateria(materia.getNomeMateria());
            
            return repository.save(materiaAntigo);
        }
        return null;
    }

    @Override
    public Materia delete(String id) {
        var buscaMateria = repository.findById(id);
        if (buscaMateria.isPresent()){
            var materia = buscaMateria.get();

            repository.delete(materia);

            return materia;
        }
        return null;
    }

    @Override
    public Materia update(Materia materia) {
        return repository.save(materia);
    }
    
}
