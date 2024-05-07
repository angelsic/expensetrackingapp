package com.project.expensetrackingapp.service;

import com.project.expensetrackingapp.exception.user.PasswordNotFound;
import com.project.expensetrackingapp.exception.user.UserAlreadyExist;
import com.project.expensetrackingapp.exception.user.UserNotExist;
import com.project.expensetrackingapp.exception.user.UsernameNotFound;
import com.project.expensetrackingapp.repository.entity.User;
import com.project.expensetrackingapp.repository.entity.UserRequest;
import com.project.expensetrackingapp.repository.entity.UserResponse;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Service;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static java.util.Map.entry;

@Service
public class UserServiceImpl implements UserService{

    ModelMapper modelMapper = new ModelMapper();

    private HashMap<String, User> users = new HashMap<String, User>(){
        {
            put("admin", new User(1, "admin", "123456"));
            put("asic", new User(2, "asic", "123456"));
            put("edu", new User(3, "edu", "123456"));
            put("emora", new User(4, "emora", "123456"));
            put("aliz", new User(5, "aliz", "123456"));
        }};

    @Override
    public UserResponse saveUser(UserRequest userRequest) {
        if(userRequest.getUsername() == null){
            throw new UsernameNotFound();
        }else if(userRequest.getPassword() == null){
            throw new PasswordNotFound();
        }
        if(users.get(userRequest.getUsername()) != null) {
            throw new UserAlreadyExist(userRequest.getUsername());
        }

        User newUser = User.builder()
                .id(users.size()+1)
                .username(userRequest.getUsername())
                .password(userRequest.getPassword())
                .build();
        users.put(userRequest.getUsername(), newUser);
        return modelMapper.map(newUser, UserResponse.class);
    }

    @Override
    public UserResponse getUser() {
        String firstKey = users.keySet().stream().findFirst().get();
        return new UserResponse(users.get(firstKey).getId(), users.get(firstKey).getUsername());
    }

    @Override
    public List<UserResponse> getAllUser() {
        return users.values()
                .stream()
                .map(user -> new UserResponse(user.getId(), user.getUsername()))
                .collect(Collectors.toList());
    }

    @Override
    public UserResponse updateUser(UserRequest userRequest) {
        if(users.get(userRequest.getUsername()) != null) {
            User updUser = users.get(userRequest.getUsername());
            updUser.setUsername(userRequest.getUsername());
            updUser.setPassword(userRequest.getPassword());
            users.put(userRequest.getUsername(), updUser);
            return modelMapper.map(updUser, UserResponse.class);
        }else{
            throw new UserNotExist(userRequest.getUsername());
        }
    }

    @Override
    public String deleteUser(String username) {
        if(users.get(username) != null) {
            users.remove(username);
            return username + " was removed";
        }else{
            return username + " not exist";
        }
    }
}
