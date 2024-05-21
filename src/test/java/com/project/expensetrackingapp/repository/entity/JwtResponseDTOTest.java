package com.project.expensetrackingapp.repository.entity;

import com.project.expensetrackingapp.repository.entity.auth.JwtResponseDTO;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;

public class JwtResponseDTOTest {

    @Test
    void testToString() {
        Date expiredDate = new Date();
        JwtResponseDTO jwtResponseDTO = JwtResponseDTO.builder()
                .accessToken("testAccessToken")
                .expiredDate(expiredDate)
                .build();

        String expectedToString = "JwtResponseDTO(accessToken=testAccessToken, expiredDate=" + expiredDate + ")";
        assertThat(jwtResponseDTO.toString()).isEqualTo(expectedToString);
    }

    @Test
    void testEqualsWithSameObject() {
        Date expiredDate = new Date();
        JwtResponseDTO jwtResponseDTO = JwtResponseDTO.builder()
                .accessToken("testAccessToken")
                .expiredDate(expiredDate)
                .build();

        assertThat(jwtResponseDTO).isEqualTo(jwtResponseDTO);
    }

    @Test
    void testEqualsWithDifferentType() {
        Date expiredDate = new Date();
        JwtResponseDTO jwtResponseDTO = JwtResponseDTO.builder()
                .accessToken("testAccessToken")
                .expiredDate(expiredDate)
                .build();

        assertThat(jwtResponseDTO).isNotEqualTo(new Object());
    }

    @Test
    void testEqualsWithNull() {
        Date expiredDate = new Date();
        JwtResponseDTO jwtResponseDTO = JwtResponseDTO.builder()
                .accessToken("testAccessToken")
                .expiredDate(expiredDate)
                .build();

        assertThat(jwtResponseDTO).isNotEqualTo(null);
    }

    @Test
    void testEqualsWithDifferentValues() {
        Date expiredDate1 = new Date();
        Date expiredDate2 = new Date(System.currentTimeMillis() + 1000);

        JwtResponseDTO jwtResponseDTO1 = JwtResponseDTO.builder()
                .accessToken("testAccessToken1")
                .expiredDate(expiredDate1)
                .build();

        JwtResponseDTO jwtResponseDTO2 = JwtResponseDTO.builder()
                .accessToken("testAccessToken2")
                .expiredDate(expiredDate2)
                .build();

        assertThat(jwtResponseDTO1).isNotEqualTo(jwtResponseDTO2);
    }

    @Test
    void testHashCodeForEqualObjects() {
        Date expiredDate = new Date();
        JwtResponseDTO jwtResponseDTO1 = JwtResponseDTO.builder()
                .accessToken("testAccessToken")
                .expiredDate(expiredDate)
                .build();

        JwtResponseDTO jwtResponseDTO2 = JwtResponseDTO.builder()
                .accessToken("testAccessToken")
                .expiredDate(expiredDate)
                .build();

        assertThat(jwtResponseDTO1.hashCode()).isEqualTo(jwtResponseDTO2.hashCode());
    }

    @Test
    void testHashCodeForDifferentObjects() {
        Date expiredDate1 = new Date();
        Date expiredDate2 = new Date(System.currentTimeMillis() + 1000);

        JwtResponseDTO jwtResponseDTO1 = JwtResponseDTO.builder()
                .accessToken("testAccessToken1")
                .expiredDate(expiredDate1)
                .build();

        JwtResponseDTO jwtResponseDTO2 = JwtResponseDTO.builder()
                .accessToken("testAccessToken2")
                .expiredDate(expiredDate2)
                .build();

        assertThat(jwtResponseDTO1.hashCode()).isNotEqualTo(jwtResponseDTO2.hashCode());
    }

    @Test
    void testHashCodeForNull() {
        Date expiredDate = new Date();
        JwtResponseDTO jwtResponseDTO = JwtResponseDTO.builder()
                .accessToken("testAccessToken")
                .expiredDate(expiredDate)
                .build();

        assertThat(jwtResponseDTO.hashCode()).isNotEqualTo(null);
    }
}
