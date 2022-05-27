package com.movie.community.domain;

import com.movie.community.controller.dto.request.MovieRequestDTO;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.time.LocalDate;
import java.util.List;

/**
 * Create, update, delete 는 모두 관리자만이 가능하다.
 * 일반 사용자들은 Read 만 가능.
 */
@Getter
@NoArgsConstructor
@Entity
public class Movie {
	@Id
	@Column(name = "movie_id")
	private Long id;

	@Column
	private String title;

	@Column(columnDefinition = "TEXT")
	private String description;

	@Column
	private double score;

	@Column
	private int voteCount;

	@Column
	private String imageUrl;

	@Column
	private LocalDate releasedDate;

	@Column
	private double popularity;

	@Column
	private String genres;

	@ManyToOne(targetEntity = Holiday.class, fetch = FetchType.LAZY)
	@JoinColumn(name = "holiday_id")
	private Holiday holiday;

	@OneToMany(mappedBy = "movie")
	private List<Review> reviews;

	public Movie(MovieRequestDTO requestDTO) {
		this.id = requestDTO.getId();
		this.title = requestDTO.getTitle();
		this.description = requestDTO.getDescription();
		this.score = requestDTO.getScore();
		this.voteCount = requestDTO.getVoteCount();
		this.imageUrl = requestDTO.getImageUrl();
		this.releasedDate = requestDTO.getReleasedDate();
		this.popularity = requestDTO.getPopularity();
		this.genres = requestDTO.getGenres();
	}

	public void update(MovieRequestDTO requestDTO) {
		this.id = requestDTO.getId();
		this.title = requestDTO.getTitle();
		this.description = requestDTO.getDescription();
		this.score = requestDTO.getScore();
		this.voteCount = requestDTO.getVoteCount();
		this.imageUrl = requestDTO.getImageUrl();
		this.releasedDate = requestDTO.getReleasedDate();
		this.popularity = requestDTO.getPopularity();
	}

	public void setHoliday(Holiday holiday) {
		this.holiday = holiday;
	}
}
