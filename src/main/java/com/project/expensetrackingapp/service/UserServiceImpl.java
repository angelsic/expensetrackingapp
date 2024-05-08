package com.project.expensetrackingapp.service;

import com.project.expensetrackingapp.exception.user.PasswordNotFound;
import com.project.expensetrackingapp.exception.user.UserAlreadyExist;
import com.project.expensetrackingapp.exception.user.UserNotExist;
import com.project.expensetrackingapp.exception.user.UsernameNotFound;
import com.project.expensetrackingapp.repository.UserRepository;
import com.project.expensetrackingapp.repository.UserRoleRepository;
import com.project.expensetrackingapp.repository.entity.User;
import com.project.expensetrackingapp.repository.entity.UserRequest;
import com.project.expensetrackingapp.repository.entity.UserResponse;
import com.project.expensetrackingapp.repository.entity.UserRole;
import com.project.expensetrackingapp.utils.ConvertData;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static java.util.Map.entry;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserRoleRepository userRoleRepository;

    @Autowired
    ConvertData convertData;

    ModelMapper modelMapper = new ModelMapper();

    @Transactional
    @Override
    public UserResponse saveUser(UserRequest userRequest) {
        if(userRequest.getUsername() == null){
            throw new UsernameNotFound();
        } else if(userRequest.getPassword() == null){
            throw new PasswordNotFound();
        }

        User newUser = userRepository.findByUsername(userRequest.getUsername());
        if(newUser == null){
            User user = modelMapper.map(userRequest, User.class);
            User savedUser = userRepository.save(user);
            return modelMapper.map(savedUser, UserResponse.class);
        }
        throw new UserAlreadyExist(newUser.getUsername());
    }

    @Override
    public UserResponse getUser(String username) {
        User user = userRepository.findByUsername(username);
        return modelMapper.map(user, UserResponse.class);
    }

    @Override
    public List<UserResponse> getAllUser() {
        List<User> users = (List<User>) userRepository.findAll();
        Type setOfDTOsType = new TypeToken<List<UserResponse>>(){}.getType();
        return modelMapper.map(users, setOfDTOsType);
    }

    @Override
    public UserResponse updateUser(UserRequest userRequest) {
        if(userRequest.getUsername() == null){
            throw new UsernameNotFound();
        } else if(userRequest.getPassword() == null){
            throw new PasswordNotFound();
        }
        User user = modelMapper.map(userRequest, User.class);
        User updUser = userRepository.findByUsername(userRequest.getUsername());

        if(updUser != null){
            List<UserRole> lRoles = (List<UserRole>) userRoleRepository.findAll();

            updUser.setPassword(user.getPassword());
            updUser.setUsername(user.getUsername());
            updUser.setRoles(convertData.mergeRolesByName(lRoles, user.getRoles()));
            User usr = userRepository.save(updUser);
            return modelMapper.map(usr, UserResponse.class);
        }else {
            throw  new UserNotExist(userRequest.getUsername());
        }
    }

    @Override
    public String deleteUser(String username) {
        User user = userRepository.findByUsername(username);
        if(user != null) {
            userRepository.deleteById(user.getId());
            return username + " was removed";
        }else{
            return username + " not exist";
        }
    }
}
