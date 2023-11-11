package br.edu.univille.microservturma.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.univille.microservturma.entity.Turma;
import br.edu.univille.microservturma.repository.TurmaRepository;
import br.edu.univille.microservturma.service.TurmaService;

@Service
public class TurmaServiceImpl implements TurmaService {

    @Autowired
    private TurmaRepository repository;

    @Override
    public List<Turma> getAll() {
        var iterador = repository.findAll();
        List<Turma> listaTurmas = new ArrayList<>();

        iterador.forEach(listaTurmas::add);

        /*while(iterador.iterator().hasNext()){
            var umItem = iterador.iterator().next();
            listaTurmas.add(umItem);
        }*/


        return listaTurmas;
    }
    
}
