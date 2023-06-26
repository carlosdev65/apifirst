package br.com.dev.API.controller;


import br.com.dev.API.model.Comment;
import br.com.dev.API.model.Post;
import br.com.dev.API.repository.CommentRepository;
import br.com.dev.API.repository.PostRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
public class CommentController {
    private final CommentRepository commentRepository;
    private final PostRepository postRepository;

    public CommentController(CommentRepository commentRepository, PostRepository postRepository) {
        this.commentRepository = commentRepository;
        this.postRepository = postRepository;
    }
    @RequestMapping(value = "/comments")
    public List<Comment> getAll(){
        return commentRepository.findAll();
    }

    @PostMapping(value="/comments")
    public Comment create(@RequestBody Map<String, String> comment){
        Comment comment1 = new Comment();
        Post post = postRepository.getOne(Long.parseLong(comment.get("postId")));
        comment1.setPost(post);
        comment1.setTile(comment.get("title"));
        comment1.setBody(comment.get("body"));
        comment1.setEmail(comment.get("email"));

        return commentRepository.save(comment1);
    }
    @PutMapping("/comments/{id}")
    public Comment update(@PathVariable(value = "id") long idComment, @RequestBody Map<String, String> comment){
        Long idPost = Long.parseLong(comment.get("postId"));
        String title = comment.get("title");
        String body = comment.get("body");
        String email = comment.get("email");
        Comment comment1 = commentRepository.getOne(idComment);
        Post post = postRepository.getOne(idPost);

        comment1.setPost(post);
        comment1.setTile(title);
        comment1.setBody(body);
        comment1.setEmail(email);

        return commentRepository.save(comment1);
    }
    @DeleteMapping(value = "/comments/{id}")
    public void delete(@PathVariable Long id){
        if(commentRepository.existsById(id)){
            commentRepository.deleteById(id);
        }
    }
}
