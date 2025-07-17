package com.example.projeto_agendador_de_tarefas.controller;

import com.example.projeto_agendador_de_tarefas.business.dto.TarefasDTO;
import com.example.projeto_agendador_de_tarefas.business.service.TarefasService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
}
