package com.movie.community.controller.dto.request;

import com.movie.community.domain.Comment;
import lombok.Data;

@Data
public class CommentRequestDTO {
	private String comment;

	public Comment toEntity() {
		return new Comment(this);
	}
}
