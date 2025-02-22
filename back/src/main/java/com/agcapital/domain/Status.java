package com.agcapital.domain;

import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Status {

    public static final Status EM_ANDAMENTO = new Status("Em Andamento");
    public static final Status FINALIZADO = new Status("Finalizado");
    public static final Status CANCELADO = new Status("Cancelado");
    public static final Status AGUARDANDO = new Status("Aguardando");

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(nullable = false)
    private String descricao;

    private Status(String descricao) {
        this.descricao = descricao;
    }

}

