package com.entreprise.socialnetwork.repository;

import com.entreprise.socialnetwork.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Integer> {
}
