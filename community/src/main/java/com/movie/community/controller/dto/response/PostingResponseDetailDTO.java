package com.movie.community.controller.dto.response;

import com.movie.community.domain.Comment;
import com.movie.community.domain.Posting;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class PostingResponseDetailDTO {
	private Long id;
	private String title;
	private String content;
	private String writer;
	private LocalDateTime createdDate;

//	private List<Comment> comments;

	public PostingResponseDetailDTO(Posting posting) {
		this.id = posting.getId();
		this.title = posting.getTitle();
		this.content = posting.getContent();
		this.writer = posting.getMember().getName();
		this.createdDate = getCreatedDate(posting);
	}

	private LocalDateTime getCreatedDate(Posting posting) {
		return posting.getUpdatedDate() == null ?
				posting.getCreatedDate() : posting.getUpdatedDate();
	}
}
