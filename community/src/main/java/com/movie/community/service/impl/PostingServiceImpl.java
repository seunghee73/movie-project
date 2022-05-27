package com.movie.community.service.impl;

import com.movie.community.controller.dto.request.PostingRequestDTO;
import com.movie.community.controller.dto.response.PostingResponseDetailDTO;
import com.movie.community.controller.dto.response.PostingResponseListDTO;
import com.movie.community.controller.dto.response.ResponseDTO;
import com.movie.community.domain.Member;
import com.movie.community.domain.Posting;
import com.movie.community.repository.MemberRepository;
import com.movie.community.repository.PostingRepository;
import com.movie.community.service.PostingService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class PostingServiceImpl implements PostingService {
	private final PostingRepository postingRepository;

	private final MemberRepository memberRepository;

	@Override
	public List<PostingResponseListDTO> getAllPostings() {
		List<Posting> postings = postingRepository.findAll();
		return postings.stream()
				.map(PostingResponseListDTO::new)
				.collect(Collectors.toList());
	}

	@Override
	public PostingResponseDetailDTO getPosting(Long id) {
		if (id == null) {
			throw new IllegalArgumentException("Invalid client request");
		}

		Posting posting = postingRepository.findById(id);
		return new PostingResponseDetailDTO(posting);
	}

	@Override
	public ResponseDTO createPosting(PostingRequestDTO requestDTO, Authentication authentication) {
		if (requestDTO == null || authentication == null) {
			return new ResponseDTO("Invalid client request", 400);
		}

		String loginMemberAccount = authentication.getName();
		Member postingWriter = memberRepository.findByAccount(loginMemberAccount);

		Posting posting = requestDTO.toEntity();
		posting.setWriter(postingWriter);
		postingRepository.save(posting);

		return new ResponseDTO("success to posting", 200);
	}

	@Transactional
	@Override
	public ResponseDTO updatePosting(PostingRequestDTO requestDTO, Long id, Authentication authentication) {
		if (id == null || requestDTO == null) {
			return new ResponseDTO("Invalid client request", 400);
		}

		String loginMemberAccount = authentication.getName();

		Posting posting = postingRepository.findById(id);
		Member writerEntity = posting.getMember();
		String postingWriterAccount = writerEntity.getAccount();

		if (!postingWriterAccount.equals(loginMemberAccount)) {
			return new ResponseDTO("No authentication", 400);
		}

		postingRepository.update(requestDTO, id);

		return new ResponseDTO("success to update posting", 200);
	}

	@Transactional
	@Override
	public ResponseDTO removePosting(Long id, Authentication authentication) {
		if (id == null) {
			return new ResponseDTO("Invalid client request", 400);
		}

		String loginMemberAccount = authentication.getName();

		Posting posting = postingRepository.findById(id);
		Member writerEntity = posting.getMember();
		String postingWriterAccount = writerEntity.getAccount();

		if (!postingWriterAccount.equals(loginMemberAccount)) {
			return new ResponseDTO("No authentication", 400);
		}

		postingRepository.remove(posting);

		return new ResponseDTO("success to remove posting", 200);
	}
}
