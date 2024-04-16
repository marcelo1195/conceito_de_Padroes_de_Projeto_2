package dio.onceito_de_Padroes_de_Projetodemo.service;


import dio.onceito_de_Padroes_de_Projetodemo.model.User;
import dio.onceito_de_Padroes_de_Projetodemo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public List<User> findAllUsers(){
        return userRepository.findAll();
    }

    public User findById(long id){
        return userRepository.findById(id).orElse(null);
    }

    public User saveUser(User user){
        return userRepository.save(user);
    }

    public User updateUser(Long id, User userDetails){
        User user = userRepository.findById(id).orElse(null);
        if (user != null){
            user.setUsername(userDetails.getUsername());
            user.setPassword(userDetails.getPassword());
            user.setEmail(userDetails.getEmail());
            return userRepository.save(user);
        }
        return null;
    }

    public boolean deleteUser(Long id){
        return userRepository.findById(id).map(user -> {
            userRepository.delete(user);
            return true;
        }).orElse(false);
    }
}
