package br.edu.univille.microservturma.entity;

import org.springframework.data.annotation.Id;

import com.azure.spring.data.cosmos.core.mapping.Container;
import com.azure.spring.data.cosmos.core.mapping.GeneratedValue;
import com.azure.spring.data.cosmos.core.mapping.PartitionKey;

@Container(containerName = "curso")
public class Curso {
    @Id
    @PartitionKey
    @GeneratedValue
    public String idCurso;
    public String nomeCurso;
    
    public String idCurso() {
        return idCurso;
    }
    public void setIdCurso(String idCurso) {
        this.idCurso = idCurso;
    }
    public String getNomeCurso() {
        return nomeCurso;
    }
    public void setNomeCurso(String nomeCurso) {
        this.nomeCurso = nomeCurso;
    }

}