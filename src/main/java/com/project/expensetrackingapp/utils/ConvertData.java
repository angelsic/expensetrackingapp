package com.project.expensetrackingapp.utils;

import com.project.expensetrackingapp.repository.entity.role.UserRole;

import java.util.List;
import java.util.Set;

public interface ConvertData {
    Set<UserRole> mergeRolesByName (List<UserRole> lRoles, Set<UserRole> obj2);
}
