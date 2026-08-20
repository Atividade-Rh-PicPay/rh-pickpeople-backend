package com.example.rhpicpaybackend.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum Status {
    EM_ANALISE("Em Análise", 1),
    APROVADO("Aprovado", 2),
    REPROVADO("Reprovado", 3),
    STATUS("Contratado", 4);

    private String nome;
    private int id;

    public static Status fromNome(String nome) {
        for (Status statusProblema : values()) {
            if(statusProblema.getNome().equals(nome)) {
                return statusProblema;
            }
        }

        throw new IllegalArgumentException("Status inválido: " + nome);
    }

    public static Status fromId(int id) {
        for (Status statusProblema : values()) {
            if(statusProblema.getId() == id) {
                return statusProblema;
            }
        }

        throw new IllegalArgumentException("Status inválido: " + id);
    }

}
