package com.example.projeto_agendador_de_tarefas.infrastructure.exceptions;

public class ResourceNotFoundExceptions extends RuntimeException{

    public ResourceNotFoundExceptions(String mensagem){
        super(mensagem);
    }

    public ResourceNotFoundExceptions(String mensagem , Throwable throwable){
        super(mensagem , throwable);
    }
}
