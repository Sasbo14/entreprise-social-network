package com.entreprise.socialnetwork.controller;


import com.entreprise.socialnetwork.model.Post;
import com.entreprise.socialnetwork.model.User;
import com.entreprise.socialnetwork.repository.PostRepository;
import com.entreprise.socialnetwork.service.LikeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/posts")
public class PostController {

    @Autowired
    private PostRepository postRepository;


    @Autowired
    private LikeService likeService;

    // Ajoute un post
    @PostMapping
    public String addPost(@RequestBody Post post) {
        postRepository.save(post);
        return "Post ajouté avec succès";
    }

    //Affiche tout les posts
    @GetMapping
    public List<Post> getAllPosts() {
        return postRepository.findAll();
    }

    //Affiche les posts d'un utilisateur avec son id
    @GetMapping("/user/{userId}")
    public List<Post> getPostsByUser(@PathVariable int userId) {
        return postRepository.findAll().stream()
                .filter(p -> p.getUser().getId() == userId)
                .collect(Collectors.toList());
    }

    @PostMapping("/{postId}/like")
    public String likePost(@PathVariable int postId, @RequestParam int userId) {
        return likeService.likePost(postId, userId);
    }
    //Nombre de likes
    @GetMapping("{postId}/likes")
    public String getLikes(@PathVariable int postId) {
        int count = likeService.getLikesCount(postId);
        return "Le post " + postId + " a " + count + " like(s)";
    }
    //Liste des utilisateurs qui ont liké un post
    @GetMapping("/{postId}/likes/users")
    public List<User> getUsersWholiked(@PathVariable int postId) {
        return likeService.getUsersWhoLiked(postId);
    }
}
