package com.movie.community.controller.dto.response;

import com.movie.community.domain.Posting;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class PostingResponseListDTO {
	private Long id;
	private String title;
	private String writer;
	private LocalDateTime createdDate;

	public PostingResponseListDTO(Posting posting) {
		this.id = posting.getId();
		this.title = posting.getTitle();
		this.writer = posting.getMember().getName();
		this.createdDate = getCreatedDate(posting);
	}

	private LocalDateTime getCreatedDate(Posting posting) {
		return posting.getUpdatedDate() == null ?
				posting.getCreatedDate() : posting.getUpdatedDate();
	}
}
