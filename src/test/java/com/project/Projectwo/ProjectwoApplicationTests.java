package com.project.Projectwo;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.log;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest
class ProjectwoApplicationTests {

	@Test
	void contextLoads() {
		LocalDate localDate = LocalDate.now();
		String stringDate = localDate.format(DateTimeFormatter.ofPattern("yyyyMMdd"));
		
		log.info(stringDate);
		
	}

}
