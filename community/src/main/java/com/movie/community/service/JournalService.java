package com.movie.community.service;

import com.movie.community.controller.dto.request.JournalRequestDTO;
import com.movie.community.controller.dto.response.JournalResponseDTO;
import com.movie.community.controller.dto.response.ResponseDTO;
import org.springframework.security.core.Authentication;

import java.util.List;

public interface JournalService {
	public List<JournalResponseDTO> getMemberJournals(Authentication authentication);

	public ResponseDTO createJournal(JournalRequestDTO requestDTO, Authentication authentication);

	public ResponseDTO updateJournal(JournalRequestDTO requestDTO, Long id,
									 Authentication authentication);

	public ResponseDTO deleteJournal(Long id, Authentication authentication);
}
