package com.example.projeto_agendador_de_tarefas.business.service;

import com.example.projeto_agendador_de_tarefas.business.dto.TarefasDTO;
import com.example.projeto_agendador_de_tarefas.business.mapper.TarefasConverter;
import com.example.projeto_agendador_de_tarefas.business.mapper.TarefasUpdateConverter;
import com.example.projeto_agendador_de_tarefas.infrastructure.enums.StatusNotificacaoEnum;
import com.example.projeto_agendador_de_tarefas.infrastructure.entity.TarefasEntity;
import com.example.projeto_agendador_de_tarefas.infrastructure.exceptions.ResourceNotFoundExceptions;
import com.example.projeto_agendador_de_tarefas.infrastructure.repository.TarefasEntityRepository;
import com.example.projeto_agendador_de_tarefas.infrastructure.security.JwtUtil;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@AllArgsConstructor
public class TarefasService {
    private final TarefasEntityRepository tarefaEntityRepository;
    private final TarefasConverter tarefasConverter;
    private final JwtUtil jwtUtil;
    private final TarefasUpdateConverter tarefasUpdateConverter;

    public TarefasDTO salvarTarefas(TarefasDTO dto , String token){

        String email = jwtUtil.extrairEmailToken(token.substring(7));

        dto.setDataCriacao(LocalDateTime.now());
        dto.setStatusNotificacaoEnum(StatusNotificacaoEnum.PENDENTE);
        dto.setEmailUsuario(email);

        TarefasEntity entity = tarefasConverter.paraTarefasEntity(dto);
        return tarefasConverter.paraTarefasDTO(tarefaEntityRepository.save(entity));
    }

    public List<TarefasDTO> buscaTarefasAgendadasPorEmail(LocalDateTime dataInicial , LocalDateTime dataFinal){
        return tarefasConverter.paraListTarefasDTO(
          tarefaEntityRepository.findByDataEventoBetween(dataInicial , dataFinal)
        );
    }

    public List<TarefasDTO> buscaTarefaPorEmail(String token){
        String email = jwtUtil.extrairEmailToken(token.substring(7));

        List<TarefasEntity> tarefasUsuario = tarefaEntityRepository.findByEmailUsuario(email);

        return tarefasConverter.paraListTarefasDTO(tarefasUsuario);
    }

    public void deletarTarefaPorId(String id){
        try{
            tarefaEntityRepository.deleteById(id);
        } catch (ResourceNotFoundExceptions e){
            throw new ResourceNotFoundExceptions("Erro ao deletar tarefa por id" + id , e.getCause());
        }
    }

    public TarefasDTO alteraStatus(StatusNotificacaoEnum status , String id){
        try{
            TarefasEntity entity = tarefaEntityRepository.findById(id).orElseThrow(
                    () -> new ResourceNotFoundExceptions(
                            "status da tarefa nao encontrado" + id)
            );
            entity.setStatusNotificacaoEnum(status);
            return tarefasConverter.paraTarefasDTO(tarefaEntityRepository.save(entity));
        } catch (ResourceNotFoundExceptions e){
            throw new ResourceNotFoundExceptions("Nao foi possivel alterar status da tarefa",
                    e.getCause());
        }
    }

    public TarefasDTO updateTarefa(TarefasDTO dto , String id){
        try{
            TarefasEntity entity = tarefaEntityRepository.findById(id)
                    .orElseThrow(() -> new ResourceNotFoundExceptions("status da tarefa nao encontrado" + id)
                    );
            tarefasUpdateConverter.updateStatusTarefa(dto , entity);
            return tarefasConverter.paraTarefasDTO(tarefaEntityRepository.save(entity));
        } catch(ResourceNotFoundExceptions e){
            throw new ResourceNotFoundExceptions("Nao foi possivel alterar status da tarefa",
                    e.getCause());
        }
    }
}
