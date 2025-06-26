package com.example.demo.User;

import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserCreateForm {
	@Size(min = 3, max = 25)
	@NotEmpty(message = "사용자 ID는 필수 항목입니다.")
	private String username;

	@NotEmpty(message = "비밀번호는 필수 항목입니다.")
	@Size(min = 8, max = 20, message = "비밀번호는 8자 이상 20자 이하로 입력해야 합니다.")
	private String password1;

	@NotEmpty(message = "비밀번호 확인은 필수 항목입니다.")
	private String password2;

	@NotEmpty(message = "이메일은 필수 항목입니다.")
	@Email
	private String email;
}
