package com.movie.community.repository.impl;

import com.movie.community.controller.dto.request.PostingRequestDTO;
import com.movie.community.domain.Posting;
import com.movie.community.repository.PostingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;

@Transactional(readOnly = true)
@RequiredArgsConstructor
@Repository
public class PostingRepositoryImpl implements PostingRepository {
	private final EntityManager em;

	@Transactional
	@Override
	public void save(Posting posting) {
		em.persist(posting);
	}

	@Transactional
	@Override
	public void update(PostingRequestDTO requestDTO, Long id) {
		Posting posting = em.find(Posting.class, id);
		posting.update(requestDTO);
	}

	@Transactional
	@Override
	public void remove(Posting posting) {
		em.remove(posting);
	}

	@Override
	public Posting findById(Long id) {
		return em.find(Posting.class, id);
	}

	@Override
	public List<Posting> findAll() {
		return em.createQuery("select p from Posting p", Posting.class)
				.getResultList();
	}
}
