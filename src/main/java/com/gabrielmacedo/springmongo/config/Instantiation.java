package com.gabrielmacedo.springmongo.config;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.TimeZone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import com.gabrielmacedo.springmongo.domain.Post;
import com.gabrielmacedo.springmongo.domain.User;
import com.gabrielmacedo.springmongo.dto.AuthorDTO;
import com.gabrielmacedo.springmongo.dto.CommentDTO;
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
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		sdf.setTimeZone(TimeZone.getTimeZone("GMT"));
		
		userRepository.deleteAll();
		postRepository.deleteAll();
		
		User maria = new User(null, "Maria Brown", "maria@gmail.com");
		User alex = new User(null, "Alex Green", "alex@gmail.com");
		User bob = new User(null, "Bob Grey", "bob@gmail.com");
		
		userRepository.saveAll(Arrays.asList(maria, alex, bob));
		
		Post p1 = new Post(null, sdf.parse("21/03/2025"), "Bem vindo", "Ola meus amigos!", new AuthorDTO(maria));
		Post p2 = new Post(null, sdf.parse("21/03/2025"), "Cabelo", "Cortei o cabelo, gostarum?", new AuthorDTO(maria));
		
		CommentDTO c1 = new CommentDTO("Nusssaaa!", sdf.parse("21/03/2025"), new AuthorDTO(alex));
		CommentDTO c2 = new CommentDTO("Gostei", sdf.parse("21/03/2025"), new AuthorDTO(bob));
		CommentDTO c3 = new CommentDTO("Ruim dms", sdf.parse("21/03/2025"), new AuthorDTO(alex));
		
		p1.getComments().addAll(Arrays.asList(c1, c2));
		p2.getComments().add(c3);
		
		postRepository.saveAll(Arrays.asList(p1, p2));
		
		maria.getPosts().add(p1);
		maria.getPosts().add(p2);
		userRepository.save(maria);
	}

}
