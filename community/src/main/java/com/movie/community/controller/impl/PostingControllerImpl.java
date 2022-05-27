package com.movie.community.controller.impl;

import com.movie.community.controller.PostingController;
import com.movie.community.controller.dto.request.PostingRequestDTO;
import com.movie.community.controller.dto.response.PostingResponseDetailDTO;
import com.movie.community.controller.dto.response.PostingResponseListDTO;
import com.movie.community.controller.dto.response.ResponseDTO;
import com.movie.community.service.PostingService;
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
@RequestMapping("/postings")
@RestController
public class PostingControllerImpl implements PostingController {
	private final PostingService postingService;

	@Override
	@GetMapping("")
	public List<PostingResponseListDTO> postings() {
		List<PostingResponseListDTO> postings = postingService.getAllPostings();
		return postings;
	}

	@Override
	@GetMapping("/{postingId}")
	public PostingResponseDetailDTO posting(@PathVariable("postingId") String postingId) {
		Long id = Long.parseLong(postingId);
		PostingResponseDetailDTO posting = postingService.getPosting(id);
		return posting;
	}

	@Override
	@PostMapping("")
	public ResponseDTO create(PostingRequestDTO requestDTO, Authentication authentication) {
		ResponseDTO response = postingService.createPosting(requestDTO, authentication);
		return response;
	}

	@Override
	@PatchMapping("/{postingId}")
	public ResponseDTO update(PostingRequestDTO requestDTO,
							  @PathVariable("postingId") String postingId, Authentication authentication) {
		Long id = Long.parseLong(postingId);
		ResponseDTO response = postingService.updatePosting(requestDTO, id, authentication);
		return response;
	}

	@Override
	@DeleteMapping("/{postingId}")
	public ResponseDTO remove(String postingId, Authentication authentication) {
		Long id = Long.parseLong(postingId);
		ResponseDTO response = postingService.removePosting(id, authentication);
		return response;
	}
}
