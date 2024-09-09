package com.brunosenigalha.workshopmongo.config;

import com.brunosenigalha.workshopmongo.domain.Post;
import com.brunosenigalha.workshopmongo.domain.User;
import com.brunosenigalha.workshopmongo.dto.AuthorDTO;
import com.brunosenigalha.workshopmongo.repositories.PostRepository;
import com.brunosenigalha.workshopmongo.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.TimeZone;

@Configuration
public class Instantiation implements CommandLineRunner {

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


        Post post1 = new Post(null, LocalDate.of(2018, 3, 21),
                "Partiu Viagem", "Vou viajar para São Paulo", new AuthorDTO(maria));
        Post post2 = new Post(null, LocalDate.of(2018, 3, 23),
                "Bom dia", "Vizinho barulhento!", new AuthorDTO(maria));
        postRepository.saveAll(Arrays.asList(post1, post2));

        maria.getPosts().addAll(Arrays.asList(post1, post1));
        userRepository.save(maria);
    }
}
