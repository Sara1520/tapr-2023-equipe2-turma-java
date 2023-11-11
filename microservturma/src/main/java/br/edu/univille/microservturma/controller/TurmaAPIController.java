package br.edu.univille.microservturma.controller;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import br.edu.univille.microservturma.entity.Turma;
import br.edu.univille.microservturma.service.TurmaService;
import io.swagger.v3.oas.annotations.parameters.RequestBody;

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

    @GetMapping("/{id}")
        public ResponseEntity<Turma> buscarTruma(@PathVariable("id")  String id){
            var turma = service.getById(id);
            if(turma == null){
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            return 
                new ResponseEntity<Turma>
                (turma, HttpStatus.OK);
        }
        @PostMapping
        public ResponseEntity<Turma> inserirTurma(@RequestBody Turma turma){
            if(turma == null){
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
            turma = service.saveNew(turma);
            return 
                new ResponseEntity<Turma>
                (turma, HttpStatus.OK);
        }
        @PutMapping("/{id}")
        public ResponseEntity<Turma> atualizarTurma(@PathVariable("id")  String id, @RequestBody Turma turma){
            if(turma == null || id == ""  || id == null){
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
            turma = service.update(id, turma);
            if(turma == null){
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            return 
                new ResponseEntity<Turma>
                (turma, HttpStatus.OK);
        }
        @DeleteMapping("/{id}")
        public ResponseEntity<Turma> removerTurma(@PathVariable("id")  String id){
            if(id == ""  || id == null){
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
            var turma = service.delete(id);
            if(turma == null){
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            return 
                new ResponseEntity<Turma>
                (turma, HttpStatus.OK);
        }
    }