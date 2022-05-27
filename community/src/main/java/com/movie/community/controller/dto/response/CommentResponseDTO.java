package com.movie.community.controller.dto.response;

import com.movie.community.domain.Comment;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class CommentResponseDTO {
	private Long id;
	private String comment;
	private LocalDateTime createdDate;
	private String commenter;

	public CommentResponseDTO(Comment comment) {
		this.id = comment.getId();
		this.comment = comment.getComment();
		this.createdDate = getCreatedDate(comment);
		this.commenter = comment.getMember().getName();
	}

	private LocalDateTime getCreatedDate(Comment comment) {
		return comment.getUpdatedDate() == null ? comment.getCreatedDate() : comment.getUpdatedDate();
	}
}
