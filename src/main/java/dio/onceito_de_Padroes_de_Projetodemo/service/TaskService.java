package dio.onceito_de_Padroes_de_Projetodemo.service;

import dio.onceito_de_Padroes_de_Projetodemo.model.Task;
import dio.onceito_de_Padroes_de_Projetodemo.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskService {

    @Autowired
    private TaskRepository taskRepository;

    public List<Task> findAllTask(){
        return taskRepository.findAll();
    }

    public Task findTaskById(long id) {
        return taskRepository.findById(id).orElse(null);
    }

    public Task saveTask(Task task) {
        return taskRepository.save(task);
    }

    public Task updateTask(Long id, Task taskDetails) {
        Task task = taskRepository.findById(id).orElse(null);
        if (task != null) {
            task.setTitle(taskDetails.getTitle());
            task.setDescription(taskDetails.getDescription());
            task.setStatus(taskDetails.getStatus());
            task.setUser(taskDetails.getUser());
            return taskRepository.save(task);
        }
        return null;
    }

    public boolean deleteTask(Long id) {
        return taskRepository.findById(id).map(task -> {
            taskRepository.delete(task);
            return true;
        }).orElse(false);
    }
}
