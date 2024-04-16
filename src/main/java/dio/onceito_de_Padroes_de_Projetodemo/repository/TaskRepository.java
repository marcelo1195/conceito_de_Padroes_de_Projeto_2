package dio.onceito_de_Padroes_de_Projetodemo.repository;

import dio.onceito_de_Padroes_de_Projetodemo.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskRepository extends JpaRepository <Task, Long>{
}
