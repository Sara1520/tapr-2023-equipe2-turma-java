package br.edu.univille.microservturma.controller;



import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import br.edu.univille.microservturma.entity.Turma;
import br.edu.univille.microservturma.service.TurmaService;

@RestController
@RequestMapping("/api/v1/turmas")
public class TurmaAPIController {

    @Autowired
    private TurmaService service;

    @GetMapping
    public ResponseEntity<List<Turma>> listaTurmas(){
        //var listaTurmas = new ArrayList<Turma>();
        var listaTurmas = service.getAll();
        return
            new ResponseEntity<List<Turma>>
            (listaTurmas, HttpStatus.OK);
    }
}