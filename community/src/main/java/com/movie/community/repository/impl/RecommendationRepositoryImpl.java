package com.movie.community.repository.impl;

import com.movie.community.repository.RecommendationRepository;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class RecommendationRepositoryImpl implements RecommendationRepository {
	private final String RECOMMEND_MOVIE_VECTOR_FILE_PATH = "src/main/resources/static/recommendation_list.csv";
	private final Map<Long, List<Long>> recommendInfo = new HashMap<>();

	public RecommendationRepositoryImpl() throws IOException {
		readFile();
	}

	private void readFile() throws IOException {
		BufferedReader reader = new BufferedReader(new FileReader(RECOMMEND_MOVIE_VECTOR_FILE_PATH));
		String line = reader.readLine();

		while ((line = reader.readLine()) != null) {
			String[] identifications = line.split(",");
			Long targetId = Long.parseLong(identifications[0].trim());

			List<Long> recommendedMoviesPk = new ArrayList<>();
			for (int i = 1; i < identifications.length; i++) {
				Long otherId = Long.parseLong(identifications[i].trim());
				recommendedMoviesPk.add(otherId);
			}

			recommendInfo.put(targetId, recommendedMoviesPk);
		}
	}

	public List<Long> getRecommendMoviesByMovieId(Long id) {
		return recommendInfo.get(id);
	}
}
