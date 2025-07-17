package com.example.projeto_agendador_de_tarefas.infrastructure.repository;

import com.example.projeto_agendador_de_tarefas.infrastructure.entity.TarefasEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TarefasEntityRepository extends MongoRepository<TarefasEntity , String> {
}
