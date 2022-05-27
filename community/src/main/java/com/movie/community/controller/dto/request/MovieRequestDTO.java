package com.movie.community.controller.dto.request;

import com.movie.community.domain.Movie;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

/**
 * 영화 데이터 생성을 위한 데이터를 해당 DTO 에 담아서 서버로 가져온다.
 */
@Data
public class MovieRequestDTO {
	private Long id;
	private String title;
	private String description;
	private double score;
	private int voteCount;
	private String imageUrl;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate releasedDate;
	private double popularity;
	private String genres;
	private String holiday;

	public Movie toEntity() {
		return new Movie(this);
	}
}
