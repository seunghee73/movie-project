package com.movie.community.controller.dto.request;

import com.movie.community.domain.Posting;
import lombok.Data;

@Data
public class PostingRequestDTO {
	private String title;
	private String content;

	public Posting toEntity() {
		return new Posting(this);
	}
}
