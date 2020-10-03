package com.open.restcode.Service;

import com.open.restcode.Domain.Model.User;
import com.open.restcode.Domain.Repository.UserRepository;
import com.open.restcode.Domain.Service.UserService;
import com.open.restcode.Exeptions.ResourceNotFoundExeption;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImplement implements UserService {

    @Autowired
    private UserRepository userRepository;



    @Override
    public Page<User> getAllUsers(Pageable pageable) {

        return userRepository.findAll(pageable);
    }

    @Override
    public User getUserById(int id) {
        return userRepository.findById(id)
                .orElseThrow(()->new ResourceNotFoundExeption(
                        "User"
                ));
    }

    @Override
    public User createdUse(User user) {
        return userRepository.save(user);
    }

    @Override
    public User getUserByName(String name) {
        return userRepository.findByName(name)
                .orElseThrow(()->
                        new ResourceNotFoundExeption(
                                "User","Name",name
                        ));
    }

    @Override
    public ResponseEntity<?> deleteUser(int  userId) {
        return userRepository.findById(userId).map(user -> {
            userRepository.delete(user);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new ResourceNotFoundExeption(
                "User"));
    }

}
