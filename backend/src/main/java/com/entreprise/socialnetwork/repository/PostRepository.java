package com.entreprise.socialnetwork.repository;

import com.entreprise.socialnetwork.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Integer> {
}
