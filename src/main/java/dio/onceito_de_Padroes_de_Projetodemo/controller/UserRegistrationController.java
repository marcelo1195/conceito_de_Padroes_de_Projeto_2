package dio.onceito_de_Padroes_de_Projetodemo.controller;

import dio.onceito_de_Padroes_de_Projetodemo.dto.UserRegistrationDto;
import dio.onceito_de_Padroes_de_Projetodemo.model.User;
import dio.onceito_de_Padroes_de_Projetodemo.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Registro", description = "Recursos de Registro de Usuários")
@RestController
@RequestMapping("/auth/register")
public class UserRegistrationController {

    private final UserService userService;

    @Autowired
    public UserRegistrationController(UserService userService){
        this.userService = userService;
    }

    @Operation(summary = "Criar um usuário", description = "Adiciona um novo usuário à base de dados", tags = { "Usuários" })
    @ApiResponse(responseCode = "201", description = "Usuário criado com sucesso", content = @Content(mediaType = "application/json", schema = @Schema(implementation = User.class)))
    @PostMapping
    public ResponseEntity<User> createUser(@Parameter(description = "Dados de registro do usuário para criação") @Valid @RequestBody UserRegistrationDto registrationDto){
        User newUser = new User();
        newUser.setUsername(registrationDto.getUsername());
        newUser.setEmail(registrationDto.getEmail());
        newUser.setPassword(userService.encodePassword(registrationDto.getPassword()));
        User savedUser = userService.saveUser(newUser);

        if (savedUser != null && savedUser.getId() > 0) {
            return ResponseEntity.status(HttpStatus.CREATED).body(savedUser);
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }
}
