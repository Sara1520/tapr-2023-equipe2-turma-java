package br.edu.univille.microservturma.entity;

import org.springframework.data.annotation.Id;

import com.azure.spring.data.cosmos.core.mapping.Container;
import com.azure.spring.data.cosmos.core.mapping.GeneratedValue;
import com.azure.spring.data.cosmos.core.mapping.PartitionKey;

@Container(containerName = "materia")
public class Materia {
    @Id
    @PartitionKey
    @GeneratedValue
    public String idMateria;
    public String nomeMateria;
    
    public String idMateria() {
        return idMateria;
    }
    public void setIdMateria(String idMateria) {
        this.idMateria = idMateria;
    }
    public String getNomeMateria() {
        return nomeMateria;
    }
    public void setNomeMateria(String nomeMateria) {
        this.nomeMateria = nomeMateria;
    }

}