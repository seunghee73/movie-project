package com.movie.community.controller.dto.response;

import com.movie.community.domain.Member;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class MemberResponseDTO {
	private String name;
	private String account;
	private int age;
	private String roles;
	private LocalDateTime createdDate;

	public MemberResponseDTO(Member member) {
		this.name = member.getName();
		this.account = member.getAccount();
		this.age = member.getAge();
		this.roles = member.getRoles();
		this.createdDate = member.getCreatedDate();
	}
}
