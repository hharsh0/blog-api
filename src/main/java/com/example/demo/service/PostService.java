package com.example.demo.service;


import com.example.demo.model.Post;
import com.example.demo.model.User;
import com.example.demo.model.dto.app.PostDTO;
import com.example.demo.repository.PostRepository;
import com.example.demo.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class PostService {

    private final PostRepository postRepository;
    private final UserRepository userRepository;

    public PostService(PostRepository postRepository, UserRepository userRepository) {
        this.postRepository = postRepository;
        this.userRepository = userRepository;
    }

    public List<PostDTO> getPostsByUser(UUID userId) {
        return postRepository.findByUserId(userId).stream()
                .map(this::convertToPostDTO)
                .collect(Collectors.toList());
    }

    public PostDTO createPost(UUID userId, Post post) {
        Optional<User> user = userRepository.findById(userId);
        if (user.isPresent()) {
            post.setUser(user.get());
            Post savedPost = postRepository.save(post);
            return convertToPostDTO(savedPost);
        } else {
            throw new RuntimeException("User not found");
        }
    }

    private PostDTO convertToPostDTO(Post post) {
        PostDTO dto = new PostDTO();
        dto.setId(post.getId());
        dto.setTitle(post.getTitle());
        dto.setContent(post.getContent());
        dto.setPublished(post.getPublished());
        return dto;
    }
}
