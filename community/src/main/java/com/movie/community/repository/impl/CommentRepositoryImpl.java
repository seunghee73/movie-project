package com.movie.community.repository.impl;

import com.movie.community.controller.dto.request.CommentRequestDTO;
import com.movie.community.domain.Comment;
import com.movie.community.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

@Transactional(readOnly = true)
@RequiredArgsConstructor
@Repository
public class CommentRepositoryImpl implements CommentRepository {
	private final EntityManager em;

	@Transactional
	@Override
	public void save(Comment comment) {
		em.persist(comment);
	}

	@Transactional
	@Override
	public void update(CommentRequestDTO requestDTO, Long id) {
		Comment comment = em.find(Comment.class, id);
		comment.update(requestDTO);
	}

	@Transactional
	@Override
	public void remove(Comment comment) {
		em.remove(comment);
	}

	@Override
	public Comment findById(Long id) {
		return em.find(Comment.class, id);
	}
}
