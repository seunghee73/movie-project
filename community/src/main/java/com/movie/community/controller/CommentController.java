package com.movie.community.controller;

import com.movie.community.controller.dto.request.CommentRequestDTO;
import com.movie.community.controller.dto.response.CommentResponseDTO;
import com.movie.community.controller.dto.response.ResponseDTO;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface CommentController {
	// GET-/comments/postings/{postingId}
	public List<CommentResponseDTO> comments(@PathVariable("postingId") String postingId);

	// POST-/comments/postings/{postingId}
	public ResponseDTO create(@PathVariable("postingId") String postingId,
							  @RequestBody CommentRequestDTO requestDTO,
							  Authentication authentication);

	// PATCH-/comments/{commentId}
	public ResponseDTO update(@PathVariable("commentId") String commentId,
							  @RequestBody CommentRequestDTO requestDTO,
							  Authentication authentication);

	// DELETE-/comments/{commentId}
	public ResponseDTO delete(@PathVariable("commentId") String commentId, Authentication authentication);
}
