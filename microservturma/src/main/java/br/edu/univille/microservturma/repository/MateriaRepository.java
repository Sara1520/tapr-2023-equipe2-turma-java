package br.edu.univille.microservturma.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import br.edu.univille.microservturma.entity.Materia;

@Repository
public interface MateriaRepository 
    extends CrudRepository<Materia,String>{
    
}
