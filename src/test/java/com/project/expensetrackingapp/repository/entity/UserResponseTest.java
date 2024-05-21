package com.project.expensetrackingapp.repository.entity;

import com.project.expensetrackingapp.repository.entity.role.UserRole;
import com.project.expensetrackingapp.repository.entity.user.UserResponse;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import java.util.HashSet;
import java.util.Set;

public class UserResponseTest {

    @Test
    void testToString() {
        Set<UserRole> roles = new HashSet<>();
        roles.add(new UserRole(1L, "ADMIN"));

        UserResponse userResponse = new UserResponse(1L, "testUser", roles);

        String expectedToString = "UserResponse(id=1, username=testUser, roles=[UserRole(id=1, name=ADMIN)])";
        assertThat(userResponse.toString()).isEqualTo(expectedToString);
    }

    @Test
    void testEqualsWithSameObject() {
        Set<UserRole> roles = new HashSet<>();
        roles.add(new UserRole(1L, "ADMIN"));

        UserResponse userResponse = new UserResponse(1L, "testUser", roles);

        assertThat(userResponse).isEqualTo(userResponse);
    }

    @Test
    void testEqualsWithDifferentType() {
        Set<UserRole> roles = new HashSet<>();
        roles.add(new UserRole(1L, "ADMIN"));

        UserResponse userResponse = new UserResponse(1L, "testUser", roles);

        assertThat(userResponse).isNotEqualTo(new Object());
    }

    @Test
    void testEqualsWithNull() {
        Set<UserRole> roles = new HashSet<>();
        roles.add(new UserRole(1L, "ADMIN"));

        UserResponse userResponse = new UserResponse(1L, "testUser", roles);

        assertThat(userResponse).isNotEqualTo(null);
    }

    @Test
    void testEqualsWithDifferentValues() {
        Set<UserRole> roles1 = new HashSet<>();
        roles1.add(new UserRole(1L, "ADMIN"));

        Set<UserRole> roles2 = new HashSet<>();
        roles2.add(new UserRole(2L, "USER"));

        UserResponse userResponse1 = new UserResponse(1L, "testUser1", roles1);
        UserResponse userResponse2 = new UserResponse(2L, "testUser2", roles2);

        assertThat(userResponse1).isNotEqualTo(userResponse2);
    }

    @Test
    void testHashCodeForEqualObjects() {
        Set<UserRole> roles1 = new HashSet<>();
        roles1.add(new UserRole(1L, "ADMIN"));

        Set<UserRole> roles2 = new HashSet<>();
        roles2.add(new UserRole(1L, "ADMIN"));

        UserResponse userResponse1 = new UserResponse(1L, "testUser", roles1);
        UserResponse userResponse2 = new UserResponse(1L, "testUser", roles2);

        assertThat(userResponse1.hashCode()).isEqualTo(userResponse2.hashCode());
    }

    @Test
    void testHashCodeForDifferentObjects() {
        Set<UserRole> roles1 = new HashSet<>();
        roles1.add(new UserRole(1L, "ADMIN"));

        Set<UserRole> roles2 = new HashSet<>();
        roles2.add(new UserRole(2L, "USER"));

        UserResponse userResponse1 = new UserResponse(1L, "testUser1", roles1);
        UserResponse userResponse2 = new UserResponse(2L, "testUser2", roles2);

        assertThat(userResponse1.hashCode()).isNotEqualTo(userResponse2.hashCode());
    }

    @Test
    void testHashCodeForNull() {
        Set<UserRole> roles = new HashSet<>();
        roles.add(new UserRole(1L, "ADMIN"));

        UserResponse userResponse = new UserResponse(1L, "testUser", roles);

        assertThat(userResponse.hashCode()).isNotEqualTo(null);
    }

    @Test
    void testSetters() {
        Set<UserRole> roles = new HashSet<>();
        roles.add(new UserRole(1L, "ADMIN"));

        UserResponse userResponse = new UserResponse();
        userResponse.setId(1L);
        userResponse.setUsername("testUser");
        userResponse.setRoles(roles);

        assertThat(userResponse.getId()).isEqualTo(1L);
        assertThat(userResponse.getUsername()).isEqualTo("testUser");
        assertThat(userResponse.getRoles()).isEqualTo(roles);
    }

    @Test
    void testEquals() {
        Set<UserRole> roles1 = new HashSet<>();
        roles1.add(new UserRole(1L, "ADMIN"));

        Set<UserRole> roles2 = new HashSet<>();
        roles2.add(new UserRole(1L, "ADMIN"));

        UserResponse userResponse1 = new UserResponse();
        userResponse1.setId(1L);
        userResponse1.setUsername("testUser");
        userResponse1.setRoles(roles1);

        UserResponse userResponse2 = new UserResponse();
        userResponse2.setId(1L);
        userResponse2.setUsername("testUser");
        userResponse2.setRoles(roles2);

        assertThat(userResponse1).isEqualTo(userResponse2);
    }

    @Test
    void testNotEquals() {
        Set<UserRole> roles1 = new HashSet<>();
        roles1.add(new UserRole(1L, "ADMIN"));

        Set<UserRole> roles2 = new HashSet<>();
        roles2.add(new UserRole(2L, "USER"));

        UserResponse userResponse1 = new UserResponse();
        userResponse1.setId(1L);
        userResponse1.setUsername("testUser1");
        userResponse1.setRoles(roles1);

        UserResponse userResponse2 = new UserResponse();
        userResponse2.setId(2L);
        userResponse2.setUsername("testUser2");
        userResponse2.setRoles(roles2);

        assertThat(userResponse1).isNotEqualTo(userResponse2);
    }
}