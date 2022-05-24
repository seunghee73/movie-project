package com.movie.community.controller.dto.response;

import com.movie.community.domain.Movie;
import lombok.Data;

import java.time.LocalDate;

/**
 * 영화 목록 대한 데이터를 담아서 client 에게 제공하는 DTO
 */
@Data
public class MovieResponseListDTO {
	private Long id;
	private String title;
	private LocalDate releasedDate;
	private float score;

	public MovieResponseListDTO(Movie movie) {
		this.id = movie.getId();
		this.title = movie.getTitle();
		this.releasedDate = movie.getReleasedDate();
		this.score = movie.getScore();
	}
}
