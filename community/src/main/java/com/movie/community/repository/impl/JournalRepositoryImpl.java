package com.movie.community.repository.impl;

import com.movie.community.controller.dto.request.JournalRequestDTO;
import com.movie.community.domain.Journal;
import com.movie.community.repository.JournalRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

@Transactional
@RequiredArgsConstructor
@Repository
public class JournalRepositoryImpl implements JournalRepository {
	private final EntityManager em;

	@Override
	public void save(Journal journal) {
		em.persist(journal);
	}

	@Override
	public void update(JournalRequestDTO requestDTO, Long id) {
		Journal journal = em.find(Journal.class, id);
		journal.update(requestDTO);
	}

	@Override
	public void remove(Journal journal) {
		em.remove(journal);
	}

	@Override
	public Journal findById(Long id) {
		return em.find(Journal.class, id);
	}
}
