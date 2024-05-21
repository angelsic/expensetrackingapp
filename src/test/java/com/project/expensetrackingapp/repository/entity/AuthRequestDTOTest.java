package com.project.expensetrackingapp.repository.entity;

import com.project.expensetrackingapp.repository.entity.auth.AuthRequestDTO;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class AuthRequestDTOTest {
    @Test
    void testConstructorAndGettersSetters(){
        AuthRequestDTO authRequestDTO = new AuthRequestDTO();
        authRequestDTO.setUsername("name");
        authRequestDTO.setPassword("pass");

        assertThat(authRequestDTO.getUsername()).isEqualTo("name");
        assertThat(authRequestDTO.getPassword()).isEqualTo("pass");
    }

    @Test
    void testEqualsAndHashCode() {
        AuthRequestDTO authRequestDTO1 = AuthRequestDTO.builder().username("testUser").password("testPassword").build();
        AuthRequestDTO authRequestDTO2 = AuthRequestDTO.builder().username("testUser").password("testPassword").build();

        assertThat(authRequestDTO1).isEqualTo(authRequestDTO2);
        assertThat(authRequestDTO1.hashCode()).isEqualTo(authRequestDTO2.hashCode());
    }

    @Test
    void testBuilder() {
        AuthRequestDTO authRequestDTO = AuthRequestDTO.builder().username("testUser").password("testPassword").build();

        assertThat(authRequestDTO.getUsername()).isEqualTo("testUser");
        assertThat(authRequestDTO.getPassword()).isEqualTo("testPassword");
    }

    @Test
    void testToString() {
        AuthRequestDTO authRequestDTO = AuthRequestDTO.builder()
                .username("testUser")
                .password("testPassword")
                .build();
        String toString = authRequestDTO.toString();
        assertThat(toString).contains("username=testUser");
        assertThat(toString).contains("password=testPassword");
    }

    @Test
    void testEqualsWithSameObject() {
        AuthRequestDTO authRequestDTO = AuthRequestDTO.builder().username("testUser").password("testPassword").build();
        assertThat(authRequestDTO).isEqualTo(authRequestDTO);
    }

    @Test
    void testEqualsWithDifferentType() {
        AuthRequestDTO authRequestDTO = AuthRequestDTO.builder().username("testUser").password("testPassword").build();
        assertThat(authRequestDTO).isNotEqualTo(new Object());
    }

    @Test
    void testEqualsWithNull() {
        AuthRequestDTO authRequestDTO = AuthRequestDTO.builder().username("testUser").password("testPassword").build();
        assertThat(authRequestDTO).isNotEqualTo(null);
    }

    @Test
    void testEqualsWithDifferentValues() {
        AuthRequestDTO authRequestDTO1 = AuthRequestDTO.builder().username("testUser1").password("testPassword1").build();
        AuthRequestDTO authRequestDTO2 = AuthRequestDTO.builder().username("testUser2").password("testPassword2").build();
        assertThat(authRequestDTO1).isNotEqualTo(authRequestDTO2);
    }

    @Test
    void testHashCodeForEqualObjects() {
        AuthRequestDTO authRequestDTO1 = AuthRequestDTO.builder().username("testUser").password("testPassword").build();
        AuthRequestDTO authRequestDTO2 = AuthRequestDTO.builder().username("testUser").password("testPassword").build();
        assertThat(authRequestDTO1.hashCode()).isEqualTo(authRequestDTO2.hashCode());
    }

    @Test
    void testHashCodeForDifferentObjects() {
        AuthRequestDTO authRequestDTO1 = AuthRequestDTO.builder().username("testUser1").password("testPassword1").build();
        AuthRequestDTO authRequestDTO2 = AuthRequestDTO.builder().username("testUser2").password("testPassword2").build();
        assertThat(authRequestDTO1.hashCode()).isNotEqualTo(authRequestDTO2.hashCode());
    }

    @Test
    void testHashCodeForNull() {
        AuthRequestDTO authRequestDTO = AuthRequestDTO.builder().username("testUser").password("testPassword").build();
        assertThat(authRequestDTO.hashCode()).isNotEqualTo(null);
    }
}
