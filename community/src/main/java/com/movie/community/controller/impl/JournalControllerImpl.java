package com.movie.community.controller.impl;

import com.movie.community.controller.JournalController;
import com.movie.community.controller.dto.request.JournalRequestDTO;
import com.movie.community.controller.dto.response.JournalResponseDTO;
import com.movie.community.controller.dto.response.ResponseDTO;
import com.movie.community.service.JournalService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/journals")
@RestController
public class JournalControllerImpl implements JournalController {
	private final JournalService journalService;

	@Override
	@GetMapping("")
	public List<JournalResponseDTO> journals(Authentication authentication) {
		List<JournalResponseDTO> journals = journalService.getMemberJournals(authentication);
		return journals;
	}

	@Override
	@PostMapping("")
	public ResponseDTO create(JournalRequestDTO requestDTO, Authentication authentication) {
		ResponseDTO response = journalService.createJournal(requestDTO, authentication);
		return response;
	}

	@Override
	@PatchMapping("/{journalId}")
	public ResponseDTO update(@RequestBody JournalRequestDTO requestDTO,
							  @PathVariable("journalId") String journalId,
							  Authentication authentication) {
		Long id = Long.parseLong(journalId);
		ResponseDTO response = journalService.updateJournal(requestDTO, id, authentication);
		return response;
	}

	@Override
	@DeleteMapping("/{journalId}")
	public ResponseDTO delete(@PathVariable("journalId") String journalId,
							  Authentication authentication) {
		Long id = Long.parseLong(journalId);
		ResponseDTO response = journalService.deleteJournal(id, authentication);
		return response;
	}
}
