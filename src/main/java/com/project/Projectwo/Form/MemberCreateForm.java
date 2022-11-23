package com.project.Projectwo.Form;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MemberCreateForm {
	
	@Size(min = 3, max = 20)
	@NotEmpty(message = "ID는 필수 입력 항목입니다.")
	private String member_id;
	
	@NotEmpty(message = "비밀번호는 필수 입력 항목입니다.")
	private String member_pw1;
	
	@NotEmpty(message = "비밀번호 확인은 필수 입력 항목입니다.")
	private String member_pw2;
	
	@Email
	@NotEmpty(message = "이메일은 필수 입력 항목입니다.")
	private String email;
	
	private String name;
	
	private int age;
	
	private String address;
	
	private String tel;
}
