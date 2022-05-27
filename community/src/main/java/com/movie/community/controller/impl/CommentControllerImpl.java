package com.movie.community.controller.impl;

import com.movie.community.controller.CommentController;
import com.movie.community.controller.dto.request.CommentRequestDTO;
import com.movie.community.controller.dto.response.CommentResponseDTO;
import com.movie.community.controller.dto.response.ResponseDTO;
import com.movie.community.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/comments")
@RestController
public class CommentControllerImpl implements CommentController {
	private final CommentService commentService;

	/**
	 * 어떤 Posting 1개에 대한 Comment list 조회 : GET - /comments/postings/{postingId}
	 * 어떤 Posting 1개에 대한 Comment 생성 : POST - /comments/postings/{postingId}
	 * 어떤 Posting 1개에 대한 Comment 수정 : PATCH - /comments/{commentId}
	 * 어떤 Posting 1개에 대한 Comment 삭제 : DELETE - /comments/{commentId}
	 */

	@Override
	@GetMapping("/postings/{postingId}")
	public List<CommentResponseDTO> comments(@PathVariable("postingId") String postingId) {
		Long id = Long.parseLong(postingId);
		List<CommentResponseDTO> comments = commentService.getCommentsByPosting(id);
		return comments;
	}

	@Override
	@PostMapping("/postings/{postingId}")
	public ResponseDTO create(@PathVariable("postingId") String postingId,
							  CommentRequestDTO requestDTO, Authentication authentication) {
		Long id = Long.parseLong(postingId);
		ResponseDTO response = commentService.addComment(requestDTO, id, authentication);
		return response;
	}

	@Override
	@PatchMapping("/{commentId}")
	public ResponseDTO update(@PathVariable("commentId") String commentId,
							  CommentRequestDTO requestDTO, Authentication authentication) {
		Long id = Long.parseLong(commentId);
		ResponseDTO response = commentService.updateComment(requestDTO, id, authentication);
		return response;
	}

	@Override
	@DeleteMapping("/{commentId}")
	public ResponseDTO delete(@PathVariable("commentId") String commentId, Authentication authentication) {
		Long id = Long.parseLong(commentId);
		ResponseDTO response = commentService.removeComment(id, authentication);
		return response;
	}
}
