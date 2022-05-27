package com.movie.community.service;

import com.movie.community.controller.dto.response.MovieResponseListDTO;

import java.util.List;

public interface RecommendationService {
	// 1. 어떤 영화 에 대한 추천 영화 6개 출력
	public List<MovieResponseListDTO> getRecommendMovies(Long movieId);
}
