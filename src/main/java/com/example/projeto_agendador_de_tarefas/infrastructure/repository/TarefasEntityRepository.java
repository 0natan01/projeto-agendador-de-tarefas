package com.example.projeto_agendador_de_tarefas.infrastructure.repository;

import com.example.projeto_agendador_de_tarefas.infrastructure.entity.TarefasEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface TarefasEntityRepository extends MongoRepository<TarefasEntity , String> {
    List<TarefasEntity> findByDataEventoBetween(LocalDateTime dataInicial , LocalDateTime dataFinal);

    List<TarefasEntity> findByEmailUsuario(String email);
}
