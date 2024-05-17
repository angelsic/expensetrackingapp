package com.project.expensetrackingapp.service.user;

import com.project.expensetrackingapp.exception.user.PasswordNotFound;
import com.project.expensetrackingapp.exception.user.UserAlreadyExist;
import com.project.expensetrackingapp.exception.user.UserNotExist;
import com.project.expensetrackingapp.exception.user.UsernameNotFound;
import com.project.expensetrackingapp.repository.role.UserRoleDatabaseStrategy;
import com.project.expensetrackingapp.repository.user.UserDatabaseStrategy;
import com.project.expensetrackingapp.repository.entity.user.User;
import com.project.expensetrackingapp.repository.entity.user.UserRequest;
import com.project.expensetrackingapp.repository.entity.user.UserResponse;
import com.project.expensetrackingapp.repository.entity.role.UserRole;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.Type;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService{

    private UserDatabaseStrategy userDatabaseStrategy;

    private UserRoleDatabaseStrategy userRoleDatabaseStrategy;

    @Autowired
    public void setDatabaseStrategy(@Qualifier("userStrategy") UserDatabaseStrategy userDatabaseStrategy){
        this.userDatabaseStrategy = userDatabaseStrategy;
    }

    @Autowired
    public void setRolDatabaseStrategy(@Qualifier("roleStrategy") UserRoleDatabaseStrategy userRoleDatabaseStrategy){
        this.userRoleDatabaseStrategy = userRoleDatabaseStrategy;
    }

    ModelMapper modelMapper = new ModelMapper();

    @Override
    @Transactional
    public UserResponse saveUser(UserRequest userRequest) {
        if(userRequest.getUsername() == null){
            throw new UsernameNotFound();
        } else if(userRequest.getPassword() == null){
            throw new PasswordNotFound();
        }

        User newUser = userDatabaseStrategy.findByUsername(userRequest.getUsername());
        if(newUser == null){
            BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
            String getPass = userRequest.getPassword();
            String encodePass = encoder.encode(getPass);
            User user = modelMapper.map(userRequest, User.class);
            List<UserRole> allRoles = userRoleDatabaseStrategy.findAll();
            Set<UserRole> managedRoles = new HashSet<>();
            for (UserRole role : user.getRoles()) {
                UserRole managedRole = allRoles.stream()
                        .filter(r -> r.getName().equals(role.getName()))
                        .findFirst()
                        .orElse(userRoleDatabaseStrategy.save(role));
                managedRoles.add(managedRole);
            }
            user.setRoles(managedRoles);
            user.setPassword(encodePass);
            User savedUser = userDatabaseStrategy.save(user);
            return modelMapper.map(savedUser, UserResponse.class);
        }
        throw new UserAlreadyExist(newUser.getUsername());
    }

    @Override
    public UserResponse getUser(String username) {
        User user = userDatabaseStrategy.findByUsername(username);
        return modelMapper.map(user, UserResponse.class);
    }

    @Override
    public List<UserResponse> getAllUser() {
        List<User> users = userDatabaseStrategy.findAll();
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
        User updUser = userDatabaseStrategy.findByUsername(userRequest.getUsername());

        if(updUser != null){
            BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
            String getPass = userRequest.getPassword();
            String encodePass = encoder.encode(getPass);
            updUser.setPassword(encodePass);
            updUser.setUsername(user.getUsername());
            List<UserRole> allRoles = userRoleDatabaseStrategy.findAll();
            Set<UserRole> managedRoles = new HashSet<>();
            for (UserRole role : user.getRoles()) {
                UserRole managedRole = allRoles.stream()
                        .filter(r -> r.getName().equals(role.getName()))
                        .findFirst()
                        .orElse(userRoleDatabaseStrategy.save(role));
                managedRoles.add(managedRole);
            }
            updUser.setRoles(managedRoles);
            User usr = userDatabaseStrategy.save(updUser);
            return modelMapper.map(usr, UserResponse.class);
        }else {
            throw  new UserNotExist(userRequest.getUsername());
        }
    }

    @Override
    public String deleteUser(String username) {
        User user = userDatabaseStrategy.findByUsername(username);
        if(user != null) {
            userDatabaseStrategy.deleteById(user.getId());
            return username + " was removed";
        }else{
            return username + " not exist";
        }
    }
}
