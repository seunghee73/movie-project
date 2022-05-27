package com.movie.community.controller;

import com.movie.community.controller.dto.response.MovieResponseListDTO;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

public interface RecommendationController {
	public List<MovieResponseListDTO> getRecommendMovies(@PathVariable String movieId);
}
