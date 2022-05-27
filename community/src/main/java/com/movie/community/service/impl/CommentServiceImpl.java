package com.movie.community.service.impl;

import com.movie.community.controller.dto.request.CommentRequestDTO;
import com.movie.community.controller.dto.response.CommentResponseDTO;
import com.movie.community.controller.dto.response.ResponseDTO;
import com.movie.community.domain.Comment;
import com.movie.community.domain.Member;
import com.movie.community.domain.Posting;
import com.movie.community.repository.CommentRepository;
import com.movie.community.repository.MemberRepository;
import com.movie.community.repository.PostingRepository;
import com.movie.community.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class CommentServiceImpl implements CommentService {
	private final CommentRepository commentRepository;

	private final MemberRepository memberRepository;

	private final PostingRepository postingRepository;

	@Override
	public List<CommentResponseDTO> getCommentsByPosting(Long postingId) {
		Posting posting = postingRepository.findById(postingId);
		List<Comment> comments = posting.getComments();
		return comments.stream()
				.map(CommentResponseDTO::new)
				.collect(Collectors.toList());
	}

	@Override
	public ResponseDTO addComment(CommentRequestDTO requestDTO, Long postingId,
								  Authentication authentication) {
		if (requestDTO == null) {
			return new ResponseDTO("Invalid client request", 400);
		}

		Posting commentedPosting = postingRepository.findById(postingId);
		if (commentedPosting == null) {
			return new ResponseDTO("No such posting id=" + postingId, 400);
		}

		String loginMemberAccount = authentication.getName();
		Member loginMemberEntity = memberRepository.findByAccount(loginMemberAccount);

		Comment comment = requestDTO.toEntity();
		comment.setWriter(loginMemberEntity);
		comment.mapPosting(commentedPosting);

		commentRepository.save(comment);

		return new ResponseDTO("success to comment", 200);
	}

	@Transactional
	@Override
	public ResponseDTO updateComment(CommentRequestDTO requestDTO, Long id, Authentication authentication) {
		if (requestDTO == null) {
			return new ResponseDTO("Invalid client request", 400);
		}

		if (id == null) {
			return new ResponseDTO("No such comment id=" + id, 400);
		}

		Comment comment = commentRepository.findById(id);
		Member commenter = comment.getMember();
		String commenterAccount = commenter.getAccount();

		String loginMemberAccount = authentication.getName();
		if (!loginMemberAccount.equals(commenterAccount)) {
			return new ResponseDTO("No authentication", 400);
		}

		commentRepository.update(requestDTO, id);

		return new ResponseDTO("success to update comment", 200);
	}

	@Transactional
	@Override
	public ResponseDTO removeComment(Long id, Authentication authentication) {
		if (id == null) {
			return new ResponseDTO("Invalid client request", 400);
		}

		String loginMemberAccount = authentication.getName();

		Comment comment = commentRepository.findById(id);
		Member commenter = comment.getMember();
		String commenterAccount = commenter.getAccount();

		if (!loginMemberAccount.equals(commenterAccount)) {
			return new ResponseDTO("No authentication", 400);
		}

		commentRepository.remove(comment);
		return new ResponseDTO("success to remove comment", 200);
	}

}
