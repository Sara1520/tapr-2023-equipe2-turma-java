package br.edu.univille.microservturma.entity;

import org.springframework.data.annotation.Id;

import com.azure.spring.data.cosmos.core.mapping.Container;
import com.azure.spring.data.cosmos.core.mapping.GeneratedValue;
import com.azure.spring.data.cosmos.core.mapping.PartitionKey;

@Container(containerName = "turma")
public class Turma {
    @Id
    @GeneratedValue
    public String id;
    @PartitionKey
    public String nomeAluno;

    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getNomeAluno() {
        return nomeAluno;
    }
    public void setNomeAluno(String nomeAluno) {
        this.nomeAluno = nomeAluno;
    }
    public Object getTurma() {
        return null;
    }
    public void setTurma(Object turma) {
    }

    
}
