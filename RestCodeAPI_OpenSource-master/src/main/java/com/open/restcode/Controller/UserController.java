package com.open.restcode.Controller;


import com.open.restcode.Domain.Model.User;
import com.open.restcode.Domain.Repository.UserRepository;
import com.open.restcode.Domain.Service.UserService;
import com.open.restcode.Resource.SaveUserResource;
import com.open.restcode.Resource.UserResource;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.ModelMap;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@Tag(name = "USER",description = "RestCode API")
@RestController
@RequestMapping("/RestCode")
public class UserController {

    @Autowired
    private ModelMapper mapper;

    @Autowired
    private UserService userService;

    private User convertToEntity(SaveUserResource resource) {

        return mapper.map(resource, User.class);
    }

    private UserResource convertToResource(User entity) {

        return mapper.map(entity, UserResource.class);
    }

    @GetMapping("/userAll")
    public Page<UserResource> getAllUser(Pageable pageable) {
        Page<User> users = userService.getAllUsers(pageable);
        List<UserResource> resources = users.getContent()
                .stream().map(this::convertToResource)
                .collect(Collectors.toList());
        return new PageImpl<>(resources, pageable, resources.size());
    }



    @GetMapping("/user/{id}")
    public UserResource getUserById(@PathVariable(name = "id") int  id) {
        return convertToResource(userService.getUserById(id));
    }


    @PostMapping("/user")
    public UserResource createUser(@Valid @RequestBody SaveUserResource resource){
        User user=convertToEntity(resource);
        return convertToResource(userService.createdUse(user));
    }

    @DeleteMapping("/user/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable(name = "id") int  idUser) {

        return userService.deleteUser(idUser);
    }

}
