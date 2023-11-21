package br.edu.univille.microservturma.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.edu.univille.microservturma.entity.Materia;
import br.edu.univille.microservturma.service.MateriaService;

@RestController
@RequestMapping("/api/v1/materias")
public class MateriaAPIController {
    @Autowired
    private MateriaService service;

    @GetMapping
    public ResponseEntity<List<Materia>> listaMas(){
        var listaMaterias = service.getAll();
        return 
            new ResponseEntity<List<Materia>>
            (listaMaterias, HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Materia> buscarMateria(@PathVariable("id")  String id){
        var materia = service.getById(id);
        if(materia == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return 
            new ResponseEntity<Materia>
            (materia, HttpStatus.OK);
    }
    @PostMapping
    public ResponseEntity<Materia> inserirMateria(@RequestBody Materia materia){
        if(materia == null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        materia = service.saveNew(materia);
        return 
            new ResponseEntity<Materia>
            (materia, HttpStatus.OK);
    }
    @PutMapping("/{id}")
    public ResponseEntity<Materia> atualizarMateria(@PathVariable("id")  String id, @RequestBody Materia materia){
        if(materia == null || id == ""  || id == null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        materia = service.update(id, materia);
        if(materia == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return 
            new ResponseEntity<Materia>
            (materia, HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Materia> removerMateria(@PathVariable("id")  String id){
        if(id == ""  || id == null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        var materia = service.delete(id);
        if(materia == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return 
            new ResponseEntity<Materia>
            (materia, HttpStatus.OK);
    }

    @Topic(name = "${app.component.topic.materia}", pubsubName = "${app.component.service}")
    @PostMapping(path = "/event", consumes = MediaType.ALL_VALUE)
    public ResponseEntity<Materia> atualizarMateria(@RequestBody(required = false) CloudEvent<Materia> cloudEvent){
        var materia = service.update(cloudEvent.getData());
        System.out.println("EVENT" + ((Materia) materia).getNomeMateria());
        return 
            new ResponseEntity<Materia>
            (HttpStatus.OK);
    }
    
}