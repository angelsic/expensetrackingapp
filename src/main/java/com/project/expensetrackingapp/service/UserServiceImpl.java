package com.project.expensetrackingapp.service;

import com.project.expensetrackingapp.exception.user.PasswordNotFound;
import com.project.expensetrackingapp.exception.user.UserAlreadyExist;
import com.project.expensetrackingapp.exception.user.UserNotExist;
import com.project.expensetrackingapp.exception.user.UsernameNotFound;
import com.project.expensetrackingapp.repository.DatabaseStrategy;
import com.project.expensetrackingapp.repository.UserRoleRepository;
import com.project.expensetrackingapp.repository.entity.User;
import com.project.expensetrackingapp.repository.entity.UserRequest;
import com.project.expensetrackingapp.repository.entity.UserResponse;
import com.project.expensetrackingapp.repository.entity.UserRole;
import com.project.expensetrackingapp.utils.ConvertData;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.Type;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService{

    private DatabaseStrategy databaseStrategy;

    @Autowired
    public void setDatabaseStrategy(@Qualifier("strategy") DatabaseStrategy databaseStrategy){
        this.databaseStrategy = databaseStrategy;
    }


    @Autowired
    UserRoleRepository userRoleRepository;

    @Autowired
    ConvertData convertData;

    ModelMapper modelMapper = new ModelMapper();

    @Override
    @Transactional
    public UserResponse saveUser(UserRequest userRequest) {
        if(userRequest.getUsername() == null){
            throw new UsernameNotFound();
        } else if(userRequest.getPassword() == null){
            throw new PasswordNotFound();
        }

        User newUser = databaseStrategy.findByUsername(userRequest.getUsername());
        if(newUser == null){

            User user = modelMapper.map(userRequest, User.class);
            List<UserRole> allRoles = (List<UserRole>) userRoleRepository.findAll();
            Set<UserRole> managedRoles = new HashSet<>();
            for (UserRole role : user.getRoles()) {
                UserRole managedRole = allRoles.stream()
                        .filter(r -> r.getName().equals(role.getName()))
                        .findFirst()
                        .orElse(role);
                managedRoles.add(managedRole);
            }
            user.setRoles(managedRoles);
            User savedUser = databaseStrategy.save(user);
            return modelMapper.map(savedUser, UserResponse.class);
        }
        throw new UserAlreadyExist(newUser.getUsername());
    }

    @Override
    public UserResponse getUser(String username) {
        User user = databaseStrategy.findByUsername(username);
        return modelMapper.map(user, UserResponse.class);
    }

    @Override
    public List<UserResponse> getAllUser() {
        List<User> users = (List<User>) databaseStrategy.findAll();
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
        User updUser = databaseStrategy.findByUsername(userRequest.getUsername());

        if(updUser != null){
            List<UserRole> lRoles = (List<UserRole>) userRoleRepository.findAll();

            updUser.setPassword(user.getPassword());
            updUser.setUsername(user.getUsername());
            updUser.setRoles(convertData.mergeRolesByName(lRoles, user.getRoles()));
            User usr = databaseStrategy.save(updUser);
            return modelMapper.map(usr, UserResponse.class);
        }else {
            throw  new UserNotExist(userRequest.getUsername());
        }
    }

    @Override
    public String deleteUser(String username) {
        User user = databaseStrategy.findByUsername(username);
        if(user != null) {
            databaseStrategy.deleteById(user.getId());
            return username + " was removed";
        }else{
            return username + " not exist";
        }
    }
}
