package com.project.expensetrackingapp;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ExpensetrackingappApplication.class)
class ExpensetrackingappApplicationTests {

	@Test
	void contextLoads() {
		assertNotNull(ExpensetrackingappApplication.class);
	}
}
