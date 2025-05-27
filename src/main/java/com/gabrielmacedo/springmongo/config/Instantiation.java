package com.gabrielmacedo.springmongo.config;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import com.gabrielmacedo.springmongo.domain.Post;
import com.gabrielmacedo.springmongo.domain.User;
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
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy MM dd HH:mm:ss");
		userRepository.deleteAll();
		postRepository.deleteAll();
		
		User maria = new User(null, "Maria Brown", "maria@gmail.com");
		User alex = new User(null, "Alex Green", "alex@gmail.com");
		User bob = new User(null, "Bob Grey", "bob@gmail.com");
		
		Post p1 = new Post(null, Instant.now(), "Bem vindo", "Ola meus amigos!", maria);
		Post p2 = new Post(null, Instant.now(), "Cabelo", "Cortei o cabelo, gostarum?", maria);
		
		userRepository.saveAll(Arrays.asList(maria, alex, bob));
		postRepository.saveAll(Arrays.asList(p1, p2));
	}

}
