package com.project.expensetrackingapp.repository.entity;

import com.project.expensetrackingapp.repository.entity.role.UserRole;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
public class UserRoleTest {

    @Test
    void testToString() {
        UserRole userRole = new UserRole(1L, "ADMIN");
        String expectedToString = "UserRole(id=1, name=ADMIN)";
        assertThat(userRole.toString()).isEqualTo(expectedToString);
    }

    @Test
    void testEqualsWithSameObject() {
        UserRole userRole = new UserRole(1L, "ADMIN");
        assertThat(userRole).isEqualTo(userRole);
    }

    @Test
    void testEqualsWithDifferentType() {
        UserRole userRole = new UserRole(1L, "ADMIN");
        assertThat(userRole).isNotEqualTo(new Object());
    }

    @Test
    void testEqualsWithNull() {
        UserRole userRole = new UserRole(1L, "ADMIN");
        assertThat(userRole).isNotEqualTo(null);
    }

    @Test
    void testEqualsWithDifferentValues() {
        UserRole userRole1 = new UserRole(1L, "ADMIN");
        UserRole userRole2 = new UserRole(2L, "USER");
        assertThat(userRole1).isNotEqualTo(userRole2);
    }

    @Test
    void testHashCodeForEqualObjects() {
        UserRole userRole1 = new UserRole(1L, "ADMIN");
        UserRole userRole2 = new UserRole(1L, "ADMIN");
        assertThat(userRole1.hashCode()).isEqualTo(userRole2.hashCode());
    }

    @Test
    void testHashCodeForDifferentObjects() {
        UserRole userRole1 = new UserRole(1L, "ADMIN");
        UserRole userRole2 = new UserRole(2L, "USER");
        assertThat(userRole1.hashCode()).isNotEqualTo(userRole2.hashCode());
    }

    @Test
    void testHashCodeForNull() {
        UserRole userRole = new UserRole(1L, "ADMIN");
        assertThat(userRole.hashCode()).isNotEqualTo(null);
    }
}
