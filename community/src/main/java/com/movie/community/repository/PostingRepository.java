package com.movie.community.repository;

import com.movie.community.controller.dto.request.PostingRequestDTO;
import com.movie.community.domain.Posting;

import java.util.List;

public interface PostingRepository {
	public void save(Posting posting);

	public void update(PostingRequestDTO requestDTO, Long id);

	public void remove(Posting posting);

	public Posting findById(Long id);

	public List<Posting> findAll();
}
