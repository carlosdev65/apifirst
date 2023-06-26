package br.com.dev.API.controller;


import br.com.dev.API.model.Post;
import br.com.dev.API.model.User;
import br.com.dev.API.repository.PostRepository;
import br.com.dev.API.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
public class PostController {
    @Autowired
    private final PostRepository postRepository;
    @Autowired
    private final UserRepository userRepository;

    public PostController(PostRepository postRepository, UserRepository userRepository) {
        this.postRepository = postRepository;
        this.userRepository = userRepository;
    }
    @GetMapping(value="/posts")
    public List<Post> getAll(){
        return postRepository.findAll();
    }

    @PostMapping(value="/posts")
    public String create(@RequestBody Map<String, String> post){
        Long id = Long.parseLong(post.get("userId"));
        String title = post.get("title");
        String body = post.get("body");
        Optional<User> user = userRepository.findById(id);


        Post post1 = new Post();
        post1.setUser(user.get());
        post1.setTitle(title);
        post1.setBody(body);
        postRepository.save(post1);
        return "Post was published";
    }
    @PutMapping("/posts/{id}")
    public Post update(@PathVariable(value = "id") long idPost, @RequestBody Map<String, String> post){
        Long id = Long.parseLong(post.get("userId"));
        String title = post.get("title");
        String body = post.get("body");
        Optional<User> user = userRepository.findById(Long.parseLong(post.get("userId")));


        Optional<Post> p = postRepository.findById(idPost);
        Post post1 = p.get();
        post1.setUser(user.get());
        post1.setTitle(title);
        post1.setBody(body);

        return postRepository.save(post1);
    }
    @DeleteMapping(value = "/posts/{id}")
    public void delete(@PathVariable Long id){
        if(postRepository.existsById(id)){
            postRepository.deleteById(id);
        }
    }
}