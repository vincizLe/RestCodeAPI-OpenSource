package com.open.restcode.Domain.Service;

import com.open.restcode.Domain.Model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

public interface UserService {

    Page<User> getAllUsers(Pageable pageable);
    User getUserById(int id);
    User createdUse(User user);
    User getUserByName(String name);
    ResponseEntity<?> deleteUser(int id);
}
