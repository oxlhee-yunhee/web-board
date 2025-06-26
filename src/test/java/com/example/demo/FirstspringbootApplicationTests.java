package com.example.demo;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo.question.QuestionService;
@SpringBootTest
class FirstspringbootApplicationTests {
	
	@Autowired
	private QuestionService questionService;
	
	@Test
	void testJpa() {
		for(int i=1;i<=30;i++) {
			String subject=String.format("아아테스트데이터:[%03d]", i);
			String content="내용 없음";
			this.questionService.create(subject, content, null);
		}
	}
	
}
