package com.example.projeto_agendador_de_tarefas.infrastructure.security.client;

import com.example.projeto_agendador_de_tarefas.business.dto.UsuarioDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "usuario" , url = "${usuario.url}")
public interface UsuarioClient {

    @GetMapping("/usuario")
    UsuarioDto buscaUsuarioPorEmail(@RequestParam String email,
                                    @RequestHeader("Authorization") String token);

}
