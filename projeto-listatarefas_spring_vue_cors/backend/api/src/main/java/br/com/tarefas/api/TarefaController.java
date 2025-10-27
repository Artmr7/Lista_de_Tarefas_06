package br.com.tarefas.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/tarefas") // <-- Correção: Removido o "/api"
public class TarefaController {

    @Autowired
    private TarefaRepository tarefaRepository; // Injeção direta do Repository

    @GetMapping
    public List<Tarefa> listar() {
        return tarefaRepository.findAll(); // Lógica de busca no controller
    }

    @PostMapping
    public Tarefa criar(@RequestBody Tarefa tarefa) {
        return tarefaRepository.save(tarefa); // Lógica de persistência no controller
    }
}