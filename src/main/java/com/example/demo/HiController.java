package com.example.demo;
import org.springframework.stereotype.*;
import org.springframework.web.*;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HiController {
	@GetMapping("/hello")
	@ResponseBody
	public String hello() {
		return "Hello hello";
	}
}
