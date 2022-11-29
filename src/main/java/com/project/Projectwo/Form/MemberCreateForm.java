package com.project.Projectwo.Form;

import java.time.LocalDate;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MemberCreateForm {
	
	@Size(min = 3, max = 50)
	@NotEmpty(message = "사용자 ID는 필수 항목입니다.")
	private String identity;
	
	@NotEmpty(message = "비밀번호는 필수 항목입니다.")
	private String password1;
	
	@NotEmpty(message = "비밀번호 확인은 필수 항목입니다.")
	private String password2;
	
	@Size(min = 1, max = 30)
	@NotEmpty(message = "이름은 필수 항목입니다.")
	private String name;
	
	@Size(min = 5, max = 150)
	@NotEmpty(message = "이메일은 필수 항목입니다.")
	@Email
	private String email;
	
	@NotEmpty(message = "생년월일은 필수 항목입니다.")
	private LocalDate birth_date;
	
	@NotEmpty(message = "주소는 필수 항목입니다.")
	private String address;
	
	@NotEmpty(message = "전화번호는 필수 항목입니다.")
	private String tel;
}
