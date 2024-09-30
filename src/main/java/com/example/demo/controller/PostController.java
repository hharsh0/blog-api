package com.example.demo.controller;

import com.example.demo.model.Post;
import com.example.demo.model.dto.app.PostDTO;
import com.example.demo.service.PostService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/users/{userId}/posts")
public class PostController {

    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping
    public ResponseEntity<List<PostDTO>> getPostsByUser(@PathVariable UUID userId) {
        List<PostDTO> posts = postService.getPostsByUser(userId);
        return ResponseEntity.ok(posts);
    }

    @PostMapping
    public ResponseEntity<PostDTO> createPost(@PathVariable UUID userId, @RequestBody PostDTO postDTO) {
        PostDTO createdPost = postService.createPost(userId, convertToPost(postDTO));
        return ResponseEntity.ok(createdPost);
    }

    private Post convertToPost(PostDTO postDTO) {
        Post post = new Post();
        post.setTitle(postDTO.getTitle());
        post.setContent(postDTO.getContent());
        post.setPublished(postDTO.getPublished());
        return post;
    }
}

