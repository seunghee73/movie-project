package com.movie.community.service;

import com.movie.community.controller.dto.request.PostingRequestDTO;
import com.movie.community.controller.dto.response.PostingResponseDetailDTO;
import com.movie.community.controller.dto.response.PostingResponseListDTO;
import com.movie.community.controller.dto.response.ResponseDTO;
import org.springframework.security.core.Authentication;

import java.util.List;

public interface PostingService {
	public List<PostingResponseListDTO> getAllPostings();

	public PostingResponseDetailDTO getPosting(Long id);

	public ResponseDTO createPosting(PostingRequestDTO requestDTO, Authentication authentication);

	public ResponseDTO updatePosting(PostingRequestDTO requestDTO, Long id, Authentication authentication);

	public ResponseDTO removePosting(Long id, Authentication authentication);
}
