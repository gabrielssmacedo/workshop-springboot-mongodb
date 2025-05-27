package com.gabrielmacedo.springmongo.config;

import java.time.Instant;
import java.util.Arrays;
import java.util.Collections;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import com.gabrielmacedo.springmongo.domain.Post;
import com.gabrielmacedo.springmongo.domain.User;
import com.gabrielmacedo.springmongo.dto.AuthorDTO;
import com.gabrielmacedo.springmongo.repository.PostRepository;
import com.gabrielmacedo.springmongo.repository.UserRepository;

@Configuration
public class Instantiation implements CommandLineRunner{

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PostRepository postRepository;
	
	@Override
	public void run(String... args) throws Exception {
		userRepository.deleteAll();
		postRepository.deleteAll();
		
		User maria = new User(null, "Maria Brown", "maria@gmail.com");
		User alex = new User(null, "Alex Green", "alex@gmail.com");
		User bob = new User(null, "Bob Grey", "bob@gmail.com");
		
		userRepository.saveAll(Arrays.asList(maria, alex, bob));
		
		Post p1 = new Post(null, Instant.now(), "Bem vindo", "Ola meus amigos!", new AuthorDTO(maria));
		Post p2 = new Post(null, Instant.now(), "Cabelo", "Cortei o cabelo, gostarum?", new AuthorDTO(maria));
		
		postRepository.saveAll(Arrays.asList(p1, p2));
		
		maria.getPosts().add(p1);
		maria.getPosts().add(p2);
		userRepository.save(maria);
	}

}
