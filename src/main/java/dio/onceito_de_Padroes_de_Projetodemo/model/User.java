package dio.onceito_de_Padroes_de_Projetodemo.model;

import jakarta.persistence.*;
import dio.onceito_de_Padroes_de_Projetodemo.model.Task;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;



import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "Users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Size(min = 5, max =20, message = "Username must be between 5 and 20 characters")
    @NotBlank(message = "Username is required")
    @Column(nullable = false, unique = true)
    private String username;

    @NotBlank(message = "Password is required")
    @Size(min = 15, max = 100)
    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{15,}$",
            message = "Password must be at least 15 characters long and include at least one uppercase letter, one digit, and one special character.")
    @Column(nullable = false)
    private String password;

    @Column(nullable = false, unique = true)
    @Email(message = "Email should be valid")
    private String email;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Task> tasks;

    // Default constructor for JPA
    public User() {

    }

    //Construtor for manual object creation
    public User(String username, String password, String email, List<Task> tasks) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.tasks = tasks;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", tasks=" + tasks +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User user)) return false;
        return id == user.id && Objects.equals(getUsername(), user.getUsername()) && Objects.equals(getEmail(), user.getEmail()) && Objects.equals(getTasks(), user.getTasks());
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, getUsername(), getEmail(), getTasks());
    }
}
