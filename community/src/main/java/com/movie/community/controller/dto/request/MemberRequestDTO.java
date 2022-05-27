package com.movie.community.controller.dto.request;

import com.movie.community.domain.Member;
import lombok.Data;

@Data
public class MemberRequestDTO {
	private String name;
	private String account;
	private String password;
	private int age;
	private String roles;

	public Member toEntity() {
		return new Member(this);
	}
}
