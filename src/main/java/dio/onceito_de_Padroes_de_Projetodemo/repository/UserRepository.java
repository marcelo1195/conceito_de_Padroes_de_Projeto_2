package dio.onceito_de_Padroes_de_Projetodemo.repository;

import dio.onceito_de_Padroes_de_Projetodemo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
