package dio.onceito_de_Padroes_de_Projetodemo.service;


import dio.onceito_de_Padroes_de_Projetodemo.model.User;
import dio.onceito_de_Padroes_de_Projetodemo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;


@Service
public class UserService {

     private final UserRepository userRepository;
     private final PasswordEncoder passwordEncoder;

     @Autowired
     public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
         this.userRepository = userRepository;
         this.passwordEncoder = passwordEncoder;
     }

     public String encodePassword(String rawPassword){
         return passwordEncoder.encode(rawPassword);
     }

     public List<User> findAllUsers(){
         return userRepository.findAll();
     }

     public User findById(long id){
         return userRepository.findById(id).orElse(null);
     }

     public User saveUser(User user){
         user.setPassword(passwordEncoder.encode(user.getPassword()));
         return userRepository.save(user);
     }

     public User updateUser(Long id, User userDetails){
         User user = userRepository.findById(id).orElse(null);
         if (user != null ){
             user.setUsername(userDetails.getUsername());
             if (!userDetails.getPassword().isEmpty() && !userDetails.getPassword().equals(user.getPassword())) {
                 user.setPassword(passwordEncoder.encode(userDetails.getPassword()));
             }
             user.setEmail(userDetails.getEmail());
             userRepository.save(user);
         }
         return null;
     }

    public boolean deleteUser(Long id){
        User user = userRepository.findById(id).orElse(null);
        if (user != null){
            userRepository.delete(user);
            return true;
        }
        return false;
    }


}
