package com.project.expensetrackingapp.repository.entity;

import com.project.expensetrackingapp.repository.entity.role.UserRole;
import com.project.expensetrackingapp.repository.entity.user.UserRequest;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import java.util.HashSet;
import java.util.Set;

public class UserRequestTest {

    @Test
    void testToString() {
        Set<UserRole> roles = new HashSet<>();
        roles.add(new UserRole(1L, "ADMIN"));

        UserRequest userRequest = new UserRequest(1L, "testUser", "testPassword", roles);

        String expectedToString = "UserRequest(id=1, username=testUser, password=testPassword, roles=[UserRole(id=1, name=ADMIN)])";
        assertThat(userRequest.toString()).isEqualTo(expectedToString);
    }

    @Test
    void testEqualsWithSameObject() {
        Set<UserRole> roles = new HashSet<>();
        roles.add(new UserRole(1L, "ADMIN"));

        UserRequest userRequest = new UserRequest(1L, "testUser", "testPassword", roles);

        assertThat(userRequest).isEqualTo(userRequest);
    }

    @Test
    void testEqualsWithDifferentType() {
        Set<UserRole> roles = new HashSet<>();
        roles.add(new UserRole(1L, "ADMIN"));

        UserRequest userRequest = new UserRequest(1L, "testUser", "testPassword", roles);

        assertThat(userRequest).isNotEqualTo(new Object());
    }

    @Test
    void testEqualsWithNull() {
        Set<UserRole> roles = new HashSet<>();
        roles.add(new UserRole(1L, "ADMIN"));

        UserRequest userRequest = new UserRequest(1L, "testUser", "testPassword", roles);

        assertThat(userRequest).isNotEqualTo(null);
    }

    @Test
    void testEqualsWithDifferentValues() {
        Set<UserRole> roles1 = new HashSet<>();
        roles1.add(new UserRole(1L, "ADMIN"));

        Set<UserRole> roles2 = new HashSet<>();
        roles2.add(new UserRole(2L, "USER"));

        UserRequest userRequest1 = new UserRequest(1L, "testUser1", "testPassword1", roles1);
        UserRequest userRequest2 = new UserRequest(2L, "testUser2", "testPassword2", roles2);

        assertThat(userRequest1).isNotEqualTo(userRequest2);
    }

    @Test
    void testHashCodeForEqualObjects() {
        Set<UserRole> roles1 = new HashSet<>();
        roles1.add(new UserRole(1L, "ADMIN"));

        Set<UserRole> roles2 = new HashSet<>();
        roles2.add(new UserRole(1L, "ADMIN"));

        UserRequest userRequest1 = new UserRequest(1L, "testUser", "testPassword", roles1);
        UserRequest userRequest2 = new UserRequest(1L, "testUser", "testPassword", roles2);

        assertThat(userRequest1.hashCode()).isEqualTo(userRequest2.hashCode());
    }

    @Test
    void testHashCodeForDifferentObjects() {
        Set<UserRole> roles1 = new HashSet<>();
        roles1.add(new UserRole(1L, "ADMIN"));

        Set<UserRole> roles2 = new HashSet<>();
        roles2.add(new UserRole(2L, "USER"));

        UserRequest userRequest1 = new UserRequest(1L, "testUser1", "testPassword1", roles1);
        UserRequest userRequest2 = new UserRequest(2L, "testUser2", "testPassword2", roles2);

        assertThat(userRequest1.hashCode()).isNotEqualTo(userRequest2.hashCode());
    }

    @Test
    void testHashCodeForNull() {
        Set<UserRole> roles = new HashSet<>();
        roles.add(new UserRole(1L, "ADMIN"));

        UserRequest userRequest = new UserRequest(1L, "testUser", "testPassword", roles);

        assertThat(userRequest.hashCode()).isNotEqualTo(null);
    }

    @Test
    void testSetters() {
        Set<UserRole> roles = new HashSet<>();
        roles.add(new UserRole(1L, "ADMIN"));

        UserRequest userRequest = new UserRequest();
        userRequest.setId(1L);
        userRequest.setUsername("testUser");
        userRequest.setPassword("testPassword");
        userRequest.setRoles(roles);

        assertThat(userRequest.getId()).isEqualTo(1L);
        assertThat(userRequest.getUsername()).isEqualTo("testUser");
        assertThat(userRequest.getPassword()).isEqualTo("testPassword");
        assertThat(userRequest.getRoles()).isEqualTo(roles);
    }

    @Test
    void testEquals() {
        Set<UserRole> roles1 = new HashSet<>();
        roles1.add(new UserRole(1L, "ADMIN"));

        Set<UserRole> roles2 = new HashSet<>();
        roles2.add(new UserRole(1L, "ADMIN"));

        UserRequest userRequest1 = new UserRequest();
        userRequest1.setId(1L);
        userRequest1.setUsername("testUser");
        userRequest1.setPassword("testPassword");
        userRequest1.setRoles(roles1);

        UserRequest userRequest2 = new UserRequest();
        userRequest2.setId(1L);
        userRequest2.setUsername("testUser");
        userRequest2.setPassword("testPassword");
        userRequest2.setRoles(roles2);

        assertThat(userRequest1).isEqualTo(userRequest2);
    }

    @Test
    void testNotEquals() {
        Set<UserRole> roles1 = new HashSet<>();
        roles1.add(new UserRole(1L, "ADMIN"));

        Set<UserRole> roles2 = new HashSet<>();
        roles2.add(new UserRole(2L, "USER"));

        UserRequest userRequest1 = new UserRequest();
        userRequest1.setId(1L);
        userRequest1.setUsername("testUser1");
        userRequest1.setPassword("testPassword1");
        userRequest1.setRoles(roles1);

        UserRequest userRequest2 = new UserRequest();
        userRequest2.setId(2L);
        userRequest2.setUsername("testUser2");
        userRequest2.setPassword("testPassword2");
        userRequest2.setRoles(roles2);

        assertThat(userRequest1).isNotEqualTo(userRequest2);
    }
}