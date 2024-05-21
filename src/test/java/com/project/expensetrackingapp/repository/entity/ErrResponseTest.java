package com.project.expensetrackingapp.repository.entity;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class ErrResponseTest {

    @Test
    void testToString() {
        ErrResponse errResponse = ErrResponse.builder()
                .errorCode("404")
                .errorMessage("Not Found")
                .build();

        String expectedToString = "ErrResponse(errorCode=404, errorMessage=Not Found)";
        assertThat(errResponse.toString()).isEqualTo(expectedToString);
    }

    @Test
    void testEqualsWithSameObject() {
        ErrResponse errResponse = ErrResponse.builder()
                .errorCode("404")
                .errorMessage("Not Found")
                .build();

        assertThat(errResponse).isEqualTo(errResponse);
    }

    @Test
    void testEqualsWithDifferentType() {
        ErrResponse errResponse = ErrResponse.builder()
                .errorCode("404")
                .errorMessage("Not Found")
                .build();

        assertThat(errResponse).isNotEqualTo(new Object());
    }

    @Test
    void testEqualsWithNull() {
        ErrResponse errResponse = ErrResponse.builder()
                .errorCode("404")
                .errorMessage("Not Found")
                .build();

        assertThat(errResponse).isNotEqualTo(null);
    }

    @Test
    void testEqualsWithDifferentValues() {
        ErrResponse errResponse1 = ErrResponse.builder()
                .errorCode("404")
                .errorMessage("Not Found")
                .build();

        ErrResponse errResponse2 = ErrResponse.builder()
                .errorCode("500")
                .errorMessage("Internal Server Error")
                .build();

        assertThat(errResponse1).isNotEqualTo(errResponse2);
    }

    @Test
    void testHashCodeForEqualObjects() {
        ErrResponse errResponse1 = ErrResponse.builder()
                .errorCode("404")
                .errorMessage("Not Found")
                .build();

        ErrResponse errResponse2 = ErrResponse.builder()
                .errorCode("404")
                .errorMessage("Not Found")
                .build();

        assertThat(errResponse1.hashCode()).isEqualTo(errResponse2.hashCode());
    }

    @Test
    void testHashCodeForDifferentObjects() {
        ErrResponse errResponse1 = ErrResponse.builder()
                .errorCode("404")
                .errorMessage("Not Found")
                .build();

        ErrResponse errResponse2 = ErrResponse.builder()
                .errorCode("500")
                .errorMessage("Internal Server Error")
                .build();

        assertThat(errResponse1.hashCode()).isNotEqualTo(errResponse2.hashCode());
    }

    @Test
    void testHashCodeForNull() {
        ErrResponse errResponse = ErrResponse.builder()
                .errorCode("404")
                .errorMessage("Not Found")
                .build();

        assertThat(errResponse.hashCode()).isNotEqualTo(null);
    }
}