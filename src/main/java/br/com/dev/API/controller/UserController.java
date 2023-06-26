package br.com.dev.API.controller;


import br.com.dev.API.model.User;
import br.com.dev.API.repository.UserRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
public class UserController {
    private final UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @RequestMapping(value = "/users")
    public List<User> getAll(){
        return userRepository.findAll();
    }
    @RequestMapping(value = "/users/{id}")
    public User findOne(@PathVariable Long id){
        Optional<User> user = userRepository.findById(id);
        return user.get();
    }

    @DeleteMapping(value = "/users/{id}")
    public void delete(@PathVariable long id) {
        if(userRepository.existsById(id)){
            userRepository.deleteById(id);
        }
    }

    @PutMapping(value = "/users/{id}")
    public User update(@PathVariable(value = "id") long idUser, @RequestBody Map<String, String> user) {
        User user1 = userRepository.getOne(idUser);
        String name = user.get("name");
        String username = user.get("username");
        String email = user.get("email");

        user1.setName(name);
        user1.setUserName(username);
        user1.setEmail(email);
        return userRepository.save(user1);
    }

    @PostMapping(value = "/users")
    public User create(@RequestBody Map<String, String> user) {
        String name = user.get("name");
        String username = user.get("username");
        String email = user.get("email");

        User user1 = new User();
        user1.setName(name);
        user1.setUserName(username);
        user1.setEmail(email);
        return userRepository.save(user1);
    }

}
