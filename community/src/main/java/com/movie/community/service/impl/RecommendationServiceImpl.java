package com.movie.community.service.impl;

import com.movie.community.controller.dto.response.MovieResponseListDTO;
import com.movie.community.domain.Movie;
import com.movie.community.repository.MovieRepository;
import com.movie.community.repository.RecommendationRepository;
import com.movie.community.service.RecommendationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class RecommendationServiceImpl implements RecommendationService {
	private final RecommendationRepository recommendationRepository;

	private final MovieRepository movieRepository;

	@Override
	public List<MovieResponseListDTO> getRecommendMovies(Long movieId) {
		List<Long> recommendedMoviesId = recommendationRepository.getRecommendMoviesByMovieId(movieId);
		List<MovieResponseListDTO> movieResponseListDTOList = new ArrayList<>();
		for (Long id : recommendedMoviesId) {
			Movie movie = movieRepository.findById(id);
			movieResponseListDTOList.add(new MovieResponseListDTO(movie));
		}
		return movieResponseListDTOList;
	}
}
