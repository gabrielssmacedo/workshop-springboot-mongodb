package com.gabrielmacedo.springmongo.services;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gabrielmacedo.springmongo.domain.Post;
import com.gabrielmacedo.springmongo.repository.PostRepository;
import com.gabrielmacedo.springmongo.services.exception.ObjectNotFoundException;

@Service
public class PostService {
	
	@Autowired
	private PostRepository postRepository;
	
	public List<Post> findAll() {
		return postRepository.findAll();
	}
	
	public Post findById(String id) {
		Optional<Post> post = postRepository.findById(id);
		return post.orElseThrow(() -> new ObjectNotFoundException("Object not found"));
	}
	
	public List<Post> findByTitle(String text) {
		List<Post> post = postRepository.searchTitle(text);
		if(post.isEmpty()) {
			throw new ObjectNotFoundException("Object not found");
		}
		return post;
	}
	
	public List<Post> fullSearch(String text, java.util.Date min, java.util.Date max) {
		max = new Date(max.getTime() + 24 * 60 * 60 * 1000);
		return postRepository.fullSearch(text, min, max);
	}
	
}
