package com.example.blog.controller;

import com.example.blog.model.Comment;
import com.example.blog.model.Post;
import com.example.blog.model.User;
import com.example.blog.service.CommentService;
import com.example.blog.service.PostService;
import com.example.blog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/comments")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @Autowired
    private PostService postService;

    @Autowired
    private UserService userService;

    @PostMapping("/new")
    public String createComment(@RequestParam Long postId, @RequestParam String content) {
        Post post = postService.getPost(postId);
        User user = userService.getCurrentUser();
        Comment comment = new Comment();
        comment.setPost(post);
        comment.setUser(user);
        comment.setContent(content);
        commentService.saveComment(comment);

        // Уведомление автора поста (CustomObserver)
        post.notifyObservers("New comment on your post: " + comment.getContent());

        return "redirect:/posts/" + postId;
    }
}
