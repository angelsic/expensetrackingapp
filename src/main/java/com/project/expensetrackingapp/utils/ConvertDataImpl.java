package com.project.expensetrackingapp.utils;

import com.project.expensetrackingapp.repository.entity.role.UserRole;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
public class ConvertDataImpl implements ConvertData{

    public ConvertDataImpl(){

    }

    public Set<UserRole> mergeRolesByName (List<UserRole> lRoles, Set<UserRole> obj2){
        Set<UserRole> obj1 = new HashSet<UserRole>(lRoles);
        Set<UserRole> roles = new HashSet<>();
        for (UserRole lst2 : obj2){
            long id = 0;
            UserRole rol = new UserRole();
            for (UserRole lst1: obj1){
                if (lst1.getName().equalsIgnoreCase(lst2.getName())){
                    id = lst1.getId();
                }
            }
            rol.setId(id);
            rol.setName(lst2.getName());
            roles.add(rol);
        }
        return roles;

    }
}
