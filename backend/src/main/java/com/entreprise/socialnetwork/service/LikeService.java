package com.entreprise.socialnetwork.service;

import com.entreprise.socialnetwork.model.LikePost;
import com.entreprise.socialnetwork.model.Post;
import com.entreprise.socialnetwork.model.User;
import com.entreprise.socialnetwork.repository.LikePostRepository;
import com.entreprise.socialnetwork.repository.PostRepository;
import com.entreprise.socialnetwork.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class LikeService {
    @Autowired
    private LikePostRepository likePostRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PostRepository postRepository;

    public String likePost(int postId, int userId) {
        User user = userRepository.findById(userId).orElse(null);
        Post post = postRepository.findById(postId).orElse(null);

        if (user == null || post == null) {
            return "Utilisateur ou post introuvable !";
        }

        if(likePostRepository.findByUserAndPost(user, post).isPresent()) {
            return "Vous avez déjà liké ce post.";
        }

        LikePost like = new LikePost();
        like.setUser(user);
        like.setPost(post);
        likePostRepository.save(like);

        return "Post liké avec succès";
    }

    public int getLikesCount(int postId) {
        Post post = postRepository.findById(postId).orElse(null);
        if (post == null) return 0;
        return likePostRepository.findByPost(post).size();
    }

    public List<User> getUsersWhoLiked(int postId) {
        Post post = postRepository.findById(postId).orElse(null);
        if (post == null) return List.of();

        return likePostRepository.findByPost(post).stream()
                .map(LikePost::getUser)
                .toList();
    }
}
