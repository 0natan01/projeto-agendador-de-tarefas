package com.example.projeto_agendador_de_tarefas.infrastructure.security;


import com.example.projeto_agendador_de_tarefas.business.dto.UsuarioDto;
import com.example.projeto_agendador_de_tarefas.infrastructure.client.UsuarioClient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;


import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl  {

    @Autowired
    private UsuarioClient client;


    public UserDetails carregaDadosUsuario(String email , String token){
        UsuarioDto usuarioDto = client.buscaUsuarioPorEmail(email , token);
        return User
                .withUsername(usuarioDto.getEmail()) // Define o nome de usuário como o e-mail
                .password(usuarioDto.getSenha()) // Define a senha do usuário
                .build(); // Constrói o objeto UserDet
    }
}