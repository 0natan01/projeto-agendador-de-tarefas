package com.example.projeto_agendador_de_tarefas.controller;

import com.example.projeto_agendador_de_tarefas.business.dto.TarefasDTO;
import com.example.projeto_agendador_de_tarefas.business.service.TarefasService;
import com.example.projeto_agendador_de_tarefas.infrastructure.enums.StatusNotificacaoEnum;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/tarefas")
public class TarefasController {
    private final TarefasService tarefasService;

    @PostMapping
    public ResponseEntity<TarefasDTO>  gravarTarefas(@RequestBody TarefasDTO dto,
                                                     @RequestHeader("Authorization") String token){
        return ResponseEntity.ok(tarefasService.salvarTarefas(dto, token));
    }

    @GetMapping("/eventos")
    public ResponseEntity<List<TarefasDTO>> buscaListaDeTarefasPorPeriodo(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)LocalDateTime dataInicial ,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dataFinal){
        return ResponseEntity.ok(tarefasService.buscaTarefasAgendadasPorEmail(dataInicial , dataFinal));
    }

    @GetMapping
    public ResponseEntity<List<TarefasDTO>> buscaTarefasPorEmail(@RequestHeader("Authorization") String token){
        return ResponseEntity.ok(tarefasService.buscaTarefaPorEmail(token));
    }

    @DeleteMapping
    public ResponseEntity<Void> deletarTarefasPorId(@RequestParam("id") String id){
        tarefasService.deletarTarefaPorId(id);

        return ResponseEntity.ok().build();
    }

    @PatchMapping
    public ResponseEntity<TarefasDTO> atualizaStatus(@RequestParam("status")StatusNotificacaoEnum status,
    @RequestParam("id") String id){
        return ResponseEntity.ok(tarefasService.alteraStatus(status , id));
    }

    @PutMapping
    public ResponseEntity<TarefasDTO> alteraStatusTarefas(@RequestBody TarefasDTO dto
            , @RequestParam("id") String id ){
        return ResponseEntity.ok(tarefasService.updateTarefa(dto , id)) ;
    }
}
