package com.movie.community.controller.dto.response;

import com.movie.community.domain.Review;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ReviewResponseDTO {
	private Long id;
	private String content;
	private float score;
	private String reviewer;
	private LocalDateTime date;

	public ReviewResponseDTO(Review review) {
		this.id = review.getId();
		this.content = review.getContent();
		this.score = review.getScore();
		this.reviewer = review.getMember().getName();
		this.date = getCreatedDate(review);
	}

	private LocalDateTime getCreatedDate(Review review) {
		if (review.getUpdatedDate() == null) {
			return review.getCreatedDate();
		}

		return review.getUpdatedDate();
	}
}
