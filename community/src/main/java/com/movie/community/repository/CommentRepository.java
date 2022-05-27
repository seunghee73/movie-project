package com.movie.community.repository;

import com.movie.community.controller.dto.request.CommentRequestDTO;
import com.movie.community.domain.Comment;
import com.movie.community.domain.Posting;

import java.util.List;

public interface CommentRepository {
	public void save(Comment comment);

	public void update(CommentRequestDTO requestDTO, Long id);

	public void remove(Comment comment);

	public Comment findById(Long id);
}
