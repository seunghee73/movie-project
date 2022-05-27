package com.movie.community.controller.impl;

import com.movie.community.controller.RecommendationController;
import com.movie.community.controller.dto.response.MovieResponseListDTO;
import com.movie.community.service.MovieService;
import com.movie.community.service.RecommendationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/recommendations")
@RestController
public class RecommendationControllerImpl implements RecommendationController {
	private final RecommendationService recommendationService;

	@Override
	@GetMapping("/{movieId}")
	public List<MovieResponseListDTO> getRecommendMovies(@PathVariable("movieId") String movieId) {
		Long id = Long.parseLong(movieId);
		List<MovieResponseListDTO> recommendMovies = recommendationService.getRecommendMovies(id);
		return recommendMovies;
	}
}
