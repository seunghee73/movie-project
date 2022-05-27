package com.movie.community.controller.dto.response;

import com.movie.community.domain.Movie;
import lombok.Data;

import java.time.LocalDate;

/**
 * 영화 1개의 상세 정보에 대한 정보를 담아서 client 에게 전달하는 DTO
 */
@Data
public class MovieResponseDetailDTO {
	private Long id;
	private String title;
	private String description;
	private String imageUrl;
	private double score;
	private LocalDate releasedDate;

	public MovieResponseDetailDTO(Movie movie) {
		this.id = movie.getId();
		this.title = movie.getTitle();
		this.description = movie.getDescription();
		this.imageUrl = movie.getImageUrl();
		this.score = movie.getScore();
		this.releasedDate = movie.getReleasedDate();
	}
}
