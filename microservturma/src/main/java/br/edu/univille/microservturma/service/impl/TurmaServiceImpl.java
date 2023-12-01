package br.edu.univille.microservturma.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import br.edu.univille.microservturma.entity.Turma;
import br.edu.univille.microservturma.repository.TurmaRepository;
import br.edu.univille.microservturma.service.TurmaService;
import io.dapr.client.DaprClient;
import io.dapr.client.DaprClientBuilder;

@Service
public class TurmaServiceImpl implements TurmaService{

    @Autowired
    private TurmaRepository repository;
    private DaprClient client = new DaprClientBuilder().build();
    @Value("${app.component.topic.turma}")
    private String TOPIC_NAME;
    @Value("${app.component.service}")
	private String PUBSUB_NAME;

    @Override
    public List<Turma> getAll() {
        var iterador = repository.findAll();
        List<Turma> listaTurmas = new ArrayList<>();

        iterador.forEach(listaTurmas::add);

        return listaTurmas;
    }

    @Override
    public Turma getById(String id) {
        var turma = repository.findById(id);
        if(turma.isPresent())
            return turma.get();
        return null;
    }

    @Override
    public Turma saveNew(Turma turma) {
        turma.setId(null);
        turma = repository.save(turma);
        publicarAtualizacao(turma);
        return turma;
    }

    @Override
    public Turma update(String id, Turma turma) {
        var buscaTurmaAntigo = repository.findById(id);
        if (buscaTurmaAntigo.isPresent()){
            var turmaAntigo = buscaTurmaAntigo.get();

            //Atualizar cada atributo do objeto antigo 
            turmaAntigo.setId(turma.getId());
            turmaAntigo = repository.save(turmaAntigo);
            publicarAtualizacao(turmaAntigo);
            return turmaAntigo;
        }
        return null;
    }

    @Override
    public Turma delete(String id) {
        var buscaTurma = repository.findById(id);
        if (buscaTurma.isPresent()){
            var turma = buscaTurma.get();

            repository.delete(turma);

            return turma;
        }
        return null;
    }
    
    private void publicarAtualizacao(Turma turma){
        
        client.publishEvent(
					PUBSUB_NAME,
					TOPIC_NAME,
					turma).block();
    }
}