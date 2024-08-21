package com.example.blog.service;

import com.example.blog.model.Comment;
import com.example.blog.model.Post;
import com.example.blog.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentService {

    @Autowired
    private CommentRepository commentRepository;

    public List<Comment> findAllByPost(Post post) {
        return commentRepository.findAllByPost(post);
    }

    public Comment saveComment(Comment comment) {
        return commentRepository.save(comment);
    }
}