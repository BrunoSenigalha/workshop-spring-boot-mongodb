package com.brunosenigalha.workshopmongo.services;

import com.brunosenigalha.workshopmongo.domain.User;
import com.brunosenigalha.workshopmongo.repositories.UserRepository;
import com.brunosenigalha.workshopmongo.services.exception.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository repository;

    public List<User> findAll() {
        return repository.findAll();
    }

    public User findById(String id){
        Optional<User> obj = repository.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException("Invalid id"));
    }
}
