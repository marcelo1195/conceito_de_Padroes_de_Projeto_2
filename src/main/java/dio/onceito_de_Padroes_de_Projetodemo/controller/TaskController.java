package dio.onceito_de_Padroes_de_Projetodemo.controller;

import dio.onceito_de_Padroes_de_Projetodemo.model.Task;
import dio.onceito_de_Padroes_de_Projetodemo.service.TaskService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.List;

@Tag(name = "Tarefas", description = "Recursos de Tarefas")
@RestController
@RequestMapping("/api/tasks")
public class TaskController {

    @Autowired
    private TaskService taskService;

    @Operation(summary = "Listar todas as tarefas", description = "Retorna uma lista de tarefas", tags = { "Tarefas" })
    @ApiResponse(responseCode = "200", description = "Operação bem sucedida", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Task.class)))
    @GetMapping
    public List<Task> getAllTasks() {
        return taskService.findAllTask();
    }

    @Operation(summary = "Buscar tarefa por ID", description = "Retorna uma tarefa única", tags = { "Tarefas" })
    @ApiResponse(responseCode = "200", description = "Tarefa encontrada", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Task.class)))
    @ApiResponse(responseCode = "404", description = "Tarefa não encontrada")
    @GetMapping("/{id}")
    public ResponseEntity<Task> getTaskById(@Parameter(description = "ID da tarefa para busca") @PathVariable long id) {
        Task task = taskService.findTaskById(id);
        if (task != null) {
            return ResponseEntity.ok(task);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @Operation(summary = "Criar uma tarefa", description = "Adiciona uma nova tarefa à base de dados", tags = { "Tarefas" })
    @ApiResponse(responseCode = "201", description = "Tarefa criada com sucesso", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Task.class)))
    @PostMapping
    public Task createTask(@Parameter(description = "Objeto de tarefa para criação") @Valid @RequestBody Task task) {
        return taskService.saveTask(task);
    }

    @Operation(summary = "Atualizar uma tarefa", description = "Atualiza os detalhes de uma tarefa existente", tags = { "Tarefas" })
    @ApiResponse(responseCode = "200", description = "Tarefa atualizada com sucesso", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Task.class)))
    @ApiResponse(responseCode = "404", description = "Tarefa não encontrada")
    @PutMapping("/{id}")
    public ResponseEntity<Task> updateTask(@Parameter(description = "ID da tarefa para atualização") @PathVariable Long id, @Parameter(description = "Objeto de tarefa com novos detalhes") @RequestBody Task taskDetails) {
        Task updatedTask = taskService.updateTask(id, taskDetails);
        if (updatedTask != null) {
            return ResponseEntity.ok(updatedTask);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @Operation(summary = "Deletar uma tarefa", description = "Remove uma tarefa da base de dados", tags = { "Tarefas" })
    @ApiResponse(responseCode = "200", description = "Tarefa deletada com sucesso")
    @ApiResponse(responseCode = "404", description = "Tarefa não encontrada")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTask(@Parameter(description = "ID da tarefa para remoção") @PathVariable Long id) {
        boolean deleted = taskService.deleteTask(id);
        if (deleted) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
