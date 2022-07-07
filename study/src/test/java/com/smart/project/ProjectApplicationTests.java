package com.smart.project;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.UUID;

@SpringBootTest
class ProjectApplicationTests {

	@Test
	void contextLoads() {
		String uuid = UUID.randomUUID().toString();
		System.out.println(uuid);

		System.out.println("split = " + uuid.split("-")[0]);

	}

}
