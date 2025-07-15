package com.entreprise.socialnetwork.controller;

import com.entreprise.socialnetwork.model.Comment;
import com.entreprise.socialnetwork.model.Post;
import com.entreprise.socialnetwork.model.User;
import com.entreprise.socialnetwork.repository.CommentRepository;
import com.entreprise.socialnetwork.repository.PostRepository;
import com.entreprise.socialnetwork.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/comments")
public class CommentController {

    @Autowired
    private CommentRepository commentRepository;
    private PostRepository postRepository;
    private UserRepository userRepository;

    @PostMapping("/add")
    public String addComment(@RequestParam int postId,@RequestParam int userId,@RequestParam String content) {

        Post post = postRepository.findById(postId).orElse(null);
        User user = userRepository.findById(userId).orElse(null);

        if (post == null || user == null) {
            return "Utilisateur ou post introuvable !";
        }

        Comment comment = new Comment();
        comment.setContent(content);
        comment.setPost(post);
        comment.setUser(user);

        commentRepository.save(comment);
        return "Commentaire ajouté avec succès ! ";
    }

    @GetMapping
    public List<Comment> getAllComments() {
        return commentRepository.findAll();
    }

    @GetMapping("/post/{postId}")
    public List<Comment> getCommentsByPost(@PathVariable int postId) {
        Post post = postRepository.findById(postId).orElse(null);
        if(post == null) return List.of();
        
        return commentRepository.findAll().stream()
                .filter(c -> c.getPost().getId() == postId)
                .collect(Collectors.toList());
    }
}
