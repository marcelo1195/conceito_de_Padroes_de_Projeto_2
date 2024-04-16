package dio.onceito_de_Padroes_de_Projetodemo.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.util.Objects;

@Entity
@Table(name = "tasks")
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotBlank(message = "Title is required")
    @Column(nullable = false)
    private String title;

    @Column(length = 800)
    @Size(max = 800, message = "Description cannot be longer than 800 characters")
    private String description;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TaskStatus status;

    /* "@JoinColumn(name = "user_id"), nullable = false" Especifica o nome da coluna que será usado como chave estrangeira na tabela "Tasks"
     * para referenciar a tabela "users". A coluna 'user_id' é usada oara manter a relação com a entidade 'User' */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    public enum TaskStatus{
        PENDING, IN_PROGRESS, COMPLETED
    }

    // Default constructor JPA
    public Task(){

    }

    // Constructor
    public Task(String title, String description, TaskStatus status, User user) {
        this.title = title;
        this.description = description;
        this.status = status;
        this.user = user;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public TaskStatus getStatus() {
        return status;
    }

    public void setStatus(TaskStatus status) {
        this.status = status;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Task{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", status=" + status +
                ", user=" + user +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Task task)) return false;
        return id == task.id && Objects.equals(getTitle(), task.getTitle()) && Objects.equals(getDescription(), task.getDescription()) && getStatus() == task.getStatus() && Objects.equals(getUser(), task.getUser());
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, getTitle(), getDescription(), getStatus(), getUser());
    }
}
