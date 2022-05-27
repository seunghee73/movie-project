package com.movie.community.repository;

import java.util.List;

public interface RecommendationRepository {
	public List<Long> getRecommendMoviesByMovieId(Long movieId);
}
