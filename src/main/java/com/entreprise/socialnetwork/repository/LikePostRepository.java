package com.entreprise.socialnetwork.repository;

import com.entreprise.socialnetwork.model.LikePost;
import com.entreprise.socialnetwork.model.Post;
import com.entreprise.socialnetwork.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface LikePostRepository extends JpaRepository<LikePost, Integer> {
    Optional<LikePost> findByUserAndPost(User user, Post post);
    List<LikePost> findByPost(Post post);
}
