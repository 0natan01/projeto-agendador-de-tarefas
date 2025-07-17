package com.example.projeto_agendador_de_tarefas.business.mapper;

import com.example.projeto_agendador_de_tarefas.business.dto.TarefasDTO;
import com.example.projeto_agendador_de_tarefas.infrastructure.entity.TarefasEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TarefasConverter {
    TarefasEntity paraTarefasEntity(TarefasDTO dto);

    TarefasDTO paraTarefasDTO(TarefasEntity entity);
}
