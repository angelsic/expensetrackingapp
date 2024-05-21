package com.project.expensetrackingapp;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ServletInitializer.class)
class ServletInitializerTest {

    @Test
    void testConfigure() {
        ServletInitializer servletInitializer = new ServletInitializer();

        SpringApplicationBuilder mockBuilder = mock(SpringApplicationBuilder.class);
        when(mockBuilder.sources(ExpensetrackingappApplication.class)).thenReturn(mockBuilder);

        SpringApplicationBuilder result = servletInitializer.configure(mockBuilder);

        assertNotNull(result);
    }
}