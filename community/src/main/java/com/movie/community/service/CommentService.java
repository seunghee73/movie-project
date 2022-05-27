package com.movie.community.service;

import com.movie.community.controller.dto.request.CommentRequestDTO;
import com.movie.community.controller.dto.response.CommentResponseDTO;
import com.movie.community.controller.dto.response.ResponseDTO;
import org.springframework.security.core.Authentication;

import java.util.List;

public interface CommentService {
	public List<CommentResponseDTO> getCommentsByPosting(Long postingId);

	public ResponseDTO addComment(CommentRequestDTO requestDTO, Long postingId, Authentication authentication);

	public ResponseDTO updateComment(CommentRequestDTO requestDTO, Long id, Authentication authentication);

	public ResponseDTO removeComment(Long id, Authentication authentication);
}
