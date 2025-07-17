package com.example.projeto_agendador_de_tarefas.business.service;

import com.example.projeto_agendador_de_tarefas.business.dto.TarefasDTO;
import com.example.projeto_agendador_de_tarefas.business.mapper.TarefasConverter;
import com.example.projeto_agendador_de_tarefas.infrastructure.enums.StatusNotificacaoEnum;
import com.example.projeto_agendador_de_tarefas.infrastructure.entity.TarefasEntity;
import com.example.projeto_agendador_de_tarefas.infrastructure.repository.TarefasEntityRepository;
import com.example.projeto_agendador_de_tarefas.infrastructure.security.JwtUtil;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class TarefasService {
    private final TarefasEntityRepository tarefaEntityRepository;
    private final TarefasConverter tarefasConverter;
    private final JwtUtil jwtUtil;

    public TarefasDTO salvarTarefas(TarefasDTO dto , String token){

        String email = jwtUtil.extrairEmailToken(token.substring(7));

        dto.setDataCriacao(LocalDateTime.now());
        dto.setStatusNotificacaoEnum(StatusNotificacaoEnum.PENDENTE);
        dto.setEmailUsuario(email);

        TarefasEntity entity = tarefasConverter.paraTarefasEntity(dto);
        return tarefasConverter.paraTarefasDTO(tarefaEntityRepository.save(entity));
    }
}
