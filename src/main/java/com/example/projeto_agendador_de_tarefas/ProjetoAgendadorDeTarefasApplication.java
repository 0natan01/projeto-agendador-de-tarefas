package com.example.projeto_agendador_de_tarefas;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class ProjetoAgendadorDeTarefasApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProjetoAgendadorDeTarefasApplication.class, args);
	}

}
