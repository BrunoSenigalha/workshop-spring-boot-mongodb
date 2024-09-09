package com.brunosenigalha.workshopmongo.services;

import com.brunosenigalha.workshopmongo.domain.User;
import com.brunosenigalha.workshopmongo.dto.UserDTO;
import com.brunosenigalha.workshopmongo.repositories.UserRepository;
import com.brunosenigalha.workshopmongo.services.exception.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
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

    public User findById(String id) {
        Optional<User> obj = repository.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException(id + " Not found"));
    }

    public User insert(User obj) {
        return repository.insert(obj);
    }

    public void delete(String id) {
        User obj = repository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException(id + " Not found"));
        repository.delete(obj);
    }

    public User update(User obj) {
        return repository.findById(obj.getId())
                .map(entity -> {
                    updateDate(entity, obj);
                    return repository.save(entity);
                })
                .orElseThrow(() -> new ObjectNotFoundException(obj.getId() + " Not found"));
//        User entity = repository.findById(obj.getId())
//                .orElseThrow(() -> new ObjectNotFoundException(obj.getId() + " Not found"));
//
    }

    public User fromDTO(UserDTO objDto) {
        return new User(objDto.getId(), objDto.getName(), objDto.getEmail());
    }

    private void updateDate(User entity, User obj) {
        entity.setName(obj.getName());
        entity.setEmail(obj.getEmail());
    }
}
