package com.movie.community.controller;

import com.movie.community.controller.dto.request.PostingRequestDTO;
import com.movie.community.controller.dto.response.PostingResponseDetailDTO;
import com.movie.community.controller.dto.response.PostingResponseListDTO;
import com.movie.community.controller.dto.response.ResponseDTO;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface PostingController {
	public List<PostingResponseListDTO> postings();

	public PostingResponseDetailDTO posting(@PathVariable("postingId") String postingId);

	public ResponseDTO create(@RequestBody PostingRequestDTO requestDTO,
							  Authentication authentication);

	public ResponseDTO update(@RequestBody PostingRequestDTO requestDTO,
							  @PathVariable("postingId") String postingId,
							  Authentication authentication);

	public ResponseDTO remove(@PathVariable("postingId") String postingId,
							  Authentication authentication);
}
