package com.movie.community.repository;

import com.movie.community.controller.dto.request.JournalRequestDTO;
import com.movie.community.domain.Journal;

import java.util.List;

public interface JournalRepository {
	public void save(Journal journal);

	public void update(JournalRequestDTO requestDTO, Long id);

	public void remove(Journal journal);

	public Journal findById(Long id);
}
